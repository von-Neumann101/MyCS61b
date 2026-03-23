import java.util.ArrayList;
import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T> {
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
    public LinkedListDeque61B() {
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(T x) {
        Node first = new Node(x, sentinel, sentinel.next);
        sentinel.next.prev = first;
        sentinel.next = first;
        size += 1;
    }

    @Override
    public void addLast(T x) {
        Node last = new Node(x, sentinel.prev, sentinel);
        sentinel.prev.next = last;
        sentinel.prev = last;
        size += 1;
    }

    @Override
    public List<T> toList() {
        List<T> result = new ArrayList<>();
        Node i = sentinel.next;
        while(i != sentinel) {
            result.add(i.label);
            i = i.next;
        }
        return result;
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
        if(sentinel.next == sentinel) {
            return null;
        }
        T remove = sentinel.next.label;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return remove;
    }

    @Override
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

    @Override
    public T get(int index) {
        if(index >= size){
            return null;
        }
        Node i = sentinel.next;
        while(index != 0) {
            index -= 1;
            i = i.next;
        }
        return i.label;
    }

    @Override
    public T getRecursive(int index) {
        return helpRecursive(index, sentinel.next);
    }

    private T helpRecursive(int index, Node position) {
        if(index == 0) {
            return sentinel.next.label;
        }
        return helpRecursive(index - 1, position.next);
    }
}
