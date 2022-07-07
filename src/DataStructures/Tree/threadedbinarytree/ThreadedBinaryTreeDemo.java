package DataStructures.Tree.threadedbinarytree;

public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        //测试一把中序线索二叉树的功能
        HeroNode root = new HeroNode(1,"Tom");
        HeroNode node2 = new HeroNode(3,"jack");
        HeroNode node3 = new HeroNode(6,"Smith");
        HeroNode node4 = new HeroNode(8,"Mary");
        HeroNode node5 = new HeroNode(10,"King");
        HeroNode node6 = new HeroNode(14,"Dim");

        //手动创建二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        //测试线索化
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.threadedNodes();

        //以10号节点进行测试
        HeroNode leftNode = node5.getLeft();
        HeroNode rightNode = node5.getRight();

        System.out.println(leftNode);
        System.out.println(rightNode);

        //中序遍历线索化二叉树
        System.out.println("中序遍历线索化二叉树:");
        threadedBinaryTree.threadedList();

    }
}




//ThreadedBinaryTree 线索二叉树
class ThreadedBinaryTree{
    private HeroNode root;

    //为了实现线索化，需要创建指向当前节点的前驱节点的指针
    private HeroNode pre = null;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    //重载方法
    public void threadedNodes(){
        this.threadedNodes(root);
    }

    //中序遍历线索化二叉树
    public void threadedList(){
        //定义一个变量，存储当前遍历的节点,从root开始
        HeroNode node = root;
        while(node != null){
            //循环得找到leftType == 1的节点
            while(node.getLeftType() == 0){
                node = node.getLeft();
            }

            System.out.println(node);

            //如果当前节点的右指针指向的是后继节点，就一直输出
            while(node.getRightType() == 1){//这里while改为if好像也可以
                //获取当前节点的后继节点
                node = node.getRight();
                System.out.println(node);
            }

            //替换遍历节点
            node = node.getRight();

        }
    }


    //编写对二叉树进行中序线索化
    //对传入的节点进行线索化
    public void threadedNodes(HeroNode node){
        if(node == null){
            return;
        }

        //(1)线索化左子树
        threadedNodes(node.getLeft());

        //(2)线索化当前节点[难]

        //处理当前节点的前驱节点
        if(node.getLeft() == null) {
            //让当前节点的左指针指向前驱节点
            node.setLeft(pre);
            //修改当前节点的左指针的类型,指向前驱节点
            node.setLeftType(1);
        }

        //处理后继节点
        if(pre != null && pre.getRight() == null){
            pre.setRight(node);
            pre.setRightType(1);
        }
        //！！！每处理一个节点后，让当前节点是下一个节点的前驱节点
        pre = node;


        //(3)线索化右子树
        threadedNodes(node.getRight());

    }

    //删除节点
    public void deleteNode(int no){
        if(root != null){
            //如果只有一个root节点，这里立即判断root是不是就是要删除的
            if(root.getNo() == no){
                root = null;
            }else{
                root.deleteNode(no);
            }
        }else{
            System.out.println("这是一个空树，不能删除");
        }
    }

    //前序遍历
    public void perOrder(){
        if(this.root != null){
            this.root.preOrder();
        }else{
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //中序遍历
    public void infixOrder(){
        if(this.root != null){
            this.root.infixOrder();
        }else{
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //后序遍历
    public void postOrder(){
        if(this.root != null){
            this.root.postOrder();
        }else{
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //前序遍历查找
    public HeroNode preOrderSearch(int no){
        if(root != null){
            return root.preOrderSearch(no);
        }else{
            return null;
        }
    }

    //中序遍历查找
    public HeroNode infixOrderSearch(int no){
        if(root != null){
            return root.infixOrderSearch(no);
        }else{
            return null;
        }
    }

    //后序遍历查找
    public HeroNode postOrderSearch(int no){
        if(root != null){
            return root.postOrderSearch(no);
        }else{
            return null;
        }
    }

}









//创建HeroNode节点
class HeroNode{
    private int no;
    private String name;
    private HeroNode left;//默认为null
    private HeroNode right;//默认为null

    //如果leftType为0表示指向的是左子树，为1则表示指向前驱节点
    //如果rightType为0表示指向的是右子树，为1则表示指向后继节点
    private int leftType;
    private int rightType;

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    //构造器
    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    //递归删除节点
    public void deleteNode(int no){

        //先判断左子节点是否要删除
        if(this.left != null && this.left.no == no){
            this.left = null;
            return;
        }

        //判断右子节点是否要删除
        if(this.right != null && this.right.no == no){
            this.right = null;
            return;
        }

        //向左子树递归删除
        if(this.left != null){
            this.left.deleteNode(no);
        }

        //向右子树递归删除
        if(this.right != null){
            this.right.deleteNode(no);
        }
    }


    //编写前序遍历的方法
    public void preOrder(){
        System.out.println(this);//先输出父节点
        //递归向左子树前序遍历
        if(this.left != null){
            this.left.preOrder();
        }
        //递归向右子树前序遍历
        if(this.right != null){
            this.right.preOrder();
        }
    }

    //编写中序遍历的方法
    public void infixOrder(){
        //递归向左子树中序遍历
        if(this.left != null){
            this.left.infixOrder();
        }

        //输出父节点
        System.out.println(this);

        //递归向右子树中序遍历
        if(this.right != null){
            this.right.infixOrder();
        }
    }

    //编写后序遍历的方法
    public void postOrder(){
        //递归向左子树后续遍历
        if(this.left != null){
            this.left.postOrder();
        }

        //递归向右子树后续遍历
        if(this.right != null){
            this.right.postOrder();
        }

        //输出父节点
        System.out.println(this);
    }

    //前序遍历查找
    public HeroNode preOrderSearch(int no){
        //比较当前节点是不是
        if(this.no == no){
            return this;
        }
        //则判断当前节点的子节点是否为空，不为空则递归前序查找
        //如果左递归前序查找找到节点则返回
        HeroNode resNode = null;
        if(this.left != null){
            resNode = this.left.preOrderSearch(no);
        }
        if(resNode != null){
            return resNode;
        }

        //左递归前序查找，找到结点，则返回，否继续判断，
        //当前的结点的右子节点是否为空，如果不空，则继续向右递归前序查找
        if(this.right != null){
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;
    }

    //中序遍历查找
    public HeroNode infixOrderSearch(int no){
        HeroNode resNode = null;
        if(this.left != null){
            resNode = this.left.infixOrderSearch(no);
        }
        if (resNode != null){
            return resNode;
        }
        if(this.no == no){
            return this;
        }

        if(this.right != null){
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;
    }

    //后续遍历查找
    public HeroNode postOrderSearch(int no){
        HeroNode resNode = null;
        if(this.left != null){
            resNode = this.left.postOrderSearch(no);
        }
        if(resNode != null){
            return resNode;
        }
        if(this.right != null){
            resNode = this.right.postOrderSearch(no);
        }
        if(resNode != null){
            return resNode;
        }
        if(this.no == no){
            return this;
        }
        return resNode;
    }


}
