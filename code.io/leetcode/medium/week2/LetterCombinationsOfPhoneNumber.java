package leetcode.medium.week2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/*
* 17. Letter Combinations of a Phone Number
Medium

12222

746

Add to List

Share
Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.

A mapping of digits to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.




Example 1:

Input: digits = "23"
Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
*/
public class LetterCombinationsOfPhoneNumber {
    HashMap<Integer, List<String>> map;
    String[] keyPad = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    List<String> result = new ArrayList<>();
    public List<String> letterCombinations(String digits) {
        map = populateMap();
        if(digits.length()==0){
            return result;
        }
        //helper(digits, 0, "");
        littleBitSimplerHelper(digits, 0, new StringBuilder());
        return result;
    }
    //to simplify coding, use String array instead of map and use string builder setChar method, just
    //make sure to generate Sb from digits and then replace them, empty will not work.
    //setCharAt only works when you replace it
    //actually using append and deleteCharAt() worth it.
    public void littleBitSimplerHelper(String digits, int i, StringBuilder res){
        if(i == digits.length()){
            result.add(res.toString());
            return;
        }
        String itr = keyPad[digits.charAt(i)-'0'];
        for(int j=0; j<itr.length(); j++){
            res.append(itr.charAt(j));
            //res.setCharAt(i, itr.charAt(j));
            littleBitSimplerHelper(digits, i+1, res);
            res.deleteCharAt(res.length()-1);
        }
    }

    public void helper(String digits, int i, String res){
        if(i == digits.length()){
            result.add(res);
            return;
        }
        int k = Integer.valueOf(String.valueOf(digits.charAt(i)));
        for(String a: map.get(k)){
            res += a;
            helper(digits, i+1, res);
        }
    }

    public HashMap<Integer, List<String>> populateMap(){
        HashMap<Integer, List<String>> map = new HashMap<>();
        map.put(2, Arrays.asList("a","b","c"));
        map.put(3, Arrays.asList("d","e","f"));
        map.put(4, Arrays.asList("g","h","i"));
        map.put(5, Arrays.asList("j","k","l"));
        map.put(6, Arrays.asList("m","n","o"));
        map.put(7, Arrays.asList("p","q","r","s"));
        map.put(8, Arrays.asList("t","u","v"));
        map.put(9, Arrays.asList("w","x","y","z"));
        return map;
    }
}
