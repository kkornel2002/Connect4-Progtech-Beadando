package org.example;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int valasz = -1;

        while (valasz != 0 && valasz != 1) {
            System.out.println("1-es gomb jatek inditasa, 0-as gomb pontszamok megtekintese");

            if (scanner.hasNextInt()) {
                valasz = scanner.nextInt();
                scanner.nextLine();

                if (valasz != 0 && valasz != 1) {
                    System.out.println("Hiba, csak 0 es 1 elfogadhato");
                }
            } else {
                System.out.println("Hiba, csak 0 es 1 elfogadhato");
                scanner.nextLine();
            }
        }

        if (valasz == 1) {
            Connect4 connect4 = new Connect4();
            connect4.jatek();
        } else {
            Pontszam.megjelenitPontszamok();
        }
    }
}
