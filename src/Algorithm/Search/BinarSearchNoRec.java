package Algorithm.Search;

public class BinarSearchNoRec {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,8};
        int targetIndex = binarySearchNoRec(arr, 10);
        System.out.println("目标数在数组的下标为：" + targetIndex);
    }

    //二分查找的非递归实现（传入的是升序排列的数组）
    public static int binarySearchNoRec(int[] arr,int target){
        int left = 0;
        int right = arr.length - 1;
        while(left <= right){ //切记是<=
            int mid = (left + right) / 2;
            if(arr[mid] == target){
                return mid;
            }else if(arr[mid] > target){
                right = mid - 1;//向左边查找
            }else{
                left = mid + 1;//向右边查找
            }
        }
        return -1;
    }

}
