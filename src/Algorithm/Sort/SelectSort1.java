package Algorithm.Sort;

import java.util.Arrays;

public class SelectSort1 {
    public static void main(String[] args) {
        int arr[] = {5,-1,3,6,-50,10,9,8};
        System.out.println("排序前");
        System.out.println(Arrays.toString(arr));
        xuanze(arr);
        System.out.println("排序后");
        System.out.println(Arrays.toString(arr));
    }

    public static void xuanze(int[] arr){
        int min = 0;
        int minIndex = 0;
        for(int i = 0;i < arr.length - 1;i++) {
            min = arr[i];
            minIndex = i;
            for(int j = i + 1;j < arr.length;j++){
                if(min > arr[j]){
                    min = arr[j];
                    minIndex = j;
                }
            }
            arr[minIndex] = arr[i];
            arr[i] = min;
        }
    }

}
