package com.oop_pub.exceptions.ex1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainEx1 {
    private static void readAndPrintLine() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try (reader) {
            String linie = reader.readLine();
            System.out.println("Linia este " + linie);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        readAndPrintLine();
    }
}
