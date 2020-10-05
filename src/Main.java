import java.io.IOException;
import java.util.Scanner;

public class Main {

    /**
     * Entry-point
     *
     * Prompts the user to select a directory-
     * data-structure to use. Then puts the user
     * in a continuous loop giving them 9 options
     * to manipulate their chosen directory with
     * entries either via manual input or from a CSV
     * file.
     *
     * See README.md for more details about this class.
     * Each command is explained there. Found in the
     * top-level directory for this project.
     *
     * Case-switch statements are used to dictate
     * the control flow of the program based on user
     * input in order to execute their desired commands.
     * */
    public static void main(String[] args) throws IOException {

        // Scanner Object Constructed
        Scanner scanner = new Scanner(System.in);

        // I/O Objects Constructed
        Input input = new Input();
        Output output = new Output();

        // Constructs
        Directory directory = null;

        while(directory == null){
            System.out.println("\nPlease choose a valid data structure to use!");
            System.out.println("1 - Array");
            System.out.println("2 - ArrayList");
            System.out.println("3 - HashMap");
            System.out.print("Input 1 or 2 or 3: ");

            String dataStructureChoice = scanner.nextLine();

            // Data Structure Object Constructed Based On Choice
            switch (dataStructureChoice){
                case "1":
                    directory = new ArrayDirectory();
                    break;
                case "2":
                    directory = new ArrayListDirectory();
                    break;
                case "3":
                    directory = new HashMapDirectory();
                    break;
            }
        }

        String choice;

        while (true) {
            System.out.println("\nPlease choose a valid choice");
            // Input Method
            System.out.println("1 - Input Entry");
            // Directory Methods
            System.out.println("2 - Delete Entry By Surname");
            System.out.println("3 - Delete Entry By Extension");
            System.out.println("4 - Lookup Extension By Surname");
            System.out.println("5 - Update Extension By Surname");
            // Input Method
            System.out.println("6 - Extract From CSV File");
            // Output methods
            System.out.println("7 - Display Contents As Table");
            System.out.println("8 - Output Contents To CSV File @ 'csv files/Output.csv'");
            System.out.println("9 - Output Performance Analysis");

            System.out.print("Enter choice: ");
            choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    input.inputEntry(directory);
                    break;
                case "2":
                    System.out.print("\nEnter surname: ");
                    directory.deleteEntryUsingName(inputString());
                    break;
                case "3":
                    System.out.print("\nEnter Extension: ");
                    directory.deleteEntryUsingExtension(inputString());
                    break;
                case "4":
                    System.out.print("\nEnter Surname: ");
                    System.out.print(directory.lookupExtension(inputString())+"\n");
                    break;
                case "5":
                    System.out.print("\nEnter Surname: ");
                    String surname = inputString();
                    System.out.print("Enter New Extension: ");
                    String newNumber = inputString();
                    directory.updateExtensionUsingName(surname, newNumber);
                    break;
                case "6":
                    System.out.println("Format --> <Folder>/<Folder>/.../<Filename> (Do Not Specify The Extension)");
                    System.out.print("Enter Directory and Filename To Extract From: ");
                    input.extractAndCombineFromCSV(inputString(), directory);
                    break;
                case "7":
                    output.outputEntriesAsTable(directory);
                    break;
                case "8":
                    output.writeToCSV(directory);
                    break;
                case "9":
                    System.out.println("\nRunning 240,000 Tests - Please Wait\n");
                    output.outputPerformanceStatistics(PerformanceAnalysis.allTests(), "src/Performance.txt");
                    break;
            }
        }
    }

    /**
     * This method is used to take input
     * from the user. This method was made
     * to significantly reduce code duplication.
     *
     * @return {@code String} returns the user's
     *         input which is of type String.
     * */
    private static String inputString(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

}
