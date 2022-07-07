package DataStructures.Tree.avl;


public class AVLTreeDemo {
    public static void main(String[] args) {
//        int[] arr = {4,3,6,5,7,8}; //测试左旋转
//        int[] arr={10,12,8,9,7,6}; //测试右旋转
        int[] arr = {10,11,7,6,8,9}; //测试双旋转
        AVLTree avlTree = new AVLTree();
        //循环的添加节点到二叉排序树
        for(int i = 0;i < arr.length ; i++){
            avlTree.add(new Node(arr[i]));
        }
        System.out.println("中序遍历平衡二叉树");
        avlTree.infixOrder();
        System.out.println("在平衡处理后:");
        System.out.println("树的高度 = " + avlTree.getRoot().height());
        System.out.println("树的左子树的高度 = " + avlTree.getRoot().left.height());
        System.out.println("树的右子树的高度 = " + avlTree.getRoot().right.height());
        System.out.println("当前根节点 = " +avlTree.getRoot());
        System.out.println("当前根节点的左子节点 = " +avlTree.getRoot().left);
        System.out.println("当前根节点的右子节点 = " +avlTree.getRoot().right);

    }
}



//创建AVLTree
class AVLTree{
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

    //返回左子树的高度
    public int leftHeight(){
        if(left == null){
            return 0;
        }
        return left.height();
    }

    //返回右子树的高度
    public int rightHeight(){
        if(right == null){
            return 0;
        }
        return right.height();
    }

    //返回以该节点为根节点的树的高度
    public int height(){
        return Math.max(left == null ? 0 : left.height() , right == null ? 0 : right.height()) + 1;
    }

    //左旋转方法
    public void leftRotate(){
        //1.创建一个新的节点newNode,值等于当前根节点的值
        Node newNode = new Node(value);
        //2.把新节点的左子树设置了当前节点的左子树
        newNode.left = left;
        //3.把新节点的右子树设置为当前节点的右子树的左子树
        newNode.right = right.left;
        //4.把当前节点的值换为右子节点的值
        value = right.value;
        //5.把当前节点的右子树设置成右子树的右子树
        right = right.right;
        //6.把当前节点的左子树设置为新节点
        left = newNode;
    }

    //右旋转方法
    public void rightRotate(){
        //1.创建一个新的节点newNode,值等于当前根节点的值
        Node newNode = new Node(value);
        //2.把新节点的右子树设置了当前节点的右子树
        newNode.right = right;
        //3.把新节点的左子树设置为当前节点的左子树的右子树
        newNode.left = left.right;
        //4.把当前节点的值换为左子节点的值
        value = left.value;
        //5.把当前节点的左子树设置成左子树的左子树
        left = left.left;
        //6.把当前节点的右子树设置为新节点
        right = newNode;
    }


    //向当前节点左子树递归寻找值最小的节点(涉及排序二叉树的性质)
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

        //当添加完一个节点后，如果（右子树的高度 - 左子树的高度）> 1 ,左旋转
        if(rightHeight() - leftHeight() > 1){
            //如果它的右子树的左子树高度大于它的右子树的右子树的高度
            if(right != null && right.leftHeight() > right.rightHeight()){
                //先对当前节点的右子树进行右旋转
                right.rightRotate();
                //再对当前节点进行左旋转
                leftRotate();
            }else{
                leftRotate();//直接进行左旋转即可
            }

            return;//必须要！！！

        }

        //当添加完一个节点后，如果（左子树的高度 - 右子树的高度）> 1 ,右旋转
        if(leftHeight() - rightHeight() > 1){
            //如果它的左子树的右子树高度大于它的左子树的左子树的高度
            if(left != null && left.rightHeight() > left.leftHeight()){
                //先对当前节点的左子树进行左旋转
                left.leftRotate();
                //再对当前节点进行右旋转
                rightRotate();
            }else{
                //直接进行右旋转即可
                rightRotate();
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
