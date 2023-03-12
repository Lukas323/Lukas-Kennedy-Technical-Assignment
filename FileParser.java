import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * public class FileParser both reads the file inputted
 * and writes output array of arrays to a new file
 */
public class FileParser {

    //private String fileName declared to be used in the class's methods
    private String fileName;

    /**
     * public FileParser constructor takes in the name of the file
     * to read from and initializes the class's instance variables
     * @param fileName
     */
    public FileParser(String fileName){
        this.fileName = fileName;
        this.readFile();
    }

    /**
     * public readFile() method reads the file passed-in to
     * the FileParser class and returns an Integers[][] representing
     * the availability inputs from the professionals
     * @return
     */
    public Integer[][] readFile(){

        //if the filename passed in does not exist
        try{
            //instantiate fileReader to read file
            FileReader fileReader = new FileReader(this.fileName);

            //instantiate BufferedReader to read specific lines from the file
            BufferedReader reader = new BufferedReader(fileReader);

            //initialize line
            String line = reader.readLine();

            while(line != null && line != ""){
                //parse the line into startTime and endTime
                String parsedNumber = this.match(line);

                //return parsedNumber as integer
                Integer integer = this.makeInteger(parsedNumber);

//                System.out.println(integer);

                line = reader.readLine();
            }

            //close stream
            reader.close();

        } catch(FileNotFoundException e) {
            System.out.println
                    ("I'm sorry, but I cannot find the file" + this.fileName);
        } catch(IOException e){
            System.out.println
                    ("An IOException has occurred");
        }
        return null;
    }

    /**
     * match method
     */
    public String match(String line){
        //pattern gets everything between the quotes
        Pattern pattern = Pattern.compile("\"([^\"]*)\"");

        //matcher instantiated with line to look through for matches
        Matcher matcher = pattern.matcher(line);

        String parsedNumber = "";

        //while a match exists
        while (matcher.find()) {

            //parse numbers into a String
            parsedNumber = matcher.group(1);

            if(parsedNumber == ""){
                break;
            }

            this.makeInteger(parsedNumber);

        }

        //return String of parsedNumbers (e.g. "07:00, 09:20, 14:00)
        return parsedNumber;
    }


    /**
     * makeInteger takes in the String parsedNumber,
     * and splits it into hours and minutes
     * (e.g. 07:05, hours = 7, minutes = 5)
     * It then multiplies hours by 100 and
     * returns totalTime, hours + minutes.
     * (e.g. 705) to make sorting easier
     *
     * @param parsedNumber
     * @return
     */
    public Integer makeInteger(String parsedNumber) {
        Integer totalTime = 0;

        if(parsedNumber == ""){
            return totalTime;
        }
        //split every time period on its colon (e.g. 07:00 = ["07", "00"]
        String[] parsed = parsedNumber.split(":");

        Integer hour = Integer.valueOf(parsed[0]);
        Integer bigHour = hour*100;
        Integer minutes = Integer.valueOf(parsed[1]);
        totalTime = bigHour + minutes;

        return totalTime;
    }

    /**
     * if character is a digit return .toCharArray string into array of characters. Then stream that
     * @param character
     * @return
     */
    public boolean splitChar(char character){

        return Character.isDigit(character);
//
//        for(char[] chr: character){
//            return Character.isDigit(chr);
//        }
    }

    /**
     * public writeToFile method writes the Integer[][] output from
     * the mergeProAvailability() of the ProAvailability class to a
     * new file to be viewed
     *
     * Takes in Integer[][] output as a parameter to write it
     * to a new file
     *
     * @param availabilityArray
     */
    public void writeToFile(Integer[][] availabilityArray){

    }
}
