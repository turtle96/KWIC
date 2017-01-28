package kwic;
import java.util.ArrayList;

/** Interface to output indexed list to user
 * */
public interface Output {
    
    /** Displays indexed list and writes to file **/
    public void displayIndexAndWriteToFile(ArrayList<ArrayList<String>> titles, ArrayList<Address> addresses);
    
}
