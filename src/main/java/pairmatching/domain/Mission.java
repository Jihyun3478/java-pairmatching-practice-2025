package pairmatching.domain;

import java.util.Arrays;

public enum Mission {
    CAR_RACE(Level.LEVEL1, "자동차경주"),
    LOTTO(Level.LEVEL1, "로또"),
    NUMBER_BASEBALL_GAME(Level.LEVEL1, "숫자야구게임"),

    CART(Level.LEVEL2, "장바구니"),
    PAYMENT(Level.LEVEL2, "결제"),
    SUBWAY_PATH(Level.LEVEL2, "지하철노선도"),

    PERFORMANCE_IMPROVEMENT(Level.LEVEL4, "성능개선"),
    DEPLOYMENT(Level.LEVEL4, "배포"),
    ;
    private final Level level;
    private final String name;

    Mission(Level level, String name) {
        this.level = level;
        this.name = name;
    }

    public static Mission of(Level level, String missionName) {
        return Arrays.stream(values())
                .filter(mission -> mission.level == level && mission.name.equals(missionName))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 미션입니다. 다시 입력해주세요."));
    }
}
