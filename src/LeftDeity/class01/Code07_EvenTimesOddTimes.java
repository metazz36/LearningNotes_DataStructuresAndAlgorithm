package LeftDeity.class01;

public class Code07_EvenTimesOddTimes {

	/**
	 * 一个数组中有一种数出现了奇数次，其他数都出现了偶数次，怎么找到这一个数
	 */
	public static void printOddTimesNum1(int[] arr) {
		int eO = 0;
		for (int cur : arr) {
			eO ^= cur;
		}
		System.out.println(eO);
	}

	/**
	 * 一个数组中有两种数出现了奇数次，其他数都出现了偶数次，怎么找到这两个数
	 */
	public static void printOddTimesNum2(int[] arr) {
		int eO = 0, eOhasOne = 0;
		for (int curNum : arr) {
			eO ^= curNum;
		}
		int rightOne = eO & (~eO + 1);
		for (int cur : arr) {
			if ((cur & rightOne) != 0) { // 在rightOne位上的二进制数为1的数
				eOhasOne ^= cur;
			}
		}
		System.out.println(eOhasOne + " " + (eO ^ eOhasOne)); // e0 = e1 ^ e2 ;e0 ^ e1 = e2
	}

	public static void main(String[] args) {
		int[] arr1 = { 3, 3, 2, 3, 1, 1, 1, 3, 1, 1, 1 };
		printOddTimesNum1(arr1);
		int[] arr2 = { 4, 3, 4, 2, 2, 2, 4, 1, 1, 1, 3, 3, 1, 1, 1, 4, 2, 2 };
		printOddTimesNum2(arr2);
	}

}
