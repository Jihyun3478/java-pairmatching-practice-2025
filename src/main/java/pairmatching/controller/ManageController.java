package pairmatching.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import pairmatching.domain.model.Course;
import pairmatching.domain.model.Crew;
import pairmatching.domain.model.Crews;
import pairmatching.domain.model.MenuOption;
import pairmatching.domain.model.Pairs;
import pairmatching.dto.MatchingInfo;
import pairmatching.repository.MatchingRepository;
import pairmatching.service.MatchingService;
import pairmatching.util.FileParser;
import pairmatching.util.InputParser;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class ManageController {
    private final InputView inputView;
    private final OutputView outputView;
    private final MatchingService matchingService;

    public ManageController(InputView inputView, OutputView outputView, MatchingService matchingService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.matchingService = matchingService;
    }

    public void start() {
        List<String> backendNames = FileParser.parseBackendCrews();
        List<String> frontendNames = FileParser.parseFrontendCrews();

        MenuOption menuOption = null;
        do {
            menuOption = getMenu();

            if (menuOption == MenuOption.OPTION_1) {
                outputView.printCourseAndMission();
                MatchingInfo matchingInfo = getMatchingInfo();
                matchingPair(backendNames, frontendNames, matchingInfo);
            }
            if (menuOption == MenuOption.OPTION_2) {
                outputView.printCourseAndMission();
                MatchingInfo matchingInfo = getMatchingInfo();
                Pairs pairs = MatchingRepository.findPairs(matchingInfo);
                outputView.printMatchingResult(pairs);
            }
            if (menuOption == MenuOption.OPTION_3) {
                MatchingRepository.deleteAll();
                outputView.printResetMessage();
            }

        } while (menuOption != MenuOption.OPTION_Q);
    }

    private MenuOption getMenu() {
        return retryUntilSuccess(() -> {
            String menu = inputView.readMenu();
            return MenuOption.fromMenu(menu);
        });
    }

    private MatchingInfo getMatchingInfo() {
        return retryUntilSuccess(() -> {
            String input = inputView.readMatchingInfo();
            return InputParser.parseMatchingInfo(input);
        });
    }

    private void matchingPair(List<String> backendNames, List<String> frontendNames, MatchingInfo matchingInfo) {
        if (MatchingRepository.findMatchingInfo(matchingInfo)) {
            String rematching = getRematching();
            if (rematching.equals("아니오")) {
                outputView.printCourseAndMission();
                MatchingInfo newMatchingInfo = getMatchingInfo();
                matchingPair(backendNames, frontendNames, newMatchingInfo);
                return;
            }
        }

        Crews crews = getCrewsByCourse(backendNames, frontendNames, matchingInfo);
        Pairs pairs = matchingService.validateMatching(crews, matchingInfo);
        MatchingRepository.addMatchings(matchingInfo, pairs);
        outputView.printMatchingResult(pairs);
    }

    private Crews getCrewsByCourse(List<String> backendNames, List<String> frontendNames, MatchingInfo matchingInfo) {
        if (matchingInfo.getCourse() == Course.BACKEND) {
            return matchingService.shuffle(getCrews(backendNames, Course.BACKEND));
        }
        return matchingService.shuffle(getCrews(frontendNames, Course.FRONTEND));
    }

    private List<Crew> getCrews(List<String> names, Course course) {
        List<Crew> crews = new ArrayList<>();
        for (String name : names) {
            crews.add(new Crew(course, name));
        }
        return crews;
    }

    private String getRematching() {
        return retryUntilSuccess(inputView::readRematching);
    }

    private <T> T retryUntilSuccess(Supplier<T> action) {
        while (true) {
            try {
                return action.get();
            } catch (IllegalArgumentException exception) {
                outputView.printErrorMessage(exception);
            }
        }
    }
}
