/**
 * public LineEmptyException for when an input
 * line in the text file is empty and thus cannot be parsed
 */
public class LineEmptyException extends Exception{

    /**
     * public LineEmptyException constructor
     */
    public LineEmptyException(String message){
        System.out.println(message);
    }

}
