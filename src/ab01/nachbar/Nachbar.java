package ab01.nachbar;

public class Nachbar {
    private String vorname;
    private String nachname;

    /**
     * Konstruktor von Nachbar: setzt vorname und nachname vom Nachbar
     * @param vorname String
     * @param nachname String
     */
    public Nachbar(String vorname, String nachname) {
        this.setVorname(vorname);
        this.setNachname(nachname);
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    /**
     * hashCode generierung
     * wird generiert aus der Primzahl 31 und dem Hashcode des returns der toString Methode.
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
     * @param obj Object
     * @return bool
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Nachbar other = (Nachbar) obj;
        if (this.toString() == null) {
            return other.toString() == null;
        } else return this.toString().equals(other.toString());
    }

    @Override
    public String toString() {
        return vorname + " " + nachname;
    }
}
