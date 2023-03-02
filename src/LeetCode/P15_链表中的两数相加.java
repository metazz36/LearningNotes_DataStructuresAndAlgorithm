package LeetCode;

/*
难度：中等
题目描述：
给定两个非空链表l1和l2来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
可以假设除了数字 0 之外，这两个数字都不会以零开头。
*/

public class P15_链表中的两数相加 {
    public static void main(String[] args) {
        ListNode node11 = new ListNode(7);
        ListNode node12 = new ListNode(2);
        ListNode node13 = new ListNode(4);
        ListNode node14 = new ListNode(3);
        node11.next = node12;
        node12.next = node13;
        node13.next = node14;
        ListNode node21 = new ListNode(5);
        ListNode node22 = new ListNode(6);
        ListNode node23 = new ListNode(4);
        node21.next = node22;
        node22.next = node23;
        ListNode list = addTwoNumbers(node11, node21);
        list.show();
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode l11 = reverseList(l1);
        ListNode l22 = reverseList(l2);
        return reverseList(addTwoNumber(l11, l22));
    }

    public static ListNode reverseList(ListNode head) {
        ListNode cur = null;
        ListNode pre = head;
        while(pre != null){
            ListNode t = pre.next;
            pre.next = cur;
            cur = pre;
            pre = t;
        }
        return cur;
    }

    public static ListNode addTwoNumber(ListNode l1, ListNode l2) {
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
