package main;

import controllers.PlanetSystemAPI;

import models.GasPlanet;
import models.IcePlanet;
import models.Planet;
import utils.ScannerInput;
import utils.Utilities;


import java.io.File;
import java.io.IOException;

public class Driver {


    private PlanetSystemAPI planetAPI;

//The entry point of your app.
//Creates a new Driver object and calls its start() method.
    public static void main(String[] args) throws Exception {
        new Driver().start();
    }
    public void start() {
        planetAPI = new PlanetSystemAPI(); //Initializes the planetAPI

    //load all data once the serializers are set up
   // Calls runMainMenu() to show the main menu to the user.
    runMainMenu();
    }




//construct menus
    // Displays the main menu options to the user
    //Asks the user to choose an option and returns their choice.
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

    //Calls mainMenu() to get user's choice
    // Uses a switch statement to perform the right action
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
                runReportsMenu();
                runMainMenu();
                break;
                // search planet case
            case 3:
                System.out.println("How would like to search?: ");
                System.out.println("1. Total number of planet bodies");
                System.out.println("2. Number of gas planets");
                System.out.println("3. Number of ice planets");

                int countChoice = ScannerInput.readNextInt("Enter your choice (1-3): ");

                int countResult;
                switch (countChoice) {
                    case 1:
                        countResult = planetAPI.numberOfPlanetBodies();
                        System.out.println("Total number of planet bodies: " + countResult);
                        break;
                    case 2:
                        countResult = planetAPI.numberOfGasPlanets();
                        System.out.println("Number of gas planets: " + countResult);
                        break;
                    case 3:
                        countResult = planetAPI.numberOfIcePlanets();
                        System.out.println("Number of ice planets: " + countResult);
                        break;
                    default:
                        System.out.println("Invalid choice. Please select 1, 2, or 3.");
                        break;
                }

                runMainMenu();
                break;
            case 4:
                // Ask the user for a minimum diameter
                double minDiameter = ScannerInput.readNextDouble("Enter minimum diameter to filter and sort: ");

                // Sort the full list first
                planetAPI.sortByDiameterAscending();

                // Filter and display only those that match the criteria
                StringBuilder filtered = new StringBuilder();
                for (Planet p : planetAPI.getPlanetList()) {
                    if (p.getDiameter() >= minDiameter) {
                        filtered.append(p).append("\n");
                    }
                }

                // Show results
                System.out.println(filtered.isEmpty() ? "No planets found with diameter >= " + minDiameter : filtered.toString());

                runMainMenu();
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

    //Handles the logic based on user input from planetAPIMenu()
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
                deletePlanet();
                break;
            // list all planets
            case 3:
                listAllPlanetBodies();
                break;
            //update planet
            case 4:
                System.out.println("Which type of planet do you want to update?");
                System.out.println("1. Ice Planet");
                System.out.println("2. Gas Planet");
                int updateChoice = ScannerInput.readNextInt("Enter your choice (1 or 2): ");

                int idToUpdate = ScannerInput.readNextInt("Enter the ID of the planet to update: ");

                switch (updateChoice) {
                    case 1:
                        // Gather updated details for IcePlanet
                        String iceName = ScannerInput.readNextLine("Enter new name: ");
                        double iceMass = ScannerInput.readNextDouble("Enter new mass: ");
                        double iceDiameter = ScannerInput.readNextDouble("Enter new diameter: ");
                        double iceTemp = ScannerInput.readNextDouble("Enter new average temperature: ");
                        String iceSurfaceType = ScannerInput.readNextLine("Enter new surface type: ");

                        char iceWaterInput = ScannerInput.readNextChar("Does it have liquid water? (Y/N): ");
                        boolean iceHasWater = Utilities.YNtoBoolean(iceWaterInput);
                        String iceComposition = ScannerInput.readNextLine("Enter ice composition: ");

                        IcePlanet updatedIce = new IcePlanet(
                                iceName,
                                iceMass,
                                iceDiameter,
                                iceTemp,
                                iceSurfaceType,
                                iceHasWater,
                                iceComposition
                        );
                        updatedIce.setId(idToUpdate);  // Preserve the ID
                        Planet updatedIceResult = planetAPI.updateIcePlanet(idToUpdate, updatedIce);

                        if (updatedIceResult != null) {
                            System.out.println(" Ice planet updated successfully:");
                            System.out.println(updatedIceResult);
                        } else {
                            System.out.println(" No Ice Planet found with ID " + idToUpdate);
                        }
                        break;

                    case 2:
                        // Gather updated details for GasPlanet
                        String gasName = ScannerInput.readNextLine("Enter new name: ");
                        double gasMass = ScannerInput.readNextDouble("Enter new mass: ");
                        double gasDiameter = ScannerInput.readNextDouble("Enter new diameter: ");
                        double gasTemp = ScannerInput.readNextDouble("Enter new average temperature: ");
                        String gasSurfaceType = ScannerInput.readNextLine("Enter new surface type: ");
                        char gasHasWater = ScannerInput.readNextChar("Does it have liquid water? (Y/N): ");
                        boolean gw = Utilities.YNtoBoolean(gasHasWater);
                        String gasComposition = ScannerInput.readNextLine("Enter gas composition: ");
                        String coreComposition = ScannerInput.readNextLine("Enter core composition: ");
                        double radiationLevel = ScannerInput.readNextDouble("Enter radiation level: ");

                        GasPlanet updatedGas = new GasPlanet(
                                gasName,
                                gasMass,
                                gasDiameter,
                                gasTemp,
                                gasSurfaceType,
                                gw,
                                gasComposition,
                                coreComposition,
                                radiationLevel
                        );
                        updatedGas.setId(idToUpdate);  // Preserve the ID
                        Planet updatedGasResult = planetAPI.updateGasPlanet(idToUpdate, updatedGas);

                        if (updatedGasResult != null) {
                            System.out.println(" Gas planet updated successfully:");
                            System.out.println(updatedGasResult);
                        } else {
                            System.out.println("⚠ No Gas Planet found with ID " + idToUpdate);
                        }
                        break;

                    default:
                        System.out.println("Invalid choice. Please select 1 or 2.");
                        break;
                }

                runPlanetAPIMenu();
                break;


            default:
                System.out.println("Invalid option. Please choose one of the following options:(1, 2, 3, 4, 0).");
                runPlanetAPIMenu();
                break;
        }

    }

//Shows a submenu for planet-related actions (Add, Delete, List, Update).
//Returns user's choice.
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


    //Collects details from the user to create a new planet
    //Asks if the planet is an "ICE" or "GAS" type
    //If "ICE": Creates an IcePlanet and adds it via planetAPI
    //If "GAS": Creates a GasPlanet and adds it via planetAPI
    private void addPlanet() {

        String name =  ScannerInput.readNextLine("What is the name of the planet? : ");
        String surfaceType =  ScannerInput.readNextLine("What is the surface type of the planet? : ");
        double diameter =  ScannerInput.readNextDouble("Inform the diameter of the planet? : ");
        char hasLiquidWater =  ScannerInput.readNextChar("Does it have liquid Water?(Type Y for yes or N of No) : ");
        double mass =  ScannerInput.readNextDouble(" Inform the mass of the planet? : ");
        double avgTemp =  ScannerInput.readNextDouble(" What is the average temperature of the planet? : ");


        boolean hw = Utilities.YNtoBoolean(hasLiquidWater);//to convert "Y/N" to true/false
        String gasOrIce =  ScannerInput.readNextLine("Is it part of the Ice or Gas planets? : ").toUpperCase();

        if(!Utilities.isValidSurfaceType(surfaceType)) surfaceType = null;

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
            System.out.println(" Ice Planet added successfully! ");

        }
        else if (gasOrIce.equals("GAS")) {
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
            System.out.println("Gas Planet added successfully! ");


        }
        else {
            System.out.println("Sorry, invalid information.");


        }
        goBackToMainOrCrudMenu();

    }


    private void deletePlanet(){
        System.out.println("Please inform planet name :  ");
        String name = ScannerInput.readNextLine("==>: ");
        Planet p = planetAPI.getPlanetByName(name);
        if (p!= null){
            Planet p2 = planetAPI.deletePlanetId(p.getId());
        }

    }


    private void goBackToMainOrCrudMenu(){
        System.out.println("Please choose an option of the menu you would like to return : \n1) Main Menu \n2)Planets CRUD Menu");
        int option = ScannerInput.readNextInt("===>");
        if (option == 1){
            runMainMenu();

        } else if (option == 2) {
            runPlanetAPIMenu();

        }else {
            System.out.println("Invalid option. Please choose one of the following options:(1, 2).");
            goBackToMainOrCrudMenu();

        }
    }


    private void exitApp() {

        System.out.println("Exiting....");
        System.exit(0);
    }

    private void loadAllData()  {
        try{
            planetAPI.load();

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    private void saveAllData() {
        try{
            planetAPI.save();

        }
        catch (Exception e){
            e.printStackTrace();

        }

    }


    private int reportsMenu(){
        System.out.println("----------Reports  Menu ------- ");
        System.out.println("|  1) Planets Overview  \t \t |");
        System.out.println("|  0) Return to main menu \t\t |");
        System.out.println("| ------------------------------ |");

        return ScannerInput.readNextInt("==>> ");
    }




    public void runReportsMenu() {
        int option = reportsMenu();
        switch (option) {
                case 0:
                   runMainMenu();
                    break;
                case 1:

                    runPlanetReportsMenu();
                    break;

                default:
                    System.out.println("Invalid number. Please choose one of the following options: (1, 0).");
                    runReportsMenu();
                    break;
        }
    }

    private void runPlanetReportsMenu() {
        int option = planetReportsMenu();
        switch (option){
            case 0:
                runReportsMenu();
                break;
            case 1:
                double minimumDiameter = ScannerInput.readNextDouble("Enter minimum diameter:  ");
                listAllPlanetSmallerThan(minimumDiameter);
                break;
            case 2:
                //  ask the user's input
                double minimumMass = ScannerInput.readNextDouble("Enter minimum mass:  ");
                listAllPlanetHeavierThan(minimumMass);
                break;
            case 3:
                listAllIcePlanets();
                break;
            case 4:
                listAllGasPlanets();
                break;
            case 5:
                listAllPlanetBodies();
                break;

            default:
                System.out.println("Invalid number. Please choose one of the following options: (1, 2, 3, 4, 5, 0).");
                runPlanetReportsMenu();
                break;
        }


    }


    private int planetReportsMenu() {
        System.out.println("------ Planet Reports Submenu -------");
        System.out.println("| 1) List all planets smaller than X |");
        System.out.println("| 2) List all planets heavier than X |");
        System.out.println("| 3) List all ICE planets            |");
        System.out.println("| 4) List all GAS planets            |");
        System.out.println("| 5) List all planets                |");
        System.out.println("| 0) Back to Reports Menu            |");
        System.out.println("-------------------------------------");

        return ScannerInput.readNextInt("==>> ");
    }

    public void listAllPlanetSmallerThan(double d) {
        System.out.println(planetAPI.listAllPlanetObjectsSmallerThan(d));
    }


    public void listAllPlanetHeavierThan(double m) {

        System.out.println(planetAPI.listAllPlanetObjectsHeavierThan(m));

    }

    public void listAllIcePlanets(){
        System.out.println(planetAPI.listAllIcePlanets());
    }

    public void listAllGasPlanets(){
        System.out.println(planetAPI.listAllGasPlanets());
    }

    public void listAllPlanetBodies(){
        System.out.println(planetAPI.listAllPlanetBodies());
    }

}



    //---------------------
    //  Search/Sort
    //---------------------
// TODO search by different criteria i.e. look at the list methods and give options based on that.
// TODO sort  (and give a list of options - not a recurring menu thou)
    //---------------------
    //  Helper Methods
    //---------------------

//TODO- write any helper methods that are required



