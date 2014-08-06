package Homework.File;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by epic on 30.07.14.
 */
public class File {

    private String fileName;

    public File(String fileName) {

        this.fileName = fileName;
    }

    public  String getFileName() {
        return fileName;
    }




    public static ArrayList<String> readFile(String fileName) throws FileNotFoundException {

        ArrayList<String> fileList = new ArrayList<String>();
        BufferedReader br;

        try {
            br = new BufferedReader(new FileReader(fileName));

            String line;
            while ((line = br.readLine()) != null) {
                //System.out.println("split");
                String [] variants = line.split("\\s+");

                for (String words : variants){
                    fileList.add(words);
                }

                //System.out.println("end");
            }
            br.close();
        } catch (Exception e) {
            System.out.println("File not found: " + e);
        }
        return fileList;
    }

    public static HashMap<String, Integer> generateHashMap(ArrayList<String> currentList) {

        Container c = new Container();


        for (int i = 0; i < currentList.size(); i++) {
            String current = currentList.get(i);
            int count = 0;

            for (int j = 0; j < currentList.size(); j++) {
                if (current.equals(currentList.get(j))) {
                    count++;
                }
                c.hm.put(current, count);
            }
        }
       return c.hm;
    }

    public static void findCurrentWord(HashMap<String, Integer> hashMap) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter word : ");

        String word = null;
        try {
             word = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Map.Entry<String, Integer> set : hashMap.entrySet()){
            String countWord = set.getKey();
            int countValue = set.getValue();
            if (word.equals(countWord)){
                System.out.println("Word : " + countWord.toUpperCase() +    " Word - Count : " + countValue);
                break;
            }
        }
    }
}














