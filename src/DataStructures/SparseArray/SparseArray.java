package DataStructures.SparseArray;

import org.junit.Test;

//稀疏矩阵
public class SparseArray {

    @Test
    public void SparseArrayTest(){

        //创建一个原始的二维数组11*11
        int chessarr1[][] = new int[11][11];
        chessarr1[1][2] =1;
        chessarr1[2][3] =2;
        chessarr1[3][4] =3;
        //输出原始的二维数组
        System.out.println("原始的二维数组：");
        for(int[] row : chessarr1){
            for(int data :row){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }

        //将二维数组转为稀疏数组
        //1.先遍历二维数组得到非0的个数
        int sum=0;
        for(int i=0;i<11;i++){
            for(int j=0;j<11;j++){
                if(chessarr1[i][j]!=0){
                    sum++;
                }
            }
        }

        //2.创建对应的稀疏数组
        int sparseArr[][] = new int[sum+1][3];

        //3.给稀疏数组第一行赋值
        sparseArr[0][0]=11;
        sparseArr[0][1]=11;
        sparseArr[0][2]=sum;

        //4.遍历二维数组，将非0的值存放到稀疏数组中
        int count = 0;//用于记录是第几个非0数据
        for(int i=0;i<11;i++){
            for(int j=0;j<11;j++){
                if(chessarr1[i][j]!=0){
                    count++;
                    sparseArr[count][0]=i;
                    sparseArr[count][1]=j;
                    sparseArr[count][2]=chessarr1[i][j];

                }
            }
        }

        //输出稀疏数组的形式
        System.out.println();
        System.out.println("得到的稀疏数组为如下形式：");
        for(int i=0;i<sparseArr.length;i++){
            System.out.printf("%d\t%d\t%d\t\n",sparseArr[i][0],sparseArr[i][1],sparseArr[i][2]);
        }

        //1.将稀疏数组恢复成原始的二维数组
        int chessarr2[][] = new int[sparseArr[0][0]][sparseArr[0][1]];
        //2.在读取稀疏数组后几行的数组，并赋给原始的二维数组
        for(int i=1;i<sparseArr.length;i++){
            chessarr2[sparseArr[i][0]][sparseArr[i][1]]= sparseArr[i][2];

        }


        //输出转换后的原始二维数组
        System.out.println("转换后的原始的二维数组：");
        for(int[] row : chessarr2){
            for(int data :row){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }

    }
}
