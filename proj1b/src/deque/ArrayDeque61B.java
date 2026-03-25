package deque;

import java.util.ArrayList;
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
        nextFirst = Math.floorMod(nextFirst - 1, back.length);
        size += 1;
    }

    @Override
    public void addLast(T x) {
        back[nextLast] = x;
        nextLast = Math.floorMod(nextLast + 1, back.length);
        size += 1;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        for(int i = 0; i < size; i++){
            returnList.add(back[Math.floorMod(nextFirst + 1 + i, back.length)]);
        }
        return returnList;
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
        int backIndex = Math.floorMod(nextFirst + 1 + index, back.length - 1);
        if(backIndex >= nextLast || index < 0){
            return null;
        }
        return back[backIndex];
    }

    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
    }
}
