package kwic;
import java.util.ArrayList;

/** Sort addresses in alphabetical order
 * */
public class KwicAlphabetize implements Alphabetize {
    
    private ArrayList<Address> addresses;

    @Override
    public void sort(ArrayList<ArrayList<String>> titles, ArrayList<Address> addressesToSort) {
        addresses = addressesToSort;
        
        //bubble sort
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

    //given 2 indices and array, swaps the 2 elements
    private void swap(ArrayList<Address> addresses, int i, int j) {
        Address temp = addresses.get(i);
        addresses.set(i, addresses.get(j));
        addresses.set(j, temp);
    }

    //checks if left element is alphabetically greater than right, thus need to swap
    private boolean needToSwap(Address left, Address right, ArrayList<ArrayList<String>> titles) {
        String leftWord = "", rightWord = "";
        ArrayList<String> leftTitle = titles.get(left.getLineIndex()), rightTitle = titles.get(right.getLineIndex());
        int leftIndex = left.getStartIndex(), rightIndex = right.getStartIndex();
        
        do {
            
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
        } while (leftWord.equalsIgnoreCase(rightWord)
                && leftIndex!=left.getStartIndex()
                && rightIndex!=right.getStartIndex());
        
        if (leftIndex!=left.getStartIndex() && rightIndex==right.getStartIndex()) {
            return true;
        }
        
        return false;
    }

    @Override
    public ArrayList<Address> getAddresses() {
        return addresses;
    }

}
