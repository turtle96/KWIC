import java.util.ArrayList;

/** sort addresses in alphabetical order
 * */
public class KwicAlphabetize implements Alphabetize {
    
    private ArrayList<Address> addresses;
    
    public KwicAlphabetize(ArrayList<Address> addressesToSort) {
        this.addresses = addressesToSort;
    }

    @Override
    public void sort(ArrayList<ArrayList<String>> titles) {
        boolean swapped;
        int lastIndex = addresses.size();
        do {
            swapped = false;
            
            for (int i=1; i<lastIndex; i++) {
                if (needToSwap(addresses.get(i-1), addresses.get(i), titles)) {
                    swap(addresses, i, i-1);
                    swapped = true;
                }
            }
            lastIndex--;
        } while (swapped == true); 
    }

    private void swap(ArrayList<Address> addresses, int i, int j) {
        Address temp = addresses.get(i);
        addresses.set(i, addresses.get(j));
        addresses.set(j, temp);
    }

    private boolean needToSwap(Address left, Address right, ArrayList<ArrayList<String>> titles) {
        String leftWord = "", rightWord = "";
        ArrayList<String> leftTitle = titles.get(left.getLineIndex()), rightTitle = titles.get(right.getLineIndex());
        int leftIndex = left.getStartIndex(), rightIndex = right.getStartIndex();
        
        while (leftWord.equalsIgnoreCase(rightWord)) {
            
            leftWord = leftTitle.get(leftIndex);
            rightWord = rightTitle.get(rightIndex);
            
            leftIndex++;
            rightIndex++;
            leftIndex%=leftTitle.size();
            rightIndex%=rightTitle.size();
            
            if (leftWord.compareToIgnoreCase(rightWord) > 0) {
                return true;
            } 
            else if (leftWord.compareToIgnoreCase(rightWord) < 0) {
                return false;
            }
        }
        
        return false;
    }

    @Override
    public ArrayList<Address> getAddresses() {
        return addresses;
    }

}
