import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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

            while(line != null){
                System.out.println(Arrays.asList(line.toCharArray()));

                for(char[] character: Arrays.asList(line.toCharArray())){
                    System.out.println(character);
                }
//                Arrays.asList(line.toCharArray())
//                        .stream().filter(c-> splitChar(c)).toList();

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
        Pattern pattern = Pattern.compile("\"([^\"]*)\"");
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            System.out.println(matcher.group(1));
             // you can get it from desired index as well
        }
        return "pizza";
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
