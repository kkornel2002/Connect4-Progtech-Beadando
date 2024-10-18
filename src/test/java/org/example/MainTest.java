package org.example;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class MainTest
{

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp()
    {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    void testMain()
    {
        Main.main(new String[]{});
        String output = outContent.toString();
        assertTrue(output.contains(". . . . . . ."), "A jatek elindul.");
        assertTrue(output.contains("jatekos, valasszon egy oszlopot"), "Mukodnie kell a kiirasnak");
    }
}
