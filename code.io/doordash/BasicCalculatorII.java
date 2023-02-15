package doordash;
/*
* Given a string s which represents an expression, evaluate this expression and return its value.

The integer division should truncate toward zero.

You may assume that the given expression is always valid. All intermediate results will be in the range of [-231, 231 - 1].

Note: You are not allowed to use any built-in function which evaluates strings as mathematical expressions, such as eval().

*/
public class BasicCalculatorII {
    /*
    The solution to approach is process as we go.
    which can be done using stacks to store the last value processed.
    otherwise we can just a variable.
    we add result right away in case of addition and sub, so, for * or / we have to sub that first
    this can be avoided if we do not add current but prev.
    * */
    public int calculate(String s){

        int prevOp = 0;
        int result = 0;
        char c ;
        int current = 0;
        char op = '+';
        for(int i=0; i< s.length(); i++){
            c = s.charAt(i);
            if(c == ' '){
                continue;
            }
            if(Character.isDigit(c)){
                current = current * 10 + (c - '0');
            }
            if((!Character.isDigit(c) && c!= ' ' ) || i==s.length()-1){
                if(op == '+'){
                    result += current;
                    prevOp = current;
                }
                if(op == '-'){
                    result -= current;
                    prevOp = -current;
                }
                if(op == '*'){
                    result = result - prevOp + (prevOp * current);
                    prevOp = prevOp * current;
                }
                if(op == '/'){
                    result = result - prevOp + (prevOp / current);
                    prevOp = prevOp / current;
                }
                op = c;
                current = 0;
            }
        }
        return result;

    }

    public static void main (String[] args){
        System.out.println(new BasicCalculatorII().calculate("2*3*4"));
    }

}
