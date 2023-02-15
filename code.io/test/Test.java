package test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Test {
    //print sum of 1 to 100, odd numbers
    public void print(){

        int a = IntStream.range(1,101)
                .filter(i -> i%2!=0)
                .sum();
        System.out.println(a);
        List<Integer> list = new ArrayList<>();
        //list.stream().min()
    }
    public static void main(String[] args){
        new Test().print();
    }
}
