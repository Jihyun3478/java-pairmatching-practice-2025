package pairmatching.domain.model;

import java.util.List;

public class Crews {
    private final List<Crew> crews;

    private Crews(List<Crew> crews) {
        this.crews = crews;
    }

    public static Crews fromCrews(List<Crew> crews) {
        return new Crews(crews);
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
