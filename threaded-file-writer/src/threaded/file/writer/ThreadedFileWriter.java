/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package threaded.file.writer;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bogdan
 */
public class ThreadedFileWriter {

    
    private static final String data = "0123456789";
    
    public static void main(String[] args) {
        
        FileOutputStream fos = null;
        
        try {
            fos = new FileOutputStream("generated-file");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ThreadedFileWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Thread mainThread = new Thread(new Thread0(fos, data));
        mainThread.start();
    }
}
