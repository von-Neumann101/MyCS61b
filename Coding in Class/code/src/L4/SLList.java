package L4;

public class SLList {
    private static class IntNode {
        int item;
        IntNode next;

        IntNode(int item, IntNode next){
            this.item = item;
            this.next = next;
        }
    }

    private IntNode first;
    private int size;

    public SLList(){
        first = null;
        size = 0;
    }

    public SLList(int x){
        first = new IntNode(x, null);
        size = 1;
    }

    public void addFirst(int x){
        size ++;
        first = new IntNode(x, first);
    }

    public void addLast(int x){
        if (first == null){
            first = new IntNode(x, null);//addFirst(x)
            return;
        }
        IntNode p = first;
        while(p.next != null){
            p = p.next;
        }
        p.next = new IntNode(x, null);
        size ++;
    }

    public int getFirst(){
        return first.item;
    }


    public int size(){
        return size;
    }
}
