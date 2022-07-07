package Algorithm.Sort;

import java.util.Arrays;
import java.util.HashSet;

public class InsertSort1 {
    public static void main(String[] args) {
        int[] arr = {8,7,6,5,4,3,2,1};
        System.out.println("排序前");
        System.out.println(Arrays.toString(arr));
        charu(arr);
        System.out.println("排序后");
        System.out.println(Arrays.toString(arr));

    }

    public static void charu(int[] arr){
        int insertIndex = 0;
        int insertValue = 0;
        for(int i = 1 ; i < arr.length ; i++){
            insertIndex = i - 1;
            insertValue = arr[i];
            while(insertIndex >= 0 && arr[insertIndex] > insertValue){
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            arr[insertIndex + 1] = insertValue;
        }

    }
}
