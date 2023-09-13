package LeftDeity.class02;
import java.util.PriorityQueue;

/**
 * 给定一个数组arr，和一个数num，请把小于等于num的数放在数 组的左边，大于num的数放在数组的右边。要求额外空间复杂度O(1)，时间复杂度O(N)
 */
public class Code04_SortArrayDistanceLessK {
	public static void main(String[] args) {
		int[] arr = {10,8,12,1,2,2,9,2,5,1,3,34,12,64,35,46,6,6,7};
		printArray(arr);
		sortedArrDistanceLessK(arr,9);
		printArray(arr);
	}

	public static void sortedArrDistanceLessK(int[] arr, int k) {
		PriorityQueue<Integer> heap = new PriorityQueue<>();
		int index = 0;
		for (; index < Math.min(arr.length, k); index++) {
			heap.add(arr[index]);
		}
		int i = 0;
		for (; index < arr.length; i++, index++) {
			heap.add(arr[index]);
			arr[i] = heap.poll();
		}
		while (!heap.isEmpty()) {
			arr[i++] = heap.poll();
		}
	}

	// for test
	public static void printArray(int[] arr) {
		if (arr == null) {
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

}
