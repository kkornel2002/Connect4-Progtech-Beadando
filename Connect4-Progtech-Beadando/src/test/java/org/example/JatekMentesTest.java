package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JatekMentesTest {

    private Tabla tabla;

    @BeforeEach
    void beallit() {
        tabla = new Tabla();
    }

    @Test
    void tesztJatekAllapotMentese() throws IOException {
        File tesztFajl = new File("teszt_connect4.txt");
        assertTrue(JatekAllapotMentes.jatekAllapotMentese(tabla));
        assertFalse(tesztFajl.exists());
        tesztFajl.delete();
    }
}
