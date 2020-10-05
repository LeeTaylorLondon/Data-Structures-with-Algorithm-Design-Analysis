import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PerformanceAnalysis {

    /**
     * This method given an array of numbers or Longs
     * calculates and returns the smallest, largest and mean.
     *
     * @param times
     *        The arrayList of times to calculate
     *        smallest, largest and mean from
     * */
    private static Long[] analyseTimes (ArrayList<Long> times){

        // Pre-define array to store smallest, largest and average
        Long[] results = new Long[3];

        Long slowest = times.get(0);
        Long fastest = times.get(0);
        Long average = 0L;

        // Calculates slowest time
        for (Long time : times){
            if (time > slowest)
                slowest = time;
        }

        // Calculates fastest time
        for (Long time : times){
             if (time < fastest && time != 0)
                 fastest = time;
        }

        // Calculates average/mean
        for (Long time : times){
            average += time;
        }
        average = average/times.size();

        // Assigns results to array to return
        results[0] = fastest;
        results[1] = slowest;
        results[2] = average;

        return results;
    }

    /**
     * This method creates an arrayList of pre-defined
     * entries using unique test data - which is then
     * used for testing insertion
     *
     * @see #testInsertionArray()
     * @see #testInsertionArrayList()
     * @see #testInsertionHashMap()
     *
     * */
    public static List<Entry> preDefinedEntriesUnique(){

        Input input = new Input();

        // Creating a Pre-Defined ArrayList of 1000 Entry Objects
        ArrayListDirectory arrayListDirectoryForPreDefine = new ArrayListDirectory();

        try {
            input.extractAndCombineFromCSV("csv files/test_data_unique", arrayListDirectoryForPreDefine);
        } catch (IOException ignored) { }

        return arrayListDirectoryForPreDefine.toArrayList();
    }

    /**
     * This method creates an arrayList of pre-defined
     * entries using duplicate test data - which is then
     * used for testing insertion
     *
     * @see #testInsertionArrayDuplicate()
     * @see #testInsertionArrayListDuplicate()
     * @see #testInsertionHashMapDuplicate()
     * */
    public static List<Entry> preDefinedEntriesDuplicate(){

        Input input = new Input();

        // Creating a Pre-Defined ArrayList of 1000 Entry Objects
        ArrayListDirectory arrayListDirectoryForPreDefine = new ArrayListDirectory();

        try {
            input.extractAndCombineFromCSV("csv files/test_data", arrayListDirectoryForPreDefine);
        } catch (IOException ignored) { }

        return arrayListDirectoryForPreDefine.toArrayList();
    }

    /**
     * This method tests deleting entries by surname
     * For each iteration the tested directory is combined
     * with a CSV file of unique data this directory is
     * then converted into an arrayList - to get the
     * middle item's surname by using arrayList.get(500).getSurname();
     * The deletion takes place and is timed accordingly with
     * each timing added to an arrayList referred to as times.
     * The stopwatch is reset to correctly time the next deletion.
     *
     * However after deletion, and combination with the CSV file
     * the directory is no longer the same as before therefore deleting the
     * 500th entry by a hardcoded name would no longer work as the entry
     * depending on the directory used would no longer occupy the middle index.
     * Hence the use of converting the directory's contents into a
     * arrayList to then get the middle item.
     *
     * The exact same concept of this method is used for testing
     * other deletion methods such as extension on unique and
     * duplicate data.
     *
     * @param directory
     *        This determines the directory to test
     *
     * @see #testDeleteEntryBySurnameDuplicate(Directory)
     * @see #testDeleteEntryByExtensionAnyDirectory(Directory)
     * @see #testDeleteEntryByExtensionDuplicate(Directory)
     * */
    private static ArrayList<Long> testDeleteEntryBySurnameAnyDirectory(Directory directory) throws IOException {
        // Stores the timings from deleting entries
        ArrayList<Long> times = new ArrayList<>();
        // Input object used to extract from a CSV
        Input input = new Input();
        StopWatch stopWatch = new StopWatch();

        for (int i = 0; i < 10000; i++) {
            input.extractAndCombineFromCSV("csv files/test_data_unique", directory);
            List<Entry> arrayListDir = directory.toArrayList(); // Directory contents converted into an ArrayList
            String middleEntry = arrayListDir.get(500).getSurname(); // The middle item is found from the arrayList

            // The deletion is timed by starting the stopwatch before and ending it after
            stopWatch.start();
            directory.deleteEntryUsingName(middleEntry);
            stopWatch.stop();
            // The time is added to the arrayList times and the stopwatch is reset for the next deletion
            times.add(stopWatch.getElapsedTime());
            stopWatch.reset();
        }

        return times;

    }

    /**
     * This method has the exact same premise and concept
     * as the method testDeleteEntryBySurnameAnyDirectory
     * except for - this deletes by extension
     *
     * @see #testDeleteEntryBySurnameAnyDirectory
     * */
    private static ArrayList<Long> testDeleteEntryByExtensionAnyDirectory(Directory directory) throws IOException {
        // Stores the timings from deleting entries
        ArrayList<Long> times = new ArrayList<>();
        // Input object used to extract from a CSV
        Input input = new Input();
        StopWatch stopWatch = new StopWatch(); // Stopwatch - Object Constructed

        for (int i = 0; i < 10000; i++) {
            input.extractAndCombineFromCSV("csv files/test_data_unique", directory);
            List<Entry> arrayListDir = directory.toArrayList(); // Directory contents converted into an ArrayList
            String middleEntry = arrayListDir.get(500).getExtension(); // The middle item is found from the arrayList

            // The deletion is timed by starting the stopwatch before and ending it after
            stopWatch.start();
            directory.deleteEntryUsingName(middleEntry);
            stopWatch.stop();
            // The time is added to the arrayList times and the stopwatch is reset for the next deletion
            times.add(stopWatch.getElapsedTime());
            stopWatch.reset();
        }

        return times;

    }

    /**
     * This method given a list of predefined entries
     * being unique or duplicate - a directory to test
     * - and an arrayList of times - will test inserting
     * the predefined entries into the given directory
     * and record the insertion times generated into the
     * arrayList of times passed. This was made to reduce
     * code duplication.
     *
     * The predefined entries are of size 1000 if using
     * unique data. If using duplicate data only 974 entries
     * are added. Each data set is run 10 times. Creating
     * 10000 or 9740 timed insertions.
     *
     * @param preDefinedEntries
     *        The list of predefined entries
     *        to test inserting into a directory
     *
     * @param directory
     *        The directory to test insertion on
     *
     * @param times
     *        The recorded times from inserting
     *        entries
     *
     * @see #testInsertionArray()
     * @see #testInsertionArrayList()
     * @see #testInsertionHashMap()
     * @see #testInsertionArrayDuplicate()
     * @see #testInsertionArrayList()
     * @see #testInsertionHashMapDuplicate()
     * */
    private static void insertion(List<Entry> preDefinedEntries, Directory directory, ArrayList<Long> times){
        StopWatch stopWatch = new StopWatch();

        for (Entry entry : preDefinedEntries) {
            stopWatch.start();
            directory.insertEntry(entry);
            stopWatch.stop();
            times.add(stopWatch.getElapsedTime());
            stopWatch.reset();
        }
    }

    /**
     * This method tests insertion on the arrayDirectory
     * inserting entries from a list of predefined entries.
     * 10 times where the data set used is of size 1000
     * therefore testing 10000 insertions.
     *
     * @see #preDefinedEntriesUnique()
     * @see #insertion(List, Directory, ArrayList)
     *
     * */
    private static ArrayList<Long> testInsertionArray() {

        // Data Structure To Store Times
        ArrayList<Long> times = new ArrayList<>();

        // Creating a Pre-Defined ArrayList of 1000 Entry Objects
        List<Entry> preDefinedEntries = preDefinedEntriesUnique();

        for (int i = 0; i < 10; i++) {

            ArrayDirectory arrayDirectory = new ArrayDirectory();

            insertion(preDefinedEntries, arrayDirectory, times);

        }
        return times;
    }

    /**
     * This method tests insertion on the arrayListDirectory
     * inserting entries from a list of predefined entries.
     * 10 times where the data set used is of size 1000
     * therefore testing 10000 insertions.
     *
     * @see #preDefinedEntriesUnique()
     * @see #insertion(List, Directory, ArrayList)
     * */
    private static ArrayList<Long> testInsertionArrayList() {

        // Data Structure To Store Times
        ArrayList<Long> times = new ArrayList<>();

        List<Entry> preDefinedEntries = preDefinedEntriesUnique();

        for (int i = 0; i < 10; i++) {

            ArrayListDirectory arrayListDirectory = new ArrayListDirectory();
            insertion(preDefinedEntries, arrayListDirectory, times);

        }

        return times;
    }

    /**
     * This method tests insertion on the hashMapDirectory
     * inserting entries from a list of predefined entries.
     * 10 times where the data set used is of size 1000
     * therefore testing 10000 insertions.
     *
     * @see #preDefinedEntriesUnique()
     * @see #insertion(List, Directory, ArrayList)
     *
     * */
    private static ArrayList<Long> testInsertionHashMap() {

        // Data Structure To Store Times
        ArrayList<Long> times = new ArrayList<>();

        List<Entry> preDefinedEntries = preDefinedEntriesUnique();

        for (int i = 0; i < 10; i++) {

            HashMapDirectory hashMapDirectory = new HashMapDirectory();
            insertion(preDefinedEntries, hashMapDirectory, times);

        }

        return times;
    }

    /**
     * This method tests insertion on the arrayDirectory
     * inserting entries from a list of predefined entries.
     * 10 times where the data set used is of size 974
     * therefore testing 9740 insertions.
     *
     * @see #preDefinedEntriesDuplicate()
     * @see #insertion(List, Directory, ArrayList)
     * */
    private static ArrayList<Long> testInsertionArrayDuplicate() {

        // Data Structure To Store Times
        ArrayList<Long> times = new ArrayList<>();

        // Creating a Pre-Defined ArrayList of 1000 Entry Objects
        List<Entry> preDefinedEntries = preDefinedEntriesDuplicate();

        for (int i = 0; i < 10; i++) {

            ArrayDirectory arrayDirectory = new ArrayDirectory();
            insertion(preDefinedEntries, arrayDirectory, times);

        }
        return times;
    }

    /**
     * This method tests insertion on the arrayListDirectory
     * inserting entries from a list of predefined entries.
     * 10 times where the data set used is of size 974
     * therefore testing 9740 insertions.
     *
     * @see #preDefinedEntriesDuplicate()
     * @see #insertion(List, Directory, ArrayList)
     * */
    private static ArrayList<Long> testInsertionArrayListDuplicate() {

        // Data Structure To Store Times
        ArrayList<Long> times = new ArrayList<>();

        // Creating a Pre-Defined ArrayList of 1000 Entry Objects
        List<Entry> preDefinedEntries = preDefinedEntriesDuplicate();

        for (int i = 0; i < 10; i++) {

            ArrayListDirectory arrayListDirectory = new ArrayListDirectory();
            insertion(preDefinedEntries, arrayListDirectory, times);

        }
        return times;
    }

    /**
     * This method tests insertion on the hashMapDirectory
     * inserting entries from a list of predefined entries.
     * 10 times where the data set used is of size 974
     * therefore testing 9740 insertions.
     *
     * @see #preDefinedEntriesDuplicate()
     * @see #insertion(List, Directory, ArrayList)
     * */
    private static ArrayList<Long> testInsertionHashMapDuplicate() {

        // Data Structure To Store Times
        ArrayList<Long> times = new ArrayList<>();

        // Creating a Pre-Defined ArrayList of 1000 Entry Objects
        List<Entry> preDefinedEntries = preDefinedEntriesDuplicate();

        for (int i = 0; i < 10; i++) {

            HashMapDirectory hashMapDirectory = new HashMapDirectory();
            insertion(preDefinedEntries, hashMapDirectory, times);

        }
        return times;
    }

    /**
     * This method has the exact same premise and concept
     * as the method testDeleteEntryBySurnameAnyDirectory
     * except for - this deletes by surname on duplicate
     * data instead of using the unique data set
     *
     * @see #testDeleteEntryBySurnameAnyDirectory
     * */
    private static ArrayList<Long> testDeleteEntryBySurnameDuplicate(Directory directory) throws IOException {

        ArrayList<Long> times = new ArrayList<>();

        Input input = new Input();

        StopWatch stopWatch = new StopWatch(); // Stopwatch - Object Constructed

        for (int i = 0; i < 10000; i++) {
            input.extractAndCombineFromCSV("csv files/test_data", directory);
            List<Entry> arrayListDir = directory.toArrayList();
            String middleEntry = arrayListDir.get(arrayListDir.size()/2).getSurname();

            // System.out.println(middleEntry);
            stopWatch.start();
            directory.deleteEntryUsingName(middleEntry);
            stopWatch.stop();

            times.add(stopWatch.getElapsedTime());
            stopWatch.reset();

        }
        return times;
    }

    /**
     * This method has the exact same premise and concept
     * as the method testDeleteEntryBySurnameAnyDirectory
     * except for - this deletes by extension using a
     * data set containing duplicate entries instead of
     * the unique data set.
     *
     * @see #testDeleteEntryBySurnameAnyDirectory
     * */
    private static ArrayList<Long> testDeleteEntryByExtensionDuplicate(Directory directory) throws IOException {

        ArrayList<Long> times = new ArrayList<>();

        Input input = new Input();

        StopWatch stopWatch = new StopWatch(); // Stopwatch - Object Constructed

        for (int i = 0; i < 10000; i++) {
            input.extractAndCombineFromCSV("csv files/test_data", directory);
            List<Entry> arrayListDir = directory.toArrayList();
            String middleEntry = arrayListDir.get(arrayListDir.size()/2).getExtension();

            // System.out.println(middleEntry);
            stopWatch.start();
            directory.deleteEntryUsingName(middleEntry);
            stopWatch.stop();

            times.add(stopWatch.getElapsedTime());
            stopWatch.reset();

        }
        return times;
    }

    /**
     * The given directory is combined with
     * data from a CSV file containing duplicates - this directory
     * now contains a fixed entry at the middle position
     * The lookup is run 10000 times and recorded each time.
     *
     * This returns a list of times which is analysed using the
     * analyse array method.
     *
     * @param directory
     *        Used to specify the directory tested
     *
     * @see #analyseTimes(ArrayList)
     * */
    private static ArrayList<Long> testLookupAnyDirectoryDuplicate(Directory directory) throws IOException {

        Input input = new Input();
        StopWatch stopWatch = new StopWatch();
        ArrayList<Long> times = new ArrayList<>();

        // Only 974 Unique Entries Are Recorded - Mid Entry @ Line 478 - Gurg,T.V.G,81561
        input.extractAndCombineFromCSV("csv files/test_data",directory);

        for (int i = 0; i < 10000; i++) {
            stopWatch.start();
            directory.lookupExtension("Gurg");
            stopWatch.stop();
            times.add(stopWatch.getElapsedTime());
            stopWatch.reset();
        }

        return times;

    }

    /**
     * The given directory is combined with
     * data from a CSV file of unique data - this directory
     * now contains a fixed entry at the middle position
     * The lookup is run 10000 times and recorded each time.
     *
     * This returns a list of times which is analysed using the
     * analyse array method.
     *
     * @param directory
     *        Used to specify the directory tested
     *
     * @see #analyseTimes(ArrayList)
     * */
    private static ArrayList<Long> testLookupAnyDirectory(Directory directory) throws IOException {

        Input input = new Input();
        StopWatch stopWatch = new StopWatch();
        ArrayList<Long> times = new ArrayList<>();

        input.extractAndCombineFromCSV("csv files/test_data_unique",directory);

        // 501st Entry @ [Pestricke,L.P,52333]
        for (int i = 0; i < 10000; i++) {
            stopWatch.start();
            directory.lookupExtension("Pestricke");
            stopWatch.stop();
            times.add(stopWatch.getElapsedTime());
            stopWatch.reset();
        }

        return times;

    }

    /**
     * There are 18 different directory objects created
     * in order to completely avoid clashing of results by
     * not reusing already used directories for other tests
     * this improves the validity of results significantly.
     *
     * These 18 directory objects are tested respectively
     * by passing them as parameters into the testing methods.
     *
     * For testing insertion the directories tested are pre-defined
     * inside the method body themselves to further improve validity
     * of the results. Hence why there are 18 directory objects
     * instead of 24.
     *
     * For each testing method returns an array list of 10000
     * Long(s) which are passed to the method analyseTimes to
     * calculate the worst, best and average case. The results
     * calculated from the analysis method is added to a 2D array
     * 'results', which is then used in the output class to write
     * to a .txt file the results.
     *
     * All defined testing methods above are run here - summing
     * for a total of 240,000 tests.
     *
     * @see #testDeleteEntryBySurnameAnyDirectory(Directory)
     * @see #testDeleteEntryByExtensionAnyDirectory(Directory)
     * @see #testInsertionArray()
     * @see #testInsertionArrayList()
     * @see #testInsertionHashMap()
     * @see #testLookupAnyDirectory(Directory)
     * @see #testDeleteEntryBySurnameDuplicate(Directory)
     * @see #testInsertionArrayDuplicate()
     * @see #testInsertionArrayListDuplicate()
     * @see #testInsertionHashMapDuplicate()
     * @see #testLookupAnyDirectoryDuplicate(Directory)
     * */
    public static Long[][] allTests() throws IOException {

        // Test: [Insertion, Deletion x2, Lookup] : For : [Array, ArrayList, HashMap] : For : [¬Duplicate, Duplicate]
        // 240,000 tests are run! I deeply apologise for making you wait this long...

        Long[][] results = new Long[24][3];

        ArrayDirectory arrayDirectoryTestDeletionUnique = new ArrayDirectory();
        ArrayListDirectory arrayListDirectoryTestDeletionUnique = new ArrayListDirectory();
        HashMapDirectory hashMapDirectoryTestDeletionUnique = new HashMapDirectory();

        ArrayDirectory arrayDirectoryTestDeletionExtensionUnique = new ArrayDirectory();
        ArrayListDirectory arrayListDirectoryTestDeletionExtensionUnique = new ArrayListDirectory();
        HashMapDirectory hashMapDirectoryTestDeletionExtensionUnique = new HashMapDirectory();

        ArrayDirectory arrayDirectoryTestLookupUnique = new ArrayDirectory();
        ArrayListDirectory arrayListDirectoryTestLookupUnique = new ArrayListDirectory();
        HashMapDirectory hashMapDirectoryTestLookupUnique = new HashMapDirectory();

        // 18 directory objects created to prevent "clashing" ensuring valid results - hence the code duplication

        ArrayDirectory arrayDirectoryTestDeletionDuplicate = new ArrayDirectory();
        ArrayListDirectory arrayListDirectoryTestDeletionDuplicate = new ArrayListDirectory();
        HashMapDirectory hashMapDirectoryTestDeletionDuplicate = new HashMapDirectory();

        ArrayDirectory arrayDirectoryTestDeletionExtensionDuplicate = new ArrayDirectory();
        ArrayListDirectory arrayListDirectoryTestDeletionExtensionDuplicate = new ArrayListDirectory();
        HashMapDirectory hashMapDirectoryTestDeletionExtensionDuplicate = new HashMapDirectory();

        ArrayDirectory arrayDirectoryTestLookupDuplicate = new ArrayDirectory();
        ArrayListDirectory arrayListDirectoryTestLookupDuplicate = new ArrayListDirectory();
        HashMapDirectory hashMapDirectoryTestLookupDuplicate = new HashMapDirectory();

        // Each line generates and stores the best, worst and average case of a method in a 2D array for output
        results[0] = analyseTimes(testDeleteEntryBySurnameAnyDirectory(arrayDirectoryTestDeletionUnique));
        results[1] = analyseTimes(testDeleteEntryBySurnameAnyDirectory(arrayListDirectoryTestDeletionUnique));
        results[2] = analyseTimes(testDeleteEntryBySurnameAnyDirectory(hashMapDirectoryTestDeletionUnique));
        results[3] = analyseTimes(testDeleteEntryByExtensionAnyDirectory(arrayDirectoryTestDeletionExtensionUnique));
        results[4] = analyseTimes(testDeleteEntryByExtensionAnyDirectory(arrayListDirectoryTestDeletionExtensionUnique));
        results[5] = analyseTimes(testDeleteEntryByExtensionAnyDirectory(hashMapDirectoryTestDeletionExtensionUnique));
        results[6] = analyseTimes(testInsertionArray());
        results[7] = analyseTimes(testInsertionArrayList());
        results[8] = analyseTimes(testInsertionHashMap());
        results[9] = analyseTimes(testLookupAnyDirectory(arrayDirectoryTestLookupUnique));
        results[10] = analyseTimes(testLookupAnyDirectory(arrayListDirectoryTestLookupUnique));
        results[11] = analyseTimes(testLookupAnyDirectory(hashMapDirectoryTestLookupUnique));
        results[12] = analyseTimes(testDeleteEntryBySurnameDuplicate(arrayDirectoryTestDeletionDuplicate));
        results[13] = analyseTimes(testDeleteEntryBySurnameDuplicate(arrayListDirectoryTestDeletionDuplicate));
        results[14] = analyseTimes(testDeleteEntryBySurnameDuplicate(hashMapDirectoryTestDeletionDuplicate));
        results[15] = analyseTimes(testDeleteEntryByExtensionDuplicate(arrayDirectoryTestDeletionExtensionDuplicate));
        results[16] = analyseTimes(testDeleteEntryByExtensionDuplicate(arrayListDirectoryTestDeletionExtensionDuplicate));
        results[17] = analyseTimes(testDeleteEntryByExtensionDuplicate(hashMapDirectoryTestDeletionExtensionDuplicate));
        results[18] = analyseTimes(testInsertionArrayDuplicate());
        results[19] = analyseTimes(testInsertionArrayListDuplicate());
        results[20] = analyseTimes(testInsertionHashMapDuplicate());
        results[21] = analyseTimes(testLookupAnyDirectoryDuplicate(arrayDirectoryTestLookupDuplicate));
        results[22] = analyseTimes(testLookupAnyDirectoryDuplicate(arrayListDirectoryTestLookupDuplicate));
        results[23] = analyseTimes(testLookupAnyDirectoryDuplicate(hashMapDirectoryTestLookupDuplicate));

        return results;
    }

}
