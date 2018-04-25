package net.avax.scratchpad;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

// Keynotes: Oracle Code Chicago: Alex Buckley: Local-Variable Type Inference
//
// https://www.youtube.com/watch?v=84mCmmzksGI&t=1h15m55s
//
// JEP 286: Local-Variable Type Inference
//
// http://openjdk.java.net/jeps/286
//
// Style Guidelines for Local Variable Type Inference in Java
//
// http://openjdk.java.net/projects/amber/LVTIstyle.html

public class Main {
    public static void main(String[] args) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new
                ByteArrayOutputStream(12);

        while (byteArrayOutputStream.size() <= 10) {
            byteArrayOutputStream.write("hello".getBytes());
        }

        byte[] bytes = byteArrayOutputStream.toByteArray();

        System.out.println("Displaying byte array created from stream:");

        for (int i = 0; i < bytes.length; i++) {
            System.out.print((char) bytes[i] + " ");
        }
        System.out.println();

        int nextByte;
        ByteArrayInputStream byteArrayInputStream
                = new ByteArrayInputStream(bytes);

        System.out.println("Reading and transforming stream of byte array:");

        for (int i = 0; i < 3; i++) {
            while ((nextByte = byteArrayInputStream.read()) >= 0) {
                System.out.print(Character.toUpperCase((char) (nextByte + i))
                        + " ");
            }
            System.out.println();

            byteArrayInputStream.reset();
        }
    }
}
