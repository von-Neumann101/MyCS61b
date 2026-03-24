package L4;

public class SLList_pro {
    private static class IntNode {
        int item;
        IntNode next;

        IntNode(int item, IntNode next){
            this.item = item;
            this.next = next;
        }
    }
    // 我们不想创建一个特殊情况，我们希望nullList和其他的一样
    private IntNode sentinel;
    private int size;

    public SLList_pro(){
        sentinel = new IntNode(421, null);
        size = 0;
    }

    public SLList_pro(int x){
        sentinel = new IntNode(421, null);
        sentinel.next = new IntNode(x, null);
        size = 1;
    }

    public void addFirst(int x){
        sentinel.next = new IntNode(x, sentinel.next);
        size ++;
    }

    public void addLast(int x){
        IntNode p = sentinel;
        while(p.next != null){
            p = p.next;
        }
        p.next = new IntNode(x, null);
        size ++;
    }

    public int getFirst(){
        return sentinel.next.item;
    }


    public int size(){
        return size;
    }
}
