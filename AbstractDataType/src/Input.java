import java.util.ArrayList;

/** Interface to retrieve input from user
 * */
public interface Input {
    
    public void getInput();
    
    public ArrayList<ArrayList<String>> getTitles();
    
    public ArrayList<String> getWordsToIgnore();
    
}
