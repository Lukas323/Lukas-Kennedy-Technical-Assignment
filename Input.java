
//TODO: I need to add everything to Git
public class Input {
    //TODO: My input needs to be an array of the a
    private String startTime;
    private String endTime;

    private String[] pair;

    /**
     * public Input class constructor representing all the inputs of one professional
     *
     * Takes in startTime and endTime via arguments passed in to constructor //TODO: Are they tuples?
     * @param startTime
     * @param endTime
     */
    public Input(String startTime, String endTime){
        this.startTime = startTime;
        this.endTime = endTime;
        this.pair = new String[2];
        this.pair[0] = startTime;
        this.pair[1] = endTime;
    } //TODO: I really want to use Integers. But the format passed in will be [["07:00", "11:00"]]

    /**
     * public getter method for startTime
     * @return
     */
    public String getStartTime(){
        return this.startTime;
    }

    /**
     * public getter method for endTime
     * @return
     */
    public String getEndTime(){
        return this.endTime;
    }

    /**
     * public getter method for the startTime endTime pair
     * @return
     */
    public String[] getPair(){
        return this.pair;
    }
}
