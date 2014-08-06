/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package threaded.bruteforce;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bogdan
 */
public class Bruter implements Runnable{

    private ArrayList<Integer> tasks;
    private String hash;
    private String password = null; //result
    private static MessageDigest md = null;
    
    public Bruter(ArrayList<Integer> tasks, String hash) {
        this.tasks = tasks;
        this.hash = hash;
        try {
            this.md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Bruter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        for (int passLength : tasks) {
            String firstPass = "";
            String lastPass = "";
            for (int i = 0; i < passLength; i++) {
                firstPass += "a";
                lastPass += "z";
            }
            password = brute(firstPass, lastPass);
        }
    }
    
    /* recursive variant
    private String brute(String currentPass, String lastPass){
        System.out.println(currentPass);
        if (currentPass.equals(lastPass)) {
            if (hash.equals(getHash(currentPass))) {
                System.out.println("BINGO! " + currentPass);
                return currentPass;
            } else {
                return null;
            }
        } else {
            if (hash.equals(getHash(currentPass))) {
                System.out.println("BINGO! " + currentPass);
                return currentPass;
            } else {
                currentPass = incrementPassword(currentPass);
                brute(currentPass, lastPass);
            }
        }
        return null;
    }*/
    
    //non recursive variant
    private String brute(String currentPass, String lastPass){
        do {
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(Bruter.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(currentPass);
            if (hash.equals(getHash(currentPass))) {
                System.out.println("BINGO! " + currentPass);
                return currentPass;
            } else {
                currentPass = incrementPassword(currentPass);
            }
        } while (!currentPass.equals(lastPass));
        
        System.out.println(currentPass);
        if (hash.equals(getHash(currentPass))) {
            System.out.println("BINGO! " + currentPass);
            return currentPass;
        } else {
            return null;
        }
    }
    
    private static String incrementPassword(String pass) {
        char[] chars = pass.toCharArray();
        int last = pass.length() - 1;
        chars[last]++;
        
        for (int i = last; i >= 1; i--) {
            if (chars[i] > 'z') {
                chars[i] = 'a';
                chars[i - 1]++;
            }
        }
        
        return new String(chars);
    }
    
    public String getResult() {
        return password;
    }
    
    private static String getHash(String password) {
        try {
            return new String(getBase64(md.digest(password.getBytes("UTF-8")))).toLowerCase();
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Bruter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
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
