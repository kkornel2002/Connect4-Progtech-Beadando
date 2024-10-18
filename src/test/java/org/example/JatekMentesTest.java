package org.example;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class JatekMentesTest
{

    private Tabla tabla;
    private File testFajl;

    @BeforeEach
    void setUp()
    {
        tabla = new Tabla();
        testFajl = new File("connect4.txt");
    }

    @AfterEach
    void tearDown()
    {

        if (testFajl.exists()) {
            testFajl.delete();
        }
    }

    @Test
    void testJatekAllapotMentes() throws IOException
    {

        tabla.korongLerakas('X', 5, 0);
        tabla.korongLerakas('O', 5, 1);


        JatekMentes.jatekAllapotMentes(tabla);


        assertTrue(testFajl.exists(), "Van mentett txt.");

    }

    @Test
    void testJatekAllapotBetoltes() throws IOException
    {

        try (FileWriter fajlIro = new FileWriter(testFajl)) {
            fajlIro.write("XO.....\n");
            fajlIro.write(".......\n");
            fajlIro.write(".......\n");
            fajlIro.write(".......\n");
            fajlIro.write(".......\n");
            fajlIro.write(".......\n");
        }


        boolean sikeresBetoltes = JatekMentes.jatekAllapotBetoltes(tabla);


        assertTrue(sikeresBetoltes, "Sikeresen betoltve a txt.");


        char[][] tablaAllapot = tabla.getTabla();
        assertEquals('X', tablaAllapot[0][0], "Az [0,0] helyen 'X'-nek kell lennie.");
        assertEquals('O', tablaAllapot[0][1], "Az [0,1] helyen 'O'-nak kell lennie.");
        assertEquals('.', tablaAllapot[0][2], "Az [0,2] helyen ures helynek kell lennie.");
    }

    @Test
    void testBetoltesNemLetezoFajl()
    {

        if (testFajl.exists())
        {
            testFajl.delete();
        }


        boolean sikeresBetoltes = JatekMentes.jatekAllapotBetoltes(tabla);
        assertFalse(sikeresBetoltes, "Sikertelen, ha nincs txt.");
    }
}
