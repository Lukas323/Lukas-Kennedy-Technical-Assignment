import java.io.*;

public class Cat {

    /**
     * Reads from a file using a BufferedReader and writes the contents to a
     * second file using a BufferedWriter.
     *
     * @param reader - a buffered reader for the file
     * @param writer - a buffered writer to write to
     */
    public static void cat(BufferedReader reader, BufferedWriter writer) throws IOException {
        // TODO: read and write lines until EOF is reached!
        //read the first line
        String line = reader.readLine();

        //as long as the line isn't empty
        while (line!=null) {
            //creates a new line
            writer.newLine();

            //if you pass in current line, it says write this line to the file
            writer.write(line);

            //do whatever you want with the line here
            System.out.println(line);

            //get the next line from the file. I think readLine must have a newLine built in
            line = reader.readLine();
        }

        //close the reader file after iterating through the last line
        reader.close();
    }

    public static void main(String[] args) throws IOException{
        //TODO: Need to create FileReader and corresponding BufferReader
        if (args.length != 2) {
            System.out.println("Usage: <input file> <output file>");
            System.exit(0);
        }

        //Instantiate a new Reader reader
        //args[0] is the name of the file
        //TODO: Why args[0]
        //have to initialize it to something so it works later
        FileReader fileReader = null;

        try{
            //error would show upon creating FileReader, when you pass in a file name that doesn't exist
            fileReader = new FileReader(args[0]);
        } catch(FileNotFoundException e){
            //args[0] returns the file name
            System.out.println("I'm sorry, but I can't find the file" + args[0]);
            //status 1 cause there's an error
            System.exit(1);
        }

        //Instantiate a new BufferedReader bufferedReader
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        //args[1] should be the actual String we're passing in
        FileWriter fileWriter = new FileWriter(args[1]);

        //instantiate new bufferedWriter
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write("I am writing to a file");

        cat(bufferedReader, bufferedWriter);

        //following two clean-up lines //TODO: What does flush do?
        bufferedWriter.flush();
        bufferedWriter.close();

        // TODO: call the 'cat' method, constructing the appropriate arguments!
    }
}