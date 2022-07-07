package LeetCode;

import java.util.Arrays;

//题目：最接近的三数之和
//难度：中等
/*题目描述：
给你一个长度为 n 的整数数组 nums 和 一个目标值 target。请你从 nums 中选出三个整数，使它们的和与 target 最接近。
返回这三个数的和。
假定每组输入只存在恰好一个解。

示例 1：

输入：nums = [-1,2,1,-4], target = 1
输出：2
解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
示例 2：

输入：nums = [0,0,0], target = 1
输出：0
 

提示：

3 <= nums.length <= 1000
-1000 <= nums[i] <= 1000
-104 <= target <= 104

*/
public class P1_最接近的三数之和 {
    public static void main(String[] args) {
//        int[] nums ={-1,4,1,-4,3,2,5};//1

        int[] nums ={1,-1,1};//1
        int target = 2;//1
        System.out.println(threeSumClosest1(nums,target));
        System.out.println(threeSumClosest2(nums,target));
    }


    //方法1
    public static int threeSumClosest1(int[] nums, int target) {
        Arrays.sort(nums);
        int closestnum = nums[0] + nums[1] + nums[2];
        int l,r;//内部左右指针
        for(int i = 0;i < nums.length - 2;i++){
            l = i + 1;
            r = nums.length - 1;
            while(l < r){
                int threenum = nums[i] + nums[l] + nums[r];
                if(Math.abs(threenum - target) < Math.abs(closestnum - target)){
                    closestnum = threenum;
                }
                if(threenum > target){
                    r--;
                }else if(threenum < target){
                    l++;
                }else{
                    return target;
                }

            }
        }
        return closestnum;
    }

    //方法2：暴力破解
    public static int threeSumClosest2(int[] nums, int target) {
        Arrays.sort(nums);
        int closest = nums[0] + nums[1] + nums[2];
        if(closest >= target){
            return closest;
        }
        for(int i = 0 ; i < nums.length - 2 ; i++){
            for(int j = i + 1 ; j < nums.length - 1 ; j++){
                for(int k = j + 1 ; k < nums.length ; k++){
                    int sum = nums[i]+nums[j]+nums[k];
                    if(Math.abs(target - sum) < Math.abs(target - closest)){
                        closest = sum;
                    }
                }
            }
        }



        return closest;
    }

//    public static int threeSumClosest(int[] nums, int target) {
//        Arrays.sort(nums);
//        //假定第一组三数之和最接近target
//        int ans = nums[0] + nums[1] + nums[2];
//        if(target <= ans){
//            return ans;
//        }
//        for(int i = 0; i < nums.length; i++){
//            for(int j = i + 1; j < nums.length; j++){
//                for(int k = j + 1; k < nums.length; k++){
//                    int sum = nums[i] + nums[j] + nums[k];
//                    if(Math.abs(target - ans) >= Math.abs(target - sum)){
//                        ans = sum;
//                    }
//                }
//            }
//        }
//        return ans;
//    }

}

