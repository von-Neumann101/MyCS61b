package L7;

public class AList {
    private int[] items;
    private int size;
    private int FACTOR = 2;
    public AList(){
        size = 0;
        items = new int[100];//全部初始化为0
    }

    public void addLast(int x){
        if(size == items.length) resize(size * FACTOR);
        //我们这里每次翻倍以减少创建列表的次数,进而加快速度(Python做法)
        items[size] = x;
        size ++;
    }

    public int getLast(){
        return items[size - 1];
    }

    public int getFirst(){
        return items[0];
    }

    public int get(int i){
        return items[i];
    }

    private void resize(int capacity){
        //一旦超过size,每次都要resize,这是平方时间,会非常慢
        if(size == items.length){
            int[] a = new int[size + 1];
            System.arraycopy(items, 0, a, 0, size);
            items = a;
        }
    }

    public int removeLast() {
        int itemToReturn = getLast();
        size --;
        //这里没有把删掉的那个位置的元素设为0,其实这是不需要的,因为我们
        //把size-1了,这导致你永远也没有权限访问那,而在下一次赋值以后,这
        //个数字就永远消失了
        return itemToReturn;
    }
}
