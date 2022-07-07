package LeetCode;


//题目：11. 盛最多水的容器
//难度：中等
/*题目描述：
给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0) 。
找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。

说明：你不能倾斜容器。

 */
public class P6_盛最多水的容器{


    public static void main(String[] args) {
        int[] arr1 = {1,8,6,2,5,4,8,3,7};
        int[] arr2 = {1,2,1};
        int[] arr3 = {1,1};
        int[] arr4 = {4,3,2,1,4};
        System.out.println(maxArea2(arr1));
        System.out.println(maxArea2(arr2));
        System.out.println(maxArea2(arr3));
        System.out.println(maxArea2(arr4));


    }


    //暴力破解
    public static int maxArea1(int[] height) {
        int max = 0;
        for(int i = 0 ; i < height.length ; i++){
            for(int j = i + 1;j < height.length ; j++){
                if(height[i] < height[j]){
                    max = Math.max(max,height[i]*(j-i));
                }else{
                    max = Math.max(max,height[j]*(j-i));
                }
            }
        }
        return max;
    }

    //官方解法
    public static int maxArea2(int[] height) {
        int l = 0, r = height.length - 1;
        int ans = 0;
        while (l < r) {
            int area = Math.min(height[l], height[r]) * (r - l);
            ans = Math.max(ans, area);
            if (height[l] <= height[r]) {
                ++l;
            }
            else {
                --r;
            }
        }
        return ans;
    }

}
