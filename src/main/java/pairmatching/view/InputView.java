package pairmatching.view;

import camp.nextstep.edu.missionutils.Console;
import pairmatching.domain.model.MenuOption;

public class InputView {
    public String readMenu() {
        System.out.println("기능을 선택하세요.");
        for (MenuOption menuOption : MenuOption.values()) {
            System.out.printf("%s. %s%n", menuOption.getMenu(), menuOption.getDescription());
        }
        return Console.readLine();
    }
}
