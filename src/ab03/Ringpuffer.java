package ab03;


import java.io.Serializable;
import java.util.*;

import static java.util.Collections.copy;

public class Ringpuffer<T> implements Queue<T>, Serializable, Cloneable {
    private ArrayList<T> elements;
    private int writePos = 0;
    private int readPos = 0;
    private int size;
    private int capacity;
    private final boolean fixedCapacity;
    private final boolean discarding;

    public Ringpuffer(int capacity, boolean fixedCapacity, boolean discarding) {
        this.capacity = capacity;
        this.fixedCapacity = fixedCapacity;
        this.discarding = discarding;
        this.elements = new ArrayList<>(capacity);
    }



    @Override
    public int size() {
        return elements.size();
    }

    @Override
    public boolean isEmpty() {
        return elements.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return elements.stream().anyMatch(element -> (element == o));
    }

    @Override
    public Iterator<T> iterator() {
        return elements.iterator();
    }

    @Override
    public Object[] toArray() {
        return elements.toArray();
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return a;
    }

    @Override
    public String toString() {
        return "Ringpuffer{" +
                "elements=" + elements +
                ", writePos=" + writePos +
                ", readPos=" + readPos +
                ", size=" + size +
                ", capacity=" + capacity +
                ", fixedCapacity=" + fixedCapacity +
                ", discarding=" + discarding +
                '}';
    }

    @Override
    public boolean add(T item) {
        if (size == capacity) {
            if (fixedCapacity) {
                if (discarding) remove();
                else return false;
            } else {
                ArrayList<T> temp = new ArrayList<>(capacity);
                temp.addAll(this);
                readPos = 0;
                writePos = size;
                elements = temp;
                capacity += 1;
                for (int i = 0; i < capacity - size; i++) elements.add(null);
            }
        }
        elements.add(writePos, item);
        size++;
        writePosUp();
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (elements.contains(o) && elements.get(this.readPos).equals(o)) {
            readPosUp();
            size--;
            return true;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return elements.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        c.forEach(this::add); //Siehe Vorlesung
        return true;
    }

    public boolean offerAll(Collection<? extends T> c) {
        c.forEach(this::offer); //Siehe Vorlesung
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        elements.forEach(this::remove);
        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        elements.forEach(element -> {
            if (!c.contains(element)) remove(element);
        });
        return true;
    }

    @Override
    public void clear() {
        elements.clear();
    }

    @Override
    public boolean offer(T t) {
        if (t == null) throw new NullPointerException();
        this.add(t);
        return true;
    }

    @Override
    public T remove() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        T tmp = elements.get(this.readPos);
        elements.remove(this.readPos);
        readPosUp();
        this.size--;
        return tmp;
    }

    @Override
    public T poll() {
        T temp = peek();
        if (temp == null) return null;
        remove();
        return temp;
    }

    @Override
    public T element() {
        if (!isEmpty()) {
            return this.elements.get(this.readPos);
        }
        throw new NoSuchElementException();
    }

    @Override
    public T peek() {
        try {
            return element();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    private void readPosUp() {
        this.readPos = (readPos + 1) % capacity;
    }

    private void writePosUp() {
        this.writePos = (writePos + 1) % capacity;
    }

    @Override
    public Ringpuffer<T> clone() {
        try {
            Ringpuffer<T> c = (Ringpuffer<T>) super.clone();
            c.elements = new ArrayList<>(c.capacity);
            copy(c.elements, elements);
            return c;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}