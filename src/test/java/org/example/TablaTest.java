package org.example;
package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TablaTest {

    @Test
    void tablaInicializalasTest()
    {
        Tabla tabla = new Tabla();
        char[][] tablaAllapot = tabla.getTabla();
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                assertEquals('.', tablaAllapot[i][j], "Az ures mezokkel hiba van");
            }
        }
    }

    @Test
    void korongLerakasTest()
    {
        Tabla tabla = new Tabla();
        tabla.korongLerakas('X', 5, 3);


        assertEquals('X', tabla.getTabla()[5][3], "A korong lerakasa nem sikerult");
    }

    @Test
    void helyUresTest()
    {
        Tabla tabla = new Tabla();
        tabla.korongLerakas('O', 5, 2);

        // Ellenőrizzük, hogy a mező nem üres
        assertFalse(tabla.helyUres(5, 2), "A foglalt mezo ureskent van kezelve");

        // Ellenőrizzük, hogy egy másik mező üres
        assertTrue(tabla.helyUres(4, 2), "Az ures mezu foglaltkent van kezelve");
    }


    @Test
    void nyeresEllenorzesVizszintesTest()
    {
        Tabla tabla = new Tabla();


        tabla.korongLerakas('X', 5, 0);
        tabla.korongLerakas('X', 5, 1);
        tabla.korongLerakas('X', 5, 2);
        tabla.korongLerakas('X', 5, 3);


        assertTrue(tabla.nyeresEllenorzes('X'), "A nyeres vizszintes irányban nem ismerheto fel");
    }

    @Test
    void nyeresEllenorzesFuggolegesTest()
    {
        Tabla tabla = new Tabla();


        tabla.korongLerakas('O', 5, 0);
        tabla.korongLerakas('O', 4, 0);
        tabla.korongLerakas('O', 3, 0);
        tabla.korongLerakas('O', 2, 0);


        assertTrue(tabla.nyeresEllenorzes('O'), "A nyeres fuggoleges iranyban nem ismerheto fel");
    }

    @Test
    void nyeresEllenorzesAtloTest()
    {
        Tabla tabla = new Tabla();


        tabla.korongLerakas('X', 5, 0);
        tabla.korongLerakas('X', 4, 1);
        tabla.korongLerakas('X', 3, 2);
        tabla.korongLerakas('X', 2, 3);


        assertTrue(tabla.nyeresEllenorzes('X'), "A nyeres atlos iranyban nem ismerheto fel");
    }
}
