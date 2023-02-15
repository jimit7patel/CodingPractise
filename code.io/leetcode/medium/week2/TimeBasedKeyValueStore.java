package leetcode.medium.week2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/*Design a time-based key-value data structure that can store multiple values for the same key at different time stamps and retrieve the key's value at a certain timestamp.

Implement the TimeMap class:

TimeMap() Initializes the object of the data structure.
void set(String key, String value, int timestamp) Stores the key key with the value value at the given time timestamp.
String get(String key, int timestamp) Returns a value such that set was called previously, with timestamp_prev <= timestamp. If there are multiple such values, it returns the value associated with the largest timestamp_prev. If there are no values, it returns "".


Example 1:

Input
["TimeMap", "set", "get", "get", "set", "get", "get"]
[[], ["foo", "bar", 1], ["foo", 1], ["foo", 3], ["foo", "bar2", 4], ["foo", 4], ["foo", 5]]
Output
[null, null, "bar", "bar", null, "bar2", "bar2"]

Explanation
TimeMap timeMap = new TimeMap();
timeMap.set("foo", "bar", 1);  // store the key "foo" and value "bar" along with timestamp = 1.
timeMap.get("foo", 1);         // return "bar"
timeMap.get("foo", 3);         // return "bar", since there is no value corresponding to foo at timestamp 3 and timestamp 2, then the only value is at timestamp 1 is "bar".
timeMap.set("foo", "bar2", 4); // store the key "foo" and value "bar2" along with timestamp = 4.
timeMap.get("foo", 4);         // return "bar2"
timeMap.get("foo", 5);         // return "bar2"
*/
interface ITimeMap {

    /**
     * Stores the key and value, along with the given timestamp.
     *
     * @param key       key
     * @param value     value
     * @param timestamp timestamp
     */
    void set(String key, String value, int timestamp);

    /**
     * @param key       key
     * @param timestamp timestamp
     * @return * Returns a value such that set(key, value, timestamp_prev) was called previously, with timestamp_prev <= timestamp.
     * * If there are multiple such values, it returns the one with the largest timestamp_prev.
     * * If there are no values, it returns the empty string ("").
     */
    String get(String key, int timestamp);
}

public class TimeBasedKeyValueStore {

    private Map<String, TreeMap<Integer,String>> map;
    public TimeBasedKeyValueStore() {
        map = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        if(!map.containsKey(key)){
            TreeMap<Integer,String> m = new TreeMap<>();
            map.put(key,m);
        }
        map.get(key).put(timestamp,value);
    }

    public String get(String key, int timestamp) {
        if(!map.containsKey(key)){
            return "";
        }
        TreeMap<Integer,String> m = map.get(key);
        Integer k = m.floorKey(timestamp);
        if(k==null){
            return "";
        }
        return m.get(k);
    }
}
class Data {
    String val;
    int time;
    Data(String val, int time) {
        this.val = val;
        this.time = time;
    }
}

class TimeMapUsingBinarySearch implements ITimeMap {

    /** Initialize your data structure here. */
    Map<String, List<Data>> map;
    public TimeMapUsingBinarySearch() {
        map = new HashMap<String, List<Data>>();
    }

    public void set(String key, String value, int timestamp) {
        if (!map.containsKey(key)) map.put(key, new ArrayList<Data>());
        map.get(key).add(new Data(value, timestamp));
    }

    public String get(String key, int timestamp) {
        if (!map.containsKey(key)) return "";
        return binarySearch(map.get(key), timestamp);
    }

    protected String binarySearch(List<Data> list, int time) {
        int low = 0, high = list.size() - 1;
        while (low <= high) {
            int mid = low + (high - low)/2;
            if (list.get(mid).time == time) return list.get(mid).val;
            if (list.get(mid).time < time) {
                low = mid + 1;
            }
            else high = mid -1;
        }

        return high >= 0 ? list.get(high).val : "";
    }
}


    /**
     * Your TimeMap object will be instantiated and called as such:
     * TimeMap obj = new TimeMap();
     * obj.set(key,value,timestamp);
     * String param_2 = obj.get(key,timestamp);
     */