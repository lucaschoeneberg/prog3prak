package ab02.ui;

import ab02.util.EinUndAusgabe;

public class NutzerEingabe {
    private EinUndAusgabe io;
    private final int MINIMUN_NUMBER_OF_GRID = 3;
    private final int MAXIMUM_NUMBER_OF_GRID = 75;

    public NutzerEingabe(EinUndAusgabe io) {
        this.io = io;
    }

    public int amountCellsOfPlayfield() {
        int input;
        do {
            io.ausgeben("Anzahl der Zellen (mindestens " + MINIMUN_NUMBER_OF_GRID +
                            " | maximal " + MAXIMUM_NUMBER_OF_GRID + "): ");
            input = io.leseInteger();
        } while (input < MINIMUN_NUMBER_OF_GRID || input > MAXIMUM_NUMBER_OF_GRID);
        return input;
    }

    public int probabilityCalculation() {
        int input;
        do {
            io.ausgeben("Bitte einen Gültigen Wert zwischen 1 - 100 Eingeben: ");
            input = io.leseInteger();
        } while (input < 1 || input > 100);
        return input;
    }

    public int amountGenerationSteps() {
        int input;
        do {
            io.ausgeben("Bitte einen Gültigen Wert zwischen 1 - " + Integer.MAX_VALUE + " Eingeben: ");
            input = io.leseInteger();
        } while (input < 1 && input != -1);
        return input;
    }
}