package ab01.orchester;

import java.io.IOException;

public class App {
    public static void main(String[] args) {
        String audioDatei = "/All_Together.wav";

        Orchester orchester = new Orchester("Oper1", audioDatei);
        DirigentIn hogwartsDirigent = new DirigentIn("Oper2");
        orchester.setDirigentIn(hogwartsDirigent);

        MusikerIn m1 = new MusikerIn("Luca", Instrument.SAXOPHON);
        MusikerIn m2 = new MusikerIn("Frank", Instrument.AKKORDION);
        MusikerIn m3 = new MusikerIn("Klaus", Instrument.SAXOPHON);
        MusikerIn m4 = new MusikerIn("Benedikt", Instrument.AKKORDION);
        MusikerIn m5 = new MusikerIn("Franz", Instrument.SCHLAGZEUG);
        MusikerIn m6 = new MusikerIn("Herbert", Instrument.AKKORDION);

        // MusikerIn Liste füllen
        orchester.addMusikerIn(m1);
        orchester.addMusikerIn(m2);
        orchester.addMusikerIn(m3);
        orchester.addMusikerIn(m1);
        orchester.addMusikerIn(m2);
        orchester.addMusikerIn(m3);
        orchester.addMusikerIn(m4);
        orchester.addMusikerIn(m5);
        orchester.addMusikerIn(m6);

        orchester.getMusikerInnen().forEach(musikerIn ->
                System.out.println(musikerIn.toString())
        );

        try {
            orchester.proben();
            orchester.spielen();
            orchester.auftreten();
            orchester.spielen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
