import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ProAvailability class associates with a fileParser
 * to sort the availability hours inputted and return
 * them in their proper format (e.g. [["07:00", "09:20"]] )
 */
public class ProAvailability {

    //private arrayOfArrays instance variable declared
    private Integer[][] timeIntervals;

    /**
     * ProAvailability class constructor
     *
     * Takes in fileParser to get 2D-Array of integers
     * (time periods) pulled from the inputted file
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
        Arrays.sort(this.timeIntervals, (array1,array2) -> array1[0] - array2[0]);

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
            Integer nextStartTime = nextInterval[0];
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
    public String[][] returnHoursToString() throws LineEmptyException{
        //instantiate integerBusinessHours with 2D-Array of BusinessHours in integer form
        Integer[][] integerBusinessHours = this.createBusinessHours();

        //instantiate timeArrayList to hold the integers turned to Strings
        List<String> timeArrayList = new ArrayList<>();

        //turn hours into strings and adds them to timeArrayList for storage
        for(int i = 0; i < integerBusinessHours.length; i++){
            String intStringStart = this.makeString(integerBusinessHours[i][0]);
            timeArrayList.add(intStringStart);

            String intStringEnd = this.makeString(integerBusinessHours[i][1]);
            timeArrayList.add(intStringEnd);
        }

        /*if timeArrayList has 6 slots, finalOutput should have 3 slots,
          each containing an array with 2 slots */
        String[][] finalOutput = new String[timeArrayList.size()/2][2];

        //Regroup Strings into their respective start time and end time
        int j = 0;
        for(int i = 0; i < timeArrayList.size(); i++) { //i from 0 to 5

            int k = i / 2; //k goes 0,0,1,1,2,2 because java rounds fractions down
            finalOutput[k][j] = timeArrayList.get(i);

            j = (j + 1) % 2; //1 % 2 = 1. j alternates 1, 0, 1, 0, 1

        }

        System.out.println(Arrays.deepToString(finalOutput));
        return finalOutput;
    }

    /**
     * makeString takes in an integer and returns it
     * in the proper string format for time
     */
    public String makeString(Integer integer){
        String intString = integer.toString();

        if(intString.length() == 3){
            intString = 0 + intString;
        }
        //turn a colon into a character
        char colon = Character.valueOf(':');
        char quotationMark = Character.valueOf('"');

        //add colon to the string at the proper index
        String newIntString = this.addChar(intString, colon, 2);

        String newerIntString = this.addChar(newIntString, quotationMark, 0);

        //add quotation marks to string
        String finalIntString = "";
        for(int i = 0; i<7; i = i + 6){
            finalIntString = this.addChar(newerIntString, quotationMark, 6);
        }
        return finalIntString;
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