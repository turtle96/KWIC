import java.util.ArrayList;

public class KwicCircularShift implements CircularShift {
    
    private ArrayList<Address> addresses = new ArrayList<>();

    @Override
    public void shift(ArrayList<ArrayList<String>> titles, ArrayList<String> wordsToIgnore) {
        int lineIndex = 0, startIndex = 0;
        boolean ignoreWord = false;
        
        for (ArrayList<String> title: titles) {
            for (String word: title) {
                for (String ignore: wordsToIgnore) {
                    if (word.equals(ignore)) {
                        ignoreWord = true;
                    }
                }
                
                if (!ignoreWord) {
                    addresses.add(new Address(lineIndex, startIndex));
                }
                startIndex++;
                ignoreWord = false;
            }
            startIndex = 0;
            lineIndex++;
        }
    }

    @Override
    public ArrayList<Address> getAddresses() {
        return addresses;
    }

}
