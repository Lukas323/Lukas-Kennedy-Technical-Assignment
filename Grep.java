
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Class for finding the line numbers where a given word appears in a file.
 */
public class Grep {

    //List of HashSets initialized to store our file line strings
    private HashMap<HashSet<String>, Integer> stringsToLineMap;

    //HashShet of type Integer lineList to store file lines
    private HashSet<Integer> lineList;

    /**
     * Constructor for grep
     * @param filename the name of the file
     */
    public Grep(String filename) {

        //Instantiate storingMap to store our word keys by their line values
        this.stringsToLineMap = new HashMap<>();

        //instantiate a new FileReader to read the inputted file
        FileReader fileReader = null;

        //if the filename passed in does not exist
        try{
            fileReader = new FileReader(filename);
        } catch(FileNotFoundException e) {
            System.out.println
                    ("I'm sorry, but I cannot find the file" + filename);
        }

        //instantiate BufferedReader to read specific lines from the file
        BufferedReader reader = new BufferedReader(fileReader);

        String line = null;

        //if line passed in is not readable
        try{
            line = reader.readLine();
        } catch(IOException e) {
            System.out.println
                    ("I'm sorry, but the line" + line + "is empty");
        }

        int lineNum = 0;

        while(line != null){ //as long as the line exists
            lineNum++;

            //split up the list of strings
            String[] stringList = line.split(" ");

            HashSet<String> stringSet = new HashSet<>();


            for (String string: stringList) {
                //add every string to the HashSet to remove duplicates
                stringSet.add(string);
            }

            //map every stringSet to its lineNumber
            this.stringsToLineMap.put(stringSet, lineNum);

            //get the next line from the file.
            try {
                line = reader.readLine();
            } catch (IOException e) {
                System.out.println("I'm sorry but there was an IOException");
            }

        }

        //close the reader file after iterating through the last line
        try{
            reader.close();
        }catch(IOException e){
            System.out.println("I'm sorry but there was an IOException");
        }

    }

    /**
     * The lookup function
     * Every key in the HashMap this.stringsToLineMap
     * is a HashSet<String> and every value is the integer
     * line the key maps to.
     *
     * Lookup goes through every key in the HashMap,
     * searches for the word to lookup,and when it finds the word,
     * it adds matching line number (value) to a new set of Integers
     * to be returned.
     *
     * @param word - the word to look up
     *
     * @return The set of all line numbers where the word appears
     */

    public Set<Integer> lookup(String word) {
        HashSet<Integer> intSet = new HashSet<>();
        for(HashSet<String> set : this.stringsToLineMap.keySet()){
            if(set.contains(word)){
                intSet.add(this.stringsToLineMap.get(set));
            }
        }

        return intSet;
    }

    // Feel free to write additional helper methods :)

    /**
     * This is the main method. It takes in arguments (i.e. a file name and a word(s))
     * and calls your implementation of Grep.
     * @param args - file name and word(s) you are looking up
     */
    public static void main(String[] args) {
        if(args.length == 0){
            System.out.println("no args inputted, try again");
            System.exit(0);
        }
        for(int i=0; i<args.length; i = i+2){
            //make a new grep with args[even
            Grep grep = new Grep(args[i]);

            System.out.println("'" + args[i+1] + "'" + " was found on lines: "
                    + grep.lookup(args[i+1]));
        }
    }
}
