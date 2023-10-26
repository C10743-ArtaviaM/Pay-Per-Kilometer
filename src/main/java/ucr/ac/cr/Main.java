package ucr.ac.cr;

import java.util.Scanner;

/**
 * Main Class of the program.
 */
public class Main {

    /**
     * Main Method of the program.
     * 
     * @param args Arguments of the program.
     */
    public static void main(String[] args) {
        System.out.println("Loading App, please wait . . . ");

        // Boolean Type Variables.
        boolean validInitialization = false;

        // Classes Type Variables.
        Scanner input = new Scanner(System.in);

        // Int Type Variables.
        int userOption = 0;

        // String Type Variables.
        String userOptionString = "";

        /*
         * While cicle that let the user input the program between the valid options.
         */
        while (validInitialization == false) {
            System.out.print("How did you wants to open the app?\n1) Console\n2) Simple GUI\n-> ");
            try {
                userOptionString = input.nextLine();
                userOption = Integer.parseInt(userOptionString);

                if (userOption != 1 && userOption != 2) {
                    System.out.println("ERROR: The option " + userOptionString
                            + " is not part of the initialization options. Please select a initialization option.");
                } else {
                    validInitialization = true;
                }
            } catch (Exception invalidInt) {
                System.out.println("ERROR: You can't convert " + userOptionString
                        + " to an Int Value. Please select a initialization option.");
            }
        }

        if (userOption == 1) {
            System.out.println("Loading Console, please wait . . . ");
            run(1);
        } else if (userOption == 2) {
            System.out.println("Loading GUI, please wait . . . ");
            run(2);
        }

        System.out.println("Closing the app . . .");
        input.close();
    }

    /**
     * Method that runs the program.
     */
    public static void run(int mode) {
        // Double[] Type Variables.
        double[] latitudes = { 9.9981, 40.6896, -12.0240, 49.0097 };
        double[] longitudes = { -84.2040, -74.1744, -77.1119, 2.5479 };

        // String[] Type Variables.
        String[] iD = { "SJO", "EWR", "LAP", "CDG" };

        if (mode == 1) {
            Airline airline = new Airline();
            airline.menu(iD, latitudes, longitudes);
        } else {
            AirlineGUI airline = new AirlineGUI();
            airline.menu(iD, latitudes, longitudes);
        }
    }
}
