/**
 * @author Bradley Read
 * @version 1.0
 * @since 03-02-2020
 */

import java.util.List;

public interface Directory {

    /**
     * Insert a new entry into the directory.
     *
     * @param entry the new entry to add
     */
    void insertEntry(Entry entry);

    /**
     * Remove an entry from the directory using their surname.
     *
     * @param surname the surname of the entry to remove
     */
    void deleteEntryUsingName(String surname);

    /**
     * Remove an entry from the directory using their extension number.
     *
     * @param number the extension number of the entry to remove
     */
    void deleteEntryUsingExtension(String number);

    /**
     * Update an entry's extension number using their surname.
     *
     * @param surname   surname of the entry to be updated
     * @param newNumber the new number
     */
    void updateExtensionUsingName(String surname, String newNumber);

    /**
     * Get the extension number of an entry using their surname.
     *
     * @param surname the surname of the entry
     * @return the extension number of the entry
     */
    String lookupExtension(String surname);

    /**
     * Return an array list of all entries in the directory.
     *
     * @return an array list of all entries
     */
    List<Entry> toArrayList();

}
