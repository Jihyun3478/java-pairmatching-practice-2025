package pairmatching.domain.model;

public class Crew {
    private Course course;
    private String name;

    public Crew(Course course, String name) {
        this.course = course;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Crew)) {
            return false;
        }
        Crew crew = (Crew) object;
        return name.equals(crew.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
