import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * ProAvailability class instantiates an Array of type Input.
 * Input instantiates a pair of Strings for startTime and endTime //TODO: Or will it have ints?
 */
public class ProAvailability {

    //private arrayOfArrays instance variable declared
    private Integer[][] timeIntervals;

    /**
     * ProAvailability class constructor
     * <p>
     * Takes in fileParser to turn sort array TODO: Is it only an array here?
     *
     * @param fileParser
     */
    public ProAvailability(FileParser fileParser){
        this.timeIntervals = fileParser.getArrayOfArrays();
        try{
            this.returnHoursToString();
        } catch(LineEmptyException e){

        }

    }

    /**
     * createBusinessHours method takes in our 2D-Array of
     * integers —timeIntervals— and merges them based on
     * overlaps. First, it sorts the arrays, then it merges them
     *
     * @return
     */
    public Integer[][] createBusinessHours() throws LineEmptyException{

        //if an interval of length 0 is inputted, throw an exception
        if(this.timeIntervals.length < 1){
            throw new LineEmptyException
                    ("LineEmptyException has occurred. " +
                            "Please input a .txt file without empty lines");
        }

        //sorts each array based on first element: Puts smaller first elements first
        Arrays.sort(this.timeIntervals, (array1,array2) -> array1[0] - array2[0]); //TODO: How does this work

        //instantiating ArrayList for mutability (we don't know size of businessHours [][])
        List<Integer[]> businessHourArray = new ArrayList<>();

        //currInterval (e.g. [700, 920] ) a.k.a businessHour
        Integer[] currInterval = this.timeIntervals[0];
        businessHourArray.add(currInterval);

        for(Integer[] nextInterval: this.timeIntervals){
            /*index into each timeInterval array and set the currStartTime equal to the 1st
              index of the first array, and currEndTime equal to the 2nd index of the first array*/
            Integer currStartTime = currInterval[0];
            Integer currEndTime = currInterval[1];

            //if we have [700,920],[800,1300], nextStartTime will equal 800
            Integer nextStartTime = nextInterval[0]; //TODO: Won't this get [700] at first. Maybe only at first
            Integer nextEndTime = nextInterval[1];

            //if 920 > 800
            if(currEndTime >= nextStartTime){
                //set the currIntervals endTime to be the max of it and nextEndTime
                currInterval[1] = Math.max(currEndTime, nextEndTime);
                //changes currInterval within businessHourArray
            }
            else { //if the intervals don't overlap
                //currInterval updated to nextInterval
                currInterval = nextInterval;

                //and add the non-overlapping interval
                businessHourArray.add(currInterval);
            }
        }

        //return an arrayOfArrays businessHours that mirrors the businessHours ArrayList
        return businessHourArray.toArray(new Integer[businessHourArray.size()][]);
    }

    /**
     * return Array of Arrays of business hours back into
     * the form given via the inputs
     * (e.g. [[700, 1900]] -> [["07:00", "19:00"]]
     */
    public void returnHoursToString() throws LineEmptyException{
        Integer[][] integerBusinessHours = this.createBusinessHours();

        System.out.println(Arrays.deepToString(this.createBusinessHours()));

        //I want to say for every array in Integer[][] convert it to the proper form

        this.makeString(integerBusinessHours[0][0]);



//        String integerBusinessHours.toString();



//        for(Integer[] array: integerBusinessHours){
//            array[0]
//        }

    }

    /**
     * makeString takes in an integer and returns it
     * in the proper string format for time
     */
    public void makeString(Integer integer){
        String intString = integer.toString();

        if(intString.length() == 3){
            intString = 0 + intString;
        }
        //turn a colon into a character
        char colon = Character.valueOf(':');

        //add colon to the string at the proper index
        String newIntString = this.addChar(intString, colon, 2);

        System.out.println(newIntString);



//        char[] charArray = intString.toCharArray();
//        char[] newCharArray = new char[4];
//        for(int i = 0; i < 4; i++){
//            char character = charArray[i];
//            if(i == 1){
//                character = character + ":";
//            }
//            newCharArray[i] = character;
//        }
//        System.out.println(newCharArray);

        //add semicolon
    }

    /**
     * addChar method takes in a string within which to add the character,
     * the character to add, and the position to add it at.
     * @param string
     * @param character
     * @param position
     * @return
     */
    public String addChar(String string, char character, int position){
        //initialize the strings length
        int length = string.length();

        //create a character array with a space to add the new character
        char[] updatedArray = new char[length + 1];

        /*copy string char from index 0 to position of insertion,
        add copied characters to updatedArray. Copy these characters
        into updatedArray starting at index 0 of updatedArray
         */
        string.getChars(0, position, updatedArray, 0);

        updatedArray[position] = character;

        /*then get string characters from the position onward and copy them into
          the updatedArray, starting at position + 1
         */
        string.getChars(position, length, updatedArray, position + 1);

        return new String(updatedArray);
    }
}