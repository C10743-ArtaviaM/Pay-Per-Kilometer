package ucr.ac.cr;

import java.util.Scanner;

/**
 * Airline class which controls all the related with the calcs.
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
    public String arrayToString(String[] array, String point) {
        String arrayInfo = "";
        if (point.equalsIgnoreCase("layover")) {
            arrayInfo = "Please input the " + point + "between\n";
        } else {
            arrayInfo = "Please, input the " + point + " city between\n";
        }

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

        // While cicle that makes the menu main function.
        while (running) {
            System.out.print("MENU:\n1) Calculate Flight Distance.\n2) Quote Trip\n3) Exit\n-> ");
            userInput = input.nextLine();

            try {
                userInputInt = Integer.parseInt(userInput);
            } catch (Exception invalidInt) {
                System.out.println("ERROR: You can't convert " + userInput
                        + "to an Int Value. Please select a correct menu option.");
            }

            // It ensures that the user input a valid menu option.
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
                        System.out.println(arrayToString(iD, "origin"));
                        userInput = input.nextLine();

                        for (int i = 0; i < iD.length; i++) {
                            if (iD[i].equalsIgnoreCase(userInput)) {
                                origin = i;
                                originFound = true;
                                break;
                            }
                        }

                        if (originFound == false) {
                            System.out.println("You have entered a wrong origin city. Please input a correct option.");
                        }
                    }

                    /*
                     * While cicle that let the user input the destine city between the valid
                     * options.
                     */
                    while (destineFound == false) {
                        System.out.println("Please, input the destine city between");
                        for (int i = 0; i < iD.length; i++) {
                            if (i == 0) {
                                System.out.print("[" + iD[i] + ",");
                            } else if (i == (iD.length - 1)) {
                                System.out.println(" " + iD[i] + "]");
                            } else {
                                System.out.print(" " + iD[i] + ",");
                            }
                        }

                        System.out.print("-> ");
                        userInput = input.nextLine();
                        for (int i = 0; i < iD.length; i++) {
                            if (iD[i].equalsIgnoreCase(userInput)) {
                                destine = i;
                                destineFound = true;
                                break;
                            }
                        }

                        if (destineFound == false) {
                            System.out.println("You have entered a wrong destine city. Please input a correct option.");
                        }
                    }

                    System.out.println("The Flight has a distance of " + mathC.calcDistance(latitudes[origin],
                            longitudes[origin], latitudes[destine], longitudes[destine]) + "KM.");

                    // Trip Quoter
                } else if (userInputInt == 2) {
                    // Boolean Type Variables.
                    boolean correctAmountLayovers = false;
                    boolean correctData = false;
                    boolean correctDestine = false;
                    boolean correctOrigin = false;
                    boolean correctSubscription = false;

                    // Double Type Variables.
                    double totalDistance;

                    // Int Type Variables.
                    int layovers = 0;

                    // String / String[] Type Variables.
                    String subscription = "";
                    String[] route;

                    /*
                     * While cicle that let the user input the subscription type between the valid
                     * options.
                     */
                    while (correctSubscription == false) {
                        System.out.print("Input the subscription type between\n[REG | PREM]\n-> ");
                        userInput = input.nextLine();

                        if (userInput.equalsIgnoreCase("REG") || userInput.equalsIgnoreCase("PREM")) {
                            correctSubscription = true;
                            subscription = userInput;
                        }

                        if (correctSubscription == false) {
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

                        try {
                            userInputInt = Integer.parseInt(userInput);

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
                    while (correctOrigin == false) {
                        System.out.print("Please insert your origin city\n-> ");
                        userInput = input.nextLine();

                        for (int i = 0; i < iD.length; i++) {
                            if (userInput.equalsIgnoreCase(iD[i])) {
                                route[0] = userInput;
                                correctOrigin = true;
                                break;
                            }
                        }

                        if (correctOrigin == false) {
                            System.out.println("You have entered a wrong destine city. Please input a correct option.");
                        }
                    }

                    /*
                     * If condition that checks if the travel has layovers.
                     */
                    if (layovers != 0) {
                        /*
                         * While cicle that let the user input the layovers between the valid options.
                         */
                        while (correctData == false) {
                            int counter = 1;

                            while (counter <= layovers) {
                                System.out.print("Please insert your layover #" + counter + "\n-> ");
                                userInput = input.nextLine();

                                for (int i = 1; i < iD.length; i++) {
                                    if (userInput.equalsIgnoreCase(iD[i])) {
                                        route[counter] = userInput;
                                        correctData = true;
                                        counter++;
                                        break;
                                    }

                                }

                                if (correctData == false) {
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
                    while (correctDestine == false) {
                        System.out.print("Please insert your destine city\n-> ");
                        userInput = input.nextLine();
                        for (int i = 0; i < iD.length; i++) {
                            if (userInput.equalsIgnoreCase(iD[i])) {
                                route[iD.length - 1] = userInput;
                                correctDestine = true;
                                break;
                            }
                        }

                        if (correctDestine == false) {
                            System.out.println("You have entered a wrong destine city. Please input a correct option.");
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
