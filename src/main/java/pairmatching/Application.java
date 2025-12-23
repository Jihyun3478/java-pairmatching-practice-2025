package pairmatching;

import camp.nextstep.edu.missionutils.Console;
import java.util.Objects;
import pairmatching.util.BackendInfoReader;
import pairmatching.util.FileReader;
import pairmatching.util.FrontendInfoReader;

public class Application {
    private static final String BACKEND_CREW_INFO = "src/main/resources/backend-crew.md";
    private static final String FRONTEND_CREW_INFO = "src/main/resources/frontend-crew.md";

    public static void main(String[] args) {
        int startFunction = getStartFunction();

        FileReader backendInfoReader = new BackendInfoReader();
        Crews backendCrews = backendInfoReader.readFile(BACKEND_CREW_INFO);
        FileReader frontendInfoReader = new FrontendInfoReader();
        Crews frontendCrews = frontendInfoReader.readFile(FRONTEND_CREW_INFO);


    }

    private static int getStartFunction() {
        while (true) {
            try {
                System.out.println("기능을 선택하세요.");
                System.out.println("1. 페어 매칭");
                System.out.println("2. 페어 조회");
                System.out.println("3. 페어 초기화");
                System.out.println("Q. 종료");

                String input = Console.readLine();
                if (Objects.isNull(input) || input.isEmpty()) {
                    throw new IllegalArgumentException("[ERROR] 입력값이 비어있습니다. 다시 입력해주세요.");
                }

                return Integer.parseInt(input);
            } catch (Exception exception) {
                System.out.println("[ERROR] 잘못된 형식의 입력입니다. 다시 입력해주세요.");
            }
        }
    }
}
