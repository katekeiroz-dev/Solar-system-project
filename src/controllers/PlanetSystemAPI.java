package controllers;


import com.thoughtworks.xstream.io.xml.DomDriver;
import models.GasPlanet;
import models.IcePlanet;
import models.Planet;
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


//This means the class PlanetSystemAPI is implementing an interface called ISerializer.
//An interface in Java defines methods that a class must implement—but it doesn't provide the actual implementation.
//So, PlanetSystemAPI must provide concrete implementations of all methods defined in the ISerializer interface.
public class PlanetSystemAPI implements ISerializer{



    //TODO Create a list to store the Planets - done
    //TODO create a File field to story filename - done

    //TODO create constructor to initialise filename and instantiate the planets list



    private File file = null;
    private List<Planet> planetList = new ArrayList<>();


    //throws Exception means the method might throw an Exception (an error) that must be handled
    @Override
    public void load() throws Exception {

        //TODO: FIX BUG HEREEE

        File file = new File("planets.xml");

        if (file.exists()) {
            XStream xstream = new XStream(new DomDriver());
            xstream.alias("planet", Planet.class);
            xstream.alias("planets", List.class);

            this.planetList = (List<Planet>) xstream.fromXML(file);


        } else {
            System.err.println("Arquivo 'planets.xml' não encontrado.");

        }
    }

    @Override
    public void save() throws Exception {
        File file = new File("planets.xml");

        XStream xstream = new XStream(new DomDriver());
        xstream.alias("planet", Planet.class);
        xstream.alias("planets", List.class);  // <planets> será a tag raiz
        xstream.toXML(planetList, new FileWriter(file));

    }

    @Override
    public String fileName() {


        return "";
    }

    //----------- CRUD Methods ------------------

    //the IOException is an exception to the IO operation , different from others.
    // Exception is the most general one.
    // This informs that this method may throw an IO exception
    public boolean addPlanetObject(Planet planet) throws IOException{

        planetList.add(planet);

        return false;
    }



         /*
         This is the return type — it returns a Planet object.
        deletePlanetIndex: This is the method name —
        it suggests that the method deletes a planet at a specified index.
        (int index): This is the parameter — an integer specifying the position in the list where
        the planet should be deleted.
          */
    public Planet deletePlanetIndex(int index) {
        if (index >= 0 && index < planetList.size()) {
            /*
            If the index is valid, it removes the planet at that index from the planetList.
            It also returns the removed Planet object
             */
            return planetList.remove(index);
        }
        return null;
    }

/*


    public Planet deletePlanetId(int id) {
        for (Planet planet : planetList) {//This loops through each Planet object in the planetList
            if (planet.getId() == id) { //Checks if the current planet's ID matches the one given.
                planetList.remove(planet); //The planet is removed from the list.
                //The removed planet is returned immediately.
                return planet; //If no planet with the matching ID is found after the loop finishes, null is returned.
            }
        }
        return null;
    }
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


    public Planet getPlanetByIndex(int index) {
        if (index >= 0 && index < planetList.size()) {
            return planetList.get(index);
        }
        return null;
    }

    public Planet getPlanetById(int id) {
        for (Planet planet : planetList) {
            if (planet.getId() == id) {
                return planet;
            }
        }
        return null;
    }



    // --------- Reporting Methods ----------

    public String listAllPlanetBodies() {
        if (planetList.isEmpty()) return "No Planets";
        // String is immutable, meaning every time you concatenate with +, a new String object is created.This wastes
        // memory and slows things down when looping a lot.
        //So when we use StringBuilder when you're building up a string in a loop. It avoids performance issues caused by
        // repeatedly creating and discarding String objects
        //It builds the string in one place — in a buffer — without creating new string objects every time.
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < planetList.size(); i++) {
            sb.append(i).append(": ").append(planetList.get(i)).append("\n");
        }
        return sb.toString();
    }

    //This method returns a list of all gas planets from the main list, with their index and name.
    //It uses instanceof to filter by type.
    //It keeps the original index to help identify or reference planets.
    //Returns "No Gas Planets" if none are found.
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

    public String listAllPlanetObjectsHeavierThan(double mass) {
        StringBuilder sb = new StringBuilder();
        for (Planet p : planetList) {
            if (p.getMass() >= mass) {
                sb.append(p).append("\n");
            }
        }
        return sb.isEmpty() ? "No Planet heavier than " + mass : sb.toString();
    }

    public String listAllPlanetObjectsSmallerThan(double diameter) {
        StringBuilder sb = new StringBuilder();
        for (Planet p : planetList) {
            if (p.getDiameter() <= diameter) {
                sb.append(p).append("\n");
            }
        }
        return sb.isEmpty() ? "No Planet smaller than " + diameter : sb.toString();
    }


    // --------- number methods/ count method ---------
    public int numberOfPlanetBodies() {
        return planetList.size();
    }

    public int numberOfGasPlanets() {
        int count = 0;
        for (Planet p : planetList) {
            if (p instanceof GasPlanet) count++;
        }
        return count;
    }

    public int numberOfIcePlanets() {
        int count = 0;
        for (Planet p : planetList) {
            if (p instanceof IcePlanet) count++;
        }
        return count;
    }




    //---------------- validation method -------------------------

    public boolean isValidId(int id) {
        for (Planet p : planetList) {
            if (p.getId() == id) return false;
        }
        return true;
    }


    //-------- Update Methods -------------------------------

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


    //TODO - delete methods



    //---------------- sort methods ----------------------------

    //Because the diameter is a double (a decimal number), this method(COMPARATOR) is made to compare those numbers efficiently.
    public void sortByDiameterAscending() {
        planetList.sort(Comparator.comparingDouble(Planet::getDiameter)); //:: is just a concise way to refer to a method
        // instead of writing a full lambda expression -> etc
    }

    public void sortByDiameterDescending() {
        planetList.sort(Comparator.comparingDouble(Planet::getDiameter).reversed());
    }

    //This method swaps two planets in a list — it switches their positions.
    private void swapPlanet(List<Planet> list, int i, int j) {
        Planet temp = list.get(i);
        list.set(i, list.get(j)); //Replaces the planet at index i with the planet at index j.
        list.set(j, temp); //Puts the planet we saved in temp (originally at i) into index j
    }




    //---------------- Top 5 methods ---------------------------------

    public String topFiveHighestRadiationGasPlanet() {
        return planetList.stream()
                .filter(p -> p instanceof GasPlanet)
                .map(p -> (GasPlanet) p)
                .sorted(Comparator.comparingDouble(GasPlanet::getRadiationLevel).reversed())
                .limit(5)
                .map(Object::toString)
                .collect(Collectors.joining("\n"));
    }

    public String topFiveBiggestPlanet() {
        return planetList.stream()
                .sorted(Comparator.comparingDouble(Planet::getDiameter).reversed())
                .limit(5)
                .map(Object::toString)
                .collect(Collectors.joining("\n"));
    }







    // TODO Persistence methods



}   
