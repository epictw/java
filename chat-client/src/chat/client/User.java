/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author bogdan
 */
public class User implements Runnable {

private String username;
private Connector connector;
    
    public User(String username, Connector connector) {
        this.username = username;
        this.connector = connector;
    }

    @Override
    public void run() {
        
        
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        String sentence = "";

        try {
            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress IPAddress = InetAddress.getByName("localhost");

            while (true) {
                System.out.print(username + ": ");
                sentence = inFromUser.readLine();
                String message = username + ": " + sentence;
                connector.sendMessage(message);
            }
        } catch (Exception e) {
            System.out.print("Whoops! It didn't work!\n");
        }
    }
}
