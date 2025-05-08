package main;

import utils.ScannerInput;

public class PlanetObjectMenu {

    public PlanetObjectMenu(){

        System.out.println("--------PLanet Object Menu ---------- ");
        System.out.println("|  1) Add a planet object \t |");
        System.out.println("|  2) Delete a planet object \t\t\t |");
        System.out.println("|  3) List all Planet object \t\t\t |");
        System.out.println("|  4) Update planet object \t\t\t |");
        System.out.println("|  0) Return to main menu \t\t\t |");
        System.out.println("| -------------------------- |");

        int returnUserData = requestUserData();
        switch (returnUserData) {
            //exit case
            case 0:
                System.out.println("User pressed 0");
                break;
            //planet CRUD case
            case 1:
                PlanetObjectMenu planetObjectMenu = new PlanetObjectMenu();
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
        }

    }


    private int requestUserData() {
        return ScannerInput.readNextInt("==>> ");
    }



}
