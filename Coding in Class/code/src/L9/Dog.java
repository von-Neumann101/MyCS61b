package L9;

public class Dog implements Comparable<Dog> {
    String name;
    int size;

    public Dog(String n, int s){
        name = n;
        size = s;
    }

    @Override
    public int compareTo(Dog dog){
        if(size < dog.size){
            return -1;
        }else if (size > dog.size){
            return 1;
        }else{
            return 0;
        }
    }
}
