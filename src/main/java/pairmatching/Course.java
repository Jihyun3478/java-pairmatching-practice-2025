package pairmatching;

import java.util.Arrays;

public enum Course {
    BACKEND("백엔드"),
    FRONTEND("프론트엔드");

    private String name;

    Course(String name) {
        this.name = name;
    }

    public static Course isExistCourse(String courseName) {
        return Arrays.stream(values()).filter(value -> value.name.equals(courseName))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 교육과정 입니다. 다시 입력해주세요."));
    }
}
