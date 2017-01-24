import java.util.ArrayList;

/** compile list of Addresses of keywords and respective titles
 * */
public class KwicCircularShift implements CircularShift {
    
    private ArrayList<Address> addresses = new ArrayList<>();

    @Override
    public void shift(ArrayList<ArrayList<String>> titles, ArrayList<String> wordsToIgnore) {
        int lineIndex = 0, startIndex = 0;
        boolean isValidWord = true;
        
        for (ArrayList<String> title: titles) {
            for (String word: title) {
                isValidWord = isValidWord(wordsToIgnore, word);
                
                if (isValidWord) {
                    addresses.add(new Address(lineIndex, startIndex));
                }
                startIndex++;
                isValidWord = true;
            }
            startIndex = 0;
            lineIndex++;
        }
    }

    private boolean isValidWord(ArrayList<String> wordsToIgnore, String word) {
        for (String ignore: wordsToIgnore) {
            if (word.equalsIgnoreCase(ignore)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ArrayList<Address> getAddresses() {
        return addresses;
    }

}
