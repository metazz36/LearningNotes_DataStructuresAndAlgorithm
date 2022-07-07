package Algorithm.Sort;

import java.util.Arrays;

public class BubbleSort1 {
    public static void main(String[] args) {
        int[] arr={7,6,5,4,3,2,1,1};
        maopao(arr);
        System.out.print(Arrays.toString(arr));
    }

    public static void maopao(int[] arr){
        int temp = 0;
        for(int i = 0 ;i < arr.length - 1;i++){
            for(int j = 0;j < arr.length - 1 -i;j++){
                if(arr[j] > arr[j+1]){
                    temp = arr[j+1];
                    arr[j+1] =arr[j];
                    arr[j] = temp;
                }
            }
        }
    }
}
