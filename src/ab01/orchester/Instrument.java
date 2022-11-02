package ab01.orchester;

public enum Instrument {
    SAXOPHON("/Baritone.wav"),
    SCHLAGZEUG("/Drum.wav"),
    AKKORDION("/Accordion.wav");

    private final String audiodatei;

    Instrument(String datei) {
        this.audiodatei = datei;
    }

    public String getAudiodatei() {
        return this.audiodatei;
    }

    @Override
    public String toString() {
        return "Instrument "+ audiodatei +
                " " + super.toString();
    }
}
