package DataStructures.Recursion;

public class MiGong {
    public static void main(String[] args) {
        //先创建一个二维数组，模拟迷宫
        int[][] map = new int[8][7];
        //使用1表示墙
        //上下全部置为1
        for(int i = 0;i < 7 ;i++){
            map[0][i]=1;
            map[7][i]=1;
        }
        //左右全部置为1
        for(int i = 0;i < 8;i++){
            map[i][0]=1;
            map[i][6]=1;
        }
        //设置挡板
        map[3][1] = 1; map[3][2] = 1;

        //输出地图
        System.out.println("地图为：");
        for(int i = 0;i < 8;i++){
            for(int j = 0; j < 7;j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();

        }

        //使用递归给小球找路
//        setWay2(map,1,1);
        setWay2(map,1,1);


        //输出新的地图
        System.out.println("小球走过后的地图为：");
        for(int i = 0;i < 8;i++){
            for(int j = 0; j < 7;j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();

        }
    }

    //规定：
    //1.当map[i][j]为0表示没走过；为2表示可以走；为3表示该点已经走过，但是走不通
    //2.起点map[1][1] 终点map[6][5]
    //3.在走迷宫时，需要定一个策略，下-》右-》上-》左，如果走不通再回溯
    //使用递归回溯来给小球寻路
    //map为地图，i和j表示起始坐标
    public static boolean setWay(int[][] map,int i,int j){
        if(map[6][5] == 2){ //表示通路已经找到
            return true;
        }else{
            if(map[i][j] == 0){ //表示当前这个点还没走过
                //按照策略走
                map[i][j] = 2;//假定该点是可以走通的
                if(setWay(map,i+1,j)){//向下走
                    return true;
                }else if(setWay(map,i,j+1)){//向右走
                    return true;
                }else if(setWay(map,i-1,j)){
                    return true;
                }else if(setWay(map,i,j-1)){
                    return true;
                }else{//说明该点是无法走通的
                    map[i][j] = 3;
                    return false;
                }
            }else{//如果map[i][j] != 0,map可能为1，2，3
                return false;

            }
        }
    }

    //修改寻路策略（上右下左）
    public static boolean setWay2(int[][] map,int i,int j){
        if(map[6][5] == 2){ //表示通路已经找到
            return true;
        }else if(map[i][j] == 0){
                //按照策略走
                map[i][j] = 2;//假定该点是可以走通的
                if(setWay2(map,i-1,j)){//向上走
                    return true;
                }else if(setWay2(map,i,j+1)){//向右走
                    return true;
                }else if(setWay2(map,i+1,j)){//向下走
                    return true;
                }else if(setWay2(map,i,j-1)){//向左走
                    return true;
                }else{//说明该点是无法走通的
                    map[i][j] = 3;
                    return false;
                }
            }else{ //如果map[i][j] != 0,map可能为1，2，3
                return false;
            }
        }

}
