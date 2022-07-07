package Algorithm.Sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {8,7,6,5,4,3,2,1};
        System.out.println("排序前");
        System.out.println(Arrays.toString(arr));
        InsertSort(arr);
        System.out.println("排序后");
        System.out.println(Arrays.toString(arr));

//        //测试一下插入排序的速度（n的平方），给80000个数据进行测试
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
//        InsertSort(arr);
//
//        Date date2 = new Date();
//        String date2Str = simpleDateFormat.format(date2);
//        System.out.println("排序后的时间是=" + date2Str);
    }


    public static void InsertSort(int[] arr){
        int insertVal = 0;
        int insertIndex = 0 ;
        for(int i = 1; i < arr.length; i++){
            insertVal = arr[i];
            insertIndex = i-1 ;
            while(insertIndex >= 0 && insertVal < arr[insertIndex]){
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            //判断是否需要赋值（也可以不判断）
//            if(insertIndex + 1 != i){
                arr[insertIndex + 1] = insertVal;
//            }

        }
    }
}
