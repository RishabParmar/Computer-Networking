
package progs;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TCpmultiserver extends Thread{

    Socket s;
    public TCpmultiserver(Socket s) {
        this.s = s;
    }
    
    public void run(){
        DataOutputStream dout = null;
        String str1="",str2="";
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            DataInputStream din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());
            while(true){
            str1 = din.readUTF();
            System.out.println(this.getName()+" : Client says: "+str1);
            if(str1.equals("stop")){
                break;
            }
            System.out.println("Enter your message: ");
            str2 = br.readLine();
            dout.writeUTF(str2);
            dout.flush();}
        } catch (IOException ex) {
            Logger.getLogger(TCpmultiserver.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dout.close();
            } catch (IOException ex) {
                Logger.getLogger(TCpmultiserver.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    public static void main(String args[]) throws IOException{
        ServerSocket ss = new ServerSocket(6500);
        while(true){
        Socket s = ss.accept();
        TCpmultiserver obj = new TCpmultiserver(s);
        obj.start();
        }
       
    }
    
}
