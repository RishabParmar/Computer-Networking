
package progs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPclient {
    
    public static void main(String args[]) throws SocketException, UnknownHostException, IOException{
        
        String str1 ="",str2 ="";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        byte[] buff = new byte[1024];
        DatagramSocket ds = new DatagramSocket();
        InetAddress ip = InetAddress.getByName("localhost"); 
        while(true){
        System.out.println("Enter the message: ");
        str1 = br.readLine();
        DatagramPacket dp = new DatagramPacket(str1.getBytes(), str1.length(), ip, 5400);
        ds.send(dp);
        if(str1.equals("stop")){
            break;
        }
        DatagramPacket dpp = new DatagramPacket(buff,1024);
        ds.receive(dp);
        str2 = new String(dp.getData(),0,dp.getLength());
        System.out.println("Server says: "+str2);
        }
        ds.close();
    }
    
}
