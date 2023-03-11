import javafx.util.Pair;

/**
 * ProAvailability class instantiates an Array of type Input.
 * Input instantiates a pair of Strings for startTime and endTime //TODO: Or will it have ints?
 */
public class ProAvailability {

    //private Array of inputs
    private Input[] inputsArrayed;

    /**
     * ProAvailability class constructor
     *
     * Takes in an Array of args to populate
     * array of inputs (e.g. [("07:00", "11:00"),("13:00","19:00"), etc)
     *
     * @param args
     */
    public ProAvailability(String[] args){

        //for every pair of arguments
        for(int i = 0; i < args.length/2; i = i + 2){

            //instantiate a new Input with startTime = args[even] and endTime = args[odd]
            Input input = new Input(args[i], args[i+1]);

            //add new Input to array of inputs
            this.inputsArrayed[i] = input;
        }
    }
}