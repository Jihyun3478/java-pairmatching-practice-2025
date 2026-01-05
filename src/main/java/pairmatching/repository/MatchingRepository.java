package pairmatching.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import pairmatching.domain.model.Course;
import pairmatching.domain.model.Level;
import pairmatching.domain.model.Pair;
import pairmatching.dto.MatchingInfo;

public class MatchingRepository {

    private MatchingRepository() {
    }

    private static final Map<MatchingInfo, List<Pair>> matchings = new HashMap<>();

    public static void addMatchings(MatchingInfo matchingInfo, List<Pair> pairs) {
        matchings.put(matchingInfo, pairs);
    }

    public static boolean findMatchingInfo(MatchingInfo matchingInfo) {
        return matchings.keySet().stream()
                .anyMatch(info ->
                        info.getCourse() == matchingInfo.getCourse()
                                && info.getLevel() == matchingInfo.getLevel()
                                && info.getMission() == matchingInfo.getMission());
    }

    public static List<Pair> findPairs(MatchingInfo matchingInfo) {
        return matchings.keySet().stream()
                .filter(info -> info.getCourse() == matchingInfo.getCourse()
                        && info.getLevel() == matchingInfo.getLevel()
                        && info.getMission() == matchingInfo.getMission())
                .findAny()
                .map(matchings::get)
                .orElse(null);
    }

    public static List<List<Pair>> findByCourseAndLevel(Course course, Level level) {
        return matchings.keySet().stream()
                .filter(matchingInfo -> matchingInfo.getCourse() == course && matchingInfo.getLevel() == level)
                .map(matchings::get)
                .collect(Collectors.toList());

    }

    public static void deleteAll() {
        matchings.clear();
    }
}
