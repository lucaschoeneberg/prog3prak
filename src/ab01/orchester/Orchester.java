package ab01.orchester;

import ab01.audio.StdAudioPlayer;
import ab01.audio.adapter.SimpleAudioPlayerAdapter;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Orchester {
    private final String bezeichnung;
    private final String audioDateiKonzert;
    private DirigentIn dirigentIn;
    private final Set<MusikerIn> musikerInnen;
    private Verhalten verhalten;

    public Orchester(String bezeichnung, String audioDateiKonzert) {
        this.musikerInnen = new HashSet<MusikerIn>();
        this.bezeichnung = bezeichnung;
        this.audioDateiKonzert = audioDateiKonzert;
    }

    public void setDirigentIn(DirigentIn dirigentIn) {
        this.dirigentIn = dirigentIn;
    }

    public void addMusikerIn(MusikerIn musikerIn) {
        this.musikerInnen.add(musikerIn);
    }

    public Collection<MusikerIn> getMusikerInnen() {
        return musikerInnen;
    }

    public String getAudioDateiKonzert() {
        return audioDateiKonzert;
    }

    public void proben() {
        this.verhalten = new Probe();
    }

    public void auftreten() {
        this.verhalten = new Konzert();
    }

    public void spielen() throws IOException {
        this.verhalten.spielen(this);
    }

    private static class Konzert implements Verhalten {
        /**
         * @param orchester Orchester Object
         * @throws IOException
         */
        @Override
        public void spielen(Orchester orchester) throws IOException {
            URL url = Orchester.class.getResource(orchester.getAudioDateiKonzert());
            StdAudioPlayer player;
            try {
                player = new SimpleAudioPlayerAdapter(url);
                player.einmaligAbspielen(url);
            } catch (IOException e) {
                throw new IOException("Auftritt wird abgebrochen", e);
            }
        }
    }

    private static class Probe implements Verhalten {
        /**
         * @param orchester Orchester Object
         * @throws IOException
         */
        @Override
        public void spielen(Orchester orchester) throws IOException {
            try {
                orchester.getMusikerInnen().forEach(musikerIn -> {
                    URL url = Orchester.class.getResource(musikerIn.getInstrument().getAudiodatei());
                    StdAudioPlayer player;
                    try {
                        player = new SimpleAudioPlayerAdapter(url);
                        player.einmaligAbspielen(url);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            } catch (RuntimeException e) {
                throw new IOException("Probe wird abgebrochen", e);
            }
        }
    }
}
