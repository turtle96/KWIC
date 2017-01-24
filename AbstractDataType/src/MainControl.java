import java.util.ArrayList;

/** Main class
* */
public class MainControl {

    public static void main(String[] args) {
        Input input = new KwicInput();
        input.readInput();
        
        CircularShift circularShift = new KwicCircularShift();
        
        ArrayList<ArrayList<String>> titles = input.getTitles();
        
		circularShift.shift(titles, input.getWordsToIgnore());
		
		ArrayList<Address> addresses = circularShift.getAddresses();
        
        Output output = new KwicOutput();
        output.display(titles, addresses);
        
    }

}
