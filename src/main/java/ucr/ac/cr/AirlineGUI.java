package ucr.ac.cr;

import javax.swing.*;;

public class AirlineGUI {
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
                    // Boolean Type Variables.
                    boolean destineFound = false;
                    boolean originFound = false;

                    // Int Type Variables.
                    int destine = 0;
                    int origin = 0;

                    // Double Type Variables.
                    double distance = 0;

                    /*
                     * While cicle that let the user input the origin city between the valid
                     * options.
                     */
                    while (originFound == false) {
                        String arrayInfo = arrayToString(iD, "origin");
                        userInput = JOptionPane.showInputDialog(gui, arrayInfo, "Origin", 1);

                        for (int i = 0; i < iD.length; i++) {
                            if (iD[i].equalsIgnoreCase(userInput)) {
                                origin = i;
                                originFound = true;
                                break;
                            }
                        }

                        if (originFound == false) {
                            JOptionPane.showMessageDialog(gui,
                                    "You have entered a wrong origin city. Please input a correct option.", "ERROR",
                                    JOptionPane.WARNING_MESSAGE);
                        }
                    }

                    /*
                     * While cicle that let the user input the destine city between the valid
                     * options.
                     */
                    while (destineFound == false) {
                        String arrayInfo = arrayToString(iD, "destine");
                        userInput = JOptionPane.showInputDialog(gui, arrayInfo, "Destine", 1);

                        for (int i = 0; i < iD.length; i++) {
                            if (iD[i].equalsIgnoreCase(userInput)) {
                                destine = i;
                                destineFound = true;
                                break;
                            }
                        }
                        if (originFound == false) {
                            JOptionPane.showMessageDialog(gui,
                                    "You have entered a wrong destine city. Please input a correct option.", "ERROR",
                                    JOptionPane.WARNING_MESSAGE);
                        }
                    }

                    distance = mathC.calcDistance(latitudes[origin], longitudes[origin], latitudes[destine],
                            longitudes[destine]);

                    JOptionPane.showMessageDialog(gui, "The Flight has a distance of " + distance + "KM.");

                    // Trip Quoter
                } else if (userInputInt == 2) {
                    // Boolean Type Variables.
                    boolean correctAmountLayovers = false;
                    boolean destineFound = false;
                    boolean layoversFound = false;
                    boolean originFound = false;
                    boolean subscriptionFound = false;

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
                    while (subscriptionFound == false) {
                        userInput = JOptionPane.showInputDialog(gui,
                                "Input the subscription type between [REG | PREM]:", "Subscription", 1);

                        if (userInput.equalsIgnoreCase("REG") || userInput.equalsIgnoreCase("PREM")) {
                            subscriptionFound = true;
                            subscription = userInput;
                        } else {
                            JOptionPane.showMessageDialog(gui,
                                    "You have entered a wrong subscription type. Please input a correct option.",
                                    "ERROR",
                                    JOptionPane.WARNING_MESSAGE);
                        }
                    }

                    /*
                     * While cicle that let the user input the amount of layovers.
                     */
                    while (correctAmountLayovers == false) {
                        userInput = JOptionPane.showInputDialog(gui, "Please insert the amount of layovers:",
                                "Layovers", 1);

                        try {
                            userInputInt = Integer.parseInt(userInput);

                            if (userInputInt < 0) {
                                JOptionPane.showMessageDialog(gui, "The option " + userInput
                                        + " is negative and you can't have negative layovers. Please select a correct amount of layovers.");
                            } else {
                                layovers = userInputInt;
                                correctAmountLayovers = true;
                            }
                        } catch (Exception invalidInt) {
                            JOptionPane.showMessageDialog(gui,
                                    "You can't convert " + userInput
                                            + " to an Int Value. Please select a correct amount of layovers.",
                                    "ERROR",
                                    JOptionPane.WARNING_MESSAGE);
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
                        String arrayInfo = arrayToString(iD, "origin");
                        userInput = JOptionPane.showInputDialog(gui, arrayInfo, "Origin", 1);

                        for (int i = 0; i < iD.length; i++) {
                            if (userInput.equalsIgnoreCase(iD[i])) {
                                route[0] = userInput;
                                originFound = true;
                                break;
                            }
                        }

                        if (originFound == false) {
                            JOptionPane.showMessageDialog(gui,
                                    "You have entered a wrong origin city. Please input a correct option.", "ERROR",
                                    JOptionPane.WARNING_MESSAGE);
                        }
                    }

                    /*
                     * If condition that checks if the travel has layovers.
                     */
                    if (layovers != 0) {
                        /*
                         * While cicle that let the user input the layovers between the valid options.
                         */
                        while (layoversFound == false) {
                            int counter = 1;

                            while (counter <= layovers) {
                                String layover = "layover #" + counter;
                                String arrayInfo = arrayToString(iD, layover);
                                userInput = JOptionPane.showInputDialog(gui, arrayInfo, "Origin", 1);

                                for (int i = 1; i < iD.length; i++) {
                                    if (userInput.equalsIgnoreCase(iD[i])) {
                                        route[counter] = userInput;
                                        layoversFound = true;
                                        counter++;
                                        break;
                                    }

                                }

                                if (layoversFound == false) {
                                    JOptionPane.showMessageDialog(gui,
                                            "You have entered an incorrect city, please enter all the layovers again",
                                            "ERROR",
                                            JOptionPane.WARNING_MESSAGE);
                                }
                            }
                        }
                    }

                    /*
                     * While cicle that let the user input the destine city between the valid
                     * options.
                     */
                    while (destineFound == false) {
                        String arrayInfo = arrayToString(iD, "destine");
                        userInput = JOptionPane.showInputDialog(gui, arrayInfo, "Destine", 1);

                        for (int i = 0; i < iD.length; i++) {
                            if (userInput.equalsIgnoreCase(iD[i])) {
                                route[iD.length - 1] = userInput;
                                destineFound = true;
                                break;
                            }
                        }

                        if (destineFound == false) {
                            JOptionPane.showMessageDialog(gui,
                                    "You have entered a wrong destine city. Please input a correct option.", "ERROR",
                                    JOptionPane.WARNING_MESSAGE);
                        }
                    }

                    /*
                     * Calculates the total distance of the travel and generates the cost of it.
                     */
                    totalDistance = mathC.calcTotalDistance(iD, latitudes, longitudes, route);
                    JOptionPane.showMessageDialog(gui,
                            "The flight will have a cost of $" + mathC.travelPrice(totalDistance, subscription) + ".",
                            "COST INFO",
                            JOptionPane.PLAIN_MESSAGE);
                    // Close
                } else {
                    running = false;
                }
            } else {
                JOptionPane.showMessageDialog(gui,
                        "The option " + userInput + "is not part of the menu. Please select a correct menu option.",
                        "ERROR",
                        JOptionPane.WARNING_MESSAGE);
                System.exit(0);
            }
        }
    }
}
