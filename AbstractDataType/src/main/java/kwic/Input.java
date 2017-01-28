package kwic;
import java.util.ArrayList;
import java.util.Scanner;

/** Interface to retrieve input from user
 * */
public interface Input {
    
    /** Given Scanner, reads in input for both titles and words to ignore
     *  saves parsed data as ArrayLists
     **/
    public void readInput(Scanner scanner);
    
    /** Returns parsed list of titles **/
    public ArrayList<ArrayList<String>> getTitles();
    
    /** Returns parsed list of words **/
    public ArrayList<String> getWordsToIgnore();
   
}
