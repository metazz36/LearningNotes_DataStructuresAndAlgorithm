package Algorithm.Sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class SelectSort {
    public static void main(String[] args) {
        int arr[] = {5,-1,3,6,-50,10,9,8};
        System.out.println("排序前");
        System.out.println(Arrays.toString(arr));
        SelectSort(arr);
        System.out.println("排序后");
        System.out.println(Arrays.toString(arr));

        //测试一下选择排序的速度（n的平方），给80000个数据进行测试
//        int[] arr = new int[80000];
//        for(int i = 0 ; i < 80000 ; i++){
//            arr[i] = (int)(Math.random() * 8000000);//生成一个[0,800000)的数
//        }
//
//        Date date1 = new Date();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String date1Str = simpleDateFormat.format(date1);
//        System.out.println("排序前的时间是=" + date1Str);
//
//        SelectSort(arr);
//
//        Date date2 = new Date();
//        String date2Str = simpleDateFormat.format(date2);
//        System.out.println("排序后的时间是=" + date2Str);

    }

    public static void SelectSort(int[] arr) {
        int min = 0; //临时变量
        int minIndex = 0; //临时变量
        for(int i = 0; i < arr.length -1; i++){
            min = arr[i];
            minIndex = i;
            for(int j = i+1;j < arr.length; j++){
                if(min > arr[j]){
                    min = arr[j];
                    minIndex = j;
                }
            }
            arr[minIndex] = arr[i];
            arr[i] = min;

        }
    }

    public static void SelectSort1(int[] arr){

    }
}
