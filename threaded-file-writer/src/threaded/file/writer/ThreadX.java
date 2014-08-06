/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package threaded.file.writer;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bogdan
 */
public class ThreadX implements Runnable{
    
    private FileOutputStream fos;
    private String data;
    private final long milisecWait = 100;
    private String name;
    private int counter;
    
    public ThreadX(FileOutputStream fos, String data, String name) {
        
        this.fos = fos;
        this.data = data;
        this.name = name;
        this.counter = 0;
    }
    
    @Override
    public void run() {
        
        while (counter < data.length()) {
            writeByte();
        }
    }
    
    private synchronized void writeByte() {
        //System.out.println("writeByte start " + name);
        try {
            System.out.println("writeByte waiting " + name);
            wait();
            System.out.println("writeByte wait OK " + name);
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadX.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            Thread.sleep(milisecWait);
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadX.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (counter < data.length()) {
            try {
                fos.write(new Integer(data.getBytes()[counter]));
            } catch (IOException ex) {
                Logger.getLogger(ThreadX.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        counter++;
        
        System.out.println("writeByte send notify " + name);
        notify();
        System.out.println("writeByte send notify OK " + name);
        //System.out.println("writeByte end");
    }
    
    public synchronized void prepareForWriteByte() {
        //System.out.println("prepareForWriteByte start " + name);
        System.out.println("prepareForWriteByte send notify " + name);
        notify();
        System.out.println("prepareForWriteByte send notify OK " + name);
        
        try {
            Thread.sleep(milisecWait);
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadX.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            System.out.println("prepareForWriteByte waiting " + name);
            wait();
            System.out.println("prepareForWriteByte wait OK " + name);
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadX.class.getName()).log(Level.SEVERE, null, ex);
        }
        //System.out.println("prepareForWriteByte end " + name);
    }
}
