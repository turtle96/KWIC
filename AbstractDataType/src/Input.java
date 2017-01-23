import java.util.ArrayList;

/** Interface to retrieve input from user
 * */
public interface Input {
    
    public void readInput();
    
    public ArrayList<ArrayList<String>> getTitles();
    
    public ArrayList<String> getWordsToIgnore();
    
}
