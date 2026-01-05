package pairmatching.view;

import camp.nextstep.edu.missionutils.Console;
import pairmatching.domain.model.MenuOption;
import pairmatching.util.InputValidator;

public class InputView {
    public String readMenu() {
        System.out.println("기능을 선택하세요.");
        for (MenuOption menuOption : MenuOption.values()) {
            System.out.printf("%s. %s%n", menuOption.getMenu(), menuOption.getDescription());
        }
        return Console.readLine();
    }

    public String readMatchingInfo() {
        System.out.println("과정, 레벨, 미션을 선택하세요.");
        System.out.println("ex) 백엔드, 레벨1, 자동차경주");

        String input = Console.readLine();
        InputValidator.validateNotEmpty(input);

        return input;
    }

    public String readRematching() {
        System.out.println();
        System.out.println("매칭 정보가 있습니다. 다시 매칭하시겠습니까?");
        System.out.println("네 | 아니오");

        String input = Console.readLine();
        InputValidator.validateRematching(input);

        return input;
    }
}
