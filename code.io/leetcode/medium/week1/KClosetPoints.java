package leetcode.medium.week1;

import java.util.Comparator;
import java.util.PriorityQueue;

/*
* 973. K Closest Points to Origin
Medium

6202

223

Add to List

Share
Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane and an integer k, return the k closest points to the origin (0, 0).

The distance between two points on the X-Y plane is the Euclidean distance (i.e., √(x1 - x2)2 + (y1 - y2)2).

You may return the answer in any order. The answer is guaranteed to be unique (except for the order that it is in).

 */
public class KClosetPoints {
    public int[][] kClosest(int[][] points, int k) {
        Comparator<int[]> cmp = Comparator.comparing(a -> distance(a));

        PriorityQueue<int[]> pq = new PriorityQueue<>(k,cmp.reversed());

       for(int[] point: points){
           pq.add(point);
           if(pq.size()>k){
               pq.remove();
           }
       }

       int[][] result = new int[k][2];
       int i=0;
       while(!pq.isEmpty()){
           result[i++] = pq.remove();
       }
       return result;
    }
    public int[][] usingPartition(int[][] points, int K) {

        int[][] result = new int[K][2];
        helper(points,0,points.length-1,K-1);

        for(int i=0; i<K; i++){
            result[i][0]=points[i][0];
            result[i][1]=points[i][1];
        }
        return result;

    }

    public void helper(int[][] nums, int start, int end, int index){
        int p = lomutos(nums,start,end);
        if(p==index){
            return;
        }else if(p>index){
            helper(nums,start,p-1,index);
        }else{
            helper(nums,p+1,end,index);
        }

    }

    public int lomutos(int[][] a, int start, int end){

        double p = distance(a[start]);
        int orange = start;
        int green = start+1;
        while(green<=end){
            if(distance(a[green])<p){
                orange++;
                swap(a,orange,green);
            }
            green++;
        }
        swap(a,orange,start);
        return orange;
    }
    public void swap(int[][]a, int x, int y){
        int[] t = a[x];
        a[x] = a[y];
        a[y] = t;
    }

    public double distance(int[] point){
        return Math.sqrt((point[0]*point[0]) + (point[1] * point[1]));
    }
    public static void main(String[] args){
        System.out.println(new KClosetPoints().kClosest(new int[][]{{1,3},{-2,2}},1));
    }
}
