package ab02;

import ab02.ui.Quadrat;
import ab02.util.Interaktionsbrett;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class QuadratTest {
    private Interaktionsbrett ib;
    private Quadrat cube;

    @BeforeEach
    void setUp() {
        ib = new Interaktionsbrett();
        cube = new Quadrat(1, 1, 200);
    }

    @Test
    void drawFrame() {
        System.out.println("Test: draw Frame of Quadrat.");
        cube.drawFrame(ib);
    }

    @Test
    void drawFilling() throws InterruptedException {
        System.out.println("Test: draw Filed Frame of Quadrat.");
        cube.drawFilling(ib);
        Thread.sleep(900);
    }
}