package DataStructures.Tree.huffmantree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr = {13,7,8,3,29,6,1};
        Node root = createHuffmanTree(arr);

        //测试
        System.out.println("前序遍历赫夫曼树:");
        preOreder(root);
    }

    //编写一个前序遍历的方法
    public static void preOreder(Node root){
        if(root != null){
            root.preOrder();
        }else{
            System.out.println("空树不能遍历");
        }
    }

    //创建赫夫曼树的方法
    public static Node createHuffmanTree(int[] arr){
        //第一步：为了操作方便
        //1.遍历arr数组
        //2.将arr的每个元素构成一个Node
        //3.将Node放入到ArrayList中
        List<Node> nodes = new ArrayList<Node>();
        for(int value : arr){
            nodes.add(new Node(value));
        }

        //创建赫夫曼树循环过程
        while(nodes.size() > 1){
            //从小到大排序
            Collections.sort(nodes);

            //这行代码是为了分析思路，可删除
            System.out.println("nodes =" +nodes);

            //取出根节点权值最小的两颗二叉树
            //(1)取出权值最小的节点（二叉树）
            Node leftNode = nodes.get(0);
            //(2)取出权值第二小的节点（二叉树）
            Node rifhtNode = nodes.get(1);
            //(3)构建一颗新的二叉树
            Node parent = new Node(leftNode.value + rifhtNode.value);
            parent.left = leftNode;
            parent.right = rifhtNode;
            //(4)从nodes删除处理过的二叉树
            nodes.remove(leftNode);
            nodes.remove(rifhtNode);
            //(5)将parent加入到nodes
            nodes.add(parent);
        }

        //最后返回赫夫曼树的root节点
        return nodes.get(0);


    }

}

//创建节点类
//为了让Node对象支持排序Collections集合排序，让Node类实现Comparable接口
class Node implements Comparable<Node> {
    public int value;//权值
    public Node left;//指向左子节点
    public Node right;//指向右子节点

    public Node(int value){
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        //表示从小到大排序
        return this.value - o.value;
    }

    //前序遍历
    public void preOrder(){
        System.out.println(this);
        if(this.left != null){
            this.left.preOrder();
        }
        if(this.right != null){
            this.right.preOrder();
        }
    }

}
