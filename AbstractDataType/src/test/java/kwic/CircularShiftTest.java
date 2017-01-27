package kwic;

import java.util.ArrayList;

import org.junit.Test;

public class CircularShiftTest extends KwicTest {
    @Test
    public void shift_shouldIgnoreWordsCorrectly() {
        CircularShift circularShift = new KwicCircularShift();
        ArrayList<ArrayList<String>> titles = prepareExpectedTitles("the flying chicken of the west and the turkey");
        ArrayList<String> wordsToIgnore = prepareExpectedWords("THE AnD oF");
        circularShift.shift(titles, wordsToIgnore);
        
        assertAddressesMatch(circularShift.getAddresses(), titles, "flying", "chicken", "west", "turkey");
    }
    
    private void assertAddressesMatch(ArrayList<Address> addresses, ArrayList<ArrayList<String>> titles, 
            String... expected) {
        int i = 0;
        for (String str: expected) {
            Address address = addresses.get(i);
            String keyword = titles.get(address.getLineIndex()).get(address.getStartIndex());
            if (!keyword.equals(str)) {
                assert false;
            }
            i++;
        }
        assert true;
    }
}
