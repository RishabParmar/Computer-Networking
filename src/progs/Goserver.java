package progs;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Goserver {

    public static void main(String args[]) throws IOException {

        ServerSocket ss = new ServerSocket(4200);
        Socket s = ss.accept();
        int flag = 0, loc = -1;
        String str1 = "", str2 = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        DataInputStream din = new DataInputStream(s.getInputStream());
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
        int[] result = new int[10];
        for (int i = 0; i < 8; i++) {
            result[i] = din.readInt();
            System.out.print(" " + result[i]);
        }

        for (int i = 0; i < 8; i++) {
            if (result[i] < 0) {
                flag = 1;
                loc = i;
                break;
            }
        }
        if (flag == 1) {
            dout.writeInt(loc);
            //Receiving correct data
            System.out.println("\n The correct data after removal of error is: ");
            for(int i=loc;i<8;i++){
                result[i] = din.readInt();
            }
            for(int i=0;i<8;i++){
                System.out.print(" "+result[i]);
            }
        } else {
            dout.writeInt(loc);
            System.out.println("The data received is correct");
            for (int i = 0; i < 8; i++) {
                System.out.print(" " + result[i]);
            }
        }

    }

}
