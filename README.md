<h1> CSC1035: Assessment 2 </h1>

<h3> Synopsis </h3>

This program allows the user to manipulate a staff telephone directory - entries can be inserted, deleted and looked up.  
Staff entries consist of a surname, initials and extension. 

<h3> Performance and Correctness Testing</h3>

See the [Performance and Correctness Testing Report](performance Testing.pdf) for my discussion about the difference in  
performance of data structures and some testing conducted to ensure the validity of certain methods.

<h3> How To Use - Entry Point </h3>
To run this program simply run the "Main" class. Upon running the program you will be prompted with:  

        Please choose a valid data structure to use!
        1 - Array
        2 - ArrayList
        3 - HashMap
        Input 1 or 2 or 3: 
This is where you must select which data structure to use to store and manipulate entries.  
Only type "<b>1</b>", "<b>2</b>", or "<b>3</b>" and press <b>enter</b> to select a data structure of your choice. After selecting a valid choice  
You should be prompted with 9 choices as follows:  

        Please choose a valid choice
        1 - Input Entry
        2 - Delete Entry By Surname
        3 - Delete Entry By Extension
        4 - Lookup Extension By Surname
        5 - Update Extension By Surname
        6 - Extract From CSV File
        7 - Display Contents As Table
        8 - Output Contents To CSV File @ 'csv files/Output.csv'
        9 - Output Performance Analysis
        Enter choice: 
To select a valid choice please enter the preceding number to the corresponding choice and then press enter.  

By typing <b>"1"</b> and pressing <b>enter</b>, you will be prompted to input a surname, initials and extension. Only the extension  
will be validated to meet the specified format in the specification - as in the specification the surname and initials were not stated  
with any formatting rules. Attempting to enter an entry that has a surname already assigned to another entry in the  
directory will not have any affect on the directory. If you wish to test this - attempt to input a duplicate entry  
and then type <b>"7"</b> and press <b>enter</b> to view the contents of the directory.    

By typing <b>"2"</b> and pressing <b>enter</b>, you will be prompted to enter a surname to delete the corresponding entry  
entering a valid surname will delete an entry whereas not entering a valid surname will not have any affect on the  
directory. 

By typing <b>"3"</b> and pressing <b>enter</b>, this will have the exact same behaviour or effect as option 2 but instead an entry's  
extension will be required to delete the corresponding entry.  

By typing <b>"4"</b> and pressing <b>enter</b>, you will be prompted to enter a surname - if an entry exists with the surname  
passed then the entry's extension will be printed otherwise null will be printed.  

By typing <b>"5"</b> and pressing <b>enter</b>, you will be prompted to enter a surname then extension. If an entry with the specified  
surname exists and extension is valid then the existing entry's extension will be set to the new extension inputted.  
Otherwise the directory will not be affected.  

By typing <b>"6"</b> and pressing <b>enter</b>, you will prompted to enter a directory and filename to specify where this program   
should extract entry data from to combine with your selected directory data structure. Important to note that all  
CSV data has been moved to a directory in this directory named "csv files" - running this command and specifying  
"csv files/test_data" will extract data from the test data containing duplicates. Also important to note that the  
user should not specify the file extension as ".csv" this is automatically appended to the directory the user inputs.  
To restrict the user to only extracting from CSV files. If duplicate entries exist within a CSV then they will not  
be added to the given directory - such that if someone with surname "Taylor" is detected more than once only the  
first encounter will be added whilst every subsequent entry with the same surname is ignored.

By typing <b>"7"</b> and pressing <b>enter</b>, a table of the current directory's contents will be outputted. This is very useful  
in checking what is currently in the directory and to validate if an action affected the directory or not.  

By typing <b>"8"</b> and pressing <b>enter</b>, the directory's content is written to the CSV file in "csv files/Output.csv".  
This directory output is not customizable to the user as the specification only states to customise the output of  
the results of the performance results. However if one wishes to change this output directory - one may do so  
by changing the String in "Output.java" line 112 from "csv files/Output.csv" to something else.

                    // file points to the file to write to
                    FileWriter file = new FileWriter("csv files/Output.csv");
                    // This creates a buffer-write object
                    BufferedWriter fileWriteTo = new BufferedWriter(file);
By typing <b>"9"</b> and pressing <b>enter</b>, this will initiate 240,000 performance tests - sorry for the wait. For more details  
about testing please see below. Clarifying how to test - and - what is tested.

<h3> Running Tests </h3>

Run the "Main" class, you will be prompted to choose a data structure - regardless of data structure choice -  
all data structures are tested for all methods using both unique and duplicate data sets. Once prompted with 9 choices,  
simply type <b>"9"</b> and press <b>enter</b>, then wait a very long time for 240000 tests to run. Sorry again for the wait.  
The results of this test will be outputted to "src/Performance.txt".  
  
The filepath and file output can be modified by navigating to the "Main.java" class and locating this code on line 99

         case "9":
                System.out.println("Running 240,000 Tests - Please Wait");
                output.outputPerformanceStatistics(PerformanceAnalysis.allTests(), "src/Performance.txt");
                break;
Where "src/Performance.txt" is stated in the output method, by changing that String, the output location will be changed.  

<h4> What is Tested? </h4>

There are 3 directories, array, arrayList and hashMap. There are also 4 methods to test, deletion by surname,  
deletion by extension, lookup extension, and insertion. Lastly there are two sets of test data one containing only  
unique entries and one containing duplicate entries. For each directory each method is tested ten-thousand times  
for both sets of data. The results are measured in nano-seconds in which the best, worst and average case for each  
test conducted, is written to the text file. 

<h3> License </h3>

See the [LICENSE.md](LICENSE.md) file for license details.

<h3> Built With </h3>

* [BufferReader](https://docs.oracle.com/javase/8/docs/api/java/io/BufferedReader.html) - Used to read from a CSV file
* [FileReader](https://docs.oracle.com/javase/8/docs/api/java/io/FileReader.html) - Used to read from a CSV file
* [FileNotFoundException](https://docs.oracle.com/javase/7/docs/api/java/io/FileNotFoundException.html) - Used to handle 
if the file not cannot be found  
* [IOException](https://docs.oracle.com/javase/7/docs/api/java/io/IOException.html) - Used to handle I/O Exceptions
* [BufferedWriter](https://docs.oracle.com/javase/7/docs/api/java/io/BufferedWriter.html) - Used to write to the CSV file  
* [FileWriter](https://docs.oracle.com/javase/7/docs/api/java/io/FileWriter.html) - Used to write to the CSV file    
* [Arrays](https://docs.oracle.com/javase/7/docs/api/java/util/Arrays.html) - Used as a data structure for the ArrayDirectory
* [ArrayList](https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html) - Used as a data structure for the 
ArrayListDirectory
* [HashMap](https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html) - Used as a data structure for the 
HashMapDirectory
* [Scanner](https://docs.oracle.com/javase/8/docs/api/java/util/Scanner.html) - Used to accept user input
* [List](https://docs.oracle.com/javase/8/docs/api/java/util/List.html) - Apart of the toArrayList method

<h3> Acknowledgements </h3>

* [Reading From a File In Java](https://www.geeksforgeeks.org/different-ways-reading-text-file-java/) - Used to read from 
the CSV file

<h3> Author </h3>

* Name: <b> Lee Taylor </b> Student Number: <b> 190211479 </b> 

<h4> Note </h4>

There is no Nucode link due to experiencing problems with Nucode - furthermore  
using Nucode was stated as not apart of the mark scheme or specification.