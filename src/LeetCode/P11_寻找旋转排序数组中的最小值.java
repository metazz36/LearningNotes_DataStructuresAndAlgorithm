package LeetCode;


//难度：中等
public class P11_寻找旋转排序数组中的最小值 {
    public static void main(String[] args) {
//        int[] arr = {6,7,8,1,2,3,4,5};
        int[] arr = {3,4,5,1,2};
        System.out.println(minArray(arr));


    }

    public static int minArray(int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        while(l < r){
            int mid = (l + r)/2;
            if(nums[mid] < nums[r]){
                r = mid;
            }else{
                l = mid + 1;
            }
        }
        return nums[l];
    }


}
