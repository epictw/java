/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

/**
 *
 * @author bogdan
 */
public class ChatClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Username: ");
        String username = "";
        
        try {
            username = inFromUser.readLine();
        } catch (Exception e) {
            System.out.print("Whoops! It didn't work!\n");
        }
        
        if (username.isEmpty()) {
            return;
        }
        
        Connector connector = new Connector(username);
        User user = new User(username, connector);
        Thread ct = new Thread(connector);
        Thread ut = new Thread(user);
        ct.start();
        ut.start();
        
        /*//System.out.println("Debug1");
        
        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        boolean iCanWork = true;
        
        try {
            //username
            System.out.print("Username: ");
            String username = inFromUser.readLine();
            String sentence = "";
            
            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress IPAddress = InetAddress.getByName("localhost");    
            
            while (iCanWork) {
                System.out.print(username + ": ");
                sentence = inFromUser.readLine();
                String message = username + ": " + sentence;
                sendData = message.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9876);
                clientSocket.send(sendPacket);
            }
            
            
            
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);
            String modifiedSentence = new String(receivePacket.getData());
            System.out.println("FROM SERVER:" + modifiedSentence);
            clientSocket.close();
        } catch (Exception e) {
            System.out.print("Whoops! It didn't work!\n");
        }*/
    }
}
