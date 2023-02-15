package java8;

import java.util.List;
import java.util.stream.IntStream;

public class ParallelStream {

    public static void main(String[] args){
        long start = 0;
        long end = 0;
        start = System.currentTimeMillis();
        IntStream.range(1, 100);
        end = System.currentTimeMillis();

        IntStream.range(1,100)
                .parallel()
                .forEach(System.out::println);

        IntStream.range(1,10)
                .forEach(x -> {
                    System.out.println("Thread: " + Thread.currentThread().getName()+": "+x);
                });


        IntStream.range(1,10)
                .parallel()
                .forEach(x -> {
                    System.out.println("Thread: " + Thread.currentThread().getName()+": "+x);
                });

        List<Employee> employeeList = EmployeeDataBase.getEmployees();

        employeeList.parallelStream()
                .map(employee -> employee.getSalary())
                .mapToDouble(i -> i)
                .average()
                .getAsDouble();

    }
}
