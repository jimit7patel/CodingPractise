package java8;

import java.util.List;
import java.util.stream.Collectors;
/*
* reference: https://www.youtube.com/watch?v=ePJrt5-G8eM&t=318s
* https://github.com/Java-Techie-jt/java8/tree/master/example
* */
public class TaxService {
    public static List<Employee> evaluate(String input){

        return (input.equalsIgnoreCase("tax"))
                ? DataBase.getEmployees().stream().filter(emp -> emp.getSalary() > 5000).collect(Collectors.toList())
                : DataBase.getEmployees().stream().filter(emp -> emp.getSalary() <= 5000).collect(Collectors.toList());

    }
}
