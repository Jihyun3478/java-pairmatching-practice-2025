package pairmatching;

import pairmatching.controller.ManageController;
import pairmatching.view.InputView;

public class Application {
    public static void main(String[] args) {
        ManageController manageController = new ManageController(
                new InputView()
        );
        manageController.start();
    }
}
