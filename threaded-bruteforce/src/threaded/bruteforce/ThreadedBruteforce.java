/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package threaded.bruteforce;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bogdan
 */
public class ThreadedBruteforce {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //variable initialization (for reading from stream)
        boolean errorOccured;
        Integer parsed = 0;
        String hash = "";
        
        //enter data (password length)
        do {
            errorOccured = false;
            System.out.println("Enter password maximum length:");
            byte[] input = new byte[Short.MAX_VALUE]; //max byte array

            try {
                System.in.read(input);
            } catch (IOException ex) {
                Logger.getLogger(ThreadedBruteforce.class.getName()).log(Level.SEVERE, null, ex);
            }

            String str = new String(input).trim();
            
            //checks
            try {
                parsed = Integer.parseInt(str);
            } catch (NumberFormatException ex) {
                errorOccured = true;
                System.out.println("ERROR. Integer didn't parsed");
            }
            
            if (!errorOccured) {
                if (parsed <= 0) {
                    errorOccured = true;
                    System.out.println("ERROR. Integer incorrect");
                }
            }
        } while (errorOccured);
        
        //enter data (hash)
        do {
            errorOccured = false;
            System.out.println("Enter hash:");
            
            byte[] input = new byte[Short.MAX_VALUE];

            try {
                System.in.read(input);
            } catch (IOException ex) {
                Logger.getLogger(ThreadedBruteforce.class.getName()).log(Level.SEVERE, null, ex);
            }

            hash = new String(input).trim();
            
            if (hash.length() != 32) {
                errorOccured = true;
                System.out.println("ERROR. Hash incorrect");
            }
        } while (errorOccured);
        
        //algorithm
        if (parsed == 1) {
            ArrayList<Integer> task = new ArrayList<Integer>();
            task.add(1);
            Bruter b = new Bruter(task, hash);
            Thread t = new Thread(b);
            t.start();
        } else {
            ArrayList<Integer> task1 = new ArrayList<Integer>();
            ArrayList<Integer> task2 = new ArrayList<Integer>();
            for (int i = 1; i <= parsed - 1; i++) {
                task1.add(i);
            }
            task2.add(parsed);
            Bruter b1 = new Bruter(task1, hash);
            Thread t1 = new Thread(b1);
            Bruter b2 = new Bruter(task2, hash);
            Thread t2 = new Thread(b2);
            t1.start();
            t2.start();
            
            while(isSomeThreadAlive(t1, t2)) {
                
                try {
                    t1.join(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ThreadedBruteforce.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                if (!t1.isAlive()) {
                    String result = b1.getResult();
                    
                    if (result != null) {
                        System.out.println("RESULT: " + result);
                        t2.stop();
                        System.exit(0);
                    }
                }
                
                try {
                    t2.join(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ThreadedBruteforce.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                if (!t2.isAlive()) {
                    String result = b2.getResult();
                    
                    if (result != null) {
                        System.out.println("RESULT: " + result);
                        t1.stop();
                        System.exit(0);
                    }
                }
            }
        }
    }
    
    private static boolean isSomeThreadAlive(Thread thread1, Thread thread2) {

        return (thread1.isAlive() || thread2.isAlive());
    }    
}
