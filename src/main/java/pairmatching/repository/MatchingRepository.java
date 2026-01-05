package pairmatching.repository;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import pairmatching.domain.model.Course;
import pairmatching.domain.model.Level;
import pairmatching.domain.model.Pairs;
import pairmatching.dto.MatchingInfo;

public class MatchingRepository {
    
    private MatchingRepository() {}

    private static final Map<MatchingInfo, Pairs> matchings = new HashMap<>();

    public static Map<MatchingInfo, Pairs> matchings() {
        return Collections.unmodifiableMap(matchings);
    }

    public static void addMatchings(MatchingInfo matchingInfo, Pairs pairs) {
        matchings.put(matchingInfo, pairs);
    }

    public static void deleteAll() {
        matchings.clear();
    }

    public static List<Pairs> findByCourseAndLevel(Course course, Level level) {
        return matchings.keySet().stream()
                .filter(matchingInfo -> matchingInfo.getCourse() == course && matchingInfo.getLevel() == level)
                .map(matchings::get)
                .collect(Collectors.toList());

    }
}
