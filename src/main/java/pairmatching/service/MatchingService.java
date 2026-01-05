package pairmatching.service;

import java.util.ArrayList;
import java.util.List;
import pairmatching.domain.model.Crew;
import pairmatching.domain.model.Crews;
import pairmatching.domain.model.Pair;
import pairmatching.domain.model.Pairs;

public class MatchingService {
    private final ShuffleGenerator shuffleGenerator;

    public MatchingService(ShuffleGenerator shuffleGenerator) {
        this.shuffleGenerator = shuffleGenerator;
    }


    public Crews shuffle(List<Crew> crews) {
        return Crews.fromCrews(shuffleGenerator.shuffle(crews));
    }

    public Pairs pairMatching(Crews crews) {
        List<Pair> pairs = new ArrayList<>();
        int index = 0;

        while (index < crews.size()) {
            Pair pair = new Pair();
            pair.matching(crews.getCrews().get(index));
            pair.matching(crews.getCrews().get(index + 1));
            index += 2;
            pairs.add(pair);

            if (crews.isOdd() && index == crews.size() - 3) {
                Pair lastPair = new Pair();
                lastPair.matching(crews.getCrews().get(index));
                lastPair.matching(crews.getCrews().get(index + 1));
                lastPair.matching(crews.getCrews().get(index + 2));
                pairs.add(lastPair);
                break;
            }
        }
        return new Pairs(pairs);
    }
}
