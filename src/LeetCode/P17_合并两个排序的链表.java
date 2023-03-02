package LeetCode;

public class P17_合并两个排序的链表 {

    public static void main(String[] args) {
        ListNode node11 = new ListNode(1);
        ListNode node12 = new ListNode(2);
        ListNode node13 = new ListNode(4);
        node11.next = node12;
        node12.next = node13;
        node11.show();
        System.out.println();

        ListNode node21 = new ListNode(1);
        ListNode node22 = new ListNode(3);
        ListNode node23 = new ListNode(4);
        node21.next = node22;
        node22.next = node23;
        node21.show();
        System.out.println();

        ListNode listNode = mergeTwoLists(node11, node21);
        listNode.show();

    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = null;
        ListNode tail = null;
        if(l1 == null || l2 == null){
            if(l1 == null && l2 != null){
                return l2;
            }
            if(l2 == null && l1 != null){
                return l1;
            }
            return null;
        }
        while(l1 != null && l2 != null){
            if(l1.val <= l2.val){
                if(head == null){
                    head = tail = new ListNode(l1.val);
                }else{
                    tail.next = new ListNode(l1.val);
                    tail = tail.next;
                }
                l1 = l1.next;
            }else{
                if(head == null){
                    head = tail = new ListNode(l2.val);
                }else{
                    tail.next = new ListNode(l2.val);
                    tail = tail.next;
                }
                l2 = l2.next;
            }
        }
        if(l1 != null){
            tail.next = l1;
        }
        if(l2 != null){
            tail.next = l2;
        }
        return head;
    }

}
