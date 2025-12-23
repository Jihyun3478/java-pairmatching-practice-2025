package pairmatching;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import pairmatching.util.FileLineReader;
import pairmatching.util.FileReader;

public class Application {
    private static final String BACKEND_CREW_INFO = "src/main/resources/backend-crew.md";
    private static final String FRONTEND_CREW_INFO = "src/main/resources/frontend-crew.md";

    public static void main(String[] args) {
        int startFunction = getStartFunction();

        MatchingInfo matchingInfo = getMatchingInfo();

        if (startFunction == 1) {
            Pairs pairs = getPairs(matchingInfo);
            promptPairs(pairs);
        }
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

    private static MatchingInfo getMatchingInfo() {
        while (true) {
            try {
                System.out.println();
                System.out.println("#############################################");
                System.out.println("과정: 백엔드 | 프론트엔드");
                System.out.println("미션:");
                System.out.println("  - 레벨1: 자동차경주 | 로또 | 숫자야구게임");
                System.out.println("  - 레벨2: 장바구니 | 결제 | 지하철노선도");
                System.out.println("  - 레벨3:");
                System.out.println("  - 레벨4: 성능개선 | 배포");
                System.out.println("  - 레벨5:");
                System.out.println("############################################");
                System.out.println("과정, 레벨, 미션을 선택하세요.");
                System.out.println("ex) 백엔드, 레벨1, 자동차경주");

                String input = Console.readLine();
                if (Objects.isNull(input) || input.isEmpty()) {
                    throw new IllegalArgumentException("[ERROR] 입력값이 비어있습니다. 다시 입력해주세요.");
                }

                if (input.startsWith(",") || input.endsWith(",")) {
                    throw new IllegalArgumentException("[ERROR] 올바르지 않은 형식입니다. 다시 입력해주세요.");
                }

                if (input.contains(",,")) {
                    throw new IllegalArgumentException("[ERROR] 올바르지 않은 형식입니다. 다시 입력해주세요.");
                }


                String[] split = input.split(", ");
                if (Arrays.stream(split).anyMatch(String::isEmpty)) {
                    throw new IllegalArgumentException("[ERROR] 올바르지 않은 형식입니다. 다시 입력해주세요.");
                }

                List<String> matchingInfo = Arrays.stream(split).collect(Collectors.toList());
                return MatchingInfo.from(matchingInfo);
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    private static Pairs getPairs(MatchingInfo matchingInfo) {
        FileReader fileReader = new FileLineReader();

        List<String> backendNames = fileReader.readFile(BACKEND_CREW_INFO);
        List<String> shuffledBackend = Randoms.shuffle(backendNames);
        List<Crew> backendCrews = shuffledBackend.stream().map(crew -> new Crew(Course.BACKEND, crew))
                .collect(Collectors.toList());

        List<String> frontendNames = fileReader.readFile(FRONTEND_CREW_INFO);
        List<String> shuffledFrontend = Randoms.shuffle(frontendNames);
        List<Crew> frontendCrews = shuffledFrontend.stream().map(crew -> new Crew(Course.FRONTEND, crew))
                .collect(Collectors.toList());

        List<Crew> crews = new ArrayList<>();
        if (matchingInfo.getCourse() == Course.BACKEND) {
            crews = backendCrews;
        }
        if (matchingInfo.getCourse() == Course.FRONTEND) {
            crews = frontendCrews;
        }

        return Pairs.from(crews, matchingInfo.getLevel());
    }

    private static void promptPairs(Pairs pairs) {
        System.out.println();
        System.out.println("페어 매칭 결과입니다.");

        for (Pair pair : pairs.getPairs()) {
            Crews crews = pair.getCrews();
            List<String> names = crews.getCrews().stream()
                    .map(Crew::getName)
                    .collect(Collectors.toList());

            System.out.println(String.join(" : ", names));
        }
    }
}
