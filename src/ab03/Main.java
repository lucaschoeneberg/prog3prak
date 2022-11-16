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
      // Warum????  count = 0;

   /** Warum???    for (int i = 10; i < 20; i++) {
            try {
                Buffer2.add(count++);
            } catch (IllegalStateException e) {
                System.out.println(e);
            }
        }*/

        System.out.print("\nIterator Buffer print: ");
        Buffer.forEach(System.out::print);
        System.out.print("\nIterator Buffer2 print: ");
        Buffer2.forEach(System.out::print);
        System.out.println("\n\n------------------------------------------------\n" );

        System.out.println("Test: addAll from Buffer to Buffer2");
        Buffer2.addAll(Buffer);
        System.out.print("Buffer: ");
        Buffer.forEach(System.out::print);
        System.out.print("\nBuffer2: ");
        Buffer2.forEach(System.out::print);
        System.out.println("\nBuffer2 containsAll from Buffer: " + Buffer2.containsAll(Buffer));
        System.out.println("\n------------------------------------------------\n" );

        System.out.println("Test: Remove");
        Buffer2.remove();
        System.out.print("Buffer2: ");
        //Buffer2.forEach(System.out::print);
        System.out.println(Buffer2);
        System.out.println("\n------------------------------------------------\n" );

        System.out.println("Does Buffer2 contain 3: " + Buffer2.contains(3));
        System.out.println("Does Buffer2 contain 0: " + Buffer2.contains(0));
        System.out.println("Does Buffer contain 3: " + Buffer.contains(3));
        System.out.println("Does Buffer contain 0: " + Buffer.contains(0));
        System.out.println("Offer 2");
        Buffer2.offer(2);
        System.out.println("Iterator Buffer2 print:");
        Buffer2.forEach(System.out::println);

        System.out.println("peek: " + Buffer2.peek());
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