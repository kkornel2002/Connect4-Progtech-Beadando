package org.example;

public class Tabla {

    private static final int SOROK = 6;
    private static final int OSZLOPOK = 7;
    private static final char URES_HELY = '.';
    private char[] []tabla;

    public Tabla() {
        tabla = new char[SOROK][OSZLOPOK];
        tablaInicializalas();
    }

    public void tablaInicializalas() {
        for (int i = 0; i < SOROK; i++) {
            for (int j = 0; j < OSZLOPOK; j++) {
                tabla[i][j] = URES_HELY;
            }
        }
    }

    public void tablaKiiratas() {
        for (int i = 0; i < SOROK; i++) {
            for (int j = 0; j < OSZLOPOK; j++) {
                System.out.print(tabla[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public boolean helyUres(int sor, int oszlop) {
        return tabla[sor][oszlop] == URES_HELY;
    }

    public void korongLerakas(char jatekos, int sor, int oszlop) {
        tabla[sor][oszlop] = jatekos;
    }

    public char getPozicio(int sor, int oszlop) {
        return tabla[sor][oszlop];
    }

    public int getOszlopok() {
        return OSZLOPOK;
    }

    public int getUtolsoUresSor(int oszlop) {
        for (int sor = SOROK - 1; sor >= 0; sor--) {
            if (tabla[sor][oszlop] == URES_HELY) {
                return sor;
            }
        }
        return -1;
    }

    public boolean nyert(char jatekos) {
        // Vizszintes
        for (int i = 0; i < SOROK; i++) {
            for (int j = 0; j < OSZLOPOK - 3; j++) {
                if (tabla[i][j] == jatekos &&
                        tabla[i][j + 1] == jatekos &&
                        tabla[i][j + 2] == jatekos &&
                        tabla[i][j + 3] == jatekos) {
                    return true;
                }
            }
        }

        // Fuggoleges
        for (int i = 0; i < SOROK - 3; i++) {
            for (int j = 0; j < OSZLOPOK; j++) {
                if (tabla[i][j] == jatekos &&
                        tabla[i + 1][j] == jatekos &&
                        tabla[i + 2][j] == jatekos &&
                        tabla[i + 3][j] == jatekos) {
                    return true;
                }
            }
        }

        // Balrol jobbra atlosan
        for (int i = 0; i < SOROK - 3; i++) {
            for (int j = 0; j < OSZLOPOK - 3; j++) {
                if (tabla[i][j] == jatekos &&
                        tabla[i + 1][j + 1] == jatekos &&
                        tabla[i + 2][j + 2] == jatekos &&
                        tabla[i + 3][j + 3] == jatekos) {
                    return true;
                }
            }
        }

        // Jobbrol balra atlosan
        for (int i = 0; i < SOROK - 3; i++) {
            for (int j = 3; j < OSZLOPOK; j++) {
                if (tabla[i][j] == jatekos &&
                        tabla[i + 1][j - 1] == jatekos &&
                        tabla[i + 2][j - 2] == jatekos &&
                        tabla[i + 3][j - 3] == jatekos) {
                    return true;
                }
            }
        }

        return false;
    }
}
