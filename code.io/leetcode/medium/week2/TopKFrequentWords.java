package leetcode.medium.week2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/*
Given an array of strings words and an integer k, return the k most frequent strings.

Return the answer sorted by the frequency from highest to lowest. Sort the words with the same frequency by their lexicographical order.



Example 1:

Input: words = ["i","love","leetcode","i","love","coding"], k = 2
Output: ["i","love"]
Explanation: "i" and "love" are the two most frequent words.
Note that "i" comes before "love" due to a lower alphabetical order.
Example 2:

Input: words = ["the","day","is","sunny","the","the","the","sunny","is","is"], k = 4
Output: ["the","is","sunny","day"]
Explanation: "the", "is", "sunny" and "day" are the four most frequent words, with the number of occurrence being 4, 3, 2 and 1 respectively.


Constraints:

1 <= words.length <= 500
1 <= words[i].length <= 10
words[i] consists of lowercase English letters.
k is in the range [1, The number of unique words[i]]
* */
public class TopKFrequentWords {
    public List<String> topKFrequent(String[] words, int k) {
        return topKFrequentUsingPriorityQueue(words, k);
    }
    class WordWithFreq{
        String word;
        int freq;
        WordWithFreq(String word, int freq){
            this.word = word;
            this.freq = freq;
        }
    }
    public List<String> topKFrequentUsingPriorityQueue(String[] words, int k) {
        HashMap<String, Integer> map = new HashMap();
        for(String w: words){
            map.put(w, map.getOrDefault(w,0)+1);
        }
        Comparator<WordWithFreq> byFreq = Comparator.comparingInt(a -> a.freq);
        Comparator<WordWithFreq> byWord = Comparator.comparing(a -> a.word);
        byWord.reversed();
        Comparator<WordWithFreq> cmp = byFreq.thenComparing(byWord);

        Queue<WordWithFreq> q = new PriorityQueue<>(cmp);
        for(Map.Entry<String, Integer> entry: map.entrySet()){
            q.offer(new WordWithFreq(entry.getKey(), entry.getValue()));
            if(q.size() > k){
                q.poll();
            }
        }
        List<String> result = new ArrayList<>();
        int size = q.size();
        while(size-- > 0){
            result.add(0, q.poll().word);
        }
        return result;
    }
    public static void main(String[] args){
        new TopKFrequentWords().topKFrequent(new String[]{"i","love","leetcode","i","love","coding"}, 2);

    }
}
