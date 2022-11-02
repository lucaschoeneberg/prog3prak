package ab01.audio.adapter;

import ab01.audio.StdAudioPlayer;
import de.hsos.prog3.audio.SimpleAudioPlayer;

import java.io.IOException;
import java.net.URL;

public class SimpleAudioPlayerAdapter extends SimpleAudioPlayer implements StdAudioPlayer {

    public SimpleAudioPlayerAdapter(URL inputUrl) throws IOException {
        super(inputUrl);
    }

    /**
     * @param url path to audio file
     * @throws IOException
     */
    @Override
    public void einmaligAbspielen(URL url) throws IOException {
        this.play(0);
    }

    /**
     * @param url            path to audio file
     * @param wiederholungen times to play audio
     * @throws IOException
     */
    @Override
    public void wiederholtAbspielen(URL url, int wiederholungen) throws IOException {
        this.play(wiederholungen);
    }

    /**
     *
     */
    @Override
    public void tonAus() {
        this.setDebug(true);
        this.verboseLogging(true);
    }

    /**
     *
     */
    @Override
    public void tonAn() {
        this.setDebug(false);
        this.verboseLogging(true);
    }
}
