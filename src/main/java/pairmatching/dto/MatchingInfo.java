package pairmatching.dto;

import pairmatching.domain.model.Course;
import pairmatching.domain.model.Level;
import pairmatching.domain.model.Mission;

public class MatchingInfo {
    private final Course course;
    private final Level level;
    private final Mission mission;

    private MatchingInfo(Course course, Level level, Mission mission) {
        this.course = course;
        this.level = level;
        this.mission = mission;
    }

    public static MatchingInfo of(String courseName, String levelName, String missionName) {
        Level level = Level.fromName(levelName);
        return new MatchingInfo(
                Course.fromName(courseName),
                level,
                Mission.of(level, missionName)
        );
    }

    public Course getCourse() {
        return course;
    }

    public Level getLevel() {
        return level;
    }

    public Mission getMission() {
        return mission;
    }
}
