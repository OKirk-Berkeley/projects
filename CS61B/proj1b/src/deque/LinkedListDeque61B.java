package deque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T> {

    private Node sentinel;
    private int size;

    public class Node {
        private T item;
        private Node next;
        private Node prev;

        public Node() {
            this.item = null;
            this.next = null;
            this.prev = null;
        }

        public Node(T i) {
            this.item = i;
            this.next = null;
            this.prev = null;
        }
    }

    public LinkedListDeque61B() {
        this.sentinel = new Node();
        this.sentinel.next = this.sentinel;
        this.sentinel.prev = this.sentinel;
        this.size = 0;
    }

    @Override
    public void addFirst(T x) {
        Node cur = new Node(x);
        cur.prev = this.sentinel;
        cur.next = this.sentinel.next;
        cur.next.prev = cur;
        this.sentinel.next = cur;
        this.size++;
    }

    @Override
    public void addLast(T x) {
        Node cur = new Node(x);
        cur.next = this.sentinel;
        cur.prev = this.sentinel.prev;
        cur.prev.next = cur;
        this.sentinel.prev = cur;
        this.size++;
    }

    @Override
    public List<T> toList() {
        List<T> r = new ArrayList<>();
        Node x = this.sentinel;
        while (x.next != sentinel) {
            x = x.next;
            r.add(x.item);
        }
        return r;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public T removeFirst() {
        if (this.isEmpty()) {
            return null;
        }
        T temp = this.sentinel.next.item;
        this.sentinel.next = this.sentinel.next.next;
        this.sentinel.next.prev = this.sentinel;
        this.size--;
        return temp;
    }

    @Override
    public T removeLast() {
        if (this.isEmpty()) {
            return null;
        }
        T temp = this.sentinel.prev.item;
        this.sentinel.prev = this.sentinel.prev.prev;
        this.sentinel.prev.next = this.sentinel;
        this.size--;
        return temp;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= this.size) {
            return null;
        }
        Node cur = this.sentinel.next;
        while (index > 0) {
            cur = cur.next;
            index--;
        }
        return cur.item;
    }

    @Override
    public T getRecursive(int index) {
        if (index < 0 || index >= this.size) {
            return null;
        }
        return helper(index, sentinel.next);
    }

    private T helper(int index, Node node) {
        if (index == 0) {
            return node.item;
        }
        return helper(index - 1, node.next);
    }

    private class LinkedListDequeIterator<T> implements Iterator<T> {

        private Node node;

        public LinkedListDequeIterator() {
            this.node = sentinel.next;
        }

        @Override
        public boolean hasNext() {
            return !(this.node == sentinel);
        }

        @Override
        public T next() {
            T ret = (T) this.node.item;
            this.node = this.node.next;
            return ret;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof Deque61B lld && this.size() == lld.size()) {
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
