package LeftDeity.class01;

/**
 * 在一个有序数组中，找>=某个数最左侧的位置
 * 比如数组【1，3，5，7，9】，找 >= 4最左侧的位置，即 5 ,数组下标为2
 */
public class Code05_BSNearLeft {

	public static void main(String[] args) {
		int[] arr = {1,3,5,7,9};
		System.out.println(nearestIndex(arr, 0)); // 0
		System.out.println(nearestIndex(arr, 4)); // 2
		System.out.println(nearestIndex(arr, 10)); // -1
		System.out.println(nearestIndex(arr, 9)); // -1
	}

	// 在arr上，找满足>=value的最左位置
	public static int nearestIndex(int[] arr, int value) {
		int L = 0;
		int R = arr.length - 1;
		int index = -1;
		while (L <= R) {
			int mid = L + ((R - L) >> 1);
			if (arr[mid] >= value) {
				index = mid;
				R = mid - 1;
			} else {
				L = mid + 1;
			}
		}
		return index;
	}

}
