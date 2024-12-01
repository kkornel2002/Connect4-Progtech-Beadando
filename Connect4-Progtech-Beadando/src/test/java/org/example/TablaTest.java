package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TablaTest {

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

    @Test
    void tesztHibasLepes() {
        assertThrows(IndexOutOfBoundsException.class, () -> tabla.korongLerakas('X', 0, 10));
    }

    @Test
    void tesztHibasNyertes() {
        for (int i = 0; i < 3; i++) {
            tabla.korongLerakas('O', 0, i);
        }
        assertFalse(tabla.nyert('O'));
    }
}
