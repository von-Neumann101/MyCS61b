package deque;

import java.util.ArrayList;
import java.util.Iterator;
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

    @Override
    public T getRecursive(int index) {
        if(index >= size || index < 0){
            return null;
        }
        return helpRecursive(index, sentinel.next);
    }

    private T helpRecursive(int index, Node position) {
        if(index == 0) {
            return position.label;
        }
        return helpRecursive(index - 1, position.next);
    }

    public class LinkedListIterator implements Iterator<T>{
        Node position = sentinel.next;

        @Override
        public boolean hasNext() {
            return position != sentinel;
        }

        @Override
        public T next() {
            T r = position.label;
            position = position.next;
            return r;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    @Override
    public boolean equals(Object o){
        if(o == this){
            return true;
        }
        if(o instanceof LinkedListDeque61B l){
            if(l.size != size){
                return false;
            }
            for(int i = 0; i < size; i++){
                if(l.get(i) != this.get(i)){
                    return false;
                }
            }
        }else{
            return false;
        }
        return true;
    }

    @Override
    public String toString(){
        StringBuilder s = new StringBuilder("[");
        Node start = sentinel.next;
        while(start.next != sentinel){
            String item = String.valueOf(start.label);
            s.append(item).append(", ");
            start = start.next;
        }
        s.append(String.valueOf(start.label)).append("]");
        return s.toString();
    }
}
