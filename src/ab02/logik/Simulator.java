package ab02.logik;

import java.util.Arrays;
import java.util.Random;

public class Simulator implements Simulation {

    private boolean[][] spielfeld;
    private BeiAenderung beiAenderung;
    private int anzahlFelder;

    /**
     * @param anzahlDerZellen
     * @param wahrscheinlichkeitDerBesiedlung
     */
    @Override
    public void berechneAnfangsGeneration(int anzahlDerZellen, int wahrscheinlichkeitDerBesiedlung) {
        this.anzahlFelder = anzahlDerZellen;
        this.spielfeld = new boolean[anzahlDerZellen][anzahlDerZellen];
        Random random = new Random();

        for (int i = 0; i < spielfeld.length; i++)
            for (int j = 0; j < spielfeld.length; j++)
                this.spielfeld[i][j] = random.nextInt(100) < wahrscheinlichkeitDerBesiedlung;

        this.aktualisiere(this.spielfeld);
    }

    /**
     * @param berechnungsschritte
     */
    @Override
    public void berechneFolgeGeneration(int berechnungsschritte) {
        if (berechnungsschritte < 1)
            return;
        if (this.spielfeld == null)
            throw new IllegalStateException("Initialisierung fehlgeschlagen");

        boolean[][] neueGeneration = new boolean[this.anzahlFelder][this.anzahlFelder];

        for (int i = 0; i < this.anzahlFelder; ++i)
            for (int j = 0; j < this.anzahlFelder; ++j)
                neueGeneration[i][j] = this.zellenBerechnung(i, j);

        if (Arrays.deepEquals(neueGeneration, this.spielfeld)) {
            System.out.println("Keine Änderung!");
            return;
        }

        this.spielfeld = neueGeneration;
        this.aktualisiere(this.spielfeld);

        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.berechneFolgeGeneration(berechnungsschritte - 1);
    }

    private boolean zellenBerechnung(int cell, int column) {
        int neighbourCounter = 0;

        for (int i = -1; i <= 1; ++i) {
            if (cell + i < 0 || cell + i >= this.anzahlFelder) // Zeile außerhalb des Spielfeldes
                continue;

            for (int j = -1; j <= 1; ++j) {
                if (column + j < 0 || column + j >= this.anzahlFelder) // Spalte außerhalb des Spielfeldes
                    continue;

                if (i == 0 && j == 0) // aktuelle Zelle
                    continue;

                if (this.spielfeld[cell + i][column + j])
                    neighbourCounter++;
            }
        }
        if (this.spielfeld[cell][column])
            return neighbourCounter == 2 || neighbourCounter == 3; // Bleibt lebendig

        return neighbourCounter == 3; // Wird lebendig
    }

    /**
     * @param beiAenderung
     */
    @Override
    public void anmeldenFuerAktualisierungBeiAenderung(BeiAenderung beiAenderung) {
        this.beiAenderung = beiAenderung;
    }

    private void aktualisiere(boolean[][] neu) {
        if (this.beiAenderung != null)
            beiAenderung.aktualisiere(neu);
    }
}
