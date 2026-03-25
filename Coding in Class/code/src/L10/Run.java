package L10;

import java.util.Iterator;

public class Run {
    public static void main(String[] args){
        ArraySet<Integer> aset = new ArraySet<>();
        aset.add(4);
        aset.add(2);
        aset.add(1);

        for(int i:aset){
            System.out.println(i);
        }
        System.out.println(aset);
    }
}
