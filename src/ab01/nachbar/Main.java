package ab01.nachbar;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        NachbarSet();
    }

    public static void NachbarSet() {
        Set<Nachbar> nachbarSet = new HashSet<Nachbar>();
        Nachbar a = new Nachbar("Sergy", "Spät");
        Nachbar b = new Nachbar("Hendrik", "Pilzner");
        Nachbar c = new Nachbar("Luca", "Schönberg");
        Nachbar d = new Nachbar("Test", "Amtmann");
        Nachbar e = new Nachbar("Test", "Amtmann");

        nachbarSet.add(a);
        nachbarSet.add(a);
        nachbarSet.add(c);
        nachbarSet.add(d);
        nachbarSet.add(e);
        nachbarSet.add(b);

        System.out.println("Hallo " +
                nachbarSet
                        .stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining(", ")) + "."
        );
    }
}


