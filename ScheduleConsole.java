import java.util.Arrays;

/**
 * public class ScheduleConsole is the program's view
 * class. It prints the program solution to terminal as
 * well as to a file to be viewed, saved, etc.
 */
public class ScheduleConsole {

    /**
     * public ScheduleConsole constructor takes in
     * ProAvailability to pass final solution to fileParser,
     * FileParser to write solution to a new file, and
     * String arg to know what to name the new file.
     *
     * @param proAvailability
     * @param fileParser
     * @param arg
     */
    public ScheduleConsole(ProAvailability proAvailability, FileParser fileParser, String arg){

        try{
            //print solution to terminal
            System.out.println(Arrays.deepToString
                    (proAvailability.returnHoursToString()));

            //write solution to new file
            fileParser.writeToFile
                    (proAvailability.returnHoursToString(),
                            arg);
        } catch(LineEmptyException e){
        }
    }
}
