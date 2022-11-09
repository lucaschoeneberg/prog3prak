package ab02;

import ab02.ui.SpielfeldDarstellung;
import ab02.util.Interaktionsbrett;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpielfeldDarstellungTest {

    private SpielfeldDarstellung sp;
    private int FRAME_WIDTH = 340;
    private int margin = 10;
    private int amountCells = 51;
    private boolean[][] field = new boolean[amountCells][amountCells];

    @BeforeEach
    void setUp() {
        sp = new SpielfeldDarstellung(new Interaktionsbrett());
        boolean schachbrett = true;

        for (int x = 0; x < field.length; x++) {
            for (int y = 0; y < field.length; y++) {
                field[x][y] = schachbrett;
                if (schachbrett)
                    schachbrett = false;
                else if (!schachbrett)
                    schachbrett = true;

            }
        }
    }

    @Test
    void drawPlayfield() throws InterruptedException {
        System.out.println("Test: draw the entire Field");
        sp.drawPlayfield(this.field);
        Thread.sleep(500);
    }

    @Test
    void clear() throws InterruptedException {
        System.out.println("Test: draw the field");
        sp.drawPlayfield(this.field);
        Thread.sleep(900);
        System.out.println("Test: clean the field");
        sp.clear();
    }
}