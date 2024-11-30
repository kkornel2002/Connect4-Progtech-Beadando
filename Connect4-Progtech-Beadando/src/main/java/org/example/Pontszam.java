package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Pontszam {

    private static final String DB_URL = "jdbc:sqlite:data/connect4.db";

    public static void megjelenitPontszamok() {
        try (Connection kapcsolat = DriverManager.getConnection(DB_URL);
             Statement statement = kapcsolat.createStatement()) {

            String lekerdezes = "SELECT jatekosNev, pontszam, datumIdo FROM JatekEredmenyek ORDER BY datumIdo DESC;";
            ResultSet eredmeny = statement.executeQuery(lekerdezes);

            System.out.println("--- Pontszamok ---");
            while (eredmeny.next()) {
                String nev = eredmeny.getString("jatekosNev");
                int pontszam = eredmeny.getInt("pontszam");
                String datumIdo = eredmeny.getString("datumIdo");

                System.out.printf("Nev: %s | Pontszam: %d | Ido: %s%n", nev, pontszam, datumIdo);
            }

        } catch (SQLException e) {
            System.out.println("Nem sikerult lekerni a pontszamokat: " + e.getMessage());
        }
    }
}
