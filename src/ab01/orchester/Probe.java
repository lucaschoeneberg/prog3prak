package ab01.orchester;

import ab01.audio.StdAudioPlayer;
import ab01.audio.adapter.SimpleAudioPlayerAdapter;

import java.io.IOException;
import java.net.URL;

public class Probe implements Verhalten {
    /**
     * @param orchester
     * @throws IOException
     */
    @Override
    public void spielen(Orchester orchester) throws IOException {
        for (MusikerIn musikerIn : orchester.getMusikerInnen()) {
            URL url = Orchester.class.getResource(musikerIn.getInstrument().getAudiodatei());
            try {
                StdAudioPlayer player = new SimpleAudioPlayerAdapter(url);
                player.einmaligAbspielen(url);
            } catch (IOException e) {
                throw new IOException(e);
            }
        }
    }
}
