package kwic;
import java.util.ArrayList;

/** Interface to output indexed list to user
 * */
public interface Output {
    
    public void display(ArrayList<ArrayList<String>> titles, ArrayList<Address> addresses);
    
}
