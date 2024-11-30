package org.example;
public class Highscore {

    public static int Pontszam(int lepesekSzama) {
        if (lepesekSzama < 5) {
            return 100;
        } else if (lepesekSzama == 6) {
            return 90;
        } else if (lepesekSzama == 7) {
            return 80;
        } else if (lepesekSzama == 8) {
            return 70;
        } else if (lepesekSzama == 9) {
            return 60;
        } else if (lepesekSzama >= 10 && lepesekSzama <= 14) {
            return 50;
        } else if (lepesekSzama >= 15 && lepesekSzama <= 19) {
            return 40;
        } else {
            return 30;
        }
    }

    public static void main(String[] args) {


    }

    public static int getPontszamFromConnect4(int lepesekSzama) {
        return Pontszam(lepesekSzama);
    }
}
