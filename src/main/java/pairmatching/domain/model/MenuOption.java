package pairmatching.domain.model;

import java.util.Arrays;

public enum MenuOption {
    OPTION_1("1", "페어 매칭"),
    OPTION_2("2", "페어 조회"),
    OPTION_3("3", "페어 초기화"),
    OPTION_Q("Q", "종료");
    
    private final String menu;
    private final String description;

    MenuOption(String menu, String description) {
        this.menu = menu;
        this.description = description;
    }

    public static MenuOption fromMenu(String menu) {
        return Arrays.stream(values())
            .filter(option -> option.menu.equals(menu))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 기능입니다. 다시 입력해주세요."));
    }

    public String getMenu() {
        return menu;
    }

    public String getDescription() {
        return description;
    }
}
