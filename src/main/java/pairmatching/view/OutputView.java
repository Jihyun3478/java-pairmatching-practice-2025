package pairmatching.view;

import java.util.List;
import pairmatching.domain.Pair;

public class OutputView {
    private static final String NEW_LINE = "\n";

    public void printCourseAndMission() {
        System.out.println();
        System.out.println("#############################################");
        System.out.println("과정: 백엔드 | 프론트엔드");
        System.out.println("미션:");
        System.out.println("  - 레벨1: 자동차경주 | 로또 | 숫자야구게임");
        System.out.println("  - 레벨2: 장바구니 | 결제 | 지하철노선도");
        System.out.println("  - 레벨3:");
        System.out.println("  - 레벨4: 성능개선 | 배포");
        System.out.println("  - 레벨5: ");
        System.out.println("############################################");
    }

    public void printMatchingResult(List<Pair> pairs) {
        System.out.println("페어 매칭 결과입니다.");
        for (Pair pair : pairs) {
            System.out.println(String.join(" : ", pair.getPair()));
        }
        System.out.println();
    }

    public void printResetMessage() {
        System.out.println();
        System.out.println("초기화 되었습니다.");
        System.out.println();
    }

    public void printErrorMessage(IllegalArgumentException exception) {
        System.out.println(exception.getMessage());
    }
}
