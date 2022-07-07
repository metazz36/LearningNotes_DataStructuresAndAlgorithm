package LeetCode;

//题目：剑指 Offer II 077. 链表排序
//难度：中等
/*题目描述：
给定链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 */
public class P7_链表排序{
    public static void main(String[] args) {

        Node node1 = new Node(-1);
        Node node2 = new Node(5);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(0);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node1.show();
        Node node = sortList(node1);
        System.out.println();
        System.out.println("排序后:");
        node.show();

    }

    public static Node sortList(Node head) {
        // 给 链表排序 动用归并排序思想
        // 把 链表分成两个差不多长度链表 分别排序后 合并
        // 将链表 拆分成 多个部分多次合并
        if(head == null || head.next == null){
            return head;
        }
        // 得到第一次分割
        Node head1 = head;
        Node head2 = split(head);

        // 将两个链表分别排序
        head1 = sortList(head1);
        head2 = sortList(head2);
        return merge(head1,head2);

    }


    // 将以 head 为头结点的链表分成两个差不多长度的链表 
    private static Node split(Node head){
        Node slow = head;
        Node fast = head;

        while(fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }// 当 fast == null 时  slow 就是 下一个链表的头 
        Node ans = slow.next;
        slow.next = null;
        return ans;
    }

    // 合并 两个有序链表 
    private static Node merge(Node l1,Node l2){
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        if(l1.val > l2.val){
            l2.next = merge(l1,l2.next);
            return l2;
        }
        l1.next = merge(l1.next,l2);
        return l1;
    }
    
}



class Node{
    int val;
    Node next;
    Node() {}
    Node(int val) { this.val = val; }
    Node(int val, Node next) { this.val = val; this.next = next; }

    public void show(){
        System.out.print(this.val + " ");
        if(this.next != null){
            this.next.show();
        }
    }
 }