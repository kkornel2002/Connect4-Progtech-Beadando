import static org.junit.jupiter.api.Assertions.*;
import org.example.Connect4;
import org.example.JatekAllapotMentes;
import org.example.Tabla;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.util.Random;

class Connect4Test {

    private Connect4 connect4;
    private Tabla mockTabla;
    private Random mockRandom;

    @BeforeEach
    void setUp() {
        mockTabla = Mockito.mock(Tabla.class);
        mockRandom = Mockito.mock(Random.class);

        System.setIn(new ByteArrayInputStream("1\n".getBytes()));

        connect4 = new Connect4();
    }

    @Test
    void testInicializalJatekAllapot_UjJatek() {
        System.setIn(new ByteArrayInputStream("0\n".getBytes()));
        Connect4 connect4 = new Connect4();
        assertFalse(connect4.jatekNyert);
    }


    @Test
    void testGetJatekosNev() {
        connect4.jatekosNev = "Tesztjatekos";
        assertEquals("Tesztjatekos", connect4.getJatekosNev());
    }

    @Test
    void testGetLepesekSzama() {
        connect4.lepesekSzama = 5;
        assertEquals(5, connect4.getLepesekSzama());
    }


}
