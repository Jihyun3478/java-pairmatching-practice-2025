package pairmatching.domain.model;

import java.util.List;

public class Pairs {
    private final List<Pair> pairs;

    public Pairs(List<Pair> pairs) {
        this.pairs = pairs;
    }

    public List<Pair> getPairs() {
        return pairs;
    }
}
