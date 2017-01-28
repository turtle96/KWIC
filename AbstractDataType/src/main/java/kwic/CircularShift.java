package kwic;
import java.util.ArrayList;

/** Interface to shift titles circularly by keywords and ignoring 'words to ignore'
 * */
public interface CircularShift {
    
    /** Given list of titles and list of words to ignore,
     *  prepares and stores list of Addresses representing each circular shift for each title
     * **/
    public void shift(ArrayList<ArrayList<String>> titles, ArrayList<String> wordsToIgnore);
    
    /** Returns list of Addresses for circular shifts **/
    public ArrayList<Address> getAddresses();
}
