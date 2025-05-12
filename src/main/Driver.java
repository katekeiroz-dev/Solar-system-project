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

    //TODO - load all data once the serializers are set up
    runMainMenu();
}
//TODO - construct menus

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




        //TODO write menu that user will see
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
                PlanetObjectMenu planetObjectMenu = new PlanetObjectMenu(); //calling the constructor
                planetObjectMenu.displayMenu(); //calling the method
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
        //TODO - write code to call appropiate method based on value in option
        exitApp();
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

