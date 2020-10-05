import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class ArrayDirectory implements Directory {
    /**
     * This value is used to point
     * to the next empty position in the array
     * */
    private int pointer;
    /**
     * This array is used to store the entry objects
     * */
    private Entry[] entriesArray = new Entry[0];

    /**
     * This method inserts an entry.
     *
     * To insert an entry the current array of entries
     * are checked to see if an entry already exists
     * with the new entry's surname - if an entry
     * does already exist with the surname then the
     * boolean "entryAlreadyExists" is set to true
     * - if an entry does not exist with this surname
     * then "entryAlreadyExists" is set to false.
     *
     * If "entryAlreadyExists" is true
     * then the insertion process is not executed;
     * if "entryAlreadyExists" is false
     * and the extension is only 5 characters then
     * a copy of the current array is created with
     * a size of plus 1, and the new entry is
     * inserted at the end of the array.
     *
     * @param entry
     *        The entry object to insert
     * */
    @Override
    public void insertEntry(Entry entry) {
        // This boolean indicates true if an entry already exists with that surname - or false if not
        boolean entryAlreadyExists = false;
        for (Entry entryObject : this.entriesArray){
            // Each entry object is checked to see an object already exists with that surname
            if (entryObject.getSurname().equalsIgnoreCase(entry.getSurname())){
                // If an entry does exist with that surname then the boolean "entryAlreadyExists" is set to true
                entryAlreadyExists = true;
                break;
            }
        }

        if (!entryAlreadyExists && entry.getExtension().length() == 5){
            // Extends the array to store one more object - by creating a copy of the current array but with an extra space
            this.entriesArray = Arrays.copyOf(this.entriesArray, this.entriesArray.length+1);
            this.entriesArray[pointer] = entry;
            this.pointer ++; // Increases the pointer to point to the next empty location
    }
    }

    /**
     * This method iterates through the array of
     * entries and checks if their surname
     * matches the surname passed - if the passed surname
     * matches the compared entry object in the
     * array then the method to delete an entry
     * is called - the break statement is used to
     * stop the for loop as no other entry will
     * have the same surname.
     *
     * @param surname
     *        This specifies the entry to delete
     *        based on surname
     *
     * @see   #deleteEntry(int)
     * */
    @Override
    public void deleteEntryUsingName(String surname) {
        for (int i = 0; i < this.entriesArray.length; i++){
            // Check each entry-object if it's surname matches the argument passed
            if (this.entriesArray[i].getSurname().equalsIgnoreCase(surname)){
                deleteEntry(i);
                break;
            }
        }
    }

    /**
     * This method iterates through the array of
     * entries and checks if their extension
     * matches the extension passed - if the passed extension
     * matches the compared entry object in the
     * array then the method to delete an entry
     * is called - the break statement is used to
     * stop the for loop as this only deletes the
     * first entry it comes across with the passed extension.
     *
     * @param number
     *        This specifies the entry to delete
     *        based on extension
     *
     * @see   #deleteEntry(int)
     * */
    @Override
    public void deleteEntryUsingExtension(String number) {
        for (int i = 0; i < this.entriesArray.length; i++){
            // Check each entry-object if it's extension matches the argument passed
            if (this.entriesArray[i].getExtension().equals(number)){
                deleteEntry(i);
                break;
            }
        }
    }

    /**
     * This method changes an entry's extension.
     *
     * The entry to change is specified by the passed
     * surname. The new number passed, is
     * changed to only contain numbers using replaceAll
     * to prevent users from updating an extension
     * with invalid characters - and then the updated
     * new number's length is validated. Each entry object
     * is iterated through checking if it's surname
     * matches the input - if it does then the entry's
     * extension is changed through setting the extension
     * to the newNumber passed. Otherwise every object is
     * iterated through with no effect to the directory.
     *
     * After changing an extension, the loop no longer
     * needs to run - hence the use of the break statement.
     *
     * @param surname
     *        Used to specify the entry to change
     *
     * @param newNumber
     *        Used to specify the new extension for
     *        the entry
     * */
    @Override
    public void updateExtensionUsingName(String surname, String newNumber) {
        newNumber = newNumber.replaceAll("[^0-9]","");
        if (newNumber.length() == 5){
            for (Entry entry : this.entriesArray) {
                // Check each entry-obj if it's surname matches the argument passed
                if (entry.getSurname().equals(surname)) {
                    entry.setExtension(newNumber);
                    break;
                }
            }
        }
    }

    /**
     * This method iterates through all entry objects,
     * if the entry object it is currently looking at
     * matches the passed surname then the entry's
     * extension is returned
     *
     * @param surname
     *        Used to specify the entry to lookup
     *
     * @return an extension is returned
     *         if an entry with the passed surname is found;
     *         otherwise null is returned
     *
     * */
    @Override
    public String lookupExtension(String surname) {
        for (Entry entry : this.entriesArray){
            // Check each entry-obj if it's surname matches the argument passed
            if (entry.getSurname().equalsIgnoreCase(surname)){
                return entry.getExtension();
            }
        }
        return null;
    }

    /**
     * This method returns an arrayList equivalent
     * of the array directory by adding all elements
     * to an arrayList and returning it
     *
     * @return List<Entry> all elements from the array
     * */
    @Override
    public List<Entry> toArrayList() {
        // This converts the current array into an arrayList of entries
        return new ArrayList<>(Arrays.asList(this.entriesArray));
    }

    /**
     * This method deletes any entry-object in the array.
     * The entry-object to remove is, swapped, with the
     * entry-object stored at the last position in the array.
     *
     * Where the last element is the entry-object to delete.
     * The contents of the array is overwritten with the
     * current contents of the array, but with the last element
     * omitted. Using Arrays.copyOf(...).
     *
     * The pointer must be updated otherwise it will point
     * to an index out of range.
     *
     * @param i
     *        This points to the element to remove
     * */
    private void deleteEntry(int i){
        // Swapping process
        Entry temp = this.entriesArray[this.entriesArray.length - 1]; // The last entry in the array is held in temp
        // Then the last entry is overwritten with the entry to remove
        this.entriesArray[this.entriesArray.length - 1] = this.entriesArray[i];
        this.entriesArray[i] = temp; // The position of the entry to remove is overwritten with the contents of temp

        this.entriesArray = Arrays.copyOf(this.entriesArray, this.entriesArray.length - 1);
        this.pointer --;
    }

}
