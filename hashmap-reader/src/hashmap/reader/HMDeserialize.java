/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hashmap.reader;

import container.Container;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.HashMap;

/**
 *
 * @author bogdan
 */
public class HMDeserialize {
 
    public static HashMap<String, String> deserialize(String fileName) {
        
        Container c = null;

        try {
            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream oin = new ObjectInputStream(fis);
            c = (Container) oin.readObject();
        } catch (Exception ex) {
            System.out.println("Some exception: " + ex);
        }
        
        if (c == null) {
            return null;
        } else {
            return c.hm;
        }
    }
}
