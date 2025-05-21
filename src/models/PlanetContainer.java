package models;

import java.util.ArrayList;
import java.util.List;

public class PlanetContainer {
    private List<Planet> planets = new ArrayList<>();

    public List<Planet> getPlanets() {
        return planets;
    }

    public void setPlanets(List<Planet> planets) {
        this.planets = planets;
    }
}
