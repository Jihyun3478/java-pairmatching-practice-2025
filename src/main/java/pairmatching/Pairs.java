package pairmatching;

import java.util.ArrayList;
import java.util.List;

public class Pairs {
    private final List<Pair> pairs;

    public Pairs() {
        this.pairs = new ArrayList<>();
    }

    public static Pairs from(List<Crew> crews, Level level) {
        Pairs pairs = new Pairs();

        for (int index = 0; index < crews.size(); index += 2) {
            List<Crew> pairCrews = new ArrayList<>();
            pairCrews.add(crews.get(index));

            if (index + 1 < crews.size()) {
                pairCrews.add(crews.get(index + 1));
            }

            if (index + 2 == crews.size() - 1) {
                pairCrews.add(crews.get(index + 2));
                Pair pair = new Pair(new Crews(pairCrews), level);
                pairs.add(pair);
                break;
            }

            Pair pair = new Pair(new Crews(pairCrews), level);
            pairs.add(pair);
        }

        return pairs;
    }

    private void add(Pair pair) {
        this.pairs.add(pair);
    }

    public List<Pair> getPairs() {
        return pairs;
    }
}
