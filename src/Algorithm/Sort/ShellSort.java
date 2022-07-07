package Algorithm.Sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class ShellSort {

    public static void main(String[] args) {
//        int[] arr ={8,9,1,7,2,3,5,4,6,0};
//        System.out.print("开始前的情况：");
//        System.out.println(Arrays.toString(arr));
//        shellSort3(arr);
//        System.out.print("排完序后的情况：");
//        System.out.println(Arrays.toString(arr));

        int[] arr = new int[800000];
        for(int i = 0 ; i < 800000 ; i++){
            arr[i] = (int)(Math.random() * 8000000);//生成一个[0,800000)的数
        }

        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("排序前的时间是=" + date1Str);

        shellSort3(arr);

        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后的时间是=" + date2Str);
    }


    //在插入时采用移位法的希尔排序
    public static void shellSort3(int[] arr) {
        int temp = 0;
        for(int gap = arr.length / 2;gap > 0; gap /= 2){
            //从第gap个元素开始，逐个对其所在的组进行直接插入
            for(int i = gap ; i < arr.length ; i++){
                int j = i;
                temp = arr[j];
                if(arr[j] < arr[j-gap]){
                    while(j - gap >= 0 && temp < arr[j-gap]){
                        //移动
                        arr[j] = arr[j-gap];
                        j -= gap;
                    }
                    //当退出while后，就给temp找到插入的位置
                    arr[j] = temp;

                }
            }

        }
    }


    //在插入时采用交换法的希尔排序
    public static void shellSort2(int[] arr) {
        int temp = 0;
        int count = 0;
        for(int gap = arr.length / 2;gap > 0; gap /= 2){
            for(int i = gap ; i < arr.length; i++){
                for(int j = i - gap ; j >= 0 ; j -= gap){
                    //如果当前元素大于加上步长后的那个元素，就需要交换
                    if(arr[j] > arr[j+gap]){
                        temp = arr[j];
                        arr[j] = arr[j+gap];
                        arr[j+gap] = temp;
                    }

                }
            }
//            count++;
//            System.out.println("希尔排序第"+ count +"轮为" + Arrays.toString(arr));
        }
    }


    //分步骤演示的方法
    public static void shellSort1(int[] arr){
        int temp = 0;
        //第一轮
        for(int i = 5 ; i < arr.length; i++){
            for(int j = i - 5 ; j >= 0 ; j -= 5){
                //如果当前元素大于加上步长后的那个元素，就需要交换
                if(arr[j] > arr[j+5]){
                    temp = arr[j];
                    arr[j] = arr[j+5];
                    arr[j+5] = temp;
                }

            }
        }

        System.out.println("第一轮后的情况：");
        System.out.println(Arrays.toString(arr));

        //第二轮
        for(int i = 2 ; i < arr.length; i++){
            for(int j = i - 2 ; j >= 0 ; j -= 2){
                //如果当前元素大于加上步长后的那个元素，就需要交换
                if(arr[j] > arr[j+2]){
                    temp = arr[j];
                    arr[j] = arr[j+2];
                    arr[j+2] = temp;
                }

            }
        }

        System.out.println("第二轮后的情况：");
        System.out.println(Arrays.toString(arr));

        //第三轮
        for(int i = 1 ; i < arr.length; i++){
            for(int j = i - 1 ; j >= 0 ; j -= 1){
                //如果当前元素大于加上步长后的那个元素，就需要交换
                if(arr[j] > arr[j+1]){
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }

            }
        }

        System.out.println("第三轮后的情况：");
        System.out.println(Arrays.toString(arr));


    }
}
