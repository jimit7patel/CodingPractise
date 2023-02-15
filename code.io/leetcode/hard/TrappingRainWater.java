package leetcode.hard;
/*
* 42. Trapping Rain Water
Hard
24.4K
341
Companies
Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.



Example 1:


Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
Explanation: The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.
*/
class TrappingRainWater {
    //this is a bit toughTo understand.
    //see https://leetcode.com/problems/trapping-rain-water/solutions/127551/trapping-rain-water/
    //for solutions
    //gist is bruteforce and min of left max and right max
    //two pointers see solutions to understand
    //dont worry about stackAnd dynamic programming approach.
    public int trap(int[] height) {

        return optimized(height);
        //return bruteforce(height);

    }

    public int optimized(int[] height){

        int count =0;
        int left=0;
        int right = height.length-1;
        int lmax = 0;
        int rmax = 0;

        while(left< right){
            if(height[left] <= height[right]){
                if(height[left]<lmax){
                    count = count + (lmax-height[left]);
                }else{
                    lmax = height[left];
                }
                left++;
            }else{
                if(height[right]<rmax){
                    count = count + (rmax-height[right]);
                }else{
                    rmax = height[right];
                }
                right--;
            }
        }
        return count;

    }
    public int bruteforce(int[] height){

        int count = 0;
        for(int i=1; i<height.length; i++){
            int lmax = 0;
            int rmax = 0;
            for(int j=0;j<i;j++){
                lmax = Math.max(lmax,height[j]);
            }
            for(int j=i+1;j<height.length;j++){
                rmax = Math.max(rmax,height[j]);
            }

            int minheight = Math.min(lmax,rmax);

            if(height[i]<minheight){
                count = count + (minheight-height[i]);
            }

        }
        return count;
    }

}
