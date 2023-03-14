import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    public ProAvailability(FileParser fileParser) {
        this.timeIntervals = fileParser.getArrayOfArrays();
        this.createBusinessHours();
        System.out.println(Arrays.deepToString(this.createBusinessHours()));
    }

    /**
     * createBusinessHours method takes in our 2D-Array of
     * integers —timeIntervals— and merges them based on
     * overlaps. First, it sorts the arrays, then it merges them
     *
     * @return
     */
    public Integer[][] createBusinessHours() {

        if(this.timeIntervals.length < 1){
            return this.timeIntervals;
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
            else if (nextStartTime > currEndTime){ //if the intervals don't overlap
                //currInterval updated to nextInterval
                currInterval = nextInterval;

                //and add the non-overlapping interval
                businessHourArray.add(currInterval);
            }
        }

        //return an arrayOfArrays businessHours that mirrors the businessHours ArrayList
        return businessHourArray.toArray(new Integer[businessHourArray.size()][]);
    }
}