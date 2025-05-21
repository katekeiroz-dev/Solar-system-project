package controllers;


import com.thoughtworks.xstream.io.xml.DomDriver;
import models.GasPlanet;
import models.IcePlanet;
import models.Planet;
import models.PlanetContainer;
import utils.ISerializer;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import com.thoughtworks.xstream.XStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.stream.Collectors;

/**
 * The PlanetSystemAPI class provides CRUD operations, reporting, validation, sorting,
 * and persistence methods for managing a list of planets including IcePlanets and GasPlanets.
 * It implements the {@link utils.ISerializer} interface to allow serialization/deserialization of planet data.
 */
public class PlanetSystemAPI implements ISerializer {

    /** File object for handling persistence. */
    private File file = null;

    /** List that stores all planet objects. */
    private List<Planet> planetList = new ArrayList<>();

    /**
     * Loads planet data from the XML file using XStream.
     *
     * @throws Exception if there is an error during file reading or deserialization.
     */
    @Override
    public void load() throws Exception {
        File file = new File("planets.xml");

        if (file.exists()) {
            XStream xstream = new XStream(new DomDriver());
            xstream.autodetectAnnotations(true);
            XStream.setupDefaultSecurity(xstream);
            xstream.allowTypesByWildcard(new String[]{"models.*"});
            xstream.alias("planets", PlanetContainer.class);
            xstream.alias("icePlanet", IcePlanet.class);
            xstream.alias("gasPlanet", GasPlanet.class);
            xstream.addImplicitCollection(PlanetContainer.class, "planets");

            Object result = xstream.fromXML(file);
            if (result instanceof PlanetContainer) {
                PlanetContainer container = (PlanetContainer) result;
                this.planetList = container.getPlanets();
            }
        } else {
            System.err.println("File 'planets.xml' not found.");
        }
    }

    /**
     * Saves the current planet list to an XML file using XStream.
     *
     * @throws Exception if an I/O error occurs.
     */
    @Override
    public void save() throws Exception {
        File file = new File("planets.xml");
        XStream xstream = new XStream(new DomDriver());

        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypesByWildcard(new String[]{"models.*"});
        xstream.autodetectAnnotations(true);
        xstream.alias("planets", PlanetContainer.class);
        xstream.alias("icePlanet", IcePlanet.class);
        xstream.alias("gasPlanet", GasPlanet.class);
        xstream.addImplicitCollection(PlanetContainer.class, "planets");
        xstream.setMode(XStream.NO_REFERENCES);

        PlanetContainer container = new PlanetContainer();
        container.setPlanets(this.planetList);

        xstream.toXML(container, new FileWriter(file));
    }

    /**
     * Returns the name of the file used for persistence.
     *
     * @return file name as a String.
     */
    @Override
    public String fileName() {
        return "";
    }

    // ------------------- CRUD METHODS -------------------

    /**
     * Gets the list of all planets.
     *
     * @return list of planets.
     */
    public List<Planet> getPlanetList() {
        return planetList;
    }

    /**
     * Adds a new planet to the list.
     *
     * @param planet the Planet object to add.
     * @return false (unused return, could be improved).
     * @throws IOException if an I/O error occurs.
     */
    public boolean addPlanetObject(Planet planet) throws IOException {
        planetList.add(planet);
        return false;
    }

    /**
     * Deletes a planet at a given index.
     *
     * @param index the index of the planet to delete.
     * @return the removed planet or null if index is invalid.
     */
    public Planet deletePlanetIndex(int index) {
        if (index >= 0 && index < planetList.size()) {
            return planetList.remove(index);
        }
        return null;
    }

    /**
     * Deletes a planet by its ID.
     *
     * @param id the ID of the planet.
     * @return the removed planet or null if not found.
     */
    public Planet deletePlanetId(int id) {
        Iterator<Planet> iterator = planetList.iterator();
        while (iterator.hasNext()) {
            Planet planet = iterator.next();
            if (planet.getId() == id) {
                iterator.remove();
                return planet;
            }
        }
        return null;
    }

    /**
     * Retrieves a planet by its index.
     *
     * @param index the index in the list.
     * @return the corresponding Planet or null if not found.
     */
    public Planet getPlanetByIndex(int index) {
        if (index >= 0 && index < planetList.size()) {
            return planetList.get(index);
        }
        return null;
    }

    /**
     * Retrieves a planet by its ID.
     *
     * @param id the ID of the planet.
     * @return the corresponding Planet or null if not found.
     */
    public Planet getPlanetById(int id) {
        for (Planet planet : planetList) {
            if (planet.getId() == id) {
                return planet;
            }
        }
        return null;
    }

    /**
     * Retrieves a planet by its name.
     *
     * @param name the name of the planet.
     * @return the corresponding Planet or null if not found.
     */
    public Planet getPlanetByName(String name) {
        for (Planet planet : planetList) {
            if (name.equals(planet.getName()))
                return planet;
        }
        return null;
    }

    // ------------------- REPORTING METHODS -------------------

    /**
     * Lists all planets with their index and string representation.
     *
     * @return formatted string list of all planets.
     */
    public String listAllPlanetBodies() {
        if (planetList.isEmpty()) return "No Planets";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < planetList.size(); i++) {
            sb.append(i).append(": ").append(planetList.get(i)).append("\n");
        }
        return sb.toString();
    }

    /**
     * Lists all gas planets.
     *
     * @return formatted string list of gas planets or a message if none exist.
     */
    public String listAllGasPlanets() {
        StringBuilder sb = new StringBuilder();
        int index = 0;
        for (Planet p : planetList) {
            if (p instanceof GasPlanet) {
                sb.append(index).append(": ").append(p).append("\n");
            }
            index++;
        }
        return sb.isEmpty() ? "No Gas Planets" : sb.toString();
    }

    /**
     * Lists all ice planets.
     *
     * @return formatted string list of ice planets or a message if none exist.
     */
    public String listAllIcePlanets() {
        StringBuilder sb = new StringBuilder();
        int index = 0;
        for (Planet p : planetList) {
            if (p instanceof IcePlanet) {
                sb.append(index).append(": ").append(p).append("\n");
            }
            index++;
        }
        return sb.isEmpty() ? "No Ice Planets" : sb.toString();
    }

    /**
     * Lists planets with mass greater than a given value.
     *
     * @param mass the minimum mass.
     * @return list or message if none match.
     */
    public String listAllPlanetObjectsHeavierThan(double mass) {
        StringBuilder sb = new StringBuilder();
        for (Planet p : planetList) {
            if (p.getMass() > mass) {
                sb.append(p).append("\n");
            }
        }
        return sb.isEmpty() ? "No Planet heavier than " + mass : sb.toString();
    }

    /**
     * Lists planets with diameter smaller than a given value.
     *
     * @param diameter the maximum diameter.
     * @return list or message if none match.
     */
    public String listAllPlanetObjectsSmallerThan(double diameter) {
        StringBuilder sb = new StringBuilder();
        for (Planet p : planetList) {
            if (p.getDiameter() < diameter) {
                sb.append(p).append("\n");
            }
        }
        return sb.isEmpty() ? "No Planet smaller than " + diameter : sb.toString();
    }

    // ------------------- COUNTING METHODS -------------------

    /**
     * Returns the number of planets.
     *
     * @return planet count.
     */
    public int numberOfPlanetBodies() {
        return planetList.size();
    }

    /**
     * Counts how many planets are gas planets.
     *
     * @return count of gas planets.
     */
    public int numberOfGasPlanets() {
        int count = 0;
        for (Planet p : planetList) {
            if (p instanceof GasPlanet) count++;
        }
        return count;
    }

    /**
     * Counts how many planets are ice planets.
     *
     * @return count of ice planets.
     */
    public int numberOfIcePlanets() {
        int count = 0;
        for (Planet p : planetList) {
            if (p instanceof IcePlanet) count++;
        }
        return count;
    }

    // ------------------- VALIDATION METHODS -------------------

    /**
     * Checks if an ID is unique (not already used).
     *
     * @param id the ID to check.
     * @return true if ID is unique.
     */
    public boolean isValidId(int id) {
        for (Planet p : planetList) {
            if (p.getId() == id) return false;
        }
        return true;
    }

    /**
     * Checks if the index is within bounds.
     *
     * @param index the index to check.
     * @return true if valid.
     */
    public boolean isValidIndex(int index) {
        return index >= 0 && index < planetList.size();
    }

    // ------------------- UPDATE METHODS -------------------

    /**
     * Updates an ice planet with new details by ID.
     *
     * @param id the ID of the planet.
     * @param updatedDetails the new IcePlanet object.
     * @return the updated planet or null if not found.
     */
    public Planet updateIcePlanet(int id, IcePlanet updatedDetails) {
        for (int i = 0; i < planetList.size(); i++) {
            Planet p = planetList.get(i);
            if (p.getId() == id && p instanceof IcePlanet) {
                planetList.set(i, updatedDetails);
                return updatedDetails;
            }
        }
        return null;
    }

    /**
     * Updates a gas planet with new details by ID.
     *
     * @param id the ID of the planet.
     * @param updatedDetails the new GasPlanet object.
     * @return the updated planet or null if not found.
     */
    public Planet updateGasPlanet(int id, GasPlanet updatedDetails) {
        for (int i = 0; i < planetList.size(); i++) {
            Planet p = planetList.get(i);
            if (p.getId() == id && p instanceof GasPlanet) {
                planetList.set(i, updatedDetails);
                return updatedDetails;
            }
        }
        return null;
    }

    // ------------------- SORT METHODS -------------------

    /**
     * Sorts the planet list by diameter in ascending order.
     */
    public void sortByDiameterAscending() {
        planetList.sort(Comparator.comparingDouble(Planet::getDiameter));
    }

    /**
     * Swaps two planets in the list.
     *
     * @param list the list of planets.
     * @param i index of the first planet.
     * @param j index of the second planet.
     */
    private void swapPlanet(List<Planet> list, int i, int j) {
        Planet temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    // ------------------- TOP 5 METHODS -------------------

    /**
     * Lists top 5 gas planets with highest radiation levels.
     *
     * @return formatted string list.
     */
    public String topFiveHighestRadiationGasPlanet() {
        return planetList.stream()
                .filter(p -> p instanceof GasPlanet)
                .map(p -> (GasPlanet) p)
                .sorted(Comparator.comparingDouble(GasPlanet::getRadiationLevel).reversed())
                .limit(5)
                .map(Object::toString)
                .collect(Collectors.joining("\n"));
    }

    /**
     * Lists top 5 largest planets by diameter.
     *
     * @return formatted string list.
     */
    public String topFiveBiggestPlanet() {
        return planetList.stream()
                .sorted(Comparator.comparingDouble(Planet::getDiameter).reversed())
                .limit(5)
                .map(Object::toString)
                .collect(Collectors.joining("\n"));
    }
}