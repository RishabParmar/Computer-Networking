
package progs;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPserver {
    
    public static void main(String args[]) throws IOException{
        ServerSocket ss = new ServerSocket(4500);
        Socket s = ss.accept();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        DataInputStream din = new DataInputStream(s.getInputStream());
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
        String str1="",str2="";
        while(true){
            str1 = din.readUTF();
            System.out.println("Client says: "+str1);
            if(str1.equals("stop")){
                break;
            }
            System.out.println("Enter your message: ");
            str2 = br.readLine();
            dout.writeUTF(str2);
            dout.flush();
        }
        s.close();
        ss.close();
    }
    
}
