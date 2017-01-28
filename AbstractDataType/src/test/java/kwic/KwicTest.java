package kwic;

import java.util.ArrayList;
import java.util.Arrays;

/** contains methods needed for testing
 * */
public abstract class KwicTest {
    
    /** each string is split into words and stored as an array (using space as delimiter)
     *  all the arrays each representing a line/title is then stored into another array
     * */
    protected ArrayList<ArrayList<String>> prepareExpectedTitles(String... strings) {
        ArrayList<ArrayList<String>> expected = new ArrayList<>();
        ArrayList<String> expectedLines = new ArrayList<>(Arrays.asList(strings)); 
        for (String line: expectedLines) {
            expected.add(new ArrayList<>(Arrays.asList(line.split(" "))));
        }
        return expected;
    }
    
    /** string is split into words and stored as an array (using space as delimiter)
     * */
    protected ArrayList<String> prepareExpectedWords(String string) {
        return new ArrayList<>(Arrays.asList(string.split(" ")));
    }
}
