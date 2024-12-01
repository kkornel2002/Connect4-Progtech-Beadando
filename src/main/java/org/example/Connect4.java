package org.example;
import java.util.Random;
import java.util.Scanner;

public class Connect4 {

    private static final char JATEKOS_KETTO = 'O';
    private final Tabla tabla;
    private final Random veletlen;
    public boolean jatekNyert = false;
    public String jatekosNev;
    public int lepesekSzama;

    public Connect4() {
        tabla = new Tabla();
        veletlen = new Random();
        inicializalJatekAllapot();
        lepesekSzama = 0;
    }

    private void inicializalJatekAllapot() {
        Scanner beolvasas = new Scanner(System.in);
        int valasz = -1;
        while (valasz != 0 && valasz != 1) {
            System.out.println("Nyomja meg az 1-es gombot a mentett jatek betoltesehez, " +
                    "vagy a 0-as gombot uj inditasahoz:");
            if (beolvasas.hasNextInt()) {
                valasz = beolvasas.nextInt();
                beolvasas.nextLine();

                if (valasz != 0 && valasz != 1) {
                    System.out.println("Hiba, csak a 0 es 1 elfogadhato.");
                }
            } else {
                System.out.println("Hiba, csak 0 es 1 elfogadhato.");
                beolvasas.nextLine();
            }
        }

        if (valasz == 1) {
            if (JatekAllapotMentes.jatekAllapotBetoltes(tabla)) {

            } else {
                System.out.println("Betoltes sikertelen. Uj jatek indul.");
            }
        } else {
            System.out.println("Uj jatek indul.");
        }
    }

    public void jatek() {
        Scanner beolvasas = new Scanner(System.in);

        System.out.print("Adja meg a nevet: ");
        jatekosNev = beolvasas.nextLine();

        char aktualisJatekos = 'X';

        while (!jatekNyert) {
            tabla.tablaKiiratas();

            int oszlop = -1;

            if (aktualisJatekos == JATEKOS_KETTO) {
                oszlop = veletlen.nextInt(tabla.getOszlopok());
                System.out.println("A szamitogep a(z) " + (oszlop + 1) + ". oszlopot valasztotta.");
            } else {
                boolean ervenyesBemeno = false;
                while (!ervenyesBemeno) {
                    System.out.println(jatekosNev + ", valasszon egy oszlopot (1-7):");
                    if (beolvasas.hasNextInt()) {
                        oszlop = beolvasas.nextInt() - 1;
                        if (oszlop >= 0 && oszlop < tabla.getOszlopok()) {
                            ervenyesBemeno = true;
                        } else {
                            System.out.println("Csak 1-tol 7-ig adhat meg oszlopot.");
                        }
                    } else {
                        System.out.println("Csak 1-tol 7-ig adhat meg oszlopot.");
                        beolvasas.nextLine();
                    }
                }
            }

            if (tabla.helyUres(0, oszlop)) {
                tabla.korongLerakas(aktualisJatekos, tabla.getUtolsoUresSor(oszlop), oszlop);
                lepesekSzama++;

                if (tabla.nyert(aktualisJatekos)) {
                    tabla.tablaKiiratas();
                    if (aktualisJatekos == 'X') {
                        System.out.println(jatekosNev + " nyert!");
                        int pontszam = Highscore.getPontszamFromConnect4(lepesekSzama);
                        System.out.println("Pontszáma: " + pontszam);


                        Adatbazis adatbazis = new Adatbazis();
                        adatbazis.jatekEredmenyMentese(jatekosNev, lepesekSzama);
                    } else {
                        System.out.println("A szamitogep nyert!");
                    }
                    jatekNyert = true;

                    JatekAllapotMentes.jatekAllapotMentese(tabla);
                }

                aktualisJatekos = (aktualisJatekos == 'X') ? JATEKOS_KETTO : 'X';
            } else {
                System.out.println("Az oszlop tele van! Probalja ujra.");
            }
        }
    }

    public String getJatekosNev() {
        return jatekosNev;
    }

    public int getLepesekSzama() {
        return lepesekSzama;
    }

    public static void main(String[] args) {
        Connect4 connect4 = new Connect4();
        connect4.jatek();
    }

    public Tabla getTabla() {
        return tabla;
    }
}