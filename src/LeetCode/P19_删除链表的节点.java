package LeetCode;

public class P19_删除链表的节点 {
    public static void main(String[] args) {
        ListNode node11 = new ListNode(1);
        ListNode node12 = new ListNode(2);
        ListNode node13 = new ListNode(4);
        node11.next = node12;
        node12.next = node13;
        node11.show();
        System.out.println();
        ListNode listNode = deleteNode(node11, 4);
        listNode.show();

    }

    public static ListNode deleteNode(ListNode head, int val) {
        ListNode p = head;
        if(p == null){
            return null;
        }
        if(p.val == val){
            head = p.next;
            return head;
        }
        while(p.next != null){
            if(p.next.val == val){
                p.next = p.next.next;
                break;
            }
            p = p.next;
        }
        return head;
    }
}
