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
            menuOption = askMenu();

            if (menuOption == MenuOption.OPTION_1) {
                outputView.printCourseAndMission();
                MatchingInfo matchingInfo = getMatchingInfo();
                Crews backendCrews = matchingService.shuffle(getCrews(backendNames, Course.BACKEND));
                Crews frontendCrews = matchingService.shuffle(getCrews(frontendNames, Course.FRONTEND));

                if (matchingInfo.getCourse() == Course.BACKEND) {
                    Pairs pairs = matchingService.pairMatching(backendCrews);
                    MatchingRepository.addMatchings(matchingInfo, pairs);
                    outputView.printMatchingResult(pairs);
                }
                if (matchingInfo.getCourse() == Course.FRONTEND) {
                    Pairs pairs = matchingService.pairMatching(frontendCrews);
                    MatchingRepository.addMatchings(matchingInfo, pairs);
                    outputView.printMatchingResult(pairs);
                }
            }

        } while (menuOption != MenuOption.OPTION_Q);
    }

    private static List<Crew> getCrews(List<String> names, Course course) {
        List<Crew> crews = new ArrayList<>();
        for (String name : names) {
            crews.add(new Crew(course, name));
        }
        return crews;
    }

    private MenuOption askMenu() {
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
