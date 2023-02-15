package java8;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class TestDemo {
    public static void demo(){
        /*
        //int s = IntStream.of(1,2,3)
        //        .collect(Collectors.summingInt(i -> i));
        //this is error because object to int conversion, Integer is object,
        int s = Stream.of(1,2,3)
                .collect(summingInt(i -> i));
        //this works because this is stream of Integer.
                IntStream.of(1,2,3)
                        .boxed()
                        .collect(toList());
        int ss = Stream.of(1,2,3)
                .mapToInt(i -> i)
                .sum();

        Map<Integer, List<Integer>> map = Stream.of(1,2,3)
                        .collect(groupingBy(k -> k%2));
        Stream<Integer> st = Stream.of(1,2,3);

        Map<Integer, IntSummaryStatistics> m = st.collect(groupingBy(k -> k%2, summarizingInt(i -> i)));

        //{0=IntSummaryStatistics{count=1, sum=2, min=2, average=2.000000, max=2},
        // 1=IntSummaryStatistics{count=2, sum=4, min=1, average=2.000000, max=3}}

        //https://blog.jooq.org/common-sql-clauses-and-their-equivalents-in-java-8-streams/
        TreeMap<Integer, IntSummaryStatistics> mm = st.collect(groupingBy(k -> k%2, () -> new TreeMap(), summarizingInt(i -> i)));
        //https://www.youtube.com/watch?v=upDTRFun9kM
        Map<Boolean, Set<Integer>> pp = st.collect(Collectors.partitioningBy(k -> k > 2, Collectors.toSet()));

        //https://www.baeldung.com/java-groupingby-collector
        st.collect(Collectors.groupingBy(k->2, collectingAndThen(Collectors.toList(), list -> list.size())));
        */

    }
    public static void main(String[] args){

    }
}
