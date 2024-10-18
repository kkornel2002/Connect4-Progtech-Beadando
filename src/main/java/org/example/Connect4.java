package org.example;
import java.util.Random;
import java.util.Scanner;
public class Connect4 {

    private static final char JATEKOS_EGY = 'X';
    private static final char JATEKOS_KETTO = 'O';
    private final Tabla tabla;
    private final Random veletlen;
    private boolean jatekNyert = false;

    public Connect4()
    {
        tabla = new Tabla();
        veletlen = new Random();
    }

    public void jatek()
    {
        Scanner beolvasas = new Scanner(System.in);
        char aktualisJatekos = JATEKOS_EGY;

        while (!jatekNyert)
        {
            tabla.tablaKiiratas();

            int oszlop;

            // szamitogep random generalt lepese
            if (aktualisJatekos == JATEKOS_KETTO)
            {
                oszlop = veletlen.nextInt(tabla.getOszlopok());
                System.out.println("A szamitogep a(z) " + (oszlop + 1) + ". oszlopot valasztotta.");
            }
            else
            {
                System.out.println(aktualisJatekos + " jatekos, valasszon egy oszlopot (1-7): ");
                oszlop = beolvasas.nextInt() - 1;
            }

            // bevitel ellenorzes
            if (oszlop < 0 || oszlop >= tabla.getOszlopok())
            {
                System.out.println("Ne adjon meg az oszlopok szamatol kisebb vagy nagyobb szamot!.");
                continue;
            }

            // legalacsonyabb ures sor megtalalasa az oszlopban

            int sor = tabla.megtalalLegalacsonyabbUresSor(oszlop);

            if (sor == -1)
            {
                System.out.println("Az oszlop megtelt. Probalja ujra.");
                continue;
            }

            // korong lerakas
            tabla.korongLerakas(aktualisJatekos, sor, oszlop);

            jatekNyert = tabla.nyeresEllenorzes(aktualisJatekos);

            if (jatekNyert)
            {
                tabla.tablaKiiratas();
                if(aktualisJatekos == JATEKOS_EGY)
                {
                    System.out.println("On nyert!");
                }

                else
                {
                    System.out.println("A szamitogep nyert!");
                }
            }

            else
            {
                aktualisJatekos = (aktualisJatekos == JATEKOS_EGY) ? JATEKOS_KETTO : JATEKOS_EGY;
            }
        }
        beolvasas.close();
    }

    public static void main(String[] args)
    {
        Connect4 jatek = new Connect4();
        jatek.jatek();
    }
}
