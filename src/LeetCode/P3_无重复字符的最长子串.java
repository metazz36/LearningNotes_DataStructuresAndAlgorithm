package LeetCode;

import java.util.HashMap;

//题目：无重复字符的最长子串
//难度：中等
/*题目描述：给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
*/
public class P3_无重复字符的最长子串 {

    public static void main(String[] args) {
        String s ="abba";
        int i = lengthOfLongestSubstring(s);
        System.out.println(i);
    }

    public static int lengthOfLongestSubstring(String s) {
        if(s.length() == 0){
            return 0;
        }
        HashMap<Character, Integer> map = new HashMap<>();
        int max = 0;//用于记录最大不重复子串的长度
        int left = 0;//滑动窗口左下标，i相当于滑动窗口右下标
        for(int i = 0 ; i < s.length(); i++){
            if(map.containsKey(s.charAt(i))){
//                left = Math.max(left,map.get(s.charAt(i))+1); //map.get():返回字符所对应的索引，当发现重复元素时，窗口左指针右移
                left = map.get(s.charAt(i))+1;
            }
            System.out.println(left);

            map.put(s.charAt(i),i);
            max = Math.max(max,i-left+1);
        }
        System.out.println(map.toString());
        return max;
    }
}
