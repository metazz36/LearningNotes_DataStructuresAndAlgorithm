package Algorithm.Divide;

public class HanoiTower {
    public static void main(String[] args) {
        hannoiTower(3,'A','B','C');

    }

    //汉诺塔的移动方法（使用分治算法）
    public static void hannoiTower(int num,char t1,char t2,char t3){
        //如果只有一个盘
        if(num == 1){
            System.out.println("第1个盘从" + t1 + "->" + t3);
        }else{
            //1.把上面的所有盘A->B，移动过程使用到C
            hannoiTower(num - 1, t1, t3, t2);
            //2.把最下边的盘A->C
            System.out.println("第" + num + "个盘从" + t1 + "->" + t3);
            //3.把B塔的所有盘从B->C,移动过程使用到A塔
            hannoiTower(num - 1, t2, t1, t3);
        }

    }
}
