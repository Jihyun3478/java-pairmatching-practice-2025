package pairmatching.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
        Crews backendCrews = matchingService.shuffle(getCrews(backendNames, Course.BACKEND));
        Crews frontendCrews = matchingService.shuffle(getCrews(frontendNames, Course.FRONTEND));

        if (matchingInfo.getCourse() == Course.BACKEND) {
            getMatchingResult(backendCrews, matchingInfo);
            return;
        }
        getMatchingResult(frontendCrews, matchingInfo);
    }

    private List<Crew> getCrews(List<String> names, Course course) {
        List<Crew> crews = new ArrayList<>();
        for (String name : names) {
            crews.add(new Crew(course, name));
        }
        return crews;
    }

    private void getMatchingResult(Crews crews, MatchingInfo matchingInfo) {
        Pairs pairs = matchingService.pairMatching(crews);
        MatchingRepository.addMatchings(matchingInfo, pairs);
        outputView.printMatchingResult(pairs);
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
