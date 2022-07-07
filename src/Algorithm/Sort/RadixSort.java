package Algorithm.Sort;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.VoidType;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class RadixSort {

    public static void main(String[] args) {
//        int[] arr = {53,3,542,748,14,214,234,454,0,5};
//        System.out.println("原来的数组：");
//        System.out.println(Arrays.toString(arr));
//        radixSort1(arr);
//        System.out.println("现在的数组：");
//        System.out.println(Arrays.toString(arr));

        int[] arr = new int[8000000];
        for(int i = 0 ; i < 8000000 ; i++){
            arr[i] = (int)(Math.random() * 8000000);//生成一个[0,800000)的数
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("排序前的时间是" + date1Str);
        radixSort1(arr);
        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后的时间是" + date2Str);

    }

    //基数排序算法(通用)
    public static void radixSort1(int[] arr){
        //得到数组中最大的数的位数
        int max = arr[0];
        for(int i = 1 ; i < arr.length ; i++){
            if(arr[i] > max){
                max = arr[i];
            }
        }
        //得到最大数是几位数
        int maxLength = (max + "").length();

        //定义一个二维数组，表示10个桶,每个桶就是一个一维数组
        //很明显，基数排序是使用空间换时间的经典算法
        int[][] bucket = new int[10][arr.length];

        //为了记录每个桶中，实际存放了多少个数据，我们需要定义一个一维数组来记录各个桶的每次放入的数据个数
        //bucketElementCounts[i]就是记录bucker[i]桶放入的数据的个数
        int[] bucketElementCounts = new int[10];

        //合并前面演示的轮次循环
        for(int k = 0 ,n =1; k < maxLength ; k++ , n = n*10) {
            for(int j = 0 ; j < arr.length ; j++){
                //取出每个元素的对应位的值(digitOfElement即桶的位置)
                int digitOfElement = arr[j] /n % 10 ;
                //放入到对应的桶
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                bucketElementCounts[digitOfElement]++;
            }
            //把桶的数据取出放回到原来的数组
            int index = 0;
            int arrindex = 0;
            for(int i = 0 ; i < bucketElementCounts.length ; i++){
                while(index != bucketElementCounts[i]){
                    arr[arrindex++] = bucket[i][index++];
                }
                index = 0;
                //需要将每个bucketElementCounts[i]置0，后面轮次才能重新计数
                bucketElementCounts[i] = 0;
            }
            arrindex = 0;
        }


    }



    //基数排序算法(分步骤演示)
    public static void radixSort(int[] arr){

        //第一轮排序

        //定义一个二维数组，表示10个桶,每个桶就是一个一维数组
        //很明显，基数排序是使用空间换时间的经典算法
        int[][] bucket = new int[10][arr.length];

        //为了记录每个桶中，实际存放了多少个数据，我们需要定义一个一维数组来记录各个桶的每次放入的数据个数
        //bucketElementCounts[i]就是记录bucker[i]桶放入的数据的个数
        int[] bucketElementCounts = new int[10];
        for(int j = 0 ; j < arr.length ; j++){
            //取出每个元素的个位(digitOfElement即桶的位置)
            int digitOfElement = arr[j] % 10 ;
            //放入到对应的桶
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            bucketElementCounts[digitOfElement]++;
        }
        //把桶的数据取出放回到原来的数组
        int index = 0;
        int arrindex = 0;
        for(int i = 0 ; i < bucketElementCounts.length ; i++){
            while(index != bucketElementCounts[i]){
                arr[arrindex++] = bucket[i][index++];
            }
            index = 0;
            //需要将每个bucketElementCounts[i]置0，后面轮次才能重新计数
            bucketElementCounts[i] = 0;
        }
        arrindex = 0;



        System.out.println("第一轮后的数组：");
        System.out.println(Arrays.toString(arr));

        //第二轮排序
        for(int j = 0 ; j < arr.length ; j++){
            //取出每个元素的个位(digitOfElement即桶的位置)
            int digitOfElement = arr[j] /10 % 10 ;
            //放入到对应的桶
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            bucketElementCounts[digitOfElement]++;
        }
        //把桶的数据取出放回到原来的数组
        for(int i = 0 ; i < bucketElementCounts.length ; i++){
            while(index != bucketElementCounts[i]){
                arr[arrindex++] = bucket[i][index++];
            }
            index = 0;
            //需要将每个bucketElementCounts[i]置0，后面轮次才能重新计数
            bucketElementCounts[i] = 0;
        }
        arrindex = 0;

        System.out.println("第二轮后的数组：");
        System.out.println(Arrays.toString(arr));

        //第三轮排序
        for(int j = 0 ; j < arr.length ; j++){
            //取出每个元素的个位(digitOfElement即桶的位置)
            int digitOfElement = arr[j] / 100 ;
            //放入到对应的桶
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            bucketElementCounts[digitOfElement]++;
        }
        //把桶的数据取出放回到原来的数组
        for(int i = 0 ; i < bucketElementCounts.length ; i++){
            while(index != bucketElementCounts[i]){
                arr[arrindex++] = bucket[i][index++];
            }
            index = 0;
            //需要将每个bucketElementCounts[i]置0，后面轮次才能重新计数
            bucketElementCounts[i] = 0;
        }
        arrindex = 0;

        System.out.println("第三轮后的数组：");
        System.out.println(Arrays.toString(arr));

    }

}
