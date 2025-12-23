package pairmatching;

import java.util.List;

public class MatchingInfo {
    private final Course course;
    private final Level level;
    private final Mission mission;

    public MatchingInfo(Course course, Level level, Mission mission) {
        this.course = course;
        this.level = level;
        this.mission = mission;
    }

    public static MatchingInfo from(List<String> matchingInfo) {
        return new MatchingInfo(
                Course.isExistCourse(matchingInfo.get(0)),
                Level.isExistLevel(matchingInfo.get(1)),
                Mission.isExistMission(matchingInfo.get(2))
        );
    }
}
