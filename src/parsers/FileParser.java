package parsers;
import java.io.*;
import java.util.ArrayList;
import models.*;

public class FileParser {
    
    public static boolean[] toData(String path, ArrayList<SubSet> subSets) {

        String st;
        boolean firstRow = true;
        String[] data;
        int[] dataInt;
        boolean[] takenNumbers = null;

        try {
            
            File file = new File(path);

            BufferedReader br = new BufferedReader(new FileReader(file));

            while ((st = br.readLine()) != null) {
                if(st.length() > 0) {
                    if (firstRow == true) {
                        takenNumbers = new boolean[Integer.parseInt(st.trim())];
                        firstRow = false;
                    } else {
                        data = st.split(",");
                        dataInt = new int[data.length];

                        for (int i = 0; i < data.length; i++) {
                            dataInt[i] = Integer.parseInt(data[i]);
                        }

                        SubSet subSet = new SubSet(dataInt, takenNumbers.length);

                        subSets.add(subSet);
                    }
                }
            }

            br.close();
        } catch (Exception e) {
            System.out.println(e);
        } 
        return takenNumbers;
    }
}