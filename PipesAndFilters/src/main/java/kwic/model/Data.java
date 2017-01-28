package kwic.model;

/**
 * Represents data that passes through the system.
 *
 */
public class Data implements Comparable<Data> {

    private String value;
    private boolean isLast;
    
    public Data(String s) {
        value = s;
        isLast = false;
    }
    
    public Data(String s, boolean flag) {
        value = s;
        isLast = flag;
    }
    
    public String getValue() {
        return value;
    }
    
    /**
     * Return true if this is the last data.
     */
    public boolean isLast() {
        return isLast;
    }
    
    @Override
    public String toString() {
        String s = "[" + value + ", " + isLast + "]";
        return s;
    }

    @Override
    public int compareTo(Data data) {
        return value.compareTo(data.getValue());
    }
    
    /**
     * Returns a Data object that signals the end of data flag.
     */
    public static Data createEndOfDataObj() {
        return new Data("eof", true);
    }
    
}
