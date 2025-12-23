package pairmatching;

public class Pair {
    private final Crews crews;
    private final Level level;

    public Pair(Crews crews, Level level) {
        this.crews = crews;
        this.level = level;
    }

    public Crews getCrews() {
        return crews;
    }
}
