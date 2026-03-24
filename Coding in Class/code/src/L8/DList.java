package L8;

import java.util.ArrayList;
import java.util.List;

public class DList<T> implements List61B<T> {
    private class Node {
        T label;
        Node next, prev;
        Node(T label, Node prev, Node next) {
            this.label = label;
            this.next = next;
            this.prev = prev;
        }
    }
    Node sentinel;
    int size;
    public DList() {
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public void addFirst(T x) {
        Node first = new Node(x, sentinel, sentinel.next);
        sentinel.next.prev = first;
        sentinel.next = first;
        size += 1;
    }

    public void insert(T x, int position){
        if(position > size || position < 0){
            return;
        }
        Node i = sentinel.next;
        while(position != 0) {
            position -= 1;
            i = i.next;
        }
        Node newNode = new Node(x, i.next, i);
        i.next.prev = newNode;
        i.next = newNode;
        size += 1;
    }

    public void addLast(T x) {
        Node last = new Node(x, sentinel.prev, sentinel);
        sentinel.prev.next = last;
        sentinel.prev = last;
        size += 1;
    }

    public List<T> toList() {
        List<T> result = new ArrayList<>();
        Node i = sentinel.next;
        while(i != sentinel) {
            result.add(i.label);
            i = i.next;
        }
        return result;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return this.size;
    }

    public T removeFirst() {
        if(sentinel.next == sentinel) {
            return null;
        }
        T remove = sentinel.next.label;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return remove;
    }

    public T removeLast() {
        if(sentinel.prev == sentinel) {
            return null;
        }
        T remove = sentinel.prev.label;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size -= 1;
        return remove;
    }

    public T get(int index) {
        if(index >= size || index < 0){
            return null;
        }
        Node i = sentinel.next;
        while(index != 0) {
            index -= 1;
            i = i.next;
        }
        return i.label;
    }

    public T getFirst() {
        return sentinel.next.label;
    }

    public T getLast() {
        return sentinel.prev.label;
    }
}
