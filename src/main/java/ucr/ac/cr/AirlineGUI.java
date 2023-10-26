package ucr.ac.cr;

import javax.swing.*;;

public class AirlineGUI {
    public void menu(String[] iD, double[] latitudes, double[] longitudes) {
        // Boolean Type Variables.
        boolean running = true;

        // Int Type Variables.
        int userInputInt = 0;

        // Class Type Variables.
        JFrame gui = new JFrame();

        // String Type Variables.
        String userInput = "";

        JOptionPane.showMessageDialog(gui, "Welcome to the System of your new airline, PPK.");

        // Makes the menu main function.
        while (running) {
            userInput = JOptionPane.showInputDialog(gui, "1) Calculate Flight Distance.\n2) Quote Trip\n3) Exit",
                    "Menu", 1);
            try {
                userInputInt = Integer.parseInt(userInput);
            } catch (Exception invalidInt) {
                JOptionPane.showMessageDialog(gui,
                        "ERROR: You can't convert " + userInput
                                + "to an Int Value. Please select a correct menu option.",
                        "ERROR", JOptionPane.WARNING_MESSAGE);
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
                        String arrayInfo = arrayToString(iD, "origin");
                        userInput = JOptionPane.showInputDialog(gui, arrayInfo,
                                "MENU");
                        for (int i = 0; i < iD.length; i++) {
                            if (iD[i].equalsIgnoreCase(userInput)) {
                                origin = i;
                                originFound = true;
                                break;
                            }
                        }
                        System.out.println("You have entered a wrong origin city. Please input a correct option.");
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
                        // userInput = input.nextLine();
                        for (int i = 0; i < iD.length; i++) {
                            if (iD[i].equalsIgnoreCase(userInput)) {
                                destine = i;
                                destineFound = true;
                                break;
                            }
                        }
                        System.out.println("You have entered a wrong origin city. Please input a correct option.");
                    }

                    // System.out.println("The Flight has a distance of " +
                    // calcDistance(latitudes[origin],
                    // longitudes[origin], latitudes[destine], longitudes[destine]) + "KM.");

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
                        // userInput = input.nextLine();

                        if (userInput.equalsIgnoreCase("REG") || userInput.equalsIgnoreCase("PREM")) {
                            correctSubscription = true;
                            subscription = userInput;
                        }
                    }

                    /*
                     * While cicle that let the user input the amount of layovers.
                     */
                    while (correctAmountLayovers == false) {
                        System.out.print("Please insert the amount of layovers\n-> ");
                        // userInput = input.nextLine();

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
                        // userInput = input.nextLine();

                        for (int i = 0; i < iD.length; i++) {
                            if (userInput.equalsIgnoreCase(iD[i])) {
                                route[0] = userInput;
                                correctOrigin = true;
                                break;
                            }
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
                                // userInput = input.nextLine();

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
                        // userInput = input.nextLine();
                        for (int i = 0; i < iD.length; i++) {
                            if (userInput.equalsIgnoreCase(iD[i])) {
                                route[iD.length - 1] = userInput;
                                correctDestine = true;
                                break;
                            }
                        }
                    }

                    /*
                     * Calculates the total distance of the travel and generates the cost of it.
                     */
                    // totalDistance = calcTotalDistance(iD, latitudes, longitudes, route);
                    // System.out.println("The flight will have a cost of " +
                    // travelPrice(totalDistance, subscription));
                    // Close
                } else {
                    running = false;
                }
            } else {
                System.out.println("ERROR: The option " + userInput
                        + "is not part of the menu. Please select a correct menu option.");
            }
        }
    }

    public String arrayToString(String[] array, String point) {
        String arrayInfo = "Please, input the " + point + " city between\n";

        for (int i = 0; i < array.length; i++) {
            if (i == 0) {
                arrayInfo = arrayInfo + "[" + array[i] + ",";
            } else if (i == (array.length - 1)) {
                arrayInfo = " " + array[i] + "]";
            } else {
                arrayInfo = arrayInfo + " " + array[i] + ",";
            }
        }

        return arrayInfo;
    }
}
