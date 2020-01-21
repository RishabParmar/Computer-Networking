
package progs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPserver {
    
    public static void main(String args[]) throws SocketException, IOException{
        DatagramSocket ds = new DatagramSocket(5400);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        byte[] buff = new byte[1024];
        String str1="", str2="";
        while(true){
            DatagramPacket dp = new DatagramPacket(buff, 1024);
            ds.receive(dp);
            str1 = new String(dp.getData(),0,dp.getLength());
            System.out.println("Client says: "+str1);          
            System.out.println("Enter your message: ");
            str2 = br.readLine();
            InetAddress ip = dp.getAddress();
            int port = dp.getPort();
            DatagramPacket dpp = new DatagramPacket(str2.getBytes(),str2.length(),ip,port);
            ds.send(dpp);
        }
    }
    
}
