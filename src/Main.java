import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import models.*;
import parsers.*;

public class Main {

    private static ArrayList<SubSet> usedSubSets = new ArrayList<SubSet>();
    private static boolean done = false;
    public static void main(String[] args) {
        ArrayList<SubSet> subSets = new ArrayList<SubSet>();
        LinkedList<SubSet> queue = new LinkedList<SubSet>();
        boolean[] takenNumbers = FileParser.toData("/workspace/exact-cover/src/data/exact.txt", subSets);
        boolean resultFound;
        int round = 0;
        int tries = 100;

        Collections.sort(subSets, (subSet1, subSet2) -> Integer.compare(subSet2.getValues().length, subSet1.getValues().length));

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
            usedSubSets.add(subSet);
            for (int value: subSet.getValues()) {
                subSet.getUsedValues()[value] = true;
            }

            recursiveSearch(subSet, 0);
            
            usedSubSets.clear();

            if(done == true) {
                break;
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

    public static void recursiveSearch (SubSet subSet, int start) {
        
        boolean[] used = subSet.getUsedValues();
        boolean loop = true;
        int count = start;
     
        if(start != subSet.getPairs().size() && subSet.getPairs().size() > 0) {
            int end = start == 0 ?  subSet.getPairs().size() : start;
            while(loop == true) {
                boolean flag = true;
                SubSet s  = (SubSet)subSet.getPairs().toArray()[count];
                for (int j : s.getValues()) {
                    if(used[j] == true) {
                        flag = false;
                    }
                }

                if (flag == true) {
                    for (int j : s.getValues()) {
                        used[j] = true;
                    } 
                    usedSubSets.add(s);
                }

                if(count == subSet.getPairs().size() - 1 && start != 0) {
                    count = 0;
                } else if(count == end - 1) {
                    loop = false; 
                } else {
                    count++;
                }
                
            }

            boolean result = true;
            for (boolean b : used) {
                if(b == false) {
                    result = false;
                }
            }

            if (result == true) {
                System.out.println("Result found: ");
                System.out.println("Used Subsets: ");
                for (SubSet usedSubSet : usedSubSets) {
                    System.out.print("{ ");
                    for (int value : usedSubSet.getValues()) {
                        System.out.print(value + ",");
                    }
                    System.out.println("}");
                }
                done = true; 
            } else {
                 recursiveSearch(subSet, start + 1);
            }
        }
       
    }
}
