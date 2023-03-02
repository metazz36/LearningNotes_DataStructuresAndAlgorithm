package LeetCode;

public class P22_和为s的两个数字 {
    public static void main(String[] args) {
        int[] ints = {2, 7, 11, 15};
        int target = 12;
        int[] ints1 = twoSum(ints, target);
        for (int i : ints1) {
            System.out.println(i);
        }

    }

    public static int[] twoSum(int[] nums, int target) {
        int p = 0;
        int q = nums.length - 1;
        while(nums[p] + nums[q] != target && p != q && q >= 0){
            if(nums[p] + nums[q] > target){
                q--;
            }
            if(nums[p] + nums[q] < target){
                p++;
            }
        }
        if(p == q){
            return null;
        }
        return new int[]{nums[p],nums[q]};
    }
}
