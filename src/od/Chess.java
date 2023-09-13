package od;

import java.util.HashMap;
import java.util.Map;

public class Chess {

    public static void main(String[] args) {
        int[] arr1 = {-1,0,1,1,1,0,1,0,1,-1,1};
        System.out.println(xiaqi(1, arr1));
        int[] arr2 = {-1,0,1,1,1,0,1,0,1,-1,1};
        System.out.println(xiaqi(-1, arr2));
        int[] arr3 = {0,0,0,0,1,0,0,0,0,1,0};
        System.out.println(xiaqi(1, arr3));
        int[] arr4 = {0,1,-1,0,0,1,1,1,1,1};
        System.out.println(xiaqi(1,arr4));
        int[] arr5 = {0,-1,1,1,0,0,1,-1};
        System.out.println(xiaqi(1,arr5));
        int[] arr6 = {0,1,1,-1,0,1,-1,-1,0};
        System.out.println(xiaqi(-1,arr6));
        int[] arr7 = {-1,0,1,-1,1,1,1,1,0,-1,-1,-1,-1,-1,0,1,1,-1,1,1,0,1,-1,-1,0,1,1};
        System.out.println(xiaqi(1,arr7));
    }

    public static int xiaqi(int who,int[] arr){
        Map map = new HashMap<Integer,Integer>();// key是长度，value是指针
        int num = 0; // 计算长度
        int p = 0; // 指针
        int insertIndex = 0; // 插入的位置
        boolean flag = false;
        while(p < arr.length){
            if(arr[p] != who && arr[p] != 0){ // 非记录位
                if(num != 0 && num <= 5){
                    map.put(num,insertIndex);
                    flag = false;
                    num = 0;
                    p++;
                    continue;
                }
                p++;
                continue;
            }
            if(arr[p] == 0){ //插入位置
                if(flag == true){ // 已插入过，
                    flag = false;
                    if(num <= 5){
                        if(map.containsKey(num)){
                            int pre = (int)map.get(num);
                            if(Math.abs(pre - arr.length / 2) > Math.abs(insertIndex - arr.length / 2)){
                                map.put(num,insertIndex);
                            }
                        }else{
                            map.put(num,insertIndex);
                        }
                    }
                    num = 0;
                    p = insertIndex + 1;
                    continue;
                }else{ // 未插入过,插入
                    flag = true;
                    insertIndex = p;
                    num++;
                    p++;
                    continue;
                }
            }else{ // 记录位
                num++;
                p++;
                continue;
            }
        }
        if(num != 0 && num <= 5){
            if(map.containsKey(num)){
                int pre = (int)map.get(num);
                if(Math.abs(pre - arr.length / 2) > Math.abs(insertIndex - arr.length / 2)){
                    map.put(num,insertIndex);
                }
            }else{
                map.put(num,insertIndex);
            }
        }
        return (int)map.get(map.keySet().toArray()[ map.keySet().size() - 1]);
    }

}


