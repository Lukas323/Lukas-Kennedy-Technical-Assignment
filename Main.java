
/**
 * The Main class for the program.
 */
public class Main {
    /**
     * Main method that sets up the programs' classes
     * and runs the program
     * @param args arguments passed into main
     */
    public static void main(String[] args) {
        if(args.length == 0){
            System.out.println("no args inputted, try again");
            System.exit(0);
        }

        FileParser fileParser = new FileParser(args[0]);
        new ProAvailability(fileParser);
        /* println that prints to terminal is in ProAvailability
          returnHoursToString() method before the return */
    }
}
