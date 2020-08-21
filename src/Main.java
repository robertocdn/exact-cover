import java.util.LinkedList;
import java.util.ArrayList;
import models.*;
import parsers.*;

public class Main {
    public static void main(String[] args) {
        ArrayList<SubSet> subSets = new ArrayList<SubSet>();
        LinkedList<SubSet> queue = new LinkedList<SubSet>();
        boolean[] takenNumbers = FileParser.toData("/workspace/exact-cover/src/data/exact.txt", subSets);
    
        for (SubSet subSet : subSets) {
            queue.add(subSet);
        }

        while (queue.size() != 0) {
            SubSet subSet = queue.poll();
            System.out.println(subSet.getValues()[0]);
        }
    }
}

