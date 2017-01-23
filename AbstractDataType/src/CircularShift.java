import java.util.ArrayList;

/** Interface to shift titles circularly by keywords and ignoring 'words to ignore'
 * */
public interface CircularShift {
    
    public void shift();
    
    public ArrayList<Address> getAddresses();
}
