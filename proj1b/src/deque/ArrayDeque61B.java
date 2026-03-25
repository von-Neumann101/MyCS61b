package deque;

import java.util.List;
import java.lang.Math;

public class ArrayDeque61B<T> implements Deque61B<T> {

    private int nextFirst, nextLast;
    private int size;
    private T[] back;
    public ArrayDeque61B(){
        nextFirst = 3;
        nextLast = 4;
        size = 0;
        back = (T[])new Object[8];
    }

    @Override
    public void addFirst(T x) {
        back[nextFirst] = x;
        size += 1;
        if(nextFirst == 0){
            nextFirst = back.length - 1;
            return;
        }
        nextFirst -= 1;
    }

    @Override
    public void addLast(T x) {
        back[nextLast] = x;
        size += 1;
        if(nextLast == back.length - 1){
            nextLast = 0;
            return;
        }
        nextFirst += 1;
    }

    @Override
    public List<T> toList() {
        return List.of();
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        return null;
    }

    @Override
    public T removeLast() {
        return null;
    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public T getRecursive(int index) {
        return null;
    }
}
