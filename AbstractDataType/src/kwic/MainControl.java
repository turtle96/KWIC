package kwic;
import java.util.ArrayList;

/** Main class
* */
public class MainControl {

    public static void main(String[] args) {
        Input input = new KwicInput();
        input.readInput();
        ArrayList<ArrayList<String>> titles = input.getTitles();

        CircularShift circularShift = new KwicCircularShift();
        circularShift.shift(titles, input.getWordsToIgnore());
        ArrayList<Address> shiftedAddresses = circularShift.getAddresses();

        Alphabetize alphabetize = new KwicAlphabetize(shiftedAddresses);
        alphabetize.sort(titles);
        ArrayList<Address> sortedAddresses = alphabetize.getAddresses();

        Output output = new KwicOutput();
        output.display(titles, sortedAddresses);
        
    }

}
