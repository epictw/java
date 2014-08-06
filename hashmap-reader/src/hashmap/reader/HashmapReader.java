/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hashmap.reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bogdan
 */
public class HashmapReader {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        HashMap<String, String> hm = HMDeserialize.deserialize("container.dat");

        if (hm == null) {

            return;
        }

        //printHashMap(hm);
        //byte buf[] = new byte[33];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String requestedKey = null;
        
        try {
            int len = -1;
            do {
                if (len != -1) {
                    try {
                        //System.out.println("Hash have incorrect size");
                        throw new IncorrectLineSize("Hash have incorrect size");
                    } catch (IncorrectLineSize ex) {
                        Logger.getLogger(HashmapReader.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                System.out.println("Enter hash:");
                requestedKey = br.readLine();
                len = requestedKey.length();
                System.out.println(requestedKey.length());
            } while (len != 32);

            //System.out.println(new String(buf, 0, 32));
        } catch (IOException ex) {
            Logger.getLogger(HashmapReader.class.getName()).log(Level.SEVERE, null, ex);
        }

        //requestedKey = requestedKey.replaceAll("\n", "");
        System.out.println("Requested key: " + requestedKey);
        System.out.println("Requested key length: " + requestedKey.toCharArray().length);
        String value = hm.get(requestedKey);

        if (value == null) {
            System.out.println("This hash is not found");
        } else {
            System.out.println("Value: " + value);
        }
    }

    private static void printHashMap(HashMap<String, String> hm) {

        System.out.println("Printing hashmap...");

        Iterator it = hm.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry) it.next();
            System.out.println(pairs.getKey() + " = " + pairs.getValue());
            //it.remove(); // avoids a ConcurrentModificationException
        }
    }
}
