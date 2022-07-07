package Algorithm.Search;


import java.util.Arrays;

public class InsertSearch {
    public static int count;
    public static void main(String[] args) {
        int[] arr = new int[100];
        for(int i = 0 ; i < 100 ; i++){
            arr[i] = i+1;
        }
        int index = insertValueSearch(arr, 0, arr.length - 1, 34);
        System.out.println("index=" + index);
        System.out.println("查找次数 = " + count);
    }


    //编写插值查找算法
    public static int insertValueSearch(int[] arr , int left , int right , int findVal){
        count++;
        //当left > right时，说明递归整个数组，但是没有找到
        if (left > right) {
            return -1;
        }
        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];

        if(findVal > midVal){
            //向右递归
            return insertValueSearch(arr,mid+1,right,findVal);
        }else if(findVal < midVal){
            //向左递归
            return insertValueSearch(arr,left,mid - 1,findVal);
        }else {
            return mid;
        }
    }

}
