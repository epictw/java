/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tcp.client;

/**
 *
 * @author bogdan
 */

import java.lang.*;
import java.io.*;
import java.net.*;

public class TcpClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            System.out.println("Client started. Connecting to server...");
            Socket skt = new Socket("127.0.0.1", 1234);
            BufferedReader in = new BufferedReader(new InputStreamReader(skt.getInputStream()));
            System.out.print("Received string: ");

            while (!in.ready()) {
            }
            System.out.println(in.readLine());
            in.close();
            skt.close();
        } catch (Exception e) {
            System.out.print("Whoops! It didn't work!\n");
        }
    }
}
