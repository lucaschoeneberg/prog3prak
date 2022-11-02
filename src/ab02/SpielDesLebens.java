package ab02;

import ab02.logik.Simulator;
import ab02.ui.Steuerung;


public class SpielDesLebens {
    public static void main(String[] args) {
        Steuerung mainApp = new Steuerung(new Simulator());
        mainApp.startDesSpiels();
        System.exit(0);
    }
}