import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.sound.sampled.Line;

/**
 * public class FileParserTestSuite serves
 * as the testing class for the FileParser class
 *
 * It tests the main methods readFile and writeToFile
 */
public class FileParserTestSuite {

    FileParser normalParser;

    FileParser normalParser2;


    /**
     * setupTestSuite serves to initialize the fileParser
     * instance variables to be used throughout the rest of the
     * testSuite
     */
    @Before
    public void setupTestSuite() {
        this.normalParser =
                new FileParser("FileParser-test-files/baseCase.txt");

        this.normalParser2 =
                new FileParser("FileParser-test-files/baseCase2.txt");

    }

    /**
     * The following tests test the readFile()
     * method/capability of the FileParser class and
     * test the other methods of the class via readFile().
     *
     * There is no need to test duplicates here, because
     * the arrayOfArrays initialized by FileParser
     * readFile() will indeed contain duplicates, but they
     * will get sorted out in the createBusinessHours method
     * of the ProAvailability class
     *
     * There is no need to test for empty lines here, because
     * the createBusinessHours() method of the ProAvailability
     * class throws hte LineEmptyException upon encountering
     * and empty line
     */


    /**
     * test initial input case where there are no
     * duplicate arrays and no empty lines
     */
    @Test
    public void testOneLineBaseCase() {
        Integer[][] baseCase = new Integer[3][2];
        baseCase[0][0] = 700;
        baseCase[0][1] = 920;
        baseCase[1][0] = 1400;
        baseCase[1][1] = 1900;
        baseCase[2][0] = 900;
        baseCase[2][1] = 1500;
        Assert.assertArrayEquals(baseCase, this.normalParser.getArrayOfArrays());

        Integer[][] baseCase2 = new Integer[4][2];
        baseCase2[0][0] = 1000;
        baseCase2[0][1] = 1422;
        baseCase2[1][0] = 1500;
        baseCase2[1][1] = 1902;
        baseCase2[2][0] = 1902; //overlap allowed here before sorting
        baseCase2[2][1] = 1903;
        baseCase2[3][0] = 2100;
        baseCase2[3][1] = 2359;

        Assert.assertArrayEquals(baseCase2,
                this.normalParser2.getArrayOfArrays());
    }

    /**
     * tests that the file being written to holds the expected result
     */
    @Test
    public void testWriteToFile(){
        try{
            //create new fileParser based off of the base case
            FileParser fileParser = new FileParser
                    ("FileParser-test-files/baseCase.txt");

            //create a new ProAvailability associated with fileParser
            ProAvailability proAvailability = new ProAvailability(fileParser);

            //write the solution business hours of that file to a new file Output
            fileParser.writeToFile(proAvailability.returnHoursToString(), "Output");

            //create a fileParser based on newly created file Output
            FileParser fileParser1 = new FileParser("Output");

            //instantiate a new ProAvailability based on fileParser1
            ProAvailability proAvailability1 = new ProAvailability(fileParser1);

            /*
              Assert that the solution business hours returned by the first ProAvailability
              Are the same as the business hours derived from the newly created file Output
              */
            Assert.assertArrayEquals(proAvailability.returnHoursToString(),
                    proAvailability1.returnHoursToString());
        }catch (LineEmptyException e){

        }

    }
}
