import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * public class FileParser both reads the file inputted
 * and writes output array of arrays to a new file
 */
public class FileParser {

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
     * the FileParser class and returns an Integers[][]
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

                //TODO: Write comment explaining how you split it. And you should split it by a certain regex
                String[] stringList = line.split(":");
                System.out.println(stringList);
                line = null;
            }

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
}
