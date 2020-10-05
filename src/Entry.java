public class Entry {
    /**
     * This value is used to store the surname
     * */
    private String surname;
    /**
     * This value is used to store the initials
     * */
    private String initials;
    /**
     * This value is used to store the extension
     * */
    private String extension;

    /**
     * Initializes a newly created entry. Consisting of 3 Strings.
     *
     * @param surname
     *        The String value of the surname
     * @param initials
     *        The String value of the initials
     * @param extension
     *        The String value of the extension
     * */
    public Entry(String surname, String initials, String extension){
        this.surname = surname;
        this.initials = initials;
        this.extension = extension;
    }

    /**
     * @return a string representation of all
     *         of the attributes belonging to an entry.
     * */
    @Override
    public String toString() {
        return surname+" "+initials+" "+extension;
    }

    /**
     * @return an entry's surname value.
     * */
    public String getSurname(){
        return this.surname;
    }

    /**
     * @return an entry's initials value.
     * */
    public String getInitials() {
        return this.initials;
    }

    /**
     * @return an entry's extension value.
     * */
    public String getExtension(){
        return this.extension;
    }

    /**
     * Modifies an entry's extension according to
     * the parameter, extension, passed.
     *
     * @param extension
     *        The string value to set extension to
     * */
    public void setExtension(String extension){
        this.extension = extension;
    }
}
