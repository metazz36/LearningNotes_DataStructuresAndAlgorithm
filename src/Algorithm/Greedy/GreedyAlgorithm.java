package Algorithm.Greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class GreedyAlgorithm {
    public static void main(String[] args) {
        //创建广播电台，放入到Map中
        HashMap<String, HashSet<String>> broadcasts = new HashMap<>();

        //将各个电台放入到broadcasts
        HashSet<String> hashSet1 = new HashSet<>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");

        HashSet<String> hashSet2 = new HashSet<>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");

        HashSet<String> hashSet3 = new HashSet<>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");

        HashSet<String> hashSet4 = new HashSet<>();
        hashSet4.add("上海");
        hashSet4.add("天津");

        HashSet<String> hashSet5 = new HashSet<>();
        hashSet5.add("杭州");
        hashSet5.add("大连");

        //加入到Map
        broadcasts.put("K1",hashSet1);
        broadcasts.put("K2",hashSet2);
        broadcasts.put("K3",hashSet3);
        broadcasts.put("K4",hashSet4);
        broadcasts.put("K5",hashSet5);

        //allAreas存放所有地区
        HashSet<String> allAreas = new HashSet<>();
        allAreas.add("北京");
        allAreas.add("上海");
        allAreas.add("天津");
        allAreas.add("广州");
        allAreas.add("深圳");
        allAreas.add("成都");
        allAreas.add("杭州");
        allAreas.add("大连");
        
        //创建ArrayList，存放选择的电台集合
        ArrayList<String> selects = new ArrayList<>();

        //定义一个临时的集合，在遍历的过程中，存放遍历过程中的电台覆盖的地区和当前还没有覆盖的地区的交集
        HashSet<String> tempSet = new HashSet<>();

        //定义一个maxKey，保存在一次遍历过程中，能够覆盖最多未覆盖地区对应的电台的key
        //如果maxKey不为空，则会加入到selects
        String maxKey = null;
        while(allAreas.size() != 0){
            //每进行一次循环，需要将maxkey置空
            maxKey = null;
            //遍历broadcasts,取出对应的key
            for(String key : broadcasts.keySet()){
                //每进行一次for，需要将tempSet清空
                tempSet.clear();
                HashSet<String> areas = broadcasts.get(key);//当前key能够覆盖的地区
                tempSet.addAll(areas);
                //求出tempSet和allAreas的交集,交集会赋给tempSet
                tempSet.retainAll(allAreas);
                //如果当前tempSet包含的未覆盖地区的数量，比maxKey指向的集合地区还多
                //就需要重置maxKey
                if(tempSet.size() > 0 && (maxKey == null || tempSet.size() > broadcasts.get(maxKey).size())){
                    maxKey = key;
                }
            }
            if(maxKey != null){
                selects.add(maxKey);
                allAreas.removeAll(broadcasts.get(maxKey));
            }
        }
        System.out.println("得到选择的结果是：" + selects );
    }
}
