package org.example;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Adatbazis {

    private static final String DB_FILE_PATH = "data/connect4.db";
    private static final String DB_URL = "jdbc:sqlite:" + DB_FILE_PATH;

    public Adatbazis() {
        inicializalAdatbazis();
    }

    private void inicializalAdatbazis() {
        try {
            File adatbazisFajl = new File(DB_FILE_PATH);
            File adatbazisMappa = adatbazisFajl.getParentFile();
            if (adatbazisMappa != null && !adatbazisMappa.exists()) {
                adatbazisMappa.mkdirs();
            }

            try (Connection kapcsolat = getConnection()) {
                String letrehozasLekerdezes = "CREATE TABLE IF NOT EXISTS JatekEredmenyek (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "jatekosNev TEXT NOT NULL, " +
                        "pontszam INTEGER NOT NULL, " +
                        "datumIdo TEXT NOT NULL" +
                        ");";
                kapcsolat.createStatement().execute(letrehozasLekerdezes);
            }
        } catch (SQLException e) {
            System.out.println("Nem tudott betolteni az adatbazis: " + e.getMessage());
        }
    }

    public void jatekEredmenyMentese(String jatekosNev, int lepesekSzama) {
        int pontszam = Highscore.getPontszamFromConnect4(lepesekSzama);
        String beszurasLekerdezes = "INSERT INTO JatekEredmenyek (jatekosNev, pontszam, datumIdo) VALUES (?, ?, ?);";
        try (Connection kapcsolat = getConnection();
             PreparedStatement preparedStatement = kapcsolat.prepareStatement(beszurasLekerdezes)) {

            String formataltDatum = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            preparedStatement.setString(1, jatekosNev);
            preparedStatement.setInt(2, pontszam);
            preparedStatement.setString(3, formataltDatum);

            preparedStatement.executeUpdate();
            System.out.println("Eredmeny sikeresen mentve az adatbazisba.");
        } catch (SQLException e) {
            System.out.println("Hiba, az eredmeny mentese sikertelen: " + e.getMessage());
        }
    }

    protected Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }
}
