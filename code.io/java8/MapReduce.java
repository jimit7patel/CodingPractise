package java8;

import java.util.Arrays;
import java.util.List;

public class MapReduce {

    public static void main(String[] args){
        List<Integer> numbers = Arrays.asList(3,4,3,3,1);
        numbers.stream()
                //.mapToInt(Integer::intValue)
                .mapToInt(i ->i)
                .sum();

        numbers.stream()
                .reduce(0, (a,b) -> a+b);

        numbers.stream()
                .reduce(Integer::sum);

        numbers.stream()
                .reduce(1, (a,b) -> a*b);

        numbers.stream()
                .reduce(0, (a,b) -> a>b?a:b);//max

        numbers.stream()
                .reduce(Integer::max);

        List<String> words = Arrays.asList("ajd", "addf");

        words.stream()
                .reduce((word1, word2) -> word1.length() > word2.length()? word1: word2);


        EmployeeDataBase.getEmployees()
                .stream()
                .filter(employee -> employee.getSalary() > 122)
                .map(employee -> employee.getSalary())
                .mapToDouble(i -> i)
                .average()
                .getAsDouble();

        EmployeeDataBase.getEmployees()
                .stream()
                .filter(employee -> employee.getSalary() > 122)
                .map(employee -> employee.getSalary())
                .mapToDouble(i -> i)
                .sum();






    }
}
