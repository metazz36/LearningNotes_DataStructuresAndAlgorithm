package Algorithm.Sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class HeapSort {
    public static void main(String[] args) {
//        int[] arr={4,6,8,5,9,9,8,6,5,4,3,2,1};
//        System.out.println("排序前：");
//        System.out.println(Arrays.toString(arr));
//        heapSort(arr);
//        System.out.println("排序后：");
//        System.out.println(Arrays.toString(arr));



        int[] arr = new int[8000000];
        for(int i = 0 ; i < 8000000 ; i++){
            arr[i] = (int)(Math.random() * 8000000);//生成一个[0,800000)的数
        }
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("排序前的时间是" + date1Str);
        heapSort(arr);
        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后的时间是" + date2Str);
    }


    public static void heapSort(int[] arr){

        int temp;
//        //分步完成
//        adjustHeap(arr,1,arr.length);
//        System.out.println("第一次" + Arrays.toString(arr));
//
//        adjustHeap(arr,0,arr.length);
//        System.out.println("第二次" + Arrays.toString(arr));

        //1.将无序序列构建成一个堆
        for(int i = arr.length / 2 - 1;i >= 0 ; i--){
            adjustHeap(arr,i,arr.length);
        }

        //2.将堆顶元素与末尾元素交换，将最大元素沉到数组末端
        //3.重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，反复执行调整+交换步骤，直到整个序列有序
        for(int j = arr.length - 1;j > 0 ;j--){
            //交换
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr,0,j);
        }
    }


    //将以i对应的非叶子节点的树调整成大顶堆
    //arr     待调整的数组
    //i       表示非叶子节点在数组中的索引
    //length  表示对多少个元素继续调整，length在逐渐减少
    public static void adjustHeap(int[] arr,int i,int length){
        int temp = arr[i];
        //开始调整
        //k = i * 2 + 1 是 i节点的左子节点
        for(int k = i * 2 + 1;k < length;k = k * 2 + 1){
            if(k + 1 < length && arr[k] < arr[k+1]){//左子节点的值小于右子节点的值
                k++;//k指向右子节点
            }
            if(arr[k] > temp){//如果子节点大于父节点
                arr[i] = arr[k];//把较大的值赋给当前节点（父节点）w
                i = k;//！！！i 指向 k，继续for循环比较
            }else{
                break;//从左至右，从下到上调整，所以可以直接break
            }
        }
        //当for循环结束后，我们已经将i为父节点的树的最大值，放在了该树的根上(局部)
        arr[i] = temp;//将temp值放到调整后的位置

    }

}


