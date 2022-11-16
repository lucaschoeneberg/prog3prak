package ab03;

public class Main {

    public static void main(String[] args) {
        trueTrue();
        falseFalse();
    }

    public static void trueTrue() {
        Ringpuffer<Integer> BeispielTrueTrue = new Ringpuffer<>(4, true, true);
        Ringpuffer<Integer> BeispielFalseFalse = new Ringpuffer<>(4, true, true);
        test(BeispielTrueTrue, BeispielFalseFalse);
    }

    public static void falseFalse() {
        Ringpuffer<Integer> Buffer = new Ringpuffer<>(4, false, false);
        Ringpuffer<Integer> Buffer2 = new Ringpuffer<>(4, false, false);
        test(Buffer, Buffer2);
    }

    public static void test(Ringpuffer<Integer> Buffer, Ringpuffer<Integer> Buffer2) {
        int count = 0;
        for (int i = 0; i < 10; i++) {
            try {
                Buffer.add(count++);
                Buffer2.add(count);
            } catch (IllegalStateException e) {
                System.out.println(e);
            }
        }

        System.out.println("Test: Iterate with iterator over Queue");
        System.out.print("\nIterator Buffer print: ");
        Buffer.forEach(System.out::print);
        System.out.print("\nIterator Buffer2 print: ");
        Buffer2.forEach(System.out::print);
        System.out.println("\n\n----------------------------------------------------------------------------------\n");

        System.out.println("Test: addAll from Buffer to Buffer2");
        Buffer2.addAll(Buffer);
        System.out.println("Buffer1: " + Buffer);
        System.out.println("Buffer2: " + Buffer2);
        System.out.println("\nBuffer2 containsAll from Buffer: " + Buffer2.containsAll(Buffer));
        System.out.println("\n----------------------------------------------------------------------------------\n");

        System.out.println("Test: Remove");
        Buffer2.remove();
        System.out.println("Buffer2: " + Buffer2);
        System.out.println("\n----------------------------------------------------------------------------------\n");

        System.out.println("Test: contain");
        System.out.println("Does Buffer2 contain 8: " + Buffer2.contains(8));
        System.out.println("Does Buffer2 contain 6: " + Buffer2.contains(6));
        System.out.println("Does Buffer contain 3: " + Buffer.contains(3));
        System.out.println("Does Buffer contain 0: " + Buffer.contains(0));
        System.out.println("\n----------------------------------------------------------------------------------\n");

        System.out.println("Test: offer");
        System.out.println("Offer 2 to Buffer2");
        try {
            Buffer2.offer(2);
        } catch (NullPointerException e) {
            System.out.println("Nullpointerexception wird geworfen: Null wird aufgefangen");
        }
        System.out.println("Buffer2: " + Buffer2);

        System.out.println("Test: try to offer null");
        try {
            Buffer2.offer(null);
        } catch (NullPointerException e) {
            System.out.println("Nullpointerexception wird geworfen: Null wird aufgefangen");
        }
        System.out.print("Buffer2: " + Buffer2);
        System.out.println("\n\n----------------------------------------------------------------------------------\n");

        System.out.println("Test: peek on Buffer2");
        System.out.println("\npeek: " + Buffer2.peek());
        System.out.println("peek: " + Buffer2.peek());
        System.out.println("\n----------------------------------------------------------------------------------\n");

    }
}

//in zusammenarbeit mit: Daniel Block Praktikum 3
//Aufgabe 3.2

/*
    In dem Anwendungsfall eines generischen Ringpuffers bietet es sich durchaus an ein dynamisches Array zu verwenden.
        Eine Collection, wie in diesem Fall ArrayList hat einige große Vorteile, wenn es darum geht Elemente mittendrin einzufuegen,
        oder andere Elemente zu löschen, sowieso die Liste zu sortieren.
        Arrays hingegen sind performanter, wenn es darum geht, festgelegte Zahlen von Daten zu speichern.
        Da wir für unseren Ringpuffern jedoch keine komplizierten eingriffe, in die gespeicherten
        Daten vornehmen müssen (wie z.B insert, delete, etc)
        wäre es durchaus schlauer ein generisches Array zu verwenden.
        jedoch muss man berücksichtigen, dass es in unserem Beispiel den Fall geben kann,
        dass der Ringpuffer erweitert werden muss (fixedCapacity = false)
        In diesem Fall würde ein Array hingegen wieder unpraktischer sein, jeodch nicht unmöglich.
        Man könnte hierfür zum Beispiel zwei verschiedene Strategien verwenden
        (fixedCapacity = false) =>  ArrayList<T>
 (fixedCapacity = true) => generisches Array*/
