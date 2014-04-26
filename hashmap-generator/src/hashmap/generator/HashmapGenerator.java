/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hashmap.generator;

import container.Container;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 *
 * @author bogdan
 */
public class HashmapGenerator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        List<String> strings = FReader.getStrListFromFile("dictionary.txt");
        
        try {
            FileOutputStream fos = new FileOutputStream("container.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            Container c = HMGenerator.generate(strings);
            oos.writeObject(c);
            oos.flush();
            oos.close();
        } catch (Exception ex) {
            System.out.println("Some exception occured: " + ex);
        }
    }
    
}
