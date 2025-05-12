package main;

import utils.ScannerInput;

public class PlanetObjectMenu {

    public PlanetObjectMenu(){ //class constructor



    }


    private int requestUserData() {
        return ScannerInput.readNextInt("==>> ");
    }

    public void displayMenu(){
        while(true){
            System.out.println("--------PLanet Object Menu ------- ");
            System.out.println("|  1) Add a planet object \t \t |");
            System.out.println("|  2) Delete a planet object \t |");
            System.out.println("|  3) List all Planet object \t |");
            System.out.println("|  4) Update planet object \t\t |");
            System.out.println("|  0) Return to main menu \t\t |");
            System.out.println("| ------------------------------ |");

            int returnUserData = requestUserData();
            // why did i do it?
            //every case has its own break , so in case 0 you'd have to go back to the driver class.
            //we are already in an infinite loop so in order to exit from it we'd have to also use break.
            //the solution that I first thought would be to put a break after all swtich cases, but that would cause an exit from them( cases).
            //so I realised that case 0 is different from others(the others involve CRUD operations).Thus why I decided to
            //separate from the others.
            if (returnUserData == 0){
                break;
            }

            switch (returnUserData) {
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

                default:
                    System.out.println("Invalid option. Please choose one of the following options:(1, 2, 3, 4, 0).");
                    break;
            }

        }
    }


}
