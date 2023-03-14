
/**
 * The Main class for the program.
 */
public class Main {
    /**
     * Main method that sets up the REPL and runs it.
     * @param args arguments passed into main
     */
    public static void main(String[] args) {
        if(args.length == 0){
            System.out.println("no args inputted, try again");
            System.exit(0);
        }

//        System.out.println(Integer.parseInt("07"));
        //TODO: Optimize this so they can add however many files that they want
        FileParser fileParser = new FileParser(args[0]);
        ProAvailability proAvailability = new ProAvailability(fileParser);


    }
}
