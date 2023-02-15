package java8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class PredicateDemo {
    public static void main(String[] args){
        Predicate<Integer> predicate = t -> {
            if(t % 2==0){
                return true;
            }
            return false;
        };
        predicate.test(1);
        List<Integer> list = Arrays.asList(1,2,3,4);
        list.stream()
                .filter(predicate)
                .forEach(t -> { System.out.println(t);});


    }
}
