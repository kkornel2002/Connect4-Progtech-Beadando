package org.example;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Random;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class Connect4Test {

    private Connect4 game;
    private Tabla mockTabla;
    private Random mockRandom;
    private Scanner mockScanner;

    @BeforeEach
    void setUp() {
        mockTabla = mock(Tabla.class);
        mockRandom = mock(Random.class);
        mockScanner = mock(Scanner.class);


        game = new Connect4();
        game.tabla = mockTabla;
        game.veletlen = mockRandom;
    }

    @Test
    void testPlayerMoveAndWin() {
        when(mockTabla.getOszlopok()).thenReturn(7);
        when(mockTabla.megtalalLegalacsonyabbUresSor(anyInt())).thenReturn(5);
        when(mockTabla.nyeresEllenorzes(anyChar())).thenReturn(true);


        when(mockScanner.nextInt()).thenReturn(3);

        game.jatek(); // Start the game


        verify(mockTabla).korongLerakas('X', 5, 2);
        verify(mockTabla).nyeresEllenorzes('X');
        assertTrue(game.jatekNyert);
    }

    @Test
    void testComputerMove() {
        when(mockTabla.getOszlopok()).thenReturn(7);
        when(mockRandom.nextInt(anyInt())).thenReturn(1);
        when(mockTabla.megtalalLegalacsonyabbUresSor(anyInt())).thenReturn(4);
        when(mockTabla.nyeresEllenorzes(anyChar())).thenReturn(false);

        game.jatek();

        verify(mockTabla).korongLerakas('O', 4, 1);
        assertFalse(game.jatekNyert);
    }

    @Test
    void testColumnFull() {
        when(mockTabla.getOszlopok()).thenReturn(7);
        when(mockTabla.megtalalLegalacsonyabbUresSor(anyInt())).thenReturn(-1);


        when(mockScanner.nextInt()).thenReturn(3);

        game.jatek();


        verify(mockTabla, times(1)).megtalalLegalacsonyabbUresSor(2);
        verify(mockTabla, never()).korongLerakas(anyChar(), anyInt(), anyInt());
    }

}
