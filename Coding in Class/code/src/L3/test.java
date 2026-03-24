package L3;

public class test {
    public static void main(String[] args){
        intList L = new intList(5, null);
        L.first = 5;
        L = new intList(10, L);
        L = new intList(15, L);
    }
}
