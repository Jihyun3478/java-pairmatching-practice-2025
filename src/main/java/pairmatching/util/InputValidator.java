package pairmatching.util;

import java.util.Objects;

public class InputValidator {
    public static void validateNotEmpty(String input) {
        if (Objects.isNull(input) || input.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 입력값이 빈 값입니다. 다시 입력해주세요.");
        }
    }

    public static void validateRematching(String input) {
        if (!(input.equals("네") || input.equals("아니오"))) {
            throw new IllegalArgumentException("[ERROR] 잘못된 형식입니다. 다시 입력해주세요.");
        }
    }
}
