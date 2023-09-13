package Algorithm.Divide;


import java.text.SimpleDateFormat;
import java.util.Date;

public class BinaryTreeCount {

    public static int count(int n) {
        // 处理基本情况
        if (n == 0 || n == 1) {
            return 1;
        }
        // 计算所有可能的二叉树的数量
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            // 左子树的节点数为 i-1，右子树的节点数为 n-i
            sum += count(i-1) * count(n-i);
        }
        return sum;
    }

    public static void main(String[] args) {
        int n = 4;
        Date date1 = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = simpleDateFormat.format(date1);
        System.out.println("排序前的时间是" + date1Str);
        int num = count(n);
        System.out.println(n + " 个节点的二叉树数量为：" + num);
        Date date2 = new Date();
        String date2Str = simpleDateFormat.format(date2);
        System.out.println("排序后的时间是" + date2Str);
    }
}

