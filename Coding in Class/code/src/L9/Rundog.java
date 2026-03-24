package L9;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Rundog {
    public static void main(String[] args){
        List<Dog> dogs = new ArrayList<>();
        dogs.add(new Dog("awa", 200));
        dogs.add(new Dog("xuan", 2));
        dogs.add(new Dog("QwQ", 2000));

        Dog maxDog = Collections.max(dogs);
        Dog maxNameDog = Collections.max(dogs, new Dog.NameComparator());
    }
}
