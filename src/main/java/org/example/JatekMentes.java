package org.example;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class JatekMentes
{

    private static final String MENTES_FAJL = "connect4.txt";

    public static boolean jatekAllapotBetoltes(Tabla tabla)
    {
        File fajl = new File(MENTES_FAJL);

        if (!fajl.exists())
        {
            return false;
        }

        try (FileReader fajlOlvaso = new FileReader(fajl); Scanner fajlBeolvasas = new Scanner(fajlOlvaso))
        {
            for (int i = 0; i < 6; i++)
            {
                String sor = fajlBeolvasas.nextLine();
                for (int j = 0; j < 7; j++)
                {
                    tabla.korongLerakas(sor.charAt(j), i, j);
                }
            }
            return true;

        }
        catch (IOException e)

        {
            System.out.println("Hiba a txt fajl betoltesekor: " + e.getMessage());
        }
        return false;
    }

    public static void jatekAllapotMentes(Tabla tabla) {
        try (FileWriter fajlIro = new FileWriter(MENTES_FAJL))
        {
            for (int i = 0; i < 6; i++)
            {
                for (int j = 0; j < 7; j++)
                {
                    fajlIro.write(tabla.getTabla()[i][j]);
                }
                fajlIro.write('\n');
            }
            System.out.println("Jatek allapot mentve.");

        }
        catch (IOException e)
        {
            System.out.println("Hiba a jatek allapot mentesekor: " + e.getMessage());
        }
    }
}
