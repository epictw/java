/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tcp.server;

import java.lang.*;
import java.io.*;
import java.net.*;

/**
 *
 * @author bogdan
 */
public class TcpServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        String data = "hello";
        
        try {
            System.out.print("I'm started!\n");
            ServerSocket srvr = new ServerSocket(1234);
            Socket skt = srvr.accept();
            System.out.print("Client was connected!\n");
            PrintWriter out = new PrintWriter(skt.getOutputStream(), true);
            System.out.print("Sending string to client: " + data + "\n");
            out.print(data);
            out.close();
            skt.close();
            srvr.close();
        } catch (Exception e) {
            System.out.print("Whoops! It didn't work!\n");
        }
    }
}
