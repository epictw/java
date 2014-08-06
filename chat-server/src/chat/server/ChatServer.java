/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author bogdan
 */
public class ChatServer {

    private static List<Client> clients;
            
    public static void main(String[] args) {

        clients = new ArrayList<Client>();
        
        try {
            DatagramSocket serverSocket = new DatagramSocket(9876);
            
            while (true) {
                //receiving
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);
                String sentence = new String(receivePacket.getData());
                System.out.println(sentence);
                
                //analyzing
                InetAddress IPAddress = receivePacket.getAddress();
                int port = receivePacket.getPort();
                
                Client c = new Client(IPAddress, port);
                List<Client> receivers = getReceiversList(c);
                
                //sending
                for (Client receiver : receivers) {
                    DatagramPacket sendPacket = new DatagramPacket(sentence.getBytes(), sentence.length(), receiver.ip, receiver.port);
                    serverSocket.send(sendPacket);
                }
            }
        } catch (Exception e) {
            System.out.print("Whoops! It didn't work!\n");
        }
    }
    
    private static List<Client> getReceiversList(Client c) {
        
        List<Client> receivers = new ArrayList<Client>();
        boolean isFound = false;
        
        for (Client cl : clients) {
            //System.out.println("Checking:");
            //cl.print();
            //c.print();
            if (!cl.equals(c)) {
                //System.out.println("Not equal");
                receivers.add(cl);
            } else {
                //System.out.println("Equal");
                isFound = true;
            }
        }
        
        if (!isFound) {
            System.out.println("New client.");
            clients.add(c);
        }
        
        return receivers;
    }
}
