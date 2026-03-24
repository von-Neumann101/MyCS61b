package L8;

public interface List61B<Item> {
    //接口描述的是形而上的东西（无实例），故没有构造器
    public void addLast(Item x);
    public void addFirst(Item y);
    public Item getFirst();
    public Item getLast();
    public Item removeLast();
    public Item get(int i);
    public void insert(Item x, int position);
    public int size();
    default public void print(){
        for(int i = 0; i < size(); i++){
            System.out.print(get(i) + " ");
        }
    }
}
