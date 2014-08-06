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
    private long milisecWait = 1000;
    private Thread0 mainThread;
    private String name;
    
    public ThreadX(FileOutputStream fos, String data, Thread0 mainThread, String name) {
        
        this.fos = fos;
        this.data = data;
        this.mainThread = mainThread;
        this.name = name;
    }
    
    @Override
    public void run() {
        System.out.println("Thread runned " + name);
        for (int i = 0; i < data.length(); i++) {
            
            /*synchronized (mainThread) {
                try {
                    mainThread.wait();
                } catch (InterruptedException ex) {
                    Logger.getLogger(ThreadX.class.getName()).log(Level.SEVERE, null, ex);
                }
            }*/
        
            //System.out.println("Wait OK " + name);
            
            synchronized (this) {
                try {
                    Thread.sleep(milisecWait);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ThreadX.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    fos.write(new Integer(data.getBytes()[i]));
                } catch (IOException ex) {
                    Logger.getLogger(ThreadX.class.getName()).log(Level.SEVERE, null, ex);
                }
                notify();
            }
            
            System.out.println("Notify OK " + name);
        }
        try {
            Thread.sleep(milisecWait);
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadX.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Thread OK " + name);
    }
}
