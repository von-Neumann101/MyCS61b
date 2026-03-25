package L10;


import java.util.Iterator;

public class ArraySet<T> implements Iterable<T> {
    private int size;
    private T[] items;

    public ArraySet(){
        size = 0;
        items = (T[]) new Object[100];
    }

    public boolean contains(T x){
        for(int i = 0; i < size; i++){
            if(x.equals(items[i])){
                return true;
            }
        }
        return false;
    }

    public void add(T x){
        if(x == null){
            throw new IllegalArgumentException("null!");
        }
        if(contains(x)){
            return;
        }
        items[size] = x;
        size += 1;
    }

    public int size(){
        return size;
    }

    @Override
    public String toString(){
        StringBuilder stringToReturn = new StringBuilder("{");
        for(int i = 0; i < size - 1; i++){
            stringToReturn.append(items[i]);
            stringToReturn.append(", ");
        }
        stringToReturn.append(items[size - 1]);
        stringToReturn.append('}');
        return stringToReturn.toString();
    }

    private class ArraySetIterator implements Iterator<T>{
        //现在思考：我们如何实现下面两个方法

        private int positon;
        ArraySetIterator(){
            positon = 0;
        }

        @Override
        public boolean hasNext() {
            return positon < size;
        }

        @Override
        public T next() {
            T r = items[positon];
            positon += 1;
            return r;
        }
    }

    //想要使用增强for循环，必须要有迭代器（自己实现）
    public Iterator<T> iterator(){
        return new ArraySetIterator();
    }
}
