package progs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import javax.print.DocFlavor;

public class Subnetting {

    public static void main(String args[]) throws IOException {

        Subnetting obj = new Subnetting();
        Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String buff, ck = "";
        String[] buffer = new String[10];
        String[] resultNA = new String[10];
        String[] resultBA = new String[10];
        String[] ips = new String[10];
        String[] subnets = new String[10];
        String[] cidr = new String[10];
        int[] iparr = new int[10];
        System.out.println("Enter the ip address: ");
        buff = br.readLine();
        System.out.println("Enter the number of subnetworks: ");
        int b = sc.nextInt();
        int sbn = obj.calcu(b);

        buffer = buff.split("\\.");
        for (int i = 0; i < 4; i++) {
            iparr[i] = Integer.parseInt(buffer[i]);
            System.out.println(" " + iparr[i]);
        }

        if (iparr[0] >= 0 && iparr[0] <= 127) {

            System.out.println("Class A");
            int[] subnetC = new int[]{255, 0, 0, 0};
            for (int i = 0; i < 4; i++) {
                ips[i] = String.format("%8s", Integer.toBinaryString(iparr[i])).replace(" ", "0");
                if (i == 1) {
                    for (int j = 0; j < sbn; j++) {
                        ck = ck.concat("1");
                    }
                    for (int j = sbn; j < 8; j++) {
                        ck = ck.concat("0");
                    }
                    subnets[i] = ck;
                } else {
                    subnets[i] = String.format("%8s", Integer.toBinaryString(subnetC[i])).replace(" ", "0");
                }
            }
        } else if (iparr[0] >= 128 && iparr[0] <= 191) {

            System.out.println("Class B");
            int[] subnetC = new int[]{255, 255, 0, 0};
            for (int i = 0; i < 4; i++) {
                ips[i] = String.format("%8s", Integer.toBinaryString(iparr[i])).replace(" ", "0");
                if (i == 2) {
                    for (int j = 0; j < sbn; j++) {
                        ck = ck.concat("1");
                    }
                    for (int j = sbn; j < 8; j++) {
                        ck = ck.concat("0");
                    }
                    subnets[i] = ck;
                } else {
                    subnets[i] = String.format("%8s", Integer.toBinaryString(subnetC[i])).replace(" ", "0");
                }
            }
        } else if (iparr[0] >= 192 && iparr[0] <= 223) {
            System.out.println("Class C");
            int[] subnetC = new int[]{255, 255, 255, 0};
            for (int i = 0; i < 4; i++) {
                ips[i] = String.format("%8s", Integer.toBinaryString(iparr[i])).replace(" ", "0");
                if (i == 3) {
                    for (int j = 0; j < sbn; j++) {
                        ck = ck.concat("1");
                    }
                    for (int j = sbn; j < 8; j++) {
                        ck = ck.concat("0");
                    }
                    subnets[i] = ck;
                } else {
                    subnets[i] = String.format("%8s", Integer.toBinaryString(subnetC[i])).replace(" ", "0");
                }
            }
        }
        System.out.println("The ip address is as follows:");
        for (int i = 0; i < 4; i++) {
            System.out.print(" " + ips[i]);
        }
        System.out.println("\nThe subnet mask is as follows:");
        for (int i = 0; i < 4; i++) {
            System.out.print(" " + subnets[i]);
        }
        System.out.println("\nThe inverted subnet mask is as follows:");
        for (int i = 0; i < 4; i++) {
            int a = Integer.parseInt(subnets[i], 2);
            ck = Integer.toBinaryString(~a);
            cidr[i] = ck.substring(24);
            System.out.print(" " + cidr[i]);
        }

        System.out.println("\nNetwork Address is as follows:");
        for (int i = 0; i < 4; i++) {
            int n1 = Integer.parseInt(ips[i], 2);
            int n2 = Integer.parseInt(subnets[i], 2);
            resultNA[i] = String.format("%8s", Integer.toBinaryString(n1 & n2)).replace(" ", "0");
            System.out.print(" " + resultNA[i]);
            System.out.print("(" + Integer.parseInt(resultNA[i],2) + ")");
        }

        System.out.println("\nBraodcast Address is as follows:");
        for (int i = 0; i < 4; i++) {
            int n1 = Integer.parseInt(resultNA[i], 2);
            int n2 = Integer.parseInt(cidr[i], 2);
            resultBA[i] = String.format("%8s", Integer.toBinaryString(n1 | n2)).replace(" ", "0");
            System.out.print(" " + resultBA[i]);
            System.out.print("(" + Integer.parseInt(resultBA[i],2) + ")");

        }
    }

    public int calcu(int b) {
        int result = (int) Math.ceil(Math.log10(b) / Math.log10(2));
        return result;
    }

}
