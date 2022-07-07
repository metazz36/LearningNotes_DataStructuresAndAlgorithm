package DataStructures.Recursion;

public class RecursionTest {
    public static void main(String[] args) {
        test1(4);
        System.out.println("执行完递归");
        test2(4);
        System.out.println("4的阶乘为" + factorial(4));
    }


    //打印问题1
    public static void test1(int n){
        if(n > 2){
            test1(n-1);
        }
        System.out.println("n=" + n);
    }

    //打印问题2
    public static void test2(int n){
        if(n > 2){
            test2(n-1);
        }else{
            System.out.println("n=" + n);
        }
    }

    //阶乘问题
    public static int factorial(int n){
        if(n == 1){
            return 1;
        }else{
            return factorial(n-1) * n;
        }
    }
}
