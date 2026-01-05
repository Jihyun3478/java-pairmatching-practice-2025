package pairmatching.controller;

import java.awt.Menu;
import java.util.List;
import java.util.function.Supplier;
import pairmatching.domain.model.MenuOption;
import pairmatching.dto.MatchingInfo;
import pairmatching.util.FileParser;
import pairmatching.util.InputParser;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class ManageController {
    private final InputView inputView;
    private final OutputView outputView;

    public ManageController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        List<String> backendCrews = FileParser.parseBackendCrews();
        List<String> frontendCrews = FileParser.parseFrontendCrews();

        MenuOption menuOption = null;
        do {
            menuOption = askMenu();

            if (menuOption == MenuOption.OPTION_1) {
                outputView.printCourseAndMission();
                MatchingInfo matchingInfo = getMatchingInfo();
            }

        } while (menuOption != MenuOption.OPTION_Q);

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
