package kwic;
import java.util.ArrayList;

/** Interface to sort list alphabetically
 * */
public interface Alphabetize {
    
    /** Given list of titles and addresses to sort, sorts by alphabetical order and stores 
     * a list of Addresses to each circular shift for each title 
     * **/
    public void sort(ArrayList<ArrayList<String>> titles, ArrayList<Address> addressesToSort);
    
    /** Returns alphabetically sorted list of Addresses**/
    public ArrayList<Address> getAddresses();
}
