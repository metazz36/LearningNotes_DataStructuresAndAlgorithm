package LeetCode;

import java.util.ArrayList;

public class P21_复杂链表的复制 {

    public static void main(String[] args) {
        Node1 node1 = new Node1(1);
        Node1 node2 = new Node1(2);
        Node1 node3 = new Node1(3);
        node1.next = node2;
        node2.next = node3;
        Node1 node11 = copyRandomList(node1);
        node11.show();

    }

    public static Node1 copyRandomList(Node1 head) {
        if(head==null) return null;
        ArrayList<Node1> list = new ArrayList<>();
        while(head!=null){
            list.add(head);
            head = head.next;
        }
        ArrayList<Node1> res = new ArrayList<>();
        for(int i = 0;i<list.size();++i){
            res.add(new Node1(list.get(i).val));
        }
        for (int i = 0; i < list.size(); ++i) {
            res.get(i).random = list.get(i).random == null?null:res.get(list.indexOf(list.get(i).random));
            if(i!=list.size()-1) res.get(i).next = res.get(i+1);
        }
        return res.get(0);
    }

}

class Node1 {
    int val;
    Node1 next;
    Node1 random;

    public Node1(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }

    public void show(){
        System.out.print(this.val + " ");
        if(this.next != null){
            this.next.show();
        }
    }
}
