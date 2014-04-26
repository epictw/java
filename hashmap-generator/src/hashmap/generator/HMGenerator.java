/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hashmap.generator;

import container.Container;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bogdan
 */
public class HMGenerator {
    
    public HMGenerator() {
    }
    
    public static Container generate(List<String> strings) {
        
        if (strings == null) {    
            return null;
        }
        
        Container c = new Container();
        c.hm = new HashMap<String, String>();
        MessageDigest md = null;
        
        try {
            md = MessageDigest.getInstance("MD5");

            for (String str : strings) {
                
                String hash = getBase64(md.digest(str.getBytes("UTF-8")));
                c.hm.put(hash, str);
                System.out.println(str + " : " + hash);
            }
        } catch (Exception ex) {
            System.out.println("Some exception occured: " + ex);
        }
        
        return c;
    }
    
    private static String getBase64(byte[] byteArray) {
        
        StringBuilder hexString = new StringBuilder();
        
        for (int i = 0; i < byteArray.length; i++) {
            String hex = Integer.toHexString(0xFF & byteArray[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }

            hexString.append(hex);
        }

        return hexString.toString().toUpperCase();
    }
}
 