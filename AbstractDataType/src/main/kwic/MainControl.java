package kwic;
import java.util.ArrayList;
import java.util.Scanner;

/** Main class
* */
public class MainControl {

    public static void main(String[] args) {
        Input input = new KwicInput();
        input.readInput(new Scanner(System.in));
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
