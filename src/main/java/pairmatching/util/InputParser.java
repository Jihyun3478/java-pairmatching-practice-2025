package pairmatching.util;

import pairmatching.dto.MatchingInfo;

public class InputParser {
    public static MatchingInfo parseMatchingInfo(String input) {
        try {
            String[] splitInput = input.split(", ");
            if (splitInput.length != 3) {
                throw new IllegalArgumentException("[ERROR] 잘못된 형식입니다. 다시 입력해주세요.");
            }

            return MatchingInfo.of(splitInput[0], splitInput[1], splitInput[2]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 잘못된 형식입니다. 다시 입력해주세요.");
        }
    }
}
