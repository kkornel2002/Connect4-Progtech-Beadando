package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Nested
class AdatbazisTeszt {

    @Test
    void tesztAdatbazisInicializalasa() {
        Adatbazis adatbazis = new Adatbazis();
        File adatbazisFajl = new File("data/connect4.db");
        assertTrue(adatbazisFajl.exists(), "A db fajl letrejott");
    }

    @Test
    void tesztJatekEredmenyMentese() throws SQLException {
        Connection mockKapcsolat = mock(Connection.class);
        PreparedStatement mockUtasitas = mock(PreparedStatement.class);

        when(mockKapcsolat.prepareStatement(anyString())).thenReturn(mockUtasitas);

        Adatbazis adatbazis = spy(new Adatbazis());
        doReturn(mockKapcsolat).when(adatbazis).getConnection();

        adatbazis.jatekEredmenyMentese("TesztJatekos", 10);
        verify(mockUtasitas, times(1)).executeUpdate();
    }
}

class Connect4Teszt {

    private Connect4 connect4;

    @BeforeEach
    void beallit() {
        System.setIn(new ByteArrayInputStream("1\n".getBytes()));
        connect4 = new Connect4();
    }

    @Test
    void tesztKezdetiAllapot() {
        assertNotNull(connect4.getTabla());
        assertEquals(0, connect4.getLepesekSzama());
    }

    @Test
    void tesztJatekGyozelem() {
        Tabla tabla = connect4.getTabla();
        char jatekos = 'X';

        for (int i = 0; i < 4; i++) {
            tabla.korongLerakas(jatekos, 0, i);
        }

        assertTrue(tabla.nyert(jatekos));
    }
}

class RanglistaTeszt {

    @Test
    void tesztPontszamSzamitasa() {
        assertEquals(100, Highscore.Pontszam(4));
        assertEquals(90, Highscore.Pontszam(6));
        assertEquals(30, Highscore.Pontszam(20));
    }
}

class JatekMentesTeszt {

    private Tabla tabla;

    @BeforeEach
    void beallit() {
        tabla = new Tabla();
    }

    @Test
    void tesztJatekAllapotMentese() throws IOException {
        File tesztFajl = new File("teszt_connect4.txt");
        JatekAllapotMentes.jatekAllapotMentese(tabla);
        assertFalse(tesztFajl.exists());
        tesztFajl.delete();
    }

    @Test
    void tesztJatekAllapotBetoltese() throws IOException {
        Tabla tabla = new Tabla();
        File tesztFajl = new File("connect4.txt");
        tesztFajl.createNewFile();

        boolean betoltve = JatekAllapotMentes.jatekAllapotBetoltese(tabla);
        assertFalse(betoltve);

        tesztFajl.delete();
    }
}

class PontszamTeszt {

    @Test
    void tesztPontszamLekerdezes() {
        Pontszam.megjelenitPontszamok();
    }
}

class TablaTeszt {

    private Tabla tabla;

    @BeforeEach
    void beallit() {
        tabla = new Tabla();
    }

    @Test
    void tesztTablaInicializalasa() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                assertEquals('.', tabla.getPozicio(i, j));
            }
        }
    }

    @Test
    void tesztKorongLerakas() {
        tabla.korongLerakas('X', 0, 0);
        assertEquals('X', tabla.getPozicio(0, 0));
    }

    @Test
    void tesztNyertesVizsgalat() {
        for (int i = 0; i < 4; i++) {
            tabla.korongLerakas('X', 0, i);
        }
        assertTrue(tabla.nyert('X'));
    }
}
