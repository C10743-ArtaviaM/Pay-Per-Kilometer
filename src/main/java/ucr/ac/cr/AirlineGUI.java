package ucr.ac.cr;

import javax.swing.*;;

public class AirlineGUI {
    /**
     * This method calculates the distance between two points around the world.
     * 
     * @param x1 Latitudes of the origin.
     * @param y1 Longitudes of the origin.
     * @param x2 Latitudes of the destine.
     * @param y2 Longitudes of the destine.
     * @return A double return consisting in the distance.
     */
    public double calcDistance(double x1, double y1, double x2, double y2) {
        double distance = 0;

        distance = 6371.01
                * Math.acos(Math.sin(Math.toRadians(x1)) * Math.sin(Math.toRadians(x2)) + Math.cos(Math.toRadians(x1))
                        * Math.cos(Math.toRadians(x2)) * Math.cos((Math.toRadians(y1)) - (Math.toRadians(y2))));
        distance = Math.round(distance);

        return distance;
    }

    /**
     * This method calculates the total price of the travel.
     * 
     * @param distance   The distance of the travel.
     * @param clientType The type of the passenger.
     * @return A double return consisting in the price.
     */
    public double travelPrice(double distance, String clientType) {
        double price = 0;
        double exceedingDistance = 0;

        if (distance >= 1 && distance <= 1000) {
            if (clientType.equalsIgnoreCase("PREM")) {
                price = distance * 0.55;
            } else { // Regular Client
                price = distance * 0.6;
            }
        } else if (distance <= 2000) {
            exceedingDistance = distance - 1000;
            if (clientType.equalsIgnoreCase("PREM")) {
                price = (exceedingDistance * 0.30) + (1000 * 0.55);
            } else { // Regular Client
                price = (exceedingDistance * 0.4) + (1000 * 0.6);
            }
        } else {
            exceedingDistance = distance - 2000;
            if (clientType.equalsIgnoreCase("PREM")) {
                price = (exceedingDistance * 0.25) + (1000 * 0.30) + (1000 * 0.55);
            } else { // Regular Client
                price = (exceedingDistance * 0.3) + (1000 * 0.4) + (1000 * 0.6);
            }
        }
        return price;
    }

    /**
     * This method calculates the total distance of the travel.
     * 
     * @param iD         Array that contains the IDs of the cities.
     * @param latitudes  Array that contains the latitudes of the cities.
     * @param longitudes Array that contains the longitudes of the cities.
     * @param route      Array that contains the route of the passenger.
     * @return A double return consisting in the total distance of the flight.
     */
    public double calcTotalDistance(String[] iD, double[] latitudes, double[] longitudes, String[] route) {
        double totalDistance = 0;
        double prevLatitude = 0;
        double prevLongitude = 0;

        for (int i = 0; i < route.length; i++) {
            for (int j = 0; j < iD.length; j++) {
                if (route[i].equalsIgnoreCase(iD[j])) {
                    if (i != 0) {
                        totalDistance = totalDistance
                                + calcDistance(latitudes[i], longitudes[i], prevLatitude, prevLongitude);

                        /*
                         * The next variables saves the previous city latitudes and used them later to
                         * calculate the distances
                         */
                        prevLatitude = latitudes[i];
                        prevLongitude = longitudes[i];
                    } else {
                        /*
                         * The next variables saves the previous city latitudes and used them later to
                         * calculate the distances
                         */
                        prevLatitude = latitudes[i];
                        prevLongitude = longitudes[i];
                    }
                }
            }
        }
        return totalDistance;
    }

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

                    distance = calcDistance(latitudes[origin], longitudes[origin], latitudes[destine],
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
                    totalDistance = calcTotalDistance(iD, latitudes, longitudes, route);
                    JOptionPane.showMessageDialog(gui,
                            "The flight will have a cost of $" + travelPrice(totalDistance, subscription) + ".",
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
            }
        }
    }
}
