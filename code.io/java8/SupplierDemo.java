package java8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class SupplierDemo {
    public static void main(String[] args){
        Supplier<String> supplier = ()-> "adflkas";
        supplier.get();
        List<String> list = Arrays.asList("1","2","3","4");
        list.stream()
                .findAny()
                .orElseGet(supplier);


    }
}
