package pairmatching;

import java.util.Arrays;

public enum Mission {
    CAR_RACE("자동차경주", Level.LEVEL1),
    LOTTO("로또", Level.LEVEL1),
    NUMBER_BASEBALL("숫자야구게임", Level.LEVEL1),

    CART("장바구니", Level.LEVEL2),
    PAYMENT("결제", Level.LEVEL2),
    SUBWAY_ROUTE_MAP("지하철노선도", Level.LEVEL2),

    PERFORMANCE_IMPROVEMENT("성능개선", Level.LEVEL4),
    DEPLOYMENT("배포", Level.LEVEL4),
    ;

    private final String name;
    private final Level level;

    Mission(String name, Level level) {
        this.name = name;
        this.level = level;
    }

    public static Mission isExistMission(String missionName) {
        return Arrays.stream(values()).filter(value -> value.name.equals(missionName))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 미션입니다. 다시 입력해주세요."));
    }
}
