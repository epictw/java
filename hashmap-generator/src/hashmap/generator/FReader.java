/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hashmap.generator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bogdan
 */
public class FReader {
    
    public FReader() {
    }
    
    public static List<String> getStrListFromFile(String fileName) {
        
        List<String> strings = new ArrayList<String>();
        
        File file = new File(fileName);
        BufferedReader br;
        
        try {
            br = new BufferedReader(new FileReader(file));

            String line;
            while ((line = br.readLine()) != null) {
                strings.add(line);
            }
            br.close();
        } catch (Exception ex) {
            System.out.println("Some exception occured: " + ex);
        }
        
        
        return strings;
    }
}
