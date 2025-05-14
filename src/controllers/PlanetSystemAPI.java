package controllers;


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

    public static class Pessoa {
        private String nome;

        public Pessoa(String nome) {
            this.nome = nome;
        }
    }

    private File file = null;
    private List<Planet> planetList = new ArrayList<>();


    //throws Exception means the method might throw an Exception (an error) that must be handled
    @Override
    public void load() throws Exception {

    }

    @Override
    public void save() throws Exception {

    }

    @Override
    public String fileName() {
        return "";
    }

    //the IOException is an exception to the IO operation , different from others.
    // Exception is the most general one.
    // This informs that this method may throw an IO exception
    public boolean addPlanetObject(Planet planet) throws IOException{
        Pessoa pessoa = new Pessoa("Joao");
        XStream xstream = new XStream();
        xstream.alias("planet" , Planet.class);

        File file = new File("planets.xml");
        FileWriter fileWriter = new FileWriter(file,true);
        xstream.toXML(pessoa,fileWriter);
        fileWriter.write(System.lineSeparator());
        fileWriter.close();
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
