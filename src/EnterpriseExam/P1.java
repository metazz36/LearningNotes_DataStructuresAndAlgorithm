package EnterpriseExam;

import java.util.Arrays;

//不用中间变量交换两个变量的值
//编写一个函数，不用临时变量，直接交换numbers = [a, b]中a与b的值。
public class P1 {
    public static void main(String[] args) {
        int[] arr ={10,11};
        P1 p1 = new P1();
        int[] ints = p1.swapNumbers3(arr);
        System.out.println(Arrays.toString(ints));

    }

    //加减法
    public  int[] swapNumbers1(int[] numbers) {
        if (numbers[1] == numbers[0]) {
            return numbers;
        }
        numbers[1] = numbers[0] + numbers[1];
        numbers[0] = numbers[1] - numbers[0];
        numbers[1] = numbers[1] - numbers[0];
        return numbers;

    }

    //异或法
    public  int[] swapNumbers2(int[] numbers) {

        return null;
    }

    //乘除法
    public  int[] swapNumbers3(int[] numbers) {
        if (numbers[1] == numbers[0]) {
            return numbers;
        }
        numbers[1] = numbers[0] * numbers[1];
        numbers[0] = numbers[0] * numbers[1];
        numbers[1] = numbers[0] / numbers[1];
        numbers[0] = numbers[0] / numbers[1] /numbers[1];

        return numbers;
    }

}
