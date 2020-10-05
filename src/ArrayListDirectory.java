import java.util.ArrayList;
import java.util.List;

public class ArrayListDirectory implements Directory{

    /**
     * This arrayList is used to store the entry objects
     * */
    private ArrayList<Entry> entriesList = new ArrayList<>();

    /**
     * To insert an entry the current arrayList of entries
     * are checked to see if an entry already exists
     * with the new entry's surname - if an entry
     * does already exist with the surname then the
     * boolean "entryAlreadyExists" is set to true
     * - if an entry does not exist with this surname
     * then "entryAlreadyExists" is set to false.
     *
     * If the boolean "entryAlreadyExists" is true
     * then the insertion process is not executed
     * if "entryAlreadyExists" is false
     * and the extension is only 5 characters then
     * the entry is inserted.
     *
     * @param entry
     *        The entry object to insert
     * */
    @Override
    public void insertEntry(Entry entry) {
        boolean entryAlreadyExists = false;
        for (Entry entryObj : entriesList){
            // Each entry object in the directory is checked for the surname of the entry to be inserted
            if (entryObj.getSurname().equalsIgnoreCase(entry.getSurname())){
                entryAlreadyExists = true;
                break;
            }
        }
        // If there is not an entry that already exists in the directory with the new entry's surname then it is added
        if (!entryAlreadyExists && entry.getExtension().length() == 5){
            this.entriesList.add(entry);
        }
    }

    /**
     * This method iterates through the arrayList of
     * entries and checks if their surname
     * matches the surname passed - if the surname
     * matches the compared entry object in the
     * array then the method to delete an entry
     * is called.
     *
     * The break statement is used to
     * stop the for loop as no other entry will
     * have the same surname.
     *
     * @param surname
     *        This is used to locate the entry
     *        to remove
     * */
    @Override
    public void deleteEntryUsingName(String surname) {
        for (Entry entry : entriesList){
            // Check each entry-obj if it's surname matches the argument passed
            if (entry.getSurname().equalsIgnoreCase(surname)){
                entriesList.remove(entry);
                break;
            }
        }
    }

    /**
     * This method deletes an entry based on a given
     * extension - by iterating through all entry objects
     * and if the entry matches the String number passed
     * then the entry is removed.
     *
     * @param number
     *        This is used to point to the entry to delete
     * */
    @Override
    public void deleteEntryUsingExtension(String number) {
        for (Entry entry : entriesList) {
            // Check each entry-obj if it's surname matches the argument passed
            if (entry.getExtension().equals(number)) {
                entriesList.remove(entry);
                break;
            }
        }

    }

    /**
     * This method changes an entry's extension
     * by using a surname to specify which entry
     * to update.
     *
     * Each entry is unique by surname therefore
     * only a single entry will be updated hence
     * the use of a break statement to stop the
     * for loop.
     *
     * @param surname
     *        Used to point to the entry
     * @param newNumber
     *        The value of the new extension
     *        for the entry
     * */
    @Override
    public void updateExtensionUsingName(String surname, String newNumber) {
        newNumber = newNumber.replaceAll("[^0-9]",""); // Disallows invalid characters for an ext.
        if (newNumber.length() >= 5) {
            for (Entry entry : this.entriesList) {
                // Check each entry-obj if it's surname matches the argument passed - if so change the extension
                if (entry.getSurname().equalsIgnoreCase(surname)) {
                    entry.setExtension(newNumber);
                    break;
                }
            }
        }
    }

    /**
     * This method returns an entry's
     * extension, based on a surname given.
     * By checking each entry for the given
     * surname.
     *
     * @param surname
     *        Used to locate an entry's extension
     *
     * */
    @Override
    public String lookupExtension(String surname) {
        for (Entry entry : this.entriesList){
            // Check each entry-obj if it's surname matches the argument passed - if so then return it's extension
            if (entry.getSurname().equals(surname)){
                return entry.getExtension();
            }
        }
        return null; // If no entry objects contain that surname then null is returned
    }

    /**
     * This method returns the arrayList itself
     * as arrayList is a subset of List.
     * */
    @Override
    public List<Entry> toArrayList() {
        return this.entriesList;
    }

}
