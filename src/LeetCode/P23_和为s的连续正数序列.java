package LeetCode;

import java.util.ArrayList;

public class P23_和为s的连续正数序列 {

    public static void main(String[] args) {
        int[][] continuousSequence = findContinuousSequence(36);
        for (int[] ints : continuousSequence) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
    }

    public static int[][] findContinuousSequence(int target) {
        int max = target/2 + 1;
        int p = 1;
        int q = 2;
        ArrayList<int[]> list = new ArrayList<>();
        while(p < max){
            while(q <= max && (p + q) * (q - p + 1)/2 < target){
                q++;
            }
            if((p + q) * (q - p + 1)/2 == target){
                int[] res = new int[q - p + 1];
                for(int i = p;i <= q;i++){
                    res[i-p] = i;
                }
                list.add(res);
            }
            p++;
            q = p + 1;
        }
        return list.toArray(new int[list.size()][]);
    }
}
