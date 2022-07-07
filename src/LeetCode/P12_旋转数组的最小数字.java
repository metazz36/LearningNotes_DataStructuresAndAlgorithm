package LeetCode;

//难度：简单
//与P11的区别，数组元素可重复

public class P12_旋转数组的最小数字 {
    public static void main(String[] args) {
        int[] arr = {1,3,5};
        System.out.println(minArray(arr));
    }

    public static int minArray(int[] numbers) {
        int l = 0;
        int r = numbers.length - 1;
        while(l < r){
            int mid = (l + r)/2;
            if(numbers[mid] < numbers[r]){
                r = mid;
            }
            else if(numbers[mid] > numbers[r]){
                l = mid + 1;
            }else {
                //跳过一个或多个重复元素
                r--;
            }
        }
        return numbers[l];
    }

}
