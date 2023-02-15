package java8;

import java.util.*;

public class SortMapDemo {

    public static void main(String[] args){
        Map<String, Integer> map = new HashMap<>();
        map.put("eight", 8);
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());

        Collections.sort(list, (a, b) -> a.getValue() - b.getValue());
        Collections.sort(list, (a,b) -> a.getKey().compareTo(b.getKey()));

        map.entrySet().stream()
                .sorted((a,b) -> a.getValue() - b.getValue())
                .forEach(a -> System.out.println(a));
        map.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(a -> System.out.println(a));
        map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach(a -> System.out.println(a));
        Map<Employee, Integer> employeeMap = new HashMap<>();
        employeeMap.put(new Employee(175, "asdj", "IT", 43), 60);
        employeeMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey(Comparator.comparing(Employee::getSalary)));

    }


}
