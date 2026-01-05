package pairmatching.controller;

import java.util.List;
import java.util.function.Supplier;
import pairmatching.domain.model.MenuOption;
import pairmatching.util.FileParser;
import pairmatching.view.InputView;

public class ManageController {
    private final InputView inputView;

    public ManageController(InputView inputView) {
        this.inputView = inputView;
    }

    public void start() {
        do {
            List<String> backendCrews = FileParser.parseBackendCrews();
            List<String> frontendCrews = FileParser.parseFrontendCrews();
        } while (askMenu() != MenuOption.OPTION_Q);

    }

    private MenuOption askMenu() {
        return retryUntilSuccess(() -> {
            String menu = inputView.readMenu();
            return MenuOption.fromMenu(menu);
        });
    }

    private <T> T retryUntilSuccess(Supplier<T> action) {
        while (true) {
            try {
                return action.get();
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }
}
