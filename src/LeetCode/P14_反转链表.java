package LeetCode;

//难度：简单
// 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
public class P14_反转链表 {
    public static void main(String[] args) {
        ListNode n1 = new ListNode(5);
        ListNode n2 = new ListNode(4);
        ListNode n3 = new ListNode(3);
        n1.next = n2;
        n2.next = n3;
        n1.show();
        System.out.println();
        ListNode listNode = reverseList(n1);
        listNode.show();
        
    }

    // 双指针
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


}

