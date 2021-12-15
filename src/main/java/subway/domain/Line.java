package subway.domain;

import java.util.ArrayList;
import java.util.List;

public class Line {
    private String name;
    private List<CloseStation> closeStations;

    private Line(String name) {
        this.name = name;
        closeStations = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public static Line of(String name) {
        return new Line(name);
    }

    public void addCloseStation(CloseStation closeStation) {
        closeStations.add(closeStation);
    }
}
