package L5;

//可以通过改awa，创建对象的时候在见括号里指定
public class SLList<awa> {
    private class awaNode {
        awa item;
        awaNode next;

        awaNode(awa item, awaNode next){
            this.item = item;
            this.next = next;
        }
    }
    // 我们不想创建一个特殊情况，我们希望nullList和其他的一样
    private awaNode sentinel;
    private int size;

    public SLList(){
        sentinel = new awaNode(null, null);
        size = 0;
    }

    public SLList(awa x){
        sentinel = new awaNode(x, null);
        sentinel.next = new awaNode(x, null);
        size = 1;
    }

    public void addFirst(awa x){
        sentinel.next = new awaNode(x, sentinel.next);
        size ++;
    }

    public void addLast(awa x){
        awaNode p = sentinel;
        while(p.next != null){
             p = p.next;
        }
        p.next = new awaNode(x, null);
        size ++;
    }

    public awa getFirst(){
        return sentinel.next.item;
    }


    public int size(){
        return size;
    }
}
