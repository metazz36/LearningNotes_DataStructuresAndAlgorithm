package LeetCode;

//题目：167. 两数之和 II - 输入有序数组
//难度：中等

/*题目描述：
给你一个下标从 1 开始的整数数组 numbers ，该数组已按 非递减顺序排列  ，请你从数组中找出满足相加之和等于目标数 target 的两个数。
如果设这两个数分别是 numbers[index1] 和 numbers[index2] ，则 1 <= index1 < index2 <= numbers.length 。
以长度为 2 的整数数组 [index1, index2] 的形式返回这两个整数的下标 index1 和 index2。
你可以假设每个输入 只对应唯一的答案 ，而且你 不可以 重复使用相同的元素。
你所设计的解决方案必须只使用常量级的额外空间。
 */


import java.util.Arrays;
import java.util.stream.Collectors;

public class P5_两数之和II_输入有序数组{
    public static void main(String[] args) {
        int[] numbers = {2,7,11,15};
        int target = 9;
        System.out.println(Arrays.toString(twoSum(numbers, target)));
    }

    //原来的方法
/*    public static int[] twoSum(int[] numbers, int target) {
        int l = 0;
        int r = numbers.length - 1;
        int[] arr = new int[2];
        while(l != r && r > l){
            if(numbers[l] + numbers[r] == target){
                arr[0] = l + 1;
                arr[1] = r + 1;
                return arr;
            }else{
                if(numbers[l] + numbers[r] > target){
                    r--;
                }else if(numbers[l] + numbers[r] < target){
                    l++;
                }

            }
        }
        return null;
    }*/

    //改进后的方法
    //心得1：不需要额外添加的判断就不要添加。 else if(numbers[l] + numbers[r] < target)
    //心得2：不要先生成一个返回类型，直接在函数返回的时候new一个就好
    public static int[] twoSum(int[] numbers, int target) {
        int l = 0;
        int r = numbers.length - 1;
        while(l != r && r > l){
            if(numbers[l] + numbers[r] == target){
                return new int[]{l+1,r+1};
            }else{
                if(numbers[l] + numbers[r] > target){
                    r--;
                }else{
                    l++;
                }

            }
        }
        return null;
    }

}
