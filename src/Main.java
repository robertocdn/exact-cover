import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Collections;
import models.*;
import parsers.*;

public class Main {
    public static void main(String[] args) {
        ArrayList<SubSet> subSets = new ArrayList<SubSet>();
        ArrayList<SubSet> usedSubSets = new ArrayList<SubSet>();
        LinkedList<SubSet> queue = new LinkedList<SubSet>();
        boolean[] takenNumbers = FileParser.toData("/workspace/exact-cover/src/data/exact.txt", subSets);
        boolean resultFound;
        int round = 0;
        int tries = 100;


        for (SubSet subSet : subSets) {
            for (SubSet otherSubSet : subSets) {
                boolean flag = true;
                for (int value : subSet.getValues()) {
                    for (int otherValue : otherSubSet.getValues()) {
                        if(value == otherValue) {
                            flag = false;
                        }
                    }
                }
                if(flag == true) {
                    subSet.addPair(otherSubSet);
                }
            }
        }

        for (SubSet subSet : subSets) {
            for (int value: subSet.getValues()) {
                subSet.getUsedValues()[value] = true;
            }
            for (int i = 0; i < subSet.getPairs().size(); i++) {
                a(subSet);
            }
        }
/*
        do {

            resultFound = true;

            Collections.shuffle(subSets);

            for (SubSet subSet : subSets) {
                queue.add(subSet);
            }

            while (queue.size() != 0) {
                SubSet subSet = queue.poll();
                int[] subSetValues = subSet.getValues();
                boolean nonRepeated = true;
                int count = 0;

                while (count < subSetValues.length && nonRepeated == true) {
                    if (subSetValues[count] >= takenNumbers.length) {
                        nonRepeated = false;
                    } else if (takenNumbers[subSetValues[count]] == true) {
                        nonRepeated = false;
                    }
                    count++;
                }

                if (nonRepeated == true) {
                    for (int value : subSetValues) {
                        takenNumbers[value] = true;
                    }
                    usedSubSets.add(subSet);
                }

            }

            int count = 0;

            while (count < takenNumbers.length && resultFound == true) {
                if (takenNumbers[count] == false) {
                    resultFound = false;
                }
                count++;
            }

            if (resultFound == false) {
                for (int i = 0; i < takenNumbers.length; i++) {
                    takenNumbers[i] = false;
                }
                usedSubSets.clear();
            }
            round++;
        } while (round < tries && resultFound == false);

        if (resultFound == true) {
            System.out.println("Result found in round: " + round + "/" + tries);
            System.out.println("Used Subsets: ");
            for (SubSet subSet : usedSubSets) {
                System.out.print("{ ");
                for (int value : subSet.getValues()) {
                    System.out.print(value + ",");
                }
                System.out.println("}");
            }
        } else {
            System.out.println("No Result found in " + tries + "tries");
        }
        */
    }

    public static void a (SubSet subSet) {
        

        boolean[] used = subSet.getUsedValues().clone();
        
        for (int i = 0; i < subSet.getPairs().size(); i++) {
            boolean flag = true;
            SubSet s  = (SubSet)subSet.getPairs().toArray()[i];
            for (int j : s.getValues()) {
                if(used[j] == true) {
                    flag = false;
                }
            }

            if (flag == true) {
                for (int j : s.getValues()) {
                    used[j] = true;
                } 
           
            }

           a(subSet);
        }

        boolean result = true;
        for (boolean b : used) {
            if(b == false) {
                result = false;
            }
        }

         if (result == true) {
            System.out.println("Result found");
         }
    }
}
