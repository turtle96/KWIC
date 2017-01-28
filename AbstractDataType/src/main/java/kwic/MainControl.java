package kwic;
import java.util.ArrayList;
import java.util.Scanner;

/** Main class
* */
public class MainControl {

    public static void main(String[] args) {

        TextUI.printWelcomeMessage();
        
        Input input = executeInput();
        ArrayList<ArrayList<String>> titles = input.getTitles();

        ArrayList<Address> shiftedAddresses = executeCircularShift(input, titles);

        ArrayList<Address> sortedAddresses = executeAlphabetize(titles, shiftedAddresses);

        executeOutput(titles, sortedAddresses);
    }

    private static void executeOutput(ArrayList<ArrayList<String>> titles, ArrayList<Address> sortedAddresses) {
        Output output = new KwicOutput();
        output.displayIndexAndWriteToFile(titles, sortedAddresses);
    }

    private static ArrayList<Address> executeAlphabetize(ArrayList<ArrayList<String>> titles,
            ArrayList<Address> shiftedAddresses) {
        Alphabetize alphabetize = new KwicAlphabetize();
        alphabetize.sort(titles, shiftedAddresses);
        ArrayList<Address> sortedAddresses = alphabetize.getAddresses();
        return sortedAddresses;
    }

    private static ArrayList<Address> executeCircularShift(Input input, ArrayList<ArrayList<String>> titles) {
        CircularShift circularShift = new KwicCircularShift();
        circularShift.shift(titles, input.getWordsToIgnore());
        ArrayList<Address> shiftedAddresses = circularShift.getAddresses();
        return shiftedAddresses;
    }

    private static Input executeInput() {
        Input input = new KwicInput();
        input.readInput(new Scanner(System.in));
        return input;
    }

}
