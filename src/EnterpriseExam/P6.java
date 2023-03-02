package EnterpriseExam;

/**
 * @Description:
 * @Title:
 * @Package:
 * @Author: mazizhen
 * @CreateTime: 2022/12/6 10:27
 */
public class P6 {
    public static void main(String[] args) {
        int value = 10; //双方最多进球总数
        int v1 = 0;
        int v2 = 0;
        int v3 = 0;
        int count = 0;
        for(int i = 0;i <= value;i++){
            System.out.println("总共进" + i + "球的情况：");
            v3 = 0;
            v1 = 0;
            v2 = i;
            while(v1 + v2 <= i && v1 <= v2){
                if(v1 == v2){
                    System.out.println("比分为 " + v1 + ":" + v2);
                    count++;
                    v3++;
                    break;
                }
                if(v1 < v2){
                    System.out.println("比分为 " + v1 + ":" + v2);
                    System.out.println("比分为 " + v2 + ":" + v1);
                    count = count + 2;
                    v3 = v3 + 2;
                    v1++;
                    v2--;
                }
            }
            System.out.println("总共进" + i + "球的情况有" + v3 + "种");
            System.out.println("======================================");
        }
        System.out.println("总的可能事件次数为" + count);
    }
}
