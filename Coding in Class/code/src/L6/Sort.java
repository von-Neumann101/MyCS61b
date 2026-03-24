package L6;

public class Sort {
    public static void sort(String[] x){
        helper(x,0);
    }
    private static void helper(String[] x, int s){
        if(s == x.length){
            return;
        }
        int smallest = findSmallest(x, s);
        swap(x, s, smallest);
        helper(x, s + 1);
    }

    public static int findSmallest(String[] x){
        int smallindex = 0;
        for(int i = 0; i < x.length; i++){
            if(x[i].compareTo(x[smallindex]) < 0){
                smallindex = i;
            }
        }
        return smallindex;
    }

    public static int findSmallest(String[] x, int s){
        int smallindex = s;
        for(int i = s; i < x.length; i++){
            if(x[i].compareTo(x[smallindex]) < 0){
                smallindex = i;
            }
        }
        return smallindex;
    }

    public static void swap(String[] x, int a, int b){
        String tmp = x[a];
        x[a] = x[b];
        x[b] = tmp;
    }
}
