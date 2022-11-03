package ab02.ui;

import ab02.util.Interaktionsbrett;

public class SpielfeldDarstellung {
    private Interaktionsbrett ib;

    // bei 1440p Monitoren
    private final int FRAME_WIDTH = 340;

    // bei 1080p Monitoren
    // private final int FRAME_WIDTH = 940
    private int margin = 10;

    /**
     * @param ib
     */
    public SpielfeldDarstellung(Interaktionsbrett ib) {
        this.ib = ib;
    }

    /**
     * @param field
     */
    public void drawPlayfield(boolean[][] field) {
        this.clear();
        int lengthOfEachEntity = this.FRAME_WIDTH / field.length;
        for (int i = 0; i < field.length; ++i) {
            for (int j = 0; j < field.length; ++j) {
                Quadrat cube = new Quadrat(this.margin + j * lengthOfEachEntity, this.margin + i * lengthOfEachEntity, lengthOfEachEntity);
                if (field[i][j])
                    cube.drawFilling(this.ib);
                else
                    cube.drawFrame(this.ib);
            }
        }
    }


    public void clear() {
        ib.abwischen();
    }

}
