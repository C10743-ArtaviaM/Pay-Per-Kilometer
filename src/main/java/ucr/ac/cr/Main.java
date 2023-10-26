package ucr.ac.cr;

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

        System.out.print("How did you wants to open the app?\n1) Console\n2) Simple GUI\n-> ");

        int userOption = 2;
        if (userOption == 1) {
            System.out.println("Loading Console, please wait . . . ");
            run(1);
        } else if (userOption == 2) {
            System.out.println("Loading GUI, please wait . . . ");
            run(2);
        }

        System.out.println("Closing the app . . .");
    }

    /**
     * Method that runs the program.
     */
    public static void run(int mode) {
        String[] iD = { "SJO", "EWR", "LAP", "CDG" };
        double[] latitudes = { 9.9981, 40.6896, -12.0240, 49.0097 };
        double[] longitudes = { -84.2040, -74.1744, -77.1119, 2.5479 };
        if (mode == 1) {
            Airline airline = new Airline();
            airline.menu(iD, latitudes, longitudes);
        } else {
            AirlineGUI airline = new AirlineGUI();
            airline.menu(iD, latitudes, longitudes);
        }
    }
}
