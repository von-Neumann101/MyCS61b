package deque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.lang.Math;

public class ArrayDeque61B<T> implements Deque61B<T> {

    private int nextFirst, nextLast;
    private int size;
    private T[] back;
    private final int FACTOR = 2;

    public ArrayDeque61B(){
        nextFirst = 3;
        nextLast = 4;
        size = 0;
        back = (T[])new Object[8];
    }

    @Override
    public void addFirst(T x) {
        if(size == back.length){
            resize_up();
        }
        back[nextFirst] = x;
        nextFirst = Math.floorMod(nextFirst - 1, back.length);
        size += 1;
    }

    @Override
    public void addLast(T x) {
        if(size == back.length){
            resize_up();
        }
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
        if(size == 0){
            return null;
        }
        T returnLabel = back[Math.floorMod(nextFirst + 1, back.length)];
        nextFirst = Math.floorMod(nextFirst + 1, back.length);
        size -= 1;
        if(back.length >= 16 && size <= back.length / 4){
            resize_down();
        }
        return returnLabel;
    }

    @Override
    public T removeLast() {
        if(size == 0){
            return null;
        }
        T returnLabel = back[Math.floorMod(nextLast - 1, back.length)];
        nextLast = Math.floorMod(nextLast - 1, back.length);
        size -= 1;
        if(back.length >= 16 && size <= back.length / 4){
            resize_down();
        }
        return returnLabel;
    }

    @Override
    public T get(int index) {
        if(index >= size || index < 0){
            return null;
        }
        int backIndex = Math.floorMod(nextFirst + 1 + index, back.length);
        return back[backIndex];
    }

    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
    }

    private void resize_up() {
        //说实话我并不觉得使用arraycopy会复杂很多
        T[] newBack = (T[]) new Object[FACTOR * back.length];
        for(int i = 0; i < size; i++){
            newBack[i + 1] = this.get(i);
        }
        nextFirst = 0;
        nextLast = size + 1;
        back = newBack;
    }

    private void resize_down() {
        T[] newBack = (T[]) new Object[back.length / 2];
        for(int i = 0; i < size; i++){
            newBack[i + 1] = this.get(i);
        }
        nextFirst = 0;
        nextLast = size + 1;
        back = newBack;
    }

    private class ArrayDequeIterator implements Iterator<T>{

        private int position;

        ArrayDequeIterator() {
            position = 0;
        }

        @Override
        public boolean hasNext() {
            return position < size;
        }

        @Override
        public T next() {
            position += 1;
            return get(position - 1);
        }
    }

    public Iterator<T> iterator(){
        return new ArrayDequeIterator();
    }

    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if (!(o instanceof Deque61B<?> other)) {
            return false;
        }
        if(other.size() != size){
            return false;
        }
        for(int i = 0; i < size; i++){
            if(!other.get(i).equals(this.get(i))){
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString(){
        if (size == 0) {
            return "[]";
        }
        StringBuilder stringToReturn = new StringBuilder("[");
        for(int i = 0; i < size - 1; i++){
            stringToReturn.append(this.get(i));
            stringToReturn.append(", ");
        }
        stringToReturn.append(get(size - 1));
        stringToReturn.append(']');
        return stringToReturn.toString();
    }

    public int capacity() {
        return back.length;
    }
}
