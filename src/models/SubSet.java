package models;

public class SubSet {
    private int[] values;
    private boolean used;

    public SubSet(int[] values) {
        this.values = values;
        this.used = false; 
    }

    public int[] getValues() {
        return values;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }
    
}