package ucr.ac.cr;

import java.util.Scanner;

/**
 * Airline class which controls the program via console / terminal.
 */
public class Airline {
    // Class Type Variables.
    MathCalcs mathC = new MathCalcs();

    /**
     * Method used to print in a format way the IDs array.
     * 
     * @param array IDs array.
     * @param point Point of the travel.
     * @return A string consisting in the array IDs.
     */
    public String arrayToString(String[] array, String point, String type) {
        String arrayInfo = "";

        // If condition that checks if the point is a layover or not.
        if (type.equalsIgnoreCase("layover")) {
            arrayInfo = "Please input the " + point + "between\n";
        } else {
            arrayInfo = "Please, input the " + point + " city between\n";
        }

        /*
         * For cicle that makes the string with all the IDs array info.
         */
        for (int i = 0; i < array.length; i++) {
            if (i == 0) {
                arrayInfo = arrayInfo + "[" + array[i] + ",";
            } else if (i == (array.length - 1)) {
                arrayInfo = arrayInfo + " " + array[i] + "]";
            } else {
                arrayInfo = arrayInfo + " " + array[i] + ",";
            }
        }

        arrayInfo = arrayInfo + "\n-> ";
        return arrayInfo;
    }

    /**
     * Main menu of the program.
     * 
     * @param iD         Array that contains the IDs of the cities.
     * @param latitudes  Array that contains the latitudes of the cities.
     * @param longitudes Array that contains the longitudes of the cities.
     */
    public void menu(String[] iD, double[] latitudes, double[] longitudes) {
        // Boolean Type Variables.
        boolean running = true;

        // Int Type Variables.
        int userInputInt = 0;

        // Class Type Variables.
        Scanner input = new Scanner(System.in);

        // String Type Variables.
        String userInput = "";

        System.out.println("Welcome to the System of your new airline, PPK.");

        /*
         * While cicle that makes the menu main function.
         */
        while (running) {
            System.out.print("MENU:\n1) Calculate Flight Distance.\n2) Quote Trip\n3) Exit\n-> ");
            userInput = input.nextLine();

            /*
             * Try / Catch that ensures that the user put a correct input, avoiding that the
             * program falls if not.
             */
            try {
                userInputInt = Integer.parseInt(userInput);
            } catch (Exception invalidInt) {
                System.out.println("ERROR: You can't convert " + userInput
                        + "to an Int Value. Please select a correct menu option.");
            }

            // If condition that ensures that the user input a valid menu option.
            if (userInputInt >= 1 && userInputInt <= 3) {

                // Flight distance calculator
                if (userInputInt == 1) {
                    // Boolean Type Variables/
                    boolean destineFound = false;
                    boolean originFound = false;

                    // Int Type Variables.
                    int destine = 0;
                    int origin = 0;

                    /*
                     * While cicle that let the user input the origin city between the valid
                     * options.
                     */
                    while (originFound == false) {
                        System.out.print(arrayToString(iD, "origin", "origin"));
                        input.nextLine();

                        /*
                         * For cicle that compares the ID input with the IDs in the array.
                         */
                        for (int i = 0; i < iD.length; i++) {
                            if (iD[i].equalsIgnoreCase(userInput)) {
                                origin = i;
                                originFound = true;
                                break;
                            }
                        }

                        // If condition that checks if the origin input by the user exist, if not, sends
                        // an error message.
                        if (originFound == false) {
                            System.out.println(
                                    "ERROR: You have entered a wrong origin city. Please input a correct option.");
                        }
                    }

                    /*
                     * While cicle that let the user input the destine city between the valid
                     * options.
                     */
                    while (destineFound == false) {
                        System.out.print(arrayToString(iD, "destine", "destine"));
                        input.nextLine();

                        /*
                         * For cicle that compares the ID input with the IDs in the array.
                         */
                        for (int i = 0; i < iD.length; i++) {
                            if (iD[i].equalsIgnoreCase(userInput)) {
                                destine = i;
                                destineFound = true;
                                break;
                            }
                        }

                        // If condition that checks if the destine input by the user exist, if not,
                        // sends an error message.
                        if (destineFound == false) {
                            System.out.println(
                                    "ERROR: You have entered a wrong origin city. Please input a correct option.");
                        }
                    }

                    // Prints the flight info to the user.
                    System.out.println("The Flight has a distance of " + mathC.calcDistance(latitudes[origin],
                            longitudes[origin], latitudes[destine], longitudes[destine]) + "KM.");

                    // Trip Quoter
                } else if (userInputInt == 2) {
                    // Boolean Type Variables.
                    boolean correctAmountLayovers = false;
                    boolean destineFound = false;
                    boolean layoversFound = false;
                    boolean originFound = false;
                    boolean subscriptionFound = false;

                    // Int Type Variables.
                    int layovers = 0;
                    int totalDistance = 0;

                    // String / String[] Type Variables.
                    String subscription = "";
                    String[] route;

                    /*
                     * While cicle that let the user input the subscription type between the valid
                     * options.
                     */
                    while (subscriptionFound == false) {
                        System.out.print("Input the subscription type between\n[REG | PREM]\n-> ");
                        userInput = input.nextLine();

                        // If condition that checks if the user inputs a correct subscription type.
                        if (userInput.equalsIgnoreCase("REG") || userInput.equalsIgnoreCase("PREM")) {
                            subscriptionFound = true;
                            subscription = userInput;
                        }

                        // If condition that checks if the subscription input by the user exist, if not,
                        // sends an error message.
                        if (subscriptionFound == false) {
                            System.out.println(
                                    "You have entered a wrong subscription type. Please input a correct option.");
                        }
                    }

                    /*
                     * While cicle that let the user input the amount of layovers.
                     */
                    while (correctAmountLayovers == false) {
                        System.out.print("Please insert the amount of layovers\n-> ");
                        userInput = input.nextLine();

                        /*
                         * Try / Catch that ensures that the user put a correct input, avoiding that the
                         * program falls if not.
                         */
                        try {
                            userInputInt = Integer.parseInt(userInput);

                            // If conditions that checks if the amount of layovers isn't a negative option.
                            if (userInputInt < 0) {
                                System.out.println("ERROR: The option " + userInput
                                        + " is negative and you can't have negative layovers. Please select a correct amount of layovers.");
                            } else {
                                layovers = userInputInt;
                                correctAmountLayovers = true;
                            }
                        } catch (Exception invalidInt) {
                            System.out.println("ERROR: You can't convert " + userInput
                                    + " to an Int Value. Please select a correct amount of layovers.");
                        }
                    }

                    /*
                     * Creation of the route array depending on the layovers amount.
                     */
                    route = new String[layovers + 2];

                    /*
                     * While cicle that let the user input the origin city between the valid
                     * options.
                     */
                    while (originFound == false) {
                        System.out.print(arrayToString(iD, "origin", "origin"));
                        input.nextLine();

                        /*
                         * For cicle that compares the ID input with the IDs in the array.
                         */
                        for (int i = 0; i < iD.length; i++) {
                            if (iD[i].equalsIgnoreCase(userInput)) {
                                route[0] = userInput;
                                originFound = true;
                                break;
                            }
                        }

                        // If condition that checks if the origin input by the user exist, if not, sends
                        // an error message.
                        if (originFound == false) {
                            System.out.println(
                                    "ERROR: You have entered a wrong origin city. Please input a correct option.");
                        }
                    }

                    // If condition that checks if the travel has layovers.
                    if (layovers != 0) {
                        /*
                         * While cicle that let the user input the layovers between the valid options.
                         */
                        while (layoversFound == false) {
                            int counter = 1;

                            /*
                             * While cicle that let the user to input the layovers.
                             */
                            while (counter <= layovers) {
                                String layover = "layover #" + counter;
                                System.out.print(arrayToString(iD, layover, "layover"));
                                userInput = input.nextLine();

                                /*
                                 * For cicle that compares the ID input with the IDs in the array.
                                 */
                                for (int i = 1; i < iD.length; i++) {
                                    if (iD[i].equalsIgnoreCase(userInput)) {
                                        route[counter] = userInput;
                                        layoversFound = true;
                                        counter++;
                                        break;
                                    }

                                }

                                // If condition that checks if the layovers input by the user exist, if not,
                                // sends an error message.
                                if (layoversFound == false) {
                                    System.out.println(
                                            "You have entered an incorrect city, please enter all the layovers again");
                                }
                            }
                        }
                    }

                    /*
                     * While cicle that let the user input the destine city between the valid
                     * options.
                     */
                    while (destineFound == false) {
                        System.out.print(arrayToString(iD, "destine", "destine"));
                        input.nextLine();

                        /*
                         * For cicle that compares the ID input with the IDs in the array.
                         */
                        for (int i = 0; i < iD.length; i++) {
                            if (iD[i].equalsIgnoreCase(userInput)) {
                                route[iD.length - 1] = userInput;
                                destineFound = true;
                                break;
                            }
                        }

                        // If condition that checks if the destine input by the user exist, if not,
                        // sends an error message.
                        if (destineFound == false) {
                            System.out.println(
                                    "ERROR: You have entered a wrong origin city. Please input a correct option.");
                        }
                    }

                    /*
                     * Calculates the total distance of the travel and generates the cost of it.
                     */
                    totalDistance = mathC.calcTotalDistance(iD, latitudes, longitudes, route);
                    System.out.println(
                            "The flight will have a cost of " + mathC.travelPrice(totalDistance, subscription));
                    // Close
                } else {
                    running = false;
                }
            } else {
                System.out.println("ERROR: The option " + userInput
                        + "is not part of the menu. Please select a correct menu option.");
            }
        }
        /*
         * Close of the scanner.
         */
        input.close();
    }
}
