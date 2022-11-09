package ab02;

import ab02.ui.NutzerEingabe;
import ab02.util.EinUndAusgabe;

public class NutzerEingbeTest {

    public static void main(String[] args) {
        NutzerEingabe io = new NutzerEingabe(new EinUndAusgabe());

        int amountOfCells = io.amountCellsOfPlayfield();
        System.out.println("Der letzendliche Wert vom Nutzer ist: " + amountOfCells);

        int amountSteps = io.amountGenerationSteps();
        System.out.println("Der letzendliche Wert vom Nutzer ist: " + amountSteps);

        int probability = io.probabilityCalculation();
        System.out.println("Der letzendliche Wert vom Nutzer ist: " + probability);
    }
}