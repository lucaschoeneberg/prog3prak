package ab03;


import java.io.Serializable;
import java.util.*;

public class Ringpuffer<T> implements Queue<T>, Serializable, Cloneable {
    private ArrayList<T> elements = new ArrayList<>();
    private int writePos = 0;
    private int readPos = 0;
    private int size;
    private int capacity;
    private boolean fixedCapacity;
    private boolean discarding;

    public Ringpuffer(){

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
        return elements.contains(o);
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
        return elements.toArray(a);
    }

    @Override
    public boolean add(T t) {
        elements.add(writePos, t);
        writePosUp();
        return true;
    }

    @Override
    public boolean remove(Object o) {
        return elements.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return elements.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return elements.addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return elements.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return elements.retainAll(c);
    }

    @Override
    public void clear() {
        elements.clear();
    }

    @Override
    public boolean offer(T t) {
        if(!isEmpty() && t.getClass().equals(elements.get(0).getClass()))
            throw new ClassCastException();

        if (t.equals(null))
            throw new NullPointerException();

       this.add(t);
       return true;
    }

    @Override
    public T remove() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        T tmp = elements.get(this.readPos);
        readPosUp();
        return tmp;
    }

    @Override
    public T poll() {
        if (isEmpty()) {
            return null;
        }
        T tmp = elements.get(this.readPos);
        readPosUp();
        return tmp;
    }

    @Override
    public T element() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return elements.get(this.readPos);
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return elements.get(this.readPos);
    }

    private void readPosUp() {
        if(this.readPos < this.capacity)
            this.readPos++;
        else
            this.readPos = 0;
    }

    private void writePosUp() {
        if(this.writePos < this.capacity)
            this.writePos++;
        else
            this.writePos = 0;
    }
}