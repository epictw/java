/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.client;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author bogdan
 */
public class Connector implements Runnable {

    private String username;
    private DatagramSocket clientSocket;
    private InetAddress IPAddress;
    
    public Connector(String username) {
        this.username = username;
        
        try {
         clientSocket = new DatagramSocket();
         IPAddress = InetAddress.getByName("localhost");
        } catch (Exception e) {
            System.out.print("Whoops! It didn't work!\n");
        }   
    }

    @Override
    public void run() {
        
        try {
            while (true) {
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                clientSocket.receive(receivePacket);
                String message = new String(receivePacket.getData());
                System.out.println("\r" + message);
                System.out.print(username + ": ");
            }
        } catch (Exception e) {
            System.out.print("Whoops! It didn't work!\n");
        }        
    }
    
    public void sendMessage(String message) {
        
        try {
            DatagramPacket sendPacket = new DatagramPacket(message.getBytes(), message.length(), IPAddress, 9876);
            clientSocket.send(sendPacket);
        } catch (Exception e) {
            System.out.print("Whoops! It didn't work!\n");
        }
    }
}
