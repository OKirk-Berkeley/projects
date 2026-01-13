package deque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayDeque61B<T> implements Deque61B<T> {

    private T[] array;
    private int size;
    private int nextFirst;
    private int nextLast;


    public ArrayDeque61B() {
        this.array = (T[]) new Object[8];
        this.size = 0;
        this.nextFirst = 7;
        this.nextLast = 0;
    }

    private void resizeUp() {
        T[] temp = (T[]) new Object[this.array.length * 2];
        for (int i = 0; i < this.size; i++) {
            temp[i] = (T) this.get(i);
        }
        this.array = temp;
        this.nextFirst = this.array.length - 1;
        this.nextLast = this.size;
    }

    @Override
    public void addFirst(Object x) {
        if (this.size == this.array.length) {
            resizeUp();
        }
        this.array[this.nextFirst] = (T) x;
        this.nextFirst = Math.floorMod(this.nextFirst - 1 + this.array.length, this.array.length);
        this.size++;
    }

    @Override
    public void addLast(Object x) {
        if (this.size == this.array.length) {
            resizeUp();
        }
        this.array[this.nextLast] = (T) x;
        this.nextLast = Math.floorMod(this.nextLast + 1, this.array.length);
        this.size++;
    }

    @Override
    public List toList() {
        List<T> ret = new ArrayList<>();
        for (int i = 0; i < this.size; i++) {
            ret.add((T) this.get(i));
        }
        return ret;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    private void resizeDown() {
        T[] temp = (T[]) new Object[this.array.length / 2];
        for (int i = 0; i < this.size; i++) {
            temp[i] = (T) this.get(i);
        }
        this.array = temp;
        this.nextFirst = this.array.length - 1;
        this.nextLast = this.size;
    }

    @Override
    public T removeFirst() {
        if (this.isEmpty()) {
            return null;
        }
        if (this.size * 4 <= this.array.length && this.array.length > 8) {
            resizeDown();
        }
        this.nextFirst = Math.floorMod(this.nextFirst + 1, this.array.length);
        T temp = this.array[this.nextFirst];
        this.array[this.nextFirst] = null;
        this.size--;
        return temp;
    }

    @Override
    public T removeLast() {
        if (this.isEmpty()) {
            return null;
        }
        if (this.size * 4 <= this.array.length && this.array.length > 8) {
            resizeDown();
        }
        this.nextLast = Math.floorMod(this.nextLast - 1 + this.array.length, this.array.length);
        T temp = this.array[this.nextLast];
        this.array[this.nextLast] = null;
        this.size--;
        return temp;
    }

    @Override
    public T get(int index) {
        if (index >= 0 && index < this.size) {
            return this.array[Math.floorMod((this.nextFirst + 1 + index), this.array.length)];
        }
        return null;
    }

    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
    }

    private class ArrayDequeIterator<T> implements Iterator<T> {

        private int index;

        public ArrayDequeIterator() {
            this.index = 0;
        }

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public T next() {
            T ret = (T) get(this.index);
            this.index++;
            return ret;
        }
    }

    @Override
    public Iterator iterator() {
        return new ArrayDequeIterator();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof Deque61B lld && lld.size() == this.size()) {
            for (int i = 0; i < this.size(); i++) {
                if (!(this.get(i).equals(lld.get(i)))) {
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return this.toList().toString();
    }
}
