package ab01.orchester;

public class MusikerIn extends Mitglied {
    private final Instrument instrument;

    /**
     * Konstruktor von MusikerIn: übergibt den namen an
     * die super Klasse Mitglied und trägt das Instrument ein
     *
     * @param name       String
     * @param instrument Instrument
     */
    public MusikerIn(String name, Instrument instrument) {
        super(name);
        this.instrument = instrument;
    }

    /**
     * hashCode generierung
     * wird generiert aus der Primzahl 31 und dem Hashcode des returns der toString Methode.
     *
     * @return int
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.toString() == null) ? 0 : this.toString().hashCode());
        return result;
    }

    /**
     * Überprüft ob dieses objekt dem übergebenen entspricht.
     *
     * @param obj Object
     * @return bool
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        MusikerIn other = (MusikerIn) obj;
        if (this.toString() == null) {
            return other.toString() == null;
        } else return this.toString().equals(other.toString());
    }

    public Instrument getInstrument() {
        return instrument;
    }

    @Override
    public String toString() {
        return "MusikerIn: " + super.getName() + " " + instrument;
    }
}
