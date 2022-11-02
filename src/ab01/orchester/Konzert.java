package ab01.orchester;

import ab01.audio.StdAudioPlayer;
import ab01.audio.adapter.SimpleAudioPlayerAdapter;

import java.io.IOException;
import java.net.URL;

public class Konzert implements Verhalten {
    /**
     * @param orchester Orchester Object
     * @throws IOException
     */
    @Override
    public void spielen(Orchester orchester) throws IOException {
        URL url = Orchester.class.getResource(orchester.getAudioDateiKonzert());
        try {
            StdAudioPlayer player = new SimpleAudioPlayerAdapter(url);
            player.einmaligAbspielen(url);
        } catch (IOException e) {
            throw new IOException("Auftritt wird abgebrochen", e);
        }
    }
}
