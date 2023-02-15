package leetcode.easy.week1;
/*
* Given two binary strings a and b, return their sum as a binary string.



Example 1:

Input: a = "11", b = "1"
Output: "100"
*/
public class AddBinary {
    public String addBinary(String a, String b) {
        StringBuilder answer = new StringBuilder();
        int i = a.length()-1;
        int j = b.length()-1;
        int carry = 0;
        while(i >=0 || j >=0){
            int total = 0;
            if(i >= 0){
                total += a.charAt(i) - '0';
            }
            if(j >= 0){
                total += b.charAt(j) - '0';
            }
            total += carry;
            answer.append(Integer.toString(total%2));
            carry = total/2;
            i--;j--;
        }
        if(carry != 0){
            answer.append(Integer.toString(1));
        }
        return answer.reverse().toString();
    }
    public static void main(String[] args){
        System.out.println(new AddBinary().addBinary("11", "1"));
    }
}
