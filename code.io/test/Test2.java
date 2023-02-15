package test;

public class Test2 {
    public void printStar(int n){
        //       *
        //      ***
        //     *****
        for(int i=1; i<=n; i++){
            int s = n-i;
            for(int j=0; j<s; j++){
                System.out.print(" ");
            }
            for(int j=0; j<(2*i-1); j++){
                System.out.print("*");
            }
            System.out.println();
        }
    }
    public static void main(String[] args){
        new Test2().printStar(10);
    }
}
