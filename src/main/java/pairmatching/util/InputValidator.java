package pairmatching.util;

import java.util.Objects;

public class InputValidator {
    public static void validateNotEmpty(String input) {
        if (Objects.isNull(input) || input.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 입력값이 빈 값입니다. 다시 입력해주세요.");
        }
    }
}
