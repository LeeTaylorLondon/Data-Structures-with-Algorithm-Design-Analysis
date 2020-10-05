import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HashMapDirectory implements Directory {
    /**
     * This stores all of the entry objects
     * Where the surname of the entry object is the key
     * And the corresponding entry object is the value.
     *
     * The surnames are used as keys and are stored as
     * lower case versions of themselves in order to prevent
     * allowing duplicates through lower and upper case -
     * such that if the surname keys retained casing one
     * could store "Taylor" and "taylor" and "tAylor" and
     * "tayloR" as different entry objects - which are
     * duplicates but due to the functionality of hash maps
     * these would be stored regardless if casing was retained.
     * Hence the use of converting ALL surname keys to lower case.
     * */
    private HashMap<String, Entry> surnameHashMap = new HashMap<String, Entry>();

    /**
     * This stores all of the entry objects
     * Where the extension of the entry object is the key
     * And the corresponding entry object is the value
     * */
    private HashMap<String, Entry> extensionHashMap = new HashMap<String, Entry>();

    /**
     * This inserts an entry only if the new entry's surname
     * Is not used as a key in the surname hash map and if the
     * extension of the entry to insert is not already used as
     * a key in the extension hash map and the extension of the
     * entry to insert is of length 5.
     * Only then is the entry inserted in both hash maps.
     *
     * All surname keys are lower case hence the use of
     * .toLowerCase() when checking if the surname already exists.
     * To prevent duplicate entries such as "taylor" and "Taylor".
     *
     * @param entry
     *        This is the entry to insert
     * */
    @Override
    public void insertEntry(Entry entry) {
        if (!(surnameHashMap.containsKey(entry.getSurname().toLowerCase())) && !(extensionHashMap.containsKey(entry.getExtension()))
                && entry.getExtension().length() == 5){
            surnameHashMap.put(entry.getSurname().toLowerCase(),entry);
            extensionHashMap.put(entry.getExtension(),entry);
        }
    }

    /**
     * If the surname hash map contains the passed surname then
     * the corresponding entry object is removed from the extension
     * hash map first and then removed from the surname hash map.
     *
     * @param surname
     *        Used to specify the entry object to delete
     * */
    @Override
    public void deleteEntryUsingName(String surname) {
        surname = surname.toLowerCase();
        if (surnameHashMap.containsKey(surname.toLowerCase())) {
            extensionHashMap.remove(surnameHashMap.get(surname).getExtension()); // Remove from ext hash map first
            surnameHashMap.remove(surname); // Then remove from the surname hash map
        }
    }

    /**
     * If the extension hash map contains the passed extension then
     * the corresponding entry object is removed from the surname hash
     * map first and then removed from the extension hash map
     *
     * @param number
     *        Used to specify the entry object to delete
     * */
    @Override
    public void deleteEntryUsingExtension(String number) {
        if (extensionHashMap.containsKey(number)) {
            surnameHashMap.remove(extensionHashMap.get(number).getSurname().toLowerCase());
            extensionHashMap.remove(number);
        }
    }

    /**
     * This method updates an entry's extension.
     *
     * By passing a surname and new extension
     * the extension HashMap is checked to see
     * if the new extension is already used. If so
     * then the extension will not be updated,
     * otherwise the length of the new extension
     * will be validated and then the surname HashMap
     * will be checked to see if such an entry with
     * the passed surname exists. If so the outdated
     * extension used as a key in the extension HashMap
     * is removed and then the entry's ext. is updated
     * and then added back to the extension HashMap.
     *
     * @param surname
     *        The entry object to change
     * @param newNumber
     *        The entry object's new extension
     * */
    @Override
    public void updateExtensionUsingName(String surname, String newNumber) {
        // This if statement prevents an entry changing it's current extension to an extension that already exists
        newNumber = newNumber.replaceAll("[^0-9]","");
        surname = surname.toLowerCase(); // All surname keys are lower case
        if (!(extensionHashMap.containsKey(newNumber)) && newNumber.length() == 5 && surnameHashMap.containsKey(surname)) {
            // This removes the outdated key-value pair from extension-HashMap
            extensionHashMap.remove(surnameHashMap.get(surname).getExtension());
            // This updates the extension attribute in the specified entry-object via the surname
            surnameHashMap.get(surname).setExtension(newNumber);
            // Inserts updated entry-object into the extension-HashMap
            extensionHashMap.put(surnameHashMap.get(surname).getExtension(), surnameHashMap.get(surname));
        }
    }

    /**
     * This method returns an extension based on
     * acquiring the entry object determined by the
     * passed surname.
     *
     * @return String-Extension if an entry with the
     *         surname exists; otherwise null if the
     *         entry does not exist.
     * */
    @Override
    public String lookupExtension(String surname) {
        surname = surname.toLowerCase();
        if (surnameHashMap.containsKey(surname)){
            return surnameHashMap.get(surname).getExtension();
        }
        return null;
    }

    /**
     * This returns an arrayList of the surname hash map of
     * entries. As both the surname hash map and extension hash
     * map should remain consistent therefore
     * only one of them needs to be returned. An arrayList is
     * used as it is a subset of List.
     * */
    @Override
    public List<Entry> toArrayList() {
        ArrayList<Entry> entries = new ArrayList<>();
        for (String key: surnameHashMap.keySet()){
            entries.add(surnameHashMap.get(key));
        }
        return entries;
    }

}
