import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Output {
    /**
     * This variable is used to store all entries
     * from a given directory - such that given a
     * directory and then converting it into a an
     * arrayList this variable will be used to hold
     * the contents of the converted directory.
     *
     * Used to interpret the contents of a directory
     * in other methods.
     *
     * @see #writeToCSV(Directory)
     * @see #outputEntriesAsTable(Directory)
     * */
    private List<Entry> entriesArrList;

    /**
     * This method displays a table of data with the columns
     * "Surname", "Initials" and "Extension"
     * where the data displayed under these columns are equal
     * to all of the entry's attributes from the given directory.
     *
     * This method displays an ASCII table with three columns however each
     * column size is not fixed except for the extension column which is
     * always 15 spaces. The surname and initials column are of a dynamic
     * size because the surname can be long enough to break the visual
     * format of the table - which also applies to the initials - hence
     * before creating the table the width of these columns are calculated
     * by iterating through all surnames and all initials and then determining
     * the width of those columns based on the longest string found in those
     * groups of values. The table is composed of lines of dashes, these
     * dashes are Strings which are constructed based on the previous values
     * determined.
     *
     * @param directory
     *        This determines the directory to output as a table
     * */
    public void outputEntriesAsTable(Directory directory){

        this.entriesArrList = directory.toArrayList();

        // The default width of the surname column is initialized with a value of 15
        int surnameWidth = 15;

        // This checks if each entry has a longer surname than the current surname width set
        for (int i = 0; i < this.entriesArrList.size(); i++){
            if (this.entriesArrList.get(i).getSurname().length() > surnameWidth)
                surnameWidth = this.entriesArrList.get(i).getSurname().length();
        }

        // The default width of the initials column is initialized with a value of 15
        int initialsWidth = 15;

        // This checks if each entry has a longer extension than the current extension width set
        for (int i = 0; i < this.entriesArrList.size(); i++){
            if (this.entriesArrList.get(i).getInitials().length() > initialsWidth)
                initialsWidth = this.entriesArrList.get(i).getInitials().length();
        }

        // Builds surname line where the length of the line is equal to the longest surname or a length of 15
        String surnameDashes = "";
        for (int i = 0; i < surnameWidth; i++) {
            surnameDashes = surnameDashes.concat("-");
        }

        // Builds initial line where the length of the line is equal to the longest initials or a length of 15
        String initialsDashes = "";
        for (int i = 0; i < initialsWidth; i++) {
            initialsDashes = initialsDashes.concat("-");
        }

        // The column width for extension is fixed
        String extensionDashes = "---------------";

        // Prints out the table header - the columns for the table
        System.out.format("+%s+%s+%s+\n",surnameDashes,initialsDashes,extensionDashes);
        System.out.format("|%"+surnameWidth+"s|%"+initialsWidth+"s|%15s|\n","Surname","Initials","Extension");
        System.out.format("+%s+%s+%s+\n",surnameDashes,initialsDashes,extensionDashes);

        // Prints out each entry-object's details under the table header
        for (int i = 0; i < entriesArrList.size(); i++){
            System.out.format("|%"+surnameWidth+"s|%"+initialsWidth+"s|%15s|\n",entriesArrList.get(i).getSurname(),
                    entriesArrList.get(i).getInitials(),entriesArrList.get(i).getExtension());
        }

        // Prints out table closing line - table closer
        System.out.format("+%s+%s+%s+\n",surnameDashes,initialsDashes,extensionDashes);

    }

    /**
     * This method given a directory will write the contents
     * from the given directory to a CSV file. Using a fileWriter
     * and BufferedWriter. The for loop writes each entry from
     * the directory to the CSV file after the for loop finishes
     * the same code outside of the for loop is there to write
     * the last entry to the CSV file - this is because
     * if the for loop wrote every entry to the CSV file it would
     * also write a blank line at the end of the file
     *
     * @param directory
     *        This is the directory extract
     *        from to write to the CSV
     * */
    public void writeToCSV(Directory directory) throws IOException {

        this.entriesArrList = directory.toArrayList();

        if (this.entriesArrList.size() > 0) {

            FileWriter file = new FileWriter("csv files/Output.csv"); // file points to the file to write to
            BufferedWriter fileWriteTo = new BufferedWriter(file); // This creates a buffer-write object

            // Writes each entry to the CSV file
            for (int i = 0; i < entriesArrList.size() - 1; i++) {
                fileWriteTo.write(this.entriesArrList.get(i).getSurname() + ","
                        + this.entriesArrList.get(i).getInitials() + ","
                        + this.entriesArrList.get(i).getExtension() + "\n");
            }

            // Prevents blank line being written to CSV file
            fileWriteTo.write(this.entriesArrList.get(entriesArrList.size() - 1).getSurname() + ","
                    + this.entriesArrList.get(entriesArrList.size() - 1).getInitials() + ","
                    + this.entriesArrList.get(entriesArrList.size() - 1).getExtension());

            fileWriteTo.close();
            file.close();

            System.out.println("Entry Contents Written To --> 'csv files/Output.csv'");
        }

    }

    /**
     * This method writes the results of all 240,000 tests
     * to the text document specified. Sorry for the time to
     * wait created by the number of tests conducted...
     *
     * @param times
     *        A 2D array of times to be
     *        outputted to the text file.
     *
     * @param fileLocation
     *        The directory and file to
     *        write to.
     * */
    public void outputPerformanceStatistics (Long[][] times, String fileLocation) throws IOException {

        FileWriter file = new FileWriter(fileLocation);

        BufferedWriter fileWriteTo = new BufferedWriter(file);

        fileWriteTo.write("Unique Data Set Tests (Results in Nanoseconds)\n");

        fileWriteTo.write("Deletion By Surname\n\n");
        fileWriteTo.write("Array Best Case: "+times[0][0]+"| Array Worst Case: "+times[0][1]+"| Array Average Case: "+
                times[0][2]+"\n");
        fileWriteTo.write("ArrayList Best Case: "+times[1][0]+"| ArrayList Worst Case: "+times[1][1]+"| ArrayList" +
                " Average Case: "+ times[1][2]+"\n");
        fileWriteTo.write("HashMap Best Case: "+times[2][0]+"| HashMap Worst Case: "+times[2][1]+"| HashMap" +
                " Average Case: "+ times[2][2]+"\n\n");

        fileWriteTo.write("Deletion By Extension\n");
        fileWriteTo.write("Array Best Case: "+times[3][0]+"| Array Worst Case: "+times[3][1]+"| Array Average Case: "+
                times[3][2]+"\n");
        fileWriteTo.write("ArrayList Best Case: "+times[4][0]+"| ArrayList Worst Case: "+times[4][1]+"| ArrayList" +
                " Average Case: "+ times[4][2]+"\n");
        fileWriteTo.write("HashMap Best Case: "+times[5][0]+"| HashMap Worst Case: "+times[5][1]+"| HashMap" +
                " Average Case: "+ times[5][2]+"\n\n");

        fileWriteTo.write("Insertion\n");
        fileWriteTo.write("Array Best Case: "+times[6][0]+"| Array Worst Case: "+times[6][1]+"| Array Average Case: "+
                times[6][2]+"\n");
        fileWriteTo.write("ArrayList Best Case: "+times[7][0]+"| ArrayList Worst Case: "+times[7][1]+"| ArrayList" +
                " Average Case: "+ times[7][2]+"\n");
        fileWriteTo.write("HashMap Best Case: "+times[8][0]+"| HashMap Worst Case: "+times[8][1]+"| HashMap" +
                " Average Case: "+ times[8][2]+"\n\n");

        fileWriteTo.write("Lookup\n");
        fileWriteTo.write("Array Best Case: "+times[9][0]+"| Array Worst Case: "+times[9][1]+"| Array Average Case: "+
                times[9][2]+"\n");
        fileWriteTo.write("ArrayList Best Case: "+times[10][0]+"| ArrayList Worst Case: "+times[10][1]+"| ArrayList" +
                " Average Case: "+ times[10][2]+"\n");
        fileWriteTo.write("HashMap Best Case: "+times[11][0]+"| HashMap Worst Case: "+times[11][1]+"| HashMap" +
                " Average Case: "+ times[11][2]+"\n\n");

        fileWriteTo.write("Duplicate Data Set Tests (Results in Nanoseconds)\n\n");
        fileWriteTo.write("Deletion By Surname\n");
        fileWriteTo.write("Array Best Case: "+times[12][0]+"| Array Worst Case: "+times[12][1]+"| Array Average Case: "+
                times[12][2]+"\n");
        fileWriteTo.write("ArrayList Best Case: "+times[13][0]+"| ArrayList Worst Case: "+times[13][1]+"| ArrayList" +
                " Average Case: "+ times[13][2]+"\n");
        fileWriteTo.write("HashMap Best Case: "+times[14][0]+"| HashMap Worst Case: "+times[14][1]+"| HashMap" +
                " Average Case: "+ times[14][2]+"\n\n");

        fileWriteTo.write("Deletion By Extension\n");
        fileWriteTo.write("Array Best Case: "+times[15][0]+"| Array Worst Case: "+times[15][1]+"| Array Average Case: "+
                times[15][2]+"\n");
        fileWriteTo.write("ArrayList Best Case: "+times[16][0]+"| ArrayList Worst Case: "+times[16][1]+"| ArrayList" +
                " Average Case: "+ times[16][2]+"\n");
        fileWriteTo.write("HashMap Best Case: "+times[17][0]+"| HashMap Worst Case: "+times[17][1]+"| HashMap" +
                " Average Case: "+ times[17][2]+"\n\n");

        fileWriteTo.write("Insertion\n");
        fileWriteTo.write("Array Best Case: "+times[18][0]+"| Array Worst Case: "+times[18][1]+"| Array Average Case: "+
                times[18][2]+"\n");
        fileWriteTo.write("ArrayList Best Case: "+times[19][0]+"| ArrayList Worst Case: "+times[19][1]+"| ArrayList" +
                " Average Case: "+ times[19][2]+"\n");
        fileWriteTo.write("HashMap Best Case: "+times[20][0]+"| HashMap Worst Case: "+times[20][1]+"| HashMap" +
                " Average Case: "+ times[20][2]+"\n\n");

        fileWriteTo.write("Lookup\n");
        fileWriteTo.write("Array Best Case: "+times[21][0]+"| Array Worst Case: "+times[21][1]+"| Array Average Case: "+
                times[21][2]+"\n");
        fileWriteTo.write("ArrayList Best Case: "+times[22][0]+"| ArrayList Worst Case: "+times[22][1]+"| ArrayList" +
                " Average Case: "+ times[22][2]+"\n");
        fileWriteTo.write("HashMap Best Case: "+times[23][0]+"| HashMap Worst Case: "+times[23][1]+"| HashMap" +
                " Average Case: "+ times[23][2]+"\n\n");

        fileWriteTo.close();
        file.close();

        System.out.println("Done! Results @ "+fileLocation);
    }

}
