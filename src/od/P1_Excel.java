package od;


import java.util.ArrayList;
import java.util.List;

public class P1_Excel {
    public static void main(String[] args) {
        List<List<String>> lists = new ArrayList<>();
        for(int i = 0;i < 5 ;i++){
            lists.add(new ArrayList<>());
        }
        for(int i = 0;i < 5 ;i++){
            for (int j = 0 ; j < 5;j++){
                lists.get(i).add(String.valueOf(j));
            }

        }
        for (List<String> list : lists) {
            System.out.println(list);
        }
        System.out.println("A1:E5 = " + calculateExcel(5, 5, lists, "A1:E5"));
        System.out.println("A1:B2 = " + calculateExcel(5, 5, lists, "A1:B2"));
        System.out.println("D4:D4 = " + calculateExcel(5, 5, lists, "D4:D4"));
        System.out.println("A5:E1 = " + calculateExcel(5, 5, lists, "A5:E1"));
    }

    /**
     * Excel求和
     *
     * @param rows 行
     * @param cols 列
     * @param collection 单元格内容
     * @param str 选中区域
     */
    public static int calculateExcel(int rows, int cols, List<List<String>> collection,String str){
        int rowMin = 0; //最小行标
        int rowMax = 0; //最大行标
        int colMin = 0; //最小列标
        int colMax = 0; //最大列标
        int sum = 0; // 和
        String[] split = str.split(":");
        int value1 = split[0].charAt(0) - 64 - 1;
        int value2 = split[0].charAt(1) - 48 - 1;
        int value3 = split[1].charAt(0) - 64 - 1;
        int value4 = split[1].charAt(1) - 48 - 1;
        if(value1 > value3){
            rowMin = value3;
            rowMax = value1;
        }else{
            rowMin = value1;
            rowMax = value3;
        }
        if(value2 > value4){
            colMin = value4;
            colMax = value2;
        }else{
            colMin = value2;
            colMax = value4;
        }
        for(int i = rowMin;i <= rowMax;i ++){
            for (int j = colMin; j <= colMax;j++){
                sum = sum + Integer.parseInt(collection.get(i).get(j));
            }
        }
        return sum;
    }

}
