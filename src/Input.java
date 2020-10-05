import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Input {

    /**
     * This method given a CSV file of entries will extract
     * each entry and insert the entry to the given directory
     * Using a fileReader and BufferReader to read each line
     * Into a tempArray which holds three elements. These three
     * elements are the fields surname, initials and extension
     * which are used respectively in the constructor for
     * each entry object inserted.
     *
     * @param filename
     *        This is the directory and filename
     *        of the csv file to extract from
     *
     * @param directory
     *        This specifies which directory
     *        to combine the extracted contents with
     *
     * @exception ArrayIndexOutOfBoundsException
     *            If the CSV file has a blank line
     *            and this method is run on that CSV file
     *            the program will crash - this try catch ignore
     *            is used to improve user-friendly-ness
     *
     * @exception StringIndexOutOfBoundsException
     *            If the CSV file has an extension with 4 or less
     *            characters then the .substring method will be
     *            out of range which crashes the program
     *            - this try catch ignore is used to improve
     *            user-friendly-ness - by not adding the incomplete
     *            extension.
     *
     * @exception FileNotFoundException
     *            If the file is not found the entire program is
     *            halted and an error is thrown the try-catch exception
     *            Prevents the program from halting and notifies
     *            the user that the specified file does not exist.
     *            Improving user-friendly-ness.
     * */
    public void extractAndCombineFromCSV(String filename, Directory directory) throws IOException {
        try {

            FileReader file = new FileReader(filename + ".csv");
            BufferedReader br = new BufferedReader(file);

            String line;
            // While there are lines of text - each line of words will be split by each comma
            while ((line = br.readLine()) != null) {
                String[] tempEntry = line.split(",");

                try {
                    directory.insertEntry(new Entry(tempEntry[0], tempEntry[1], tempEntry[2].substring(0, 5)));
                } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException ignored){}
            }
            br.close();     // Closes the buffer writer
            file.close();   // Closes the file

        } catch (FileNotFoundException e){
            System.out.println("File '"+filename+"' not found!");
        }
    }


    /**
     * This method allows the user to input an entry to any
     * given directory. The user is prompted to input 3 strings
     * Where each one is used respectively as surname, initials
     * and extension. Only the extension is validated as this
     * is the only field stated to have rules in the specification.
     * However a user may not input numbers for initials - but
     * may input numbers for surnames to distinguish people of the
     * same surname.
     *
     * The extension is ensured to be a length of five by a while
     * loop prompting the user to re-input the extension if the
     * length of the extension is not five. However if a user puts in
     * numbers with letters - the letters are removed using replaceAll
     * which then prevents the user from entering numbers mixed with
     * letters i.e 00AAA would become 00 which has a length less than
     * 5 which is not accepted - hence the user is prompted to enter
     * the extension again.
     *
     * @param directory
     *        Used to specify the directory to insert the entry into
     * */

    public void inputEntry (Directory directory){
        Scanner scanner = new Scanner(System.in);

        // Prompts user to enter a surname and Stores the user-input as surname
        System.out.print("\nEnter surname: ");
        String surname = scanner.nextLine();

        // Prompts user to enter initials and Stores the user-input as initials
        System.out.print("Enter initials: ");
        String initials = scanner.nextLine();
        initials = initials.replaceAll("[^A-Za-z.]",""); // Disallows numbers for initials


        String extension = "";
        // While loop ensures user must enter only 5 characters
        while (extension.length() < 5) {
            System.out.print("Enter valid extension (Numbers only - Length of 5): ");
            extension = scanner.nextLine();
            extension = extension.replaceAll("[^0-9]", "");
            if (extension.length() > 5){
                extension = extension.substring(0, 5);
            }
        }
        directory.insertEntry(new Entry(surname, initials, extension));
    }

}
