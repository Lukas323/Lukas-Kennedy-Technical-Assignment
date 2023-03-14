import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ProAvailabilityTestSuite {

    FileParser normalParser;

    FileParser normalParser2;

    FileParser noOverlapParser;

    FileParser duplicateParser;

    FileParser emptyLines;

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

        this.emptyLines =
                new FileParser("FileParser-test-files/emptyLines.txt");
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
    public void testOverlap() throws LineEmptyException {
        //test complete overlap (when every interval overlaps to create 1 businessHour)
        ProAvailability oneBusinessHour =
                new ProAvailability(this.normalParser);
        Integer[][] completeOverlap = new Integer[1][2];
        completeOverlap[0][0] = 700;
        completeOverlap[0][1] = 1900;
        Assert.assertArrayEquals(completeOverlap,
                oneBusinessHour.createBusinessHours());


        //test perfect overlap (when currEnd = nextStart);
        ProAvailability multipleBusinessHours =
                new ProAvailability(this.normalParser2);
        Integer[][] nonPerfectOverlap = new Integer[3][2];
        nonPerfectOverlap[0][0] = 1000;
        nonPerfectOverlap[0][1] = 1422;
        nonPerfectOverlap[1][0] = 1500;
        nonPerfectOverlap[1][1] = 1903;
        nonPerfectOverlap[2][0] = 2100;
        nonPerfectOverlap[2][1] = 2359;
        Assert.assertArrayEquals(nonPerfectOverlap,
                multipleBusinessHours.createBusinessHours());

        //test case where there is no overlap between interval inputs
        ProAvailability separateBusinessHours =
                new ProAvailability(this.noOverlapParser);
        Integer[][] noOverlap = new Integer[3][2];
        noOverlap[0][0] = 200;
        noOverlap[0][1] = 1011;
        noOverlap[1][0] = 1400;
        noOverlap[1][1] = 1900;
        noOverlap[2][0] = 2020;
        noOverlap[2][1] = 2323;
        Assert.assertArrayEquals(noOverlap,
                separateBusinessHours.createBusinessHours());
    }

    /**
     * test that duplicate inputs only create
     * one business hour
     */
    @Test
    public void testDuplicates(){
        //I placed each previous file's inputs into the same line twice to test
        ProAvailability duplicateAvailabilities =
                new ProAvailability(this.duplicateParser);
        Integer[][] duplicatesMerged = new Integer[2][2];
        duplicatesMerged[0][0] = 700;
        duplicatesMerged[0][1] = 1903;
        duplicatesMerged[1][0] = 2100;
        duplicatesMerged[1][1] = 2359;
    }

    /**
     * testEmptyInputs tests the case of empty .txt file lines
     * and ascertains that any empty array inputs to our ProAvailability
     * class will not create empty BusinessHours //TODO: Should not.
     */
    @Test (expected = LineEmptyException.class)
    public void testEmptyInputs() throws LineEmptyException {
        ProAvailability noIntervalInputs =
                new ProAvailability(this.emptyLines);
        noIntervalInputs.createBusinessHours();
    }
}
