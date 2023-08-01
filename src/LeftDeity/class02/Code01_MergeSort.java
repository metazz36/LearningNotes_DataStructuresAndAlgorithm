package LeftDeity.class02;

/**
 * 归并排序（分治算法/分而治之）
 * 知识点1、递归的思想
 * 知识点2、合并的思路
 * 知识点3、利用master公式求解归并排序的时间复杂度
 * T（N） = a * T(N/b) + O(N^d) ；T(n) 表示问题规模为n时的时间复杂度；a 表示递归发生的次数（子问题的个数）；n/b 表示每个子问题的规模；O(N^d) 表示合并子问题和其他操作所需的时间复杂度。
 * 在归并排序中，每一次分解操作都将问题规模减半，即a = 2。并且在解决阶段，需要对两个子数组进行归并排序，每个子数组的规模为原问题的一半，即b = 2。合并阶段的时间复杂度为O(n)，即 d = 1。
 * 根据master公式，log(b,a) [以b为底，a为真数的对数] = log(2,2) = 1 = d ，所以 T（N） = O(N^d * logN) = O(N*logN) ,即归并排序的时间复杂度为O(N*logN)
 * 知识点4、归并排序的空间复杂度 对于下述方法而言，空间复杂度为O(N)，因为开辟了一个长度为N的临时数组，事实上存在空间复杂度为O(1)的归并排序，名曰《归并排序内部缓存法》（无需掌握）
 * 知识点5、排序算法的稳定性：同样值的个体之间，如果不因为排序而改变相对次序，就是这个排序是有稳定性的；否则就没有。
 * 知识点6、不具备稳定性的排序：选择排序、快速排序（可以做到稳定性，但是非常难，不需要掌握）、堆排序；具备稳定性的排序：冒泡排序、插入排序、归并排序、一切桶排序思想下的排序
 * 知识点7、目前不存在时间复杂度为O(N*logN),空间复杂度为O（1）又具有稳定性的排序算法
 */
import java.util.Arrays;

public class Code01_MergeSort {

	public static void mergeSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		mergeSort(arr, 0, arr.length - 1);
	}

	public static void mergeSort(int[] arr, int l, int r) {
		if (l == r) {
			return; // 最外层终止条件
		}
		int mid = l + ((r - l) >> 1);
		mergeSort(arr, l, mid);
		mergeSort(arr, mid + 1, r);
		merge(arr, l, mid, r);// 里层操作逻辑
	}

	public static void merge(int[] arr, int l, int m, int r) {
		int[] help = new int[r - l + 1];
		int i = 0;
		int p1 = l;
		int p2 = m + 1;
		while (p1 <= m && p2 <= r) {
			help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
		}
		while (p1 <= m) {
			help[i++] = arr[p1++];
		}
		while (p2 <= r) {
			help[i++] = arr[p2++];
		}
		for (i = 0; i < help.length; i++) {
			arr[l + i] = help[i]; // 尤其要注意这里复制的时候是从原数组的 L + i 开始 ！ ！ ！
		}
	}

	// for test
	public static void comparator(int[] arr) {
		Arrays.sort(arr);
	}

	// for test
	public static int[] generateRandomArray(int maxSize, int maxValue) {
		int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
		}
		return arr;
	}

	// for test
	public static int[] copyArray(int[] arr) {
		if (arr == null) {
			return null;
		}
		int[] res = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			res[i] = arr[i];
		}
		return res;
	}

	// for test
	public static boolean isEqual(int[] arr1, int[] arr2) {
		if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
			return false;
		}
		if (arr1 == null && arr2 == null) {
			return true;
		}
		if (arr1.length != arr2.length) {
			return false;
		}
		for (int i = 0; i < arr1.length; i++) {
			if (arr1[i] != arr2[i]) {
				return false;
			}
		}
		return true;
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

	// for test
	public static void main(String[] args) {
		int testTime = 500000;
		int maxSize = 100;
		int maxValue = 100;
		boolean succeed = true;
		for (int i = 0; i < testTime; i++) {
			int[] arr1 = generateRandomArray(maxSize, maxValue);
			int[] arr2 = copyArray(arr1);
			mergeSort(arr1);
			comparator(arr2);
			if (!isEqual(arr1, arr2)) {
				succeed = false;
				printArray(arr1);
				printArray(arr2);
				break;
			}
		}
		System.out.println(succeed ? "Nice!" : "Fucking fucked!");

		int[] arr = generateRandomArray(maxSize, maxValue);
		printArray(arr);
		mergeSort(arr);
		printArray(arr);

	}

}
