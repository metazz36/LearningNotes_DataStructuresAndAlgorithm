package Algorithm.Sort;


import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

//冒泡排序
public class BubbleSort {

    public static void main(String[] args) {

        int arr[] = {1,3,-1,-5,100,24,4,4};
        System.out.println("排序前");
        System.out.println(Arrays.toString(arr));
        bubbleSort(arr);
        System.out.println("排序后");
        System.out.println(Arrays.toString(arr));


        //测试一下冒泡排序的速度（n的平方），给80000个数据进行测试
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
//        bubbleSort(arr);
//
//        Date date2 = new Date();
//        String date2Str = simpleDateFormat.format(date2);
//        System.out.println("排序后的时间是=" + date2Str);


    }

    public static void bubbleSort(int[] arr){
        int temp = 0;//临时变量
        boolean flag = false;//标识变量，表示是否进行过交换
        for (int i = 0; i < arr.length-1; i++) {
            for (int j = 0; j < arr.length-1-i; j++) {
                if (arr[j] > arr[j + 1]) {
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }

            if(!flag){ //在一趟排序中，一次交换都没有发生过
                break;
            }else{
                flag = false; //重置flag，进行下次判断
            }

        }
    }


}
