package LeetCode;

//题目：寻找两个正序数组的中位数
//难度：困难


/*给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。
请你找出并返回这两个正序数组的 中位数 。算法的时间复杂度应该为 O(log (m+n)) 。*/
public class P9_寻找两个正序数组的中位数{

    public static void main(String[] args) {

        int[] nums1 = {1,2};
        int[] nums2 = {3,4};
        System.out.println(findMedianSortedArrays(nums1, nums2));
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] newArrays = new int[nums1.length + nums2.length];
        int l1 = 0;//nums1的移动指针
        int l2 = 0;//nums2的移动指针
        int l3 = 0;//newArrays的移动指针
        while(l1 < nums1.length && l2 < nums2.length){
            if(nums1[l1] < nums2[l2] ){
                newArrays[l3] = nums1[l1];
                l3++;
                l1++;
            }else{
                newArrays[l3] = nums2[l2];
                l3++;
                l2++;
            }
        }
        if(l1 != nums1.length ){
            while(l1 < nums1.length){
                newArrays[l3] = nums1[l1];
                l1++;
                l3++;
            }
        }

        if(l2 != nums2.length ){
            while(l2 < nums2.length){
                newArrays[l3] = nums2[l2];
                l2++;
                l3++;
            }
        }

        int mid = newArrays.length / 2;
        if(newArrays.length % 2 == 0 ){
            return (double)(newArrays[mid -1 ] + newArrays[mid])/2;
        }else{
            return newArrays[mid];
        }


    }

}
