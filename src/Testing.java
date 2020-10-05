import java.io.IOException;

public class Testing {

    /**
     * This method validates the correctness
     * of the behaviour of my system when
     * inserting duplicate entries.
     *
     * This output 1 all directories do not
     * store duplicates.
     *
     * @param directory
     *        The directory to test
     * */
    private static void validatingDirectoryInsertion(Directory directory){
        directory.insertEntry(new Entry("Taylor","L.T","00156"));
        directory.insertEntry(new Entry("Taylor","T.T","00088"));
        System.out.println("(HashMap) Size after two duplicate insertions: "+directory.toArrayList().size());
    }

    /**
     * This method validates the correctness
     * of the behaviour of my system when
     * searching for extensions under different
     * circumstances.
     *
     * @param directory
     *        The directory to test
     * */
    private static void validateDirectoryLookup(Directory directory){
        System.out.println(directory.lookupExtension("Taylor"));
    }

    public static void main(String[] args) throws IOException {

        Input input = new Input();

        ArrayDirectory arrayDirectory = new ArrayDirectory();
        ArrayListDirectory arrayListDirectory = new ArrayListDirectory();
        HashMapDirectory hashMapDirectory = new HashMapDirectory();

        validatingDirectoryInsertion(arrayDirectory);
        validatingDirectoryInsertion(arrayListDirectory);
        validatingDirectoryInsertion(hashMapDirectory);

        HashMapDirectory hashMapDirectory2 = new HashMapDirectory();

        input.extractAndCombineFromCSV("csv files/test_data_unique",arrayListDirectory);
        System.out.println("Size of ArrayListDirectory: "+arrayListDirectory.toArrayList().size());

        validateDirectoryLookup(arrayDirectory);
        validateDirectoryLookup(arrayListDirectory);
        validateDirectoryLookup(hashMapDirectory2);

    }

}
