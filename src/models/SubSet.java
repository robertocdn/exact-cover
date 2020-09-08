package models;

import java.util.ArrayList;

public class SubSet {
    private int[] values;
    ArrayList<SubSet> pairs = new ArrayList<SubSet>();
    private boolean[] usedValues;

    public SubSet(int[] values, int usedValuesLength) {
        this.values = values;
        this.usedValues = new boolean[usedValuesLength];
    }

    public int[] getValues() {
        return values;
    }

    public boolean[] getUsedValues() {
        return usedValues;
    }

    public void addPair(SubSet pair) {
        this.pairs.add(pair);
    }

    public ArrayList<SubSet> getPairs() {
        return pairs;
    }

    public void setPairs(ArrayList<SubSet> pairs) {
        this.pairs = pairs;
    }
    
}