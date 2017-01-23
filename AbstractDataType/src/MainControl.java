/** Main class
* */
public class MainControl {

    public static void main(String[] args) {
        Input input = new KwicInput();
        input.readInput();
        
        CircularShift circularShift = new KwicCircularShift();
        circularShift.shift(input.getTitles(), input.getWordsToIgnore());
        
        for (Address address: circularShift.getAddresses()) {
            System.out.println(input.getTitles().get(address.getLineIndex()) + " " + 
                    input.getTitles().get(address.getLineIndex()).get(address.getStartIndex()));
        }
        
    }

}
