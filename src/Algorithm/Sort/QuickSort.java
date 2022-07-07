package Algorithm.Sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class QuickSort {

    public static void main(String[] args) {
//        int[] arr = {4,1,-1,0,-5,-7,0};
//        quickSort(arr,0,arr.length-1);
//        System.out.println(Arrays.toString(arr));

        int[] arr = new int[8000000];
        for(int i = 0 ; i < 8000000 ; i++){
            arr[i] = (int)(Math.random() * 8000000);//生成一个[0,800000)的数
        }

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("排序前的时间是" + date1Str);

        quickSort(arr,0,arr.length-1);

        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后的时间是" + date2Str);
    }

    public static void quickSort(int[] arr,int left,int right){
        int l = left;//左下标
        int r = right;//右下标
        int pivot =arr[(left + right)/2];//中轴值
        int temp = 0;
        //while循环的目的是让比pivot值小的放到左边，大的放右边
        while(l < r){
            //在pivot左边一直找，找到大于等于pivot的值，才退出
            while(arr[l] < pivot){
                l=l+1;
            }
            //在pivot右边一直找，找到小于等于pivot的值，才退出
            while(arr[r] > pivot){
                r = r-1;
            }
            //当 l>=r 说明pivot的左右两的值已经按照左边全部是小于等于pivot的值，右边全部是大于等于pivot的值
            if(l >= r){
                break;
            }
            //交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            //如果交换完后，发现arr[l] == pivot值 需要-- 前移
            if(arr[l] == pivot){
                r--;
            }

            //如果交换完后，发现arr[r] == pivot值 需要++ 后移
            if(arr[r] == pivot){
                l++;
            }

        }

        //如果l == r，必须l++ r--，否则会出现栈溢出
        if(l == r){
            l++;
            r--;
        }

        //向左递归
        if(left < r){
            quickSort(arr,left,r);
        }

        //向右递归
        if(right > r){
            quickSort(arr,l,right);
        }
    }


}
