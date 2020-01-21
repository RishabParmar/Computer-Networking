package progs;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPclient {

    public static void main(String args[]) throws IOException {
        Socket s = new Socket("localhost", 4500);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        DataInputStream din = new DataInputStream(s.getInputStream());
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
        String str1 = "", str2 = "";
        while (true) {
            System.out.println("Enter your message: ");
            str1 = br.readLine();
            dout.writeUTF(str1);
            dout.flush();
            if (str1.equals("stop")) {
                break;
            }
            str2 = din.readUTF();
            System.out.println("Server says: " + str2);

        }
        s.close();
    }
}
