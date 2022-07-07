package DataStructures.Tree.BinarySortTree;

public class BinarySortTreeDemo{
    public static void main(String[] args) {
        int[] arr = {7,3,10,12,5,1,9,2};
        BinarySortTree binarySortTree = new BinarySortTree();
        //循环的添加节点到二叉排序树
        for(int i = 0;i < arr.length ; i++){
            binarySortTree.add(new Node(arr[i]));
        }
        System.out.println("中序遍历二叉排序树");
        binarySortTree.infixOrder();

        binarySortTree.deleteNode(2);
        binarySortTree.deleteNode(5);
        binarySortTree.deleteNode(9);
        binarySortTree.deleteNode(12);
        binarySortTree.deleteNode(7);
        binarySortTree.deleteNode(3);
        binarySortTree.deleteNode(1);
        binarySortTree.deleteNode(10);
        System.out.println("删除操作后:");
        binarySortTree.infixOrder();
        System.out.println("root = " + binarySortTree.getRoot());

    }
}




//创建二叉排序树
class BinarySortTree{
    private Node root;

    public Node getRoot() {
        return root;
    }

    //查找要删除的节点
    public Node search(int value){
        if(root == null){
            return null;
        }else{
            return root.search(value);
        }
    }

    //查找要删除的节点的父节点
    public Node searchParent(int value){
        if(root == null){
            return null;
        }else{
            return root.searchParent(value);
        }
    }

    //node传入的节点（当前二叉排序树的根节点）
    //返回的是以node为根节点的二叉排序树最小节点的值
    public int delRightTreeMin(Node node){
        Node target = node;
        //循环的查找左节点，就会找到最小值
        while(target.left != null){
            target = target.left;
        }
        //这时target就指向了最小节点
        deleteNode(target.value);
        return target.value;
    }

    //删除节点
    public void deleteNode(int value){
        if(root == null){
            return;
        }else{
            //先找到要删除的节点targetNode
            Node targetNode = search(value);
            if(targetNode == null){
                return;
            }
            //如果发现当前这颗二叉排序树只有一个节点
            if(root.left == null && root.right == null){
                root = null;
                return;
            }
            //找到删除节点的父节点
            Node parent = searchParent(value);
            //如果删除的节点是叶子节点
            if(targetNode.left == null &&targetNode.right == null){
                //判断target是parent的左子节点还是右子节点
                if(parent.left != null && parent.left.value == value){//target是parent的左子节点
                    parent.left = null;
                }else if(parent.right != null && parent.right.value == value){//target是parent的右子节点
                    parent.right = null;
                }
            }else if(targetNode.left != null && targetNode.right != null){//删除的节点既有左子树又有右子树

                //我的代码
//                Node MixNode = targetNode.right.searchRightMix();
//                deleteNode(MixNode.value);
//                targetNode.value = MixNode.value;

                int min = delRightTreeMin(targetNode.right);
                targetNode.value = min;
            }else{ //删除的节点只有左子树或右子树
                // 如果要删除的节点只有左子树
                if(targetNode.left != null){
                    if(parent != null) {
                        //如果targetNode是parent的左子节点
                        if (parent.left.value == value) {
                            parent.left = targetNode.left;
                        } else {//targetNode是parent的右子节点
                            parent.right = targetNode.left;
                        }
                    }else{
                        root = targetNode.left;
                    }
                }else{//要删除的节点只有右子树
                    if(parent != null){
                        //如果targetNode是parent的左子节点
                        if(parent.left.value == value){
                            parent.left = targetNode.right;
                        }else{ //targetNode是parent的右子节点
                            parent.right = targetNode.right;
                        }
                    }else{
                        root = targetNode.right;
                    }


                }
            }
        }
    }

    //添加节点的方法
    public void add(Node node){
        if(root == null){
            root = node;
        }else{
            root.add(node);
        }
    }

    //中序遍历方法
    public void infixOrder(){
        if(root != null){
            root.infixOrder();
        }else{
            System.out.println("树为空，无法遍历");
        }
    }
}







//节点类
class Node{
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    //向当前节点左子树递归寻找值最小的节点(设计排序二叉树的性质)
    public Node searchRightMix(){
        if(this.left != null){
           return this.left.searchRightMix();
        }else{
            return this;
        }
    }


    //查找要删除的节点
    public Node search(int value){
        if(value == this.value){ //找到
            return this;
        }else if(value < this.value){ //向左子树递归查找
            if (this.left == null) {
                return null;
            }else{
                return this.left.search(value);
            }

        }else{ //向右子树递归查找
            if(this.right == null){
                return null;
            }else{
                return this.right.search(value);
            }
        }
    }

    //查找要删除节点的父节点
    public Node searchParent(int value){
        if((this.left != null && this.left.value == value) ||(this.right != null && this.right.value == value)){
            return this;
        }else{
            //如果查找的值小于当前节点的值，并且当前节点的左子节点不为空
            if(value < this.value && this.left != null){
                //向左子树递归查找
                return this.left.searchParent(value);
            }else if(value >= this.value && this.right != null){
                //向右子树递归查找
                return this.right.searchParent(value);
            }else{
                return null;//没有找到父节点
            }
        }
    }


    //添加节点
    //递归的形式添加节点，需要满足二叉排序树的要求
    public void add(Node node){
        if(node == null){
            return;
        }
        //判断传入节点的值，和当前子树的根节点的值的关系
        if(node.value < this.value){
            if(this.left == null){
                this.left = node;
            }else{
                this.left.add(node);//递归的向左子树添加
            }
        }else{
            if(this.right == null){
                this.right = node;
            }else{
                this.right.add(node);//递归的向右子树添加
            }
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    //中序遍历
    public void infixOrder(){
        if(this.left != null){
            this.left.infixOrder();
        }
        System.out.println(this);
        if(this.right != null){
            this.right.infixOrder();
        }
    }

}
