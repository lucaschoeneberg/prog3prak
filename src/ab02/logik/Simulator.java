package ab02.logik;

import java.util.Arrays;
import java.util.Random;

public class Simulator implements Simulation {

    private boolean[][] gameField;

    private BeiAenderung onSwitch;
    private int amountFields;

    /**
     * Creates a new boolean[][] Array with the help of @param amountCells. After that it iterates trough the whole 2-Dimensional
     * Array and determines its state
     *
     * @param amountCells amount of Gridcells on each side of the Gamefield
     * @param probability the probability for a individual cell to be alive or dead
     */
    @Override
    public void calculateFirstGeneration(int amountCells, int probability) {
        this.amountFields = amountCells;
        this.gameField = new boolean[amountCells][amountCells];
        Random random = new Random();

        for (int i = 0; i < gameField.length; i++) {
            for (int j = 0; j < gameField.length; j++) {
                this.gameField[i][j] = random.nextInt(100) < probability;
            }
        }
        this.update(this.gameField);
    }

    /**
     * Recursive method to calculate the next Generation. Creates a new Gamefield and checks all of the grids requirements
     * to update the individual cells. If there are no new Changes the Game has created a stable pattern which ends the process
     *
     * 
     * @param amountSteps the User decides how many new Generations will be shown
     */
    @Override
    public void calculateNextGeneration(int amountSteps) {
        if (amountSteps < 1) //
            return;
        if (this.gameField == null) // auf einem nicht existierenden spielfeld kann nicht gearbeitet werden
            throw new IllegalStateException("Initialise failed");

        boolean[][] newGeneration = new boolean[this.amountFields][this.amountFields];

        for (int i = 0; i < this.amountFields; ++i) {
            for (int j = 0; j < this.amountFields; ++j) {
                newGeneration[i][j] = this.updateCells(i, j);
            }
        }
        if (Arrays.deepEquals(newGeneration, this.gameField)) {
            System.out.println("No Change!");
            return;
        }

        this.gameField = newGeneration;
        this.update(this.gameField);

        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.calculateNextGeneration(amountSteps - 1);
    }

    /**
     *
     * @param lines
     * @param column
     * @return
     */
    private boolean updateCells(int lines, int column) {
        int neighbourCounter = 0;

        for (int i = -1; i <= 1; ++i) {
            if (lines + i < 0 || lines + i >= this.amountFields) // Zeile außerhalb des Spielfeldes
                continue;

            for (int j = -1; j <= 1; ++j) {
                if (column + j < 0 || column + j >= this.amountFields) // Spalte außerhalb des Spielfeldes
                    continue;

                if (i == 0 && j == 0) // aktuelle Zelle
                    continue;

                if (this.gameField[lines + i][column + j])
                    neighbourCounter++;
            }
        }
        if (this.gameField[lines][column])
            return neighbourCounter == 2 || neighbourCounter == 3; // Bleibt lebendig

        return neighbourCounter == 3; // Wird lebendig
    }

    @Override
    public void signUpForUpdate(BeiAenderung onSwitch) {
        this.onSwitch = onSwitch;
    }

    private void update(boolean[][] newGeneration) {
        if (this.onSwitch != null)
            onSwitch.update(newGeneration);
    }
}