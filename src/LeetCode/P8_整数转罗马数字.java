package LeetCode;


//题目：12. 整数转罗马数字
//难度：中等
/*题目描述：
罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。

字符          数值
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，
所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
给你一个整数，将其转为罗马数字。
 */
public class P8_整数转罗马数字{
    public static void main(String[] args) {
        P8_整数转罗马数字 p8 = new P8_整数转罗马数字();
        String s = p8.intToRoman(3999);
        System.out.println(s);
    }

    public String intToRoman(int num) {
        String s ="";
        int M = num / 1000;
        num = num % 1000;
        int D = num / 500;
        num = num % 500;
        int C = num / 100;
        num = num % 100;
        int L = num / 50;
        num = num % 50;
        int X = num / 10;
        num = num % 10;
        int V = num / 5;
        num = num % 5;
        int I = num;

        //将字符拼接
        //要考虑的情况：
        //   如果I,X,C == 4，要变换字符串
        //   当I,X,C==4，还要考虑对应的V,L,D的值，根据他们组合的结果选择字符串
        //   I==4，V==0，对应IV，I==4，V==1，对应IX
        //   X==4, L==0, 对应XL, X==4, L==1，对应XC
        //   C==4，D==0，对应CD，C==4，D==1， 对应CM
        for(int i = 1;i <= M;i++){
            s = s + "M";
        }

        if(C == 4 && D == 1){
            s = s + "CM";
        }else if(C == 4 && D == 0){
            s = s + "CD";
        }else{
                if(D != 0 ){
                    s = s + "D";
                }
                for(int i = 1; i <= C;i++){
                    s = s + "C";
                }

        }

        if(X == 4 && L == 1){
            s = s + "XC";
        }else if(X == 4 && L == 0){
            s = s + "XL";
        }else{
            if(L != 0){
                s = s + "L";
            }
            for(int i = 1;i <= X; i++){
                s = s + "X";
            }
        }

        if(I == 4 && V == 1){
            s = s + "IX";
        }else if(I == 4 && V == 0){
            s = s + "IV";
        }else{
            if(V != 0){
                s = s + "V";
            }
            for(int i = 1;i <= I;i ++){
                s = s + "I";
            }
        }
        return s;
    }

}
