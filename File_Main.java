package Homework.File;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by epic on 30.07.14.
 */
public class File_Main {

    public static void main (String[] args){

        try {
            ArrayList<String> list = File.readFile("e:\\file.txt");
            /*System.out.println(list.toString());
            System.out.println("---------------------------");*/

            HashMap<String, Integer> hashMap = File.generateHashMap(list);
            /*for (Map.Entry<String, Integer> set : hashMap.entrySet()){
                System.out.println("Word: " + set.getKey());
                System.out.println("Count " + set.getValue());
            }*/

            File.findCurrentWord(hashMap);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }
}
