package LeftDeity.class01;

/**
 * 用递归方法找一个数组中的最大值
 */
public class Code08_GetMax {

	public static int getMax(int[] arr) {
		return process(arr, 0, arr.length - 1);
	}

	public static int process(int[] arr, int L, int R) {
		if (L == R) { // 最外层函数终止条件
			return arr[L];
		}
		int mid = L + ((R - L) >> 1);
		int leftMax = process(arr, L, mid);
		int rightMax = process(arr, mid + 1, R);
		return Math.max(leftMax, rightMax); // 内层函数终止条件
	}

	public static void main(String[] args) {
		int[] arr = {7,5,3,10,12,18};
		System.out.println(getMax(arr));// 18
	}

}
