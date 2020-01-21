package progs;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Selclient {

    public static void main(String args[]) throws IOException {

        Socket s = new Socket("127.0.0.1", 4200);
        int k = 0;
        String str1 = "", str2 = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        DataInputStream din = new DataInputStream(s.getInputStream());
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
        int[] data = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
        data[4] = -50;
        System.out.println("Initial ata being sent is as follows: ");
        for (int i = 0; i < 8; i++) {
            dout.writeInt(data[i]);
            System.out.print(" " + data[i]);
            dout.flush();
        }

        k = din.readInt();
        if (k != -1) {
            System.out.println("\nSending Correct data");
            data[k] = 5;
            dout.writeInt(data[k]);
            dout.flush();

        } else {
            System.out.println("Correct data was sent!!");
        }

    }

}
