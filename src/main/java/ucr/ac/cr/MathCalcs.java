package ucr.ac.cr;

/**
 * MathCalcs class that have all the calcs needed for the program.
 */
public class MathCalcs {
    /**
     * This method calculates the distance between two points around the world.
     * 
     * @param x1 Latitudes of the origin.
     * @param y1 Longitudes of the origin.
     * @param x2 Latitudes of the destine.
     * @param y2 Longitudes of the destine.
     * @return A double return consisting in the distance.
     */
    public int calcDistance(double x1, double y1, double x2, double y2) {
        // Int Type Variables.
        int distance = 0;

        distance = (int) (Math.round(6371.01
                * Math.acos(Math.sin(Math.toRadians(x1)) * Math.sin(Math.toRadians(x2)) + Math.cos(Math.toRadians(x1))
                        * Math.cos(Math.toRadians(x2)) * Math.cos((Math.toRadians(y1)) - (Math.toRadians(y2))))));

        return distance;
    }

    /**
     * This method calculates the total price of the travel.
     * 
     * @param distance   The distance of the travel.
     * @param clientType The type of the passenger.
     * @return A double return consisting in the price.
     */
    public double travelPrice(int distance, String clientType) {
        // Double Type Variables.
        double price = 0;

        // Int Type Variables.
        int exceedingDistance = 0;

        // If / else condition that calculates the price depending the distance.
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
    public int calcTotalDistance(String[] iD, double[] latitudes, double[] longitudes, String[] route) {
        // Double Type Variables.
        double prevLatitude = 0;
        double prevLongitude = 0;

        // Int Type Variables.
        int totalDistance = 0;

        /*
         * For cicle that calculates de distance between the points.
         */
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
}
