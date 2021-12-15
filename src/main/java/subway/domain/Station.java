package subway.domain;

public class Station {
    private String name;

    private Station(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Station of(String name) {
        return new Station(name);
    }
}
