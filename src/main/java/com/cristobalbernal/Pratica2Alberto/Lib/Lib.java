package com.cristobalbernal.Pratica2Alberto.Lib;

import java.util.Random;
import java.util.Scanner;

public class Lib {
    private static final Scanner lector = new Scanner(System.in);
    public static String leerLinea() {
        return lector.nextLine();
    }
    public static int leerInt() {
        return Integer.parseInt(leerLinea());
    }
    public static int numeroAleatorio(int MIN, int MAX){
        Random r = new Random();
        return r.nextInt(MAX - MIN + 1) + MIN;
    }
}
