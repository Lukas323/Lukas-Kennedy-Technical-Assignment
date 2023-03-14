# Lukas-Kennedy-Technical-Assignment
Lukas Kennedy's Business Hours Technical Assignment For Angi Summer Internship

Design Overview: 

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
2D-Array of 
sed a for loop to change every 


