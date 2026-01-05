package pairmatching.repository;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
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

    public static Pairs findPairsByMatchingInfo(MatchingInfo matchingInfo) {
        return matchings.get(matchingInfo);
    }
}
