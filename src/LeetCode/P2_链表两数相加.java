package LeetCode;

//题目：两数相加
//难度：中等
/*题目描述：
给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
请你将两个数相加，并以相同形式返回一个表示和的链表。
你可以假设除了数字 0 之外，这两个数都不会以 0 开头。

*/
public class P2_链表两数相加{
    public static void main(String[] args) {
        ListNode node11 = new ListNode(2);
        ListNode node12 = new ListNode(4);
        ListNode node13 = new ListNode(3);
        node11.next = node12;
        node12.next = node13;
        ListNode node21 = new ListNode(5);
        ListNode node22 = new ListNode(6);
        ListNode node23 = new ListNode(4);
        node21.next = node22;
        node22.next = node23;
        ListNode list = addTwoNumbers(node11, node21);
        list.show();//7 0 8

    }

    //具体方法
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null, tail = null;
        int carry = 0;//存储进位值
        while (l1 != null || l2 != null) {
            int n1 = l1 != null ? l1.val : 0;
            int n2 = l2 != null ? l2.val : 0;
            int sum = n1 + n2 + carry;
            if (head == null) {
                head = tail = new ListNode(sum % 10);
            } else {
                tail.next = new ListNode(sum % 10);
                tail = tail.next;
            }
            carry = sum / 10;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }

        //重要！容易遗忘！
        if (carry > 0) {
            tail.next = new ListNode(carry);
        }


        return head;
    }
}

class ListNode {
     int val;
     ListNode next;
     ListNode(int val) { this.val = val;
     }

     public void show(){
         System.out.print(this.val + " ");
         if(this.next != null){
             this.next.show();
         }
     }
}
