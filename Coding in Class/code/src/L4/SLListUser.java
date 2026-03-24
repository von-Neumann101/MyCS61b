package L4;

public class SLListUser {

    public static void main(String[] args){
        SLList_pro L = new SLList_pro(10);
        L.addFirst(12);
        L.addFirst(15);
        L.addFirst(14);
        System.out.println(L.getFirst());
    }
}
