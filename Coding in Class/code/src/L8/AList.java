package L8;

public class AList<Item> implements List61B<Item> {
    private Item[] items;
    private int size;

    public AList(){
        size = 0;
        items = (Item[]) new Object[100];
    }

    public void addLast(Item x){
        int FACTOR = 2;
        if(size == items.length) resize(size * FACTOR);
        items[size] = x;
        size ++;
    }

    public void insert(Item x, int position){
        if (position < 0 || position > items.length) {
            throw new IndexOutOfBoundsException();
        }
        Item[] newItems = (Item[]) new Object[items.length + 1];

        System.arraycopy(items, 0, newItems, 0, position);
        newItems[position] = x;

        System.arraycopy(items, position, newItems, position + 1, items.length - position);
        items = newItems;
    }

    public Item getLast(){
        return items[size - 1];
    }

    public Item getFirst(){
        return items[0];
    }

    public Item get(int i){
        return items[i];
    }

    private void resize(int capacity){
        //一旦超过size,每次都要resize,这是平方时间,会非常慢
        if(size == items.length){
            Item[] a =(Item[]) new Object[size + 1];
            System.arraycopy(items, 0, a, 0, size);
            items = a;
        }
    }

    public int size(){
        return size;
    }

    public Item removeLast() {
        Item itemToReturn = getLast();
        size --;
        return itemToReturn;
    }

    public void addFirst(Item x){
        insert(x, 0);
    }

}
