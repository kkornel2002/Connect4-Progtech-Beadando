package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HighscoreTest {

    @Test
    void tesztPontszamSzamitasa() {
        assertEquals(100, Highscore.Pontszam(4));
        assertEquals(50, Highscore.Pontszam(12));
        assertEquals(30, Highscore.Pontszam(25));
    }
}
