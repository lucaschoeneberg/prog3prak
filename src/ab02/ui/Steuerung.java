package ab02.ui;

import ab02.logik.BeiAenderung;
import ab02.logik.Simulation;
import ab02.util.EinUndAusgabe;
import ab02.util.Interaktionsbrett;

import java.util.Objects;

public class Steuerung implements BeiAenderung {
    private SpielfeldDarstellung gameDisplayer;
    private Simulation simulation;
    private NutzerEingabe io;

    public Steuerung(Simulation simulation) {
        this.simulation = Objects.requireNonNull(simulation);
        this.initialise();
    }

    /**
     *
     */
    private void initialise() {
        this.gameDisplayer = new SpielfeldDarstellung(new Interaktionsbrett());
        this.io = new NutzerEingabe(new EinUndAusgabe());
        this.simulation.signUpForUpdate(this);
    }

    public void gameLoop() {
        int amountCells = this.io.amountCellsOfPlayfield();
        int probability = this.io.probabilityCalculation();
        this.simulation.calculateFirstGeneration(amountCells, probability);

        int amountSteps;
        do {
            amountSteps = this.io.amountGenerationSteps();
            if (amountSteps < 0)
                break;

            this.simulation.calculateNextGeneration(amountSteps);
        } while (amountSteps >= 0);
        System.out.println("-------------------------------------------------------------" + "\n" + "Und Tschuess!!!");

    }

    /**
     * @param newGeneration
     */
    @Override
    public void update(boolean[][] newGeneration) {
        this.gameDisplayer.drawPlayfield(newGeneration);
    }
}