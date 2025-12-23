package pairmatching;

import java.util.List;

public class Crews {
    private final List<Crew> crews;

    public Crews(List<Crew> crews) {
        this.crews = crews;
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
