package ab02.logik;

public interface Simulation {
    void calculateFirstGeneration(int anzahlDerZellen, int wahrscheinlichkeitDerBesiedlung);

    void calculateNextGeneration(int berechnungsschritte);

    void signUpForUpdate(BeiAenderung beiAenderung);
}