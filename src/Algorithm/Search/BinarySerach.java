package Algorithm.Search;

import java.util.ArrayList;
import java.util.List;

//二分查找要求数组是有序的
public class BinarySerach {
    public static void main(String[] args) {
        int[] arr = {1,8,10,89,99,99,99,991234};
        List<Integer> resIndexList = binarySearch1(arr, 0, arr.length - 1, 99);
        System.out.println("resIndex=" + resIndexList);
    }

    //无法处理查找多个相同值的二分查找算法
    public static int binarySearch(int[] arr , int left , int right , int findVal){

        //当left > right时，说明递归整个数组，但是没有找到
        if (left > right) {
            return -1;
        }
//        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
        int mid = (left + right) / 2;
        int midVal = arr[mid];


        if(findVal > midVal){
            //向右递归
            return binarySearch(arr,mid+1,right,findVal);
        }else if(findVal < midVal){
            //向左递归
            return binarySearch(arr,left,mid - 1,findVal);
        }else {
            return mid;
        }
    }

    //可以处理查找多个相同值的二分查找方法
    public static List<Integer> binarySearch1(int[] arr , int left , int right , int findVal){

        //当left > right时，说明递归整个数组，但是没有找到
        if (left > right) {
            return new ArrayList<Integer>();
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if(findVal > midVal){
            //向右递归
            return binarySearch1(arr,mid+1,right,findVal);
        }else if(findVal < midVal){
            //向左递归
            return binarySearch1(arr,left,mid - 1,findVal);
        }else {
            List<Integer> resIndexList = new ArrayList<>();

            //向mid索引值左边扫描，将所有满足的元素的下标，加入到集合ArrayList
            int temp = mid -1;
            while(true){
                if(temp < 0 || arr[temp] != findVal){
                    break;
                }
                resIndexList.add(temp);
                temp--;
            }

            resIndexList.add(mid);

            //向mid索引值右边扫描，将所有满足的元素的下标，加入到集合ArrayList
            temp = mid + 1;
            while(true){
                if(temp > arr.length || arr[temp] != findVal){
                    break;
                }
                resIndexList.add(temp);
                temp++;
            }

            return resIndexList;

        }
    }
}
