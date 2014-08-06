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
public class Thread0 implements Runnable{
 
    private FileOutputStream fos;
    private String data;
    private long milisecWait = 100;
    private static final int threadsCount = 5;
    
    public Thread0(FileOutputStream fos, String data) {
        
        this.fos = fos;
        this.data = data;
    }
    
    @Override
    public void run() {
        ThreadX[] threadsx = new ThreadX[threadsCount];
        Thread[] threads = new Thread[threadsCount];
        
        for (int i = 0; i < threadsCount; i++) {
            threadsx[i] = new ThreadX(fos, data, new Integer(i).toString());
            threads[i] = new Thread(threadsx[i]);
            threads[i].start();
        }
        
       System.out.println("Debug1");
       while(isSomeThreadAlive(threads)) {
           for (int i = 0; i < threadsCount; i++) {
               System.out.println("Run prepareForWriteByte " + i);
               threadsx[i].prepareForWriteByte();
               System.out.println("End prepareForWriteByte " + i);
           }
       }
        
        /*try {
            fos.close();
        } catch (IOException ex) {
            Logger.getLogger(ThreadedFileWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Debug2");*/
    }    
    
    private static boolean isSomeThreadAlive(Thread[] threads) {

        for (int i = 0; i < threads.length; i++) {
            if (threads[i].isAlive()) {
                return true;
            }
        }

        return false;
    } 
}
