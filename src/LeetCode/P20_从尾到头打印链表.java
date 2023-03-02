package LeetCode;

import java.util.ArrayDeque;
import java.util.Deque;

public class P20_从尾到头打印链表 {

    public static void main(String[] args) {
        ListNode node11 = new ListNode(1);
        ListNode node12 = new ListNode(2);
        ListNode node13 = new ListNode(4);
        node11.next = node12;
        node12.next = node13;
        node11.show();
        System.out.println();
        int[] ints = reversePrint(node11);
        for (int anInt : ints) {
            System.out.println(anInt);
        }


    }

    public static int[] reversePrint(ListNode head) {
        Deque<Integer> stack = new ArrayDeque<Integer>();
        while(head != null){
            stack.push(head.val);
            head = head.next;
        }
        int[] res = new int[stack.size()];
        for(int i = 0;i < res.length;i++){
            res[i] = stack.pop();
        }
        return res;
    }

}
