package ab02;

import ab02.util.EinUndAusgabe;

public class NutzerEingabeTest {
    public static void main(String[] args) {
        EinUndAusgabe io = new EinUndAusgabe();
        io.leseString();
        io.leseInteger();
        io.leseFloat();
        io.leseBoolean();
    }
}
