package kwic;

/** Data holder for indexes of titles and keywords in ArrayList
 * */
public class Address {
    private int lineIndex;  //the title
    private int startIndex; //starting point, or keyword
    
    public Address(int lineIndex, int startIndex) {
        this.lineIndex = lineIndex;
        this.startIndex = startIndex;
    }
    
    public int getLineIndex() {
        return lineIndex;
    }
    
    public int getStartIndex() {
        return startIndex;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Address) {
            Address address = (Address) obj;
            return this.lineIndex==address.getLineIndex() 
                    && this.startIndex==address.getStartIndex();
        }
        else {
            return false;
        }     
    }
}
