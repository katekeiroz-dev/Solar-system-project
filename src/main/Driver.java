package main;

import controllers.PlanetSystemAPI;

import models.GasPlanet;
import models.IcePlanet;
import utils.ScannerInput;
import utils.Utilities;


import java.io.File;
import java.io.IOException;

public class Driver {


    private PlanetSystemAPI planetAPI;


    public static void main(String[] args) throws Exception {
        new Driver().start();
    }
    public void start() {
        planetAPI = new PlanetSystemAPI();

    //load all data once the serializers are set up
    runMainMenu();
}
//construct menus
// write menu that user will see
    private int mainMenu() {
        System.out.println("--------Space Place ---------- ");
        System.out.println("|  1) Planets CRUD MENU \t |");
        System.out.println("| \t\t\t\t\t\t\t |");
        System.out.println("|  2) Reports MENU \t\t\t |");
        System.out.println("| -------------------------- |");

        System.out.println("|  3) Search Planets \t\t |");
        System.out.println("| \t\t\t\t\t\t\t |");
        System.out.println("|  4) Sort Planets \t\t\t |");
        System.out.println("| -------------------------- |");

        System.out.println("|  10) Save all \t\t\t |");
        System.out.println("| \t\t\t\t\t\t\t |");
        System.out.println("|  11) Load all \t\t\t |");

        System.out.println("| -------------------------- |");
        System.out.println("|  0) Exit \t\t\t\t\t |");
        System.out.println("| -------------------------- |");


        return ScannerInput.readNextInt("==>> ");
    }


    private void runMainMenu() {
        int option = mainMenu();
        switch (option){
            //exit case
            case 0:
                System.out.println("User pressed 0");
                break;
                //planet CRUD case
            case 1:
                runPlanetAPIMenu();

                //at this point we got back from planetObjectMenu class
                //in order to execute again this menu, we call runMainMenu
                runMainMenu();
                break;
                // reports MENU case
            case 2:
                System.out.println("User pressed 2");
                break;
                // search planet case
            case 3:
                System.out.println("User pressed 3");
                break;
                //sort planet case
            case 4:
                System.out.println("User pressed 4");
                break;
                //save all case
            case 10:
                saveAllData();
                runMainMenu();
                break;
                //load all
            case 11:
                loadAllData();
                runMainMenu();
                break;
            default:
                System.out.println("Invalid number .Please choose one of the following options:(1, 2, 3, 4, 10, 11, 0).");
                runMainMenu();
                break;

        }

        exitApp();
    }

    private void runPlanetAPIMenu(){
        int option = planetAPIMenu();
        switch (option) {
            case 0:
                break;
            //add a planet
            case 1:
                addPlanet();

                break;
            // delete a planet
            case 2:
                System.out.println("User pressed 2");
                break;
            // list all planets
            case 3:
                System.out.println("User pressed 3");
                break;
            //update planet
            case 4:
                System.out.println("User pressed 4");
                break;

            default:
                System.out.println("Invalid option. Please choose one of the following options:(1, 2, 3, 4, 0).");
                break;
        }

    }

    private int planetAPIMenu(){
        System.out.println("--------PLanet Object Menu ------- ");
        System.out.println("|  1) Add a planet object \t \t |");
        System.out.println("|  2) Delete a planet object \t |");
        System.out.println("|  3) List all Planet object \t |");
        System.out.println("|  4) Update planet object \t\t |");
        System.out.println("|  0) Return to main menu \t\t |");
        System.out.println("| ------------------------------ |");


        return ScannerInput.readNextInt("==>> ");
    }

    private void addPlanet() {

        String name =  ScannerInput.readNextLine("What is the name of the planet? : ");
        String surfaceType =  ScannerInput.readNextLine("What is the surface type of the planet? : ");
        double diameter =  ScannerInput.readNextDouble("Inform the diameter of the planet? : ");
        char hasLiquidWater =  ScannerInput.readNextChar("Does it have liquid Water?(Type Y for yes or N of No) : ");
        double mass =  ScannerInput.readNextDouble(" Inform the mass of the planet? : ");
        double avgTemp =  ScannerInput.readNextDouble(" What is the average temperature of the planet? : ");

        boolean hw = Utilities.YNtoBoolean(hasLiquidWater);
        String gasOrIce =  ScannerInput.readNextLine("Is it part of the Ice or Gas planets? : ").toUpperCase();

       // System.out.println(name + " " + surfaceType + diameter + hasLiquidWater + mass + avgTemp + gasOrIce);

        if (gasOrIce.equals("ICE")){
            String iceComp = ScannerInput.readNextLine("What is the ice composition? : ");
            IcePlanet icePlanet = new IcePlanet(name,mass,diameter,avgTemp,surfaceType,hw,iceComp);

            try {
                planetAPI.addPlanetObject(icePlanet);
            } catch (IOException e) {
                System.out.println("Error while attempting to add ice planet.");
                e.printStackTrace();
            }


        } else if (gasOrIce.equals("GAS")) {
            String gasComp = ScannerInput.readNextLine("What is the gas composition? : ");
            String coreComp = ScannerInput.readNextLine("What is the core composition? : ");
            double radLevel = ScannerInput.readNextDouble("What is the radiation level? : ");
            GasPlanet gasPlanet= new GasPlanet(name,mass,diameter,avgTemp,surfaceType,hw,gasComp,coreComp,radLevel);

            try{
                planetAPI.addPlanetObject(gasPlanet);
            }
            catch (IOException e){
                System.out.println("Error while attempting to add planet.");
                e.printStackTrace();

            }



        }else {
            System.out.println("Sorry, invalid information.");
            planetAPIMenu();

        }

    }
      /*  try{
           planetAPI.addPlanetObject();


        }
    catch (IOException e){

        }

   }
    */

    private void exitApp() {

        System.out.println("Exiting....");
        System.exit(0);
    }

    private void loadAllData()  {
        try{
            planetAPI.load();

        }
        catch (Exception e){

        }

    }

    private void saveAllData() {
        try{
            planetAPI.save();

        }
        catch (Exception e){

        }

    }
    }












    //todo update methods counting methods


    //---------------------
    //  General Menu Items
    //---------------------

//TODO - write all the methods that are called from your menu
    //---------------------
    //  Search/Sort
    //---------------------
// TODO search by different criteria i.e. look at the list methods and give options based on that.
// TODO sort  (and give a list of options - not a recurring menu thou)
    //---------------------
    //  Helper Methods
    //---------------------

//TODO- write any helper methods that are required



