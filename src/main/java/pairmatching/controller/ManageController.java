package pairmatching.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import pairmatching.domain.model.Course;
import pairmatching.domain.model.Crew;
import pairmatching.domain.model.Crews;
import pairmatching.domain.model.MenuOption;
import pairmatching.domain.model.Pair;
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
                getOption1(backendNames, frontendNames);
            }
            if (menuOption == MenuOption.OPTION_2) {
                getOption2();
            }
            if (menuOption == MenuOption.OPTION_3) {
                getOption3();
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
        System.out.println("[DEBUG] getMatchingInfo 시작");
        return retryUntilSuccess(() -> {
            System.out.println("[DEBUG] action 실행");
            String input = inputView.readMatchingInfo();
            System.out.println("[DEBUG] parseMatchingInfo 호출");
            return InputParser.parseMatchingInfo(input);
        });
    }

    private void getOption1(List<String> backendNames, List<String> frontendNames) {
        outputView.printCourseAndMission();
        MatchingInfo matchingInfo = getMatchingInfo();
        matchingPair(backendNames, frontendNames, matchingInfo);
    }

    private void getOption2() {
        outputView.printCourseAndMission();
        MatchingInfo matchingInfo = getMatchingInfo();
        List<Pair> pairs = MatchingRepository.findPairs(matchingInfo);
        outputView.printMatchingResult(pairs);
    }

    private void getOption3() {
        MatchingRepository.deleteAll();
        outputView.printResetMessage();
    }

    private void matchingPair(List<String> backendNames, List<String> frontendNames, MatchingInfo matchingInfo) {
        if (MatchingRepository.findMatchingInfo(matchingInfo)) {
            String rematching = getRematching();
            if (rematching.equals("아니오")) {
                getOption1(backendNames, frontendNames);
                return;
            }
        }

        Crews crews = getCrewsByCourse(backendNames, frontendNames, matchingInfo);
        List<Pair> pairs = matchingService.validateMatching(crews, matchingInfo);
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
