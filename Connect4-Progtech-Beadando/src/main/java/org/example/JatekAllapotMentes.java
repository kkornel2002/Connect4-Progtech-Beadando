package org.example;

import java.io.*;
import java.util.Scanner;

public class JatekAllapotMentes {

    private static final String MENTES_FAJL = "connect4.txt";

    public static boolean jatekAllapotBetoltes(Tabla tabla) {
        File fajl = new File(MENTES_FAJL);

        if (!fajl.exists()) {
            System.out.println("Nincs tabla fajl: " + MENTES_FAJL);
            return false;
        }

        try (FileReader fajlOlvaso = new FileReader(fajl); Scanner fajlBeolvasas = new Scanner(fajlOlvaso)) {
            for (int i = 0; i < 6; i++) {
                if (!fajlBeolvasas.hasNextLine()) {
                    System.out.println("Hianyos a mentett fajll.");
                    return false;
                }
                String sor = fajlBeolvasas.nextLine();
                for (int j = 0; j < 7; j++) {
                    tabla.korongLerakas(sor.charAt(j), i, j);
                }
            }
            System.out.println("Tabla sikeresen betoltve.");
            return true;
        } catch (IOException e) {
            System.out.println("Hiba a tabla betoltesekor: " + e.getMessage());
        }
        return false;
    }

    public static boolean jatekAllapotMentese(Tabla tabla) {
        File fajl = new File(MENTES_FAJL);

        try (FileWriter fajlIro = new FileWriter(fajl)) {
            for (int i = 0; i < 6; i++) {
                StringBuilder sor = new StringBuilder();
                for (int j = 0; j < 7; j++) {
                    sor.append(tabla.getPozicio(i, j));
                }
                fajlIro.write(sor.toString() + System.lineSeparator());
            }
            System.out.println("Jelenlegi jatektabla sikeresen mentve: " + fajl.getAbsolutePath());
            return true;
        } catch (IOException e) {
            System.out.println("Hiba a jatektabla mentesekor: " + e.getMessage());
        }
        return false;
    }

    public static boolean jatekAllapotBetoltese(Tabla tabla) {
        return false;
    }
}
