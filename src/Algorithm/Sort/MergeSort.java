package Algorithm.Sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;


public class MergeSort {
    public static int count = 0;
    public static void main(String[] args) {
//        int arr[] = {8,4,5,7,1,3,6,2};
//        int temp[] = new int[arr.length];
//        mergeSort(arr,0,arr.length-1,temp);
//        System.out.println("归并排序后："+ Arrays.toString(arr));
//        System.out.println("merge函数调用次数为"+ count);

        int[] arr = new int[8000];
        int temp[] = new int[arr.length];
        for(int i = 0 ; i < 8000 ; i++){
            arr[i] = (int)(Math.random() * 8000);//生成一个[0,800000)的数
            System.out.print(arr[i]+" ");
        }
        System.out.println();
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("排序前的时间是" + date1Str);
        mergeSort(arr,0,arr.length-1,temp);
        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后的时间是" + date2Str);
        for(int i = 0 ; i < 8000 ; i++){
            System.out.print(arr[i]+" ");
        }

//       System.out.println("merge函数调用次数为"+ count);
    }

    //分+合方法
    public static void mergeSort(int[] arr,int left,int right,int[] temp){
        if(left < right){
            int mid = (left + right) / 2;//中间索引
            //向左递归进行分解
            mergeSort(arr,left,mid,temp);
            //向右递归进行分解
            mergeSort(arr,mid+1,right,temp);
            //合并
            merge(arr,left,mid,right,temp);
        }

    }

    /*
    * arr 排序的原始数组
    * left 左边有序序列的初始索引
    * mid 中间索引
    * right 右边索引
    * temp 工具数组
    */
    //合并的方法
    public static void merge(int[] arr,int left,int mid,int right,int[] temp){
        int i = left;//初始化i，左边有序序列的初始索引
        int j = mid + 1; //初始化j，右边有序序列的初始索引
        int t = 0;//指向temp数组的当前索引

        //事件一
        //先把左右两边（已经有序）的数据按照规则填充到temp数组
        //直到左右两边有一边处理完毕
        while(i <= mid && j<= right){ //继续
            //如果左边的有序序列的当前元素，小于等于右边有序序列的当前元素
            //即将左边的当前元素，拷贝到temp数组
            //后移t和i
            if(arr[i] <= arr[j]){
                temp[t] = arr[i];
                t += 1;
                i += 1;
            }else{//反之，……
                temp[t] = arr[j];
                t += 1;
                j += 1;
            }
        }

        //事件二
        //把有剩余数据的一边的数据依次填充到temp数组
        while(i <= mid){//左边的有序序列还有剩余数组，全部填充到temp
            temp[t] = arr[i];
            t += 1;
            i += 1;
        }

        while(j <= right){//右边的有序序列还有剩余数组，全部填充到temp
            temp[t] = arr[j];
            t += 1;
            j += 1;
        }

        //事件三
        //将temp数组的元素拷贝到arr数组
        //注意并不是每次都拷贝所有
        t = 0;
        int tempLeft = left;
        while(tempLeft <= right){
            arr[tempLeft] = temp[t];
            t += 1;
            tempLeft += 1;
        }

        count++;
    }
}
