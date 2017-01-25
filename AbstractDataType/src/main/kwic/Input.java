package kwic;
import java.util.ArrayList;
import java.util.Scanner;

/** Interface to retrieve input from user
 * */
public interface Input {
    
    void readInput(Scanner scanner);
    
    public ArrayList<ArrayList<String>> getTitles();
    
    public ArrayList<String> getWordsToIgnore();
   
}
