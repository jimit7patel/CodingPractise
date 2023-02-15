package java8;

import java.util.List;
import java.util.stream.Collectors;

/*
* */
public class MapVsFlatMap {
    public static void main(String[] args){
        List<Customer> customers = EkartDataBase.getAll();
        List<String> emails = customers.stream()
                .map(customer -> customer.getEmail())
                .collect(Collectors.toList());
        List<List<String>> phones = customers.stream()
                .map(customer -> customer.getPhoneNumbers())
                .collect(Collectors.toList());

        List<String> phonesFlattened = customers.stream()
                .flatMap(customer -> customer.getPhoneNumbers().stream())
                .collect(Collectors.toList());


    }

}
