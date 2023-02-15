package java8;

import java.util.Arrays;
import java.util.Optional;

public class OptionalDemo {
    public Customer getCustomerEmail(String email) throws Exception {
        return EkartDataBase.getAll().stream()
                .filter(customer -> customer.getEmail().equals(email))
                .findAny()
                .orElseThrow(() -> new Exception("no customer"));
    }
    public static void main(String[] args){
        Customer customer = new Customer(101, "jo", null, Arrays.asList("32123", "313123"));
        Optional.empty();
        Optional<String> test = Optional.of(customer.getEmail());
        Optional<String> optional = Optional.ofNullable(customer.getEmail());
        if(optional.isPresent()){
            optional.get();
        }else{

        }
        optional.orElse("default");
        optional.orElseThrow(() -> new IllegalArgumentException("email not present"));
        optional.map(String::toUpperCase).orElseGet(() -> "default");


    }
}
