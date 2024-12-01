package org.example;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class AdatbazisTest {

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
