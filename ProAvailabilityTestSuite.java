import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ProAvailabilityTestSuite {

    FileParser normalParser;

    FileParser normalParser2;

    FileParser noOverlapParser;

    FileParser duplicateParser;

    /**
     * setupTestSuite initializes the instance
     * variables to be used throughout the testSuite
     */
    @Before
    public void setupTestSuite(){
        this.normalParser =
                new FileParser("FileParser-test-files/baseCase.txt");

        this.normalParser2 =
                new FileParser("FileParser-test-files/baseCase2.txt");

        this.noOverlapParser =
                new FileParser("FileParser-test-files/noOverlap.txt");

        this.duplicateParser =
                new FileParser("FileParser-test-files/duplicateInputs.txt");
    }


    /**
     * The following tests test the createBusinessHours method of
     * the ProAvailability class to verify that BusinessHours are
     * being created properly and to account for edge cases
     */


    /**
     * tests that overlapping inputs merge to form
     * a businessHour
     */
    @Test
    public void testOverlap(){
        //test complete overlap (when every interval overlaps to create 1 businessHour)
        ProAvailability oneBusinessHour =
                new ProAvailability(this.normalParser);
        Integer[][] complete = new Integer[1][2];
        complete[0][0] = 700;
        complete[0][1] = 1900;
        Assert.assertArrayEquals(complete,
                oneBusinessHour.createBusinessHours());


        //test perfect overlap (when currEnd = nextStart);
        ProAvailability multipleBusinessHours =
                new ProAvailability((this.normalParser2));
        Integer[][] nonPerfectOverlap = new Integer[3][2];
        nonPerfectOverlap[0][0] = 1000;
        nonPerfectOverlap[0][1] = 1422;
        nonPerfectOverlap[1][0] = 1500;
        nonPerfectOverlap[1][1] = 1903;
        nonPerfectOverlap[2][0] = 2100;
        nonPerfectOverlap[2][1] = 2359;
        Assert.assertArrayEquals(nonPerfectOverlap,
                multipleBusinessHours.createBusinessHours());


    }

    /**
     * test that duplicate inputs only create
     * one business hour
     */
    @Test
    public void testDuplicates(){


    }

    /**
     * testEmptyInputs tests the case of empty .txt file lines
     * and ascertains that any empty array inputs to our ProAvailability
     * class will not create empty BusinessHours //TODO: Should not.
     */
    @Test
    public void testEmptyInputs(){
        //TODO: Requires that empty arrays are being given
        //      to sort instead of throwing Exception. How will we handle that?
    }

    /**
     * tests that if the start time equals the end time
     * ___ //TODO: What will happen?
     */
    @Test
    public void testSameStartEnd(){

    }

}
