package L10;

import java.util.Iterator;

public class Run {
    public static void main(String[] args){
        ArraySet<Integer> aset = new ArraySet<>();
        aset.add(4);
        aset.add(2);
        aset.add(1);

        Iterator<Integer> aseer = aset.iterator();
        while(aseer.hasNext()){
            int i = aseer.next();
            System.out.println(i);
        }
    }
}
