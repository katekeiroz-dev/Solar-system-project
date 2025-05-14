package main;

import controllers.PlanetSystemAPI;

import utils.ScannerInput;

import java.io.File;

public class Driver {


    private PlanetSystemAPI planetAPI;


    public static void main(String[] args) throws Exception {
        new Driver().start();
    }
    public void start() {
    //TODO - construct fields

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
                System.out.println("User pressed 10");
                break;
                //load all
            case 11:
                System.out.println("User pressed 11");
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

    private void addPlanet(){

    }


    private void exitApp() {

        System.out.println("Exiting....");
        System.exit(0);
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


}

