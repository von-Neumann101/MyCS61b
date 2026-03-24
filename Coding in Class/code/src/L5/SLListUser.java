package L5;


public class SLListUser {

    public static void main(String[] args){
        SLList<Integer> L = new SLList<>(10);
        L.addFirst(12);
        L.addFirst(15);
        L.addFirst(14);
        System.out.println(L.getFirst());
    }
}
