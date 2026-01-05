package pairmatching.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Pair {
    private final List<Crew> pair;

    public Pair() {
        this.pair = new ArrayList<>();
    }

    public void matching(Crew crew) {
        this.pair.add(crew);
    }

    public List<String> getPair() {
        return pair.stream()
                .map(Crew::getName)
                .collect(Collectors.toList());
    }

    public boolean existsIn(List<List<Pair>> existingPairs) {
        return existingPairs.stream()
                .flatMap(Collection::stream)
                .anyMatch(this::equals);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Pair)) {
            return false;
        }
        Pair newPair = (Pair) object;
        return pair.size() == newPair.pair.size()
                && new HashSet<>(pair).containsAll(newPair.pair);
    }

    @Override
    public int hashCode() {
        return new HashSet<>(pair).hashCode();
    }
}
