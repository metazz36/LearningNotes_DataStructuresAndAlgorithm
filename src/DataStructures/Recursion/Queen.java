package DataStructures.Recursion;




class Demo{
    //测试
    public static void main(String[] args) {
        Queen queue8 = new Queen();
        queue8.check(0);
        System.out.printf("总共有%d种解法",Queen.count);
        System.out.println();
        System.out.printf("判断冲突次数%d次",Queen.judgeCount);
    }
}

public class Queen {
    //先定义1个max。表示有多少个皇后
    int max = 8;
    //定义一维数组array，保存皇后放置位置的结果
    int[] array = new int[max];
    static int count = 0;
    static int judgeCount = 0;

    //放置第n个皇后
    public void check(int n){
        if(n == max){
            print();
            return;
        }
        //依次放入皇后，并判断是否冲突
        for (int i = 0;i < max ;i++){
            //先把当前这个皇后n，放在该行的第1列
            array[n] = i;
            //判断当放置第n个到i列时，是否冲突
            if(judge(n)){ //不冲突
                //接着放置第n+1个皇后i，即开始递归
                check(n+1);
            }
            //如果冲突，进入下一趟循环，继续执行array[n] = i,即将第n个皇后，放置在本行的后移的一个位置

        }
   }

    //查看当我们放置第n个皇后，就去检测该皇后是否和前面已经摆放的皇后冲突
    public boolean judge(int n){//n表示第n个皇后
        judgeCount++;
        for(int i = 0;i < n; i++){
            //array[i] == array[n]表示判断第n个皇后是否和前面的n-1个皇后在同一列
            //Math.abs(n-i) == Math.abs(array[n]-array[i])表示判断第n个皇后是否和前面的第n-1个皇后在同一斜线
            if(array[i] == array[n] || Math.abs(n-i) == Math.abs(array[n]-array[i])){
                return false;
            }
        }
        return true;
    }

    //将一维数组的结果打印成皇后的位置
    public void print(){
        count++;
        for(int i = 0;i < array.length; i++){
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
