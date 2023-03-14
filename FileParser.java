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
                //parse the line into startTime and endTime
                String parsedNumber = this.match(line);

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
     * The method takes each .txt file line and returns an array of arrays
     * of the time availability inputs as integers (e.g. [[700,920], [1400,1900]] )
     */
    public String match(String line) throws LineEmptyException { //TODO: Change method name
        //pattern gets everything between the quotes
        Pattern pattern = Pattern.compile("\"([^\"]*)\"");

        //matcher instantiated with line to look through for matches
        Matcher matcher = pattern.matcher(line);

        String parsedNumber = "";

        ArrayList<Integer> intArray= new ArrayList<>();

        //while a match exists
        while (matcher.find()) {

            //parse numbers into a String
            parsedNumber = matcher.group(1);

            //instantiating integer to store the parsedNumber integer
            Integer integer = this.makeInteger(parsedNumber);

            //add integer to int array (e.g. [700, 920, 1400, 1900]
            intArray.add(integer);

            //every two numbers should go in an array array of arrays, and
            //every two numbers should be 1 array
            //add them all to an arrayList and then split it by two
        }

        System.out.println(intArray);

        Integer[][] arrayOfArrays = new Integer[intArray.size()/2][2];

        //Regroup integers into their respective start time and end time
        int j = 0;
        for(int i = 0; i < intArray.size(); i++){ //i from 0 to 5

            int k = i/2; //k goes 0,0,1,1,2,2 because java rounds fractions down
            arrayOfArrays[k][j] = intArray.get(i);
//            System.out.println(Arrays.deepToString(arrayOfArrays));
            j = (j+1) % 2;
        }

//        HashSet<Integer[]> duplicateRemover = new HashSet<>();

        //Add every array in arrayOfArrays to a HashSet to remove duplicates
//        for(Integer[] array: arrayOfArrays){ //TODO: Remove duplicates
//            duplicateRemover.add(array);
//        }
//
//        Iterator iterator = duplicateRemover.iterator();

//        while(iterator.hasNext()){ TODO: Put it back into an array
//            int x = 0;
//            for(int i = 0; i<duplicateRemover.size(); i++){
//                int k = i/2; //k goes 0,0,1,1,2,2 because java rounds fractions down
//
//                arrayOfArrays[i][x] = duplicateRemover.;
////            System.out.println(Arrays.deepToString(arrayOfArrays));
//                x = (x+1) % 2;
//            }
//        }

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
