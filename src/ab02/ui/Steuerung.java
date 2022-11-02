package ab02.ui;

import ab02.logik.BeiAenderung;
import ab02.logik.Simulation;
import ab02.util.EinUndAusgabe;
import ab02.util.Interaktionsbrett;

import java.util.Objects;

public class Steuerung implements BeiAenderung {
    private SpielfeldDarstellung spielfeldDarstellung;
    private Simulation simulation;
    private NutzerEingabe io;

    public Steuerung(Simulation simulation) {
        this.simulation = Objects.requireNonNull(simulation);
        this.initialisierung();
    }

    /**
     *
     */
    private void initialisierung() {
        this.spielfeldDarstellung = new SpielfeldDarstellung(new Interaktionsbrett());
        this.io = new NutzerEingabe(new EinUndAusgabe());
        this.simulation.anmeldenFuerAktualisierungBeiAenderung(this);
    }

    public void startDesSpiels() {
        int anzahlZellen = this.io.anzahlZellenDesSpielfeds();
        int wahrscheinlichkeit = this.io.wahrscheinlichkeitDerBesiedelung();
        this.simulation.berechneAnfangsGeneration(anzahlZellen, wahrscheinlichkeit);

        int anzahlSchritte;
        do {
            anzahlSchritte = this.io.anzahlDerSimulationsschritte();
            if (anzahlSchritte < 0)
                break;

            this.simulation.berechneFolgeGeneration(anzahlSchritte);
        } while (anzahlSchritte >= 0);
        System.out.println("-------------------------------------------------------------" + "\n" + "Und Tschuess!!!");

    }


    /**
     * @param neueGeneration
     */
    @Override
    public void aktualisiere(boolean[][] neueGeneration) {
        this.spielfeldDarstellung.spielfeldDarstellen(neueGeneration);
    }
}