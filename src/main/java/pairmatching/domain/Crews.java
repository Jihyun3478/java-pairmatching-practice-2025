package pairmatching.domain;

import java.util.ArrayList;
import java.util.List;

public class Crews {
    private final List<Crew> crews;

    private Crews(List<Crew> crews) {
        this.crews = crews;
    }

    public static Crews fromCrews(List<Crew> crews) {
        return new Crews(crews);
    }

    public List<Pair> createPairs() {
        List<Pair> pairs = new ArrayList<>();
        int index = 0;

        while (index < crews.size()) {
            Pair pair = new Pair();
            pair.matching(crews.get(index));
            pair.matching(crews.get(index + 1));
            index += 2;
            pairs.add(pair);

            if (isOdd() && index == crews.size() - 3) {
                Pair lastPair = new Pair();
                lastPair.matching(crews.get(index));
                lastPair.matching(crews.get(index + 1));
                lastPair.matching(crews.get(index + 2));
                pairs.add(lastPair);
                break;
            }
        }
        return pairs;
    }

    public boolean isOdd() {
        return crews.size() % 2 == 1;
    }

    public int size() {
        return crews.size();
    }

    public List<Crew> getCrews() {
        return crews;
    }
}
