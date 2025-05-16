package controllers;


import com.thoughtworks.xstream.io.xml.DomDriver;
import models.Planet;
import utils.ISerializer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import com.thoughtworks.xstream.XStream;
import java.io.FileWriter;
import java.io.IOException;


//This means the class PlanetSystemAPI is implementing an interface called ISerializer.
//An interface in Java defines methods that a class must implement—but it doesn't provide the actual implementation.
//So, PlanetSystemAPI must provide concrete implementations of all methods defined in the ISerializer interface.
public class PlanetSystemAPI implements ISerializer{



    private File file = null;
    private List<Planet> planetList = new ArrayList<>();


    //throws Exception means the method might throw an Exception (an error) that must be handled
    @Override
    public void load() throws Exception {
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

    //the IOException is an exception to the IO operation , different from others.
    // Exception is the most general one.
    // This informs that this method may throw an IO exception
    public boolean addPlanetObject(Planet planet) throws IOException{

        planetList.add(planet);

        return false;
    }





    //TODO Create a list to store the Planets
    //TODO create a File field to story filename

    //TODO create constructor to initialise filename and instantiate the planets list

    //TODO - CRUD Methods

    // TODO Reporting Methods

    // TODO number methods

    //TODO validation method below:
    //the following is isValidId can be updated
    //to suit your code - checks is the id already there in the list
    /*
    public boolean isValidId(int id) {
        for (Planet pl : whateverYouCalledYourList) {
            if (pl.getId().equals(id))
                return false;
        }
            return true;
        }
*/

    //TODO get Planet methods

    //TODO - delete methods



    //TODO - sort methods

    //TODO Top 5 methods





    // TODO Persistence methods

}   
