package kwic;

import java.util.ArrayList;

import org.junit.Test;

public class AlphabetizeTest extends KwicTest {
    @Test
    public void sort_shouldSortAlphabetically() {
        Alphabetize alphabetize = new KwicAlphabetize();
        ArrayList<ArrayList<String>> titles = 
                prepareExpectedTitles("the chicken", "the alpha lala zingogogogo", "beta robot", 
                        "the alpha lala", "the Chicken", "the chicken", "zip cat", "zip");
        ArrayList<Address> addressesToSort = new ArrayList<>();
        addressesToSort.add(new Address(0, 1));
        addressesToSort.add(new Address(1, 1));
        addressesToSort.add(new Address(2, 0));
        addressesToSort.add(new Address(2, 1));
        addressesToSort.add(new Address(3, 1));
        addressesToSort.add(new Address(4, 1));
        addressesToSort.add(new Address(5, 1));
        addressesToSort.add(new Address(6, 0));
        addressesToSort.add(new Address(7, 0));
        
        alphabetize.sort(titles, addressesToSort);
        
        ArrayList<Address> expected = new ArrayList<>();
        expected.add(new Address(4, 1));
        expected.add(new Address(3, 1));
        expected.add(new Address(1, 1));
        expected.add(new Address(2, 0));
        expected.add(new Address(0, 1));
        expected.add(new Address(5, 1));
        expected.add(new Address(2, 1));
        expected.add(new Address(7, 0));
        expected.add(new Address(6, 0));
        
        assert alphabetize.getAddresses().equals(expected);
    }
}
