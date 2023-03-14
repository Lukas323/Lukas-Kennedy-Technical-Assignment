# Lukas-Kennedy-Technical-Assignment
Lukas Kennedy's Business Hours Technical Assignment For Angi Summer Internship

Directions on how to run the program: 

1. Please create a .txt file holding the Pro availability hours that you want to
   be sorted, in the structure specified in the handout. 

2. Then, please run the Main method of the program. This will allow you to click on
   the Main drop down menu, right next to the green run triangle. Click on the dropdown 
   menu and select "Edit Configurations". From here you will replace the .txt file
   already there with the file you want to input to the program. 
   Please keep the space between the .txt file and the String "Output" so that the program 
   can tell the difference between arg[0] and arg[1].

Warning: The writeToFile() method of the FileParser class is tested using arg[1] file 
"Output" that is already there. If you remove "Output" String and replace it with 
your own, this test, testWriteToFile() of the FileParserTestSuite, will not run 
successfully. You are welcome to remove the String "Output" and replace it with your
own, but when running testWriteToFile(), if you could replace "Output" back at its
original spot as arg[1], that would be much appreciated.

Program Design Overview: 

There are 7 classes total, but there are 3 important classes to focus on.

Important classes:

1. Main
2. FileParser
3. ProAvailability
4. ScheduleConsole


Main: Main serves to initialize the three other classes and run the program. 

FileParser: 

FileParser does the bulk of the program's job. It reads in lines from the inputted text file 
and converts the 2D-Array of Strings into a 2D-Array of Integer intervals to be sorted. 
It does through the methods readFile, match, makeInteger, and makeArray. 
The readFile() method contains the other three methods. readFile() reads in each line of the file
inputted and calls match() to retrieve all of the colon-ed integers from within quotation marks and
put them into an ArrayList (e.g. [07:00, 09:20, 14:00, 19:00], etc). Each Integer in this arrayList is then
turned into an integer, removing the colon (e.g. [700, 920, 1400, 1900]), and then added to a new arrayList
to store. Then the makeArray() method is called, which regroups these integers back into a 2D-Array as 
time intervals once more (e.g. [[700, 920], [1400, 1900], [900, 1500]]) and stores this 2D-Array in the 
instance variable this.arrayOfArrays. A getter method is provided for ProAvailability to access this 
arrayOfArrays. Even though a getter method goes against good program design, it was useful here. The other
option would have been making readFile return the arrayOfArrays, which wouldn't have made sense. Now comes
the ProAvailability class.

ProAvailability: 

Takes in the 2D-Array of paired integers from the FileParser class it is associated with and sorts
these integers via the createBusinessHours method. This method basically says, I have a 2D-Array
[[startTime1, endTime1],[startTime2, endTime2]] a.k.a. [[currInterval], [nextInterval]].
I want to merge both arrays into one if the timeIntervals overlap (endTime1 > startTime2). 
Otherwise I want to add my currInterval to a 2D-Array of businessHours to return, and 
assign my currInterval to be the nextInterval, comparing interval2 with interval3, and so on. 
Through this process the 2D-Array of timeInterval pairs are grouped into overarching businessHours. 

The next job of ProAvailability was to turn this 2D-Array of businessHours back into the form
it was initially recieved in from the file (2D-Array of Strings). The makeString() method turns 
each integer back into its String form (700 -> "07:00"), implementing the addChar() method to 
do this. addChar() is the method used to add a char —in this case colon and quotation marks—
to the desired position in each newly formed String. 

The returnHoursToString() method initializes the variable integerBusinessHours to hold the 
2D-Array of sorted businessHours (in integer form). It then uses a for loop to turn every 
integer in this 2D-Array businessHours into its proper String, adding each new String to 
an ArrayList to hold. This ArrayList is then converted back into an ArrayOfArrays and returned,
and thus are the businessHours (e.g. [[700, 1900]]), converted into their output form, as 
Strings (e.g. [["07:00", "19:00"]]). 

ScheduleConsole Class: 

The ScheduleConsole class controls the viewing of the final solution. It calls on a passed-in
fileParser to write the final solution to a new file for viewing, and calls on the passed-in 
ProAvailability class to print the final solution to terminal.

Main Class:

The Main class instantiates all three important classes, passing the appropriate classes
into constructors as parameters. 

Noteworthy classes:

LineEmptyException.class is the custom Exception thrown when a line is empty, telling the 
user that the empty line needs to be removed from the file for the program to run correctly. 
If the empty line were not removed from the file, the program would return an array of empty
business hours, which is not ideal. 

FileParserTestSuite and ProAvailabilityTestSuite tests the methods and overall class dynamics
of the program. They test edge cases and ensure that the program will run as it should, 
regardless of the eccentricities in the availability hours that each Pro inputs. 


