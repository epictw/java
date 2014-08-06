/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat.server;

import java.net.InetAddress;

/**
 *
 * @author bogdan
 */
public class Client {
    
    public InetAddress ip;
    public int port;
    
    public Client(InetAddress ip, int port) {
        
        this.ip = ip;
        this.port = port;
    }
    
    @Override
    public boolean equals(Object obj) {
        Client c = (Client) obj;
        if ((c.ip.equals(this.ip)) && (c.port == this.port)) {
            return true;
        } else {
            return false;
        }
    }
    
    public void print() {
        System.out.println("IP: " + this.ip.toString() + ", Port: " + this.port);
    }
}
