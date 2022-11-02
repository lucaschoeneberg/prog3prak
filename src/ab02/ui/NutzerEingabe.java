package ab02.ui;

import ab02.util.EinUndAusgabe;

public class NutzerEingabe {
    private EinUndAusgabe io;
    private final int MINDESTZELLENANZAHL = 3;
    private final int MAMIMALZELLENANZAHL = 75;

    /**
     * @param io
     */
    public NutzerEingabe(EinUndAusgabe io) {
        this.io = new EinUndAusgabe();
    }

    /**
     * @return
     */
    public int anzahlZellenDesSpielfeds() {
        int input;
        do {
            io.ausgeben("Anzahl der Zellen (mindestens " + MINDESTZELLENANZAHL +
                            " | maximal " + MAMIMALZELLENANZAHL + "): ");
            input = io.leseInteger();
        } while (input < MINDESTZELLENANZAHL || input > MAMIMALZELLENANZAHL);
        return input;
    }

    /**
     * @return
     */
    public int wahrscheinlichkeitDerBesiedelung() {
        int input;
        do {
            io.ausgeben("Bitte einen Gültigen Wert zwischen 1 - 100 Eingeben: ");
            input = io.leseInteger();
        } while (input < 1 || input > 100);
        return input;
    }

    /**
     * #
     *
     * @return
     */
    public int anzahlDerSimulationsschritte() {
        int input;
        do {
            io.ausgeben("Bitte einen Gültigen Wert zwischen 1 - " + Integer.MAX_VALUE + " Eingeben: ");
            input = io.leseInteger();
        } while (input < -1);
        return input;
    }
}

