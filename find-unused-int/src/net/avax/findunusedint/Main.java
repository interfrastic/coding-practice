package net.avax.findunusedint;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int count = 0;

        while (sc.hasNextInt()) {
            int i = sc.nextInt();
            count++;
        }

        System.out.println("Read " + count + " integer(s).");
    }
}
