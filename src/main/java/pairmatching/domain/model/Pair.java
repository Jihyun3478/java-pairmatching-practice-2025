package pairmatching.domain.model;

import java.util.ArrayList;
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
}
