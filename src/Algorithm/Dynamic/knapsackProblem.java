package Algorithm.Dynamic;

public class knapsackProblem {
    public static void main(String[] args) {
        int[] w = {1,4,3};//物品的重量
        int[] val ={1500,3000,2000};//物品的价值
        int m = 4;//背包的容量
        int n = val.length;//物品的个数

        //创建二维数组，v[i][j]表示在前i个物品中能够装入容量为j的背包中的最大价值
        int[][] v = new int[n+1][m+1];

        //为了记录放入商品的情况（种类和个数）,我们顶一个数组
        int[][] path = new int[n+1][m+1];

        //初始化第一行和第一列(默认就是0 可以不处理)
        for(int i = 0 ; i < v.length ; i++){
            v[i][0] = 0;//处理第一列
        }
        for(int i = 0 ; i < v[0].length ; i++){
            v[0][i] = 0;//处理第一行
        }



        //根据讲义公式，来动态规划处理
        for(int i = 1; i < v.length ; i++){//不处理第一行
            for(int j = 1 ; j < v[i].length ; j++){//不处理第一列
                //公式
                if(w[i - 1] > j){//因为我们程序i是从1开始的，因此原来公式中的w[i]修改成w[i-1]
                    v[i][j] = v[i-1][j];
                }else{
                    //因为我们的i从1开始的，因此讲义公式需要调整成如下
                    //v[i][j] = Math.max(v[i-1][j],val[i-1]+v[i-1][j-w[i-1]]);

                    //为了记录商品存放到背包的情况，我们不能直接使用上述代码，使用if-else来替代
                    if(v[i-1][j] < val[i-1]+v[i-1][j-w[i-1]]){
                        v[i][j] =  val[i-1]+v[i-1][j-w[i-1]];
                        path[i][j] = 1;//把当前的情况记录到path
                    }else{
                        v[i][j] =  v[i-1][j];
                    }

                }
            }
        }

        //输出一下v
        for(int i = 0 ; i < v.length ; i++){
            for(int j = 0 ; j < v[i].length ; j++){
                System.out.print(v[i][j] + " ");
            }
            System.out.println();
        }

        //输出背包的存放情况
      /*  for(int i = 0 ; i < path.length ; i++){
            for(int j = 0 ; j < path[i].length ; j++){
                System.out.printf("第%d个商品放入到背包\n",i);
            }
        }*/
        int i = path.length - 1;//行的最大下标
        int j = path[0].length - 1;//列的最大下标
        while(i > 0 && j > 0){
            if(path[i][j] == 1){
                System.out.printf("第%d个商品放入到背包\n",i);
                j = w[i-1];
            }
            i--;
        }
    }

 }
