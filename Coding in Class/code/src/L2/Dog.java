package L2;

public class Dog {

    public int size;

    public Dog(int s){
        size = s;
    }

    public void makeNoise(){
        if(size > 20){
            System.out.println("AwA");
        }else{
            System.out.println("awa");
        }
    }
}
