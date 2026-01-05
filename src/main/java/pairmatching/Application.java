package pairmatching;

import pairmatching.controller.ManageController;
import pairmatching.view.InputView;
import pairmatching.view.OutputView;

public class Application {
    public static void main(String[] args) {
        ManageController manageController = new ManageController(
                new InputView(),
                new OutputView()
        );
        manageController.start();
    }
}
