import javax.sound.sampled.Line;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * public class FileParser both reads the file inputted
 * and writes output array of arrays to a new file
 */
public class FileParser {

    //private String fileName declared to be used in the class's methods
    private String fileName;

    private Integer[][] arrayOfArrays;

    /**
     * public FileParser constructor takes in the name of the file
     * to read from and initializes the class's instance variables
     * @param fileName
     */
    public FileParser(String fileName) {
        this.fileName = fileName;

        try{
            this.readFile();
        } catch(LineEmptyException e){
            System.out.println
                    (".txt file has an empty line, " +
                            "please remove it for the solution to proceed");
        }
    }

    /**
     * public readFile() method reads the file passed-in to
     * the FileParser class and returns an Integers[][] representing
     * the availability inputs from the professionals
     * @return
     */
    public Integer[][] readFile() throws LineEmptyException{

        //if the filename passed in does not exist
        try{
            //instantiate fileReader to read file
            FileReader fileReader = new FileReader(this.fileName);

            //instantiate BufferedReader to read specific lines from the file
            BufferedReader reader = new BufferedReader(fileReader);

            //initialize line
            String line = reader.readLine();

            while(line != null){
                ArrayList<Integer> intArray = new ArrayList<>();

                //parsedNumber is an individual number indicating time (e.g. 07:00) (e.g. 09:20)
                List<String> parsedNumbers = this.match(line);

                for(String parsedNumber: parsedNumbers){

                    //parsedNumber colon : removed and returned in integer form (e.g. 700)
                    Integer timeInteger = this.makeInteger(parsedNumber);

                    //add integer to int array (e.g. [700, 920, 1400, 1900]
                    intArray.add(timeInteger);
                }


                this.makeArray(intArray);

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
     * Match goes through a .txt file line and retrieves
     * the times from within the quotation marks (07:00, 09:20)
     * and returns a list of times
     */
    public List<String> match(String line) {
        //pattern gets everything between the quotes
        Pattern pattern = Pattern.compile("\"([^\"]*)\"");

        //matcher instantiated with line to look through for matches
        Matcher matcher = pattern.matcher(line);

        String parsedNumber = "";

        List<String> listParsedString = new ArrayList<>();

        //while a match exists
        while (matcher.find()) {

            //parse numbers into a String
            parsedNumber = matcher.group(1);

            listParsedString.add(parsedNumber);
        }

        return listParsedString;
    }

    /**
     * makeArray takes in each integer parsedInt and returns
     * an Array of Arrays of those integers parsed. (e.g. [[700,920], [1400,1900]] )
     * @return
     * @throws LineEmptyException
     */
    public void makeArray(List<Integer> intArray) {

        ArrayList<Integer[]> arrayListOfArrays = new ArrayList<>();

        Integer[][] arrayOfArrays = new Integer[intArray.size()/2][2];

        //Regroup integers into their respective start time and end time
        int j = 0;
        for(int i = 0; i < intArray.size(); i++){ //i from 0 to 5

            int k = i/2; //k goes 0,0,1,1,2,2 because java rounds fractions down
            arrayOfArrays[k][j] = intArray.get(i);

            j = (j+1) % 2;
        }

        /*Although this is really high runtime, I think it necessary
          in order to remove duplicates*/
        for(Integer[] array: arrayOfArrays){
          arrayListOfArrays.add(array);
        }

        //HashSet instantiated to remove duplicates time periods
        HashSet<Integer[]> duplicateRemover = new HashSet<>();

        for(Integer[] array : arrayListOfArrays){
            duplicateRemover.add(array);
            if(duplicateRemover.contains(array)){
                //arrayList mutability needed to remove duplicates
                arrayListOfArrays.remove(array);
            }
        }

        //Regroup back into arrayOfArrays
        for(int i = 0; i < arrayListOfArrays.size(); i++){ //i from 0 to 5

            this.arrayOfArrays[i][i] = intArray.get(i);
        }
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
    public Integer makeInteger(String parsedNumber) throws LineEmptyException{

        if(parsedNumber == ""){
            throw new LineEmptyException //TODO: This can't be the best way to handle this. What if this is one of their edge cases
                    (".txt file inputted has empty lines, please try again.");
        }
        //split every time period on its colon (e.g. 07:00 = ["07", "00"]
        String[] parsed = parsedNumber.split(":");

        Integer hour = Integer.valueOf(parsed[0]);
        Integer bigHour = hour*100;
        Integer minutes = Integer.valueOf(parsed[1]);
        Integer totalTime = bigHour + minutes;

        return totalTime;
    }

    /**
     * getter method for arrayOfArrays instance variable
     * (e.g. [[700, 920], [1400, 1900], [900, 1500]] )
     * @return
     */
    public Integer[][] getArrayOfArrays(){
        return this.arrayOfArrays;
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
