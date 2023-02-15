package java8;

interface Calculator{
    //void switchOn();
    //void sum(int input);
    int sub(int a, int b);
}
public class CalculatorImpl {

    public static void main(String[] args){
        /* Calculator cal = () -> System.out.println("demo");
        cal.switchOn();


        Calculator cal = (a) -> System.out.println(a);
        cal.sum(112);

         */

        Calculator cal = (a,b) -> (a-b);
        Calculator cal1 = (a, b) -> {
            if(a > b){
                throw new RuntimeException("invalid");
            }
            return a-b;
        };
    }
}
