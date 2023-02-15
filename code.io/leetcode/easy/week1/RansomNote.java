package leetcode.easy.week1;

import java.util.HashMap;

/*
* Given two strings ransomNote and magazine, return true if ransomNote can be constructed by using the letters from magazine and false otherwise.

Each letter in magazine can only be used once in ransomNote.

*/
public class RansomNote {
    public boolean canConstruct(String ransomNote, String magazine) {
        HashMap<Integer, Integer> count = new HashMap<>();
        magazine.codePoints().forEach(code -> count.put(code, count.getOrDefault(code,0)+1));
        for(int code : ransomNote.codePoints().toArray()){
            if(count.containsKey(code)){
                int c = count.get(code);
                if(c==0){
                    return false;
                }
                count.put(code,c-1);
            }else{
                return false;
            }
        };
        return true;
    }
}
