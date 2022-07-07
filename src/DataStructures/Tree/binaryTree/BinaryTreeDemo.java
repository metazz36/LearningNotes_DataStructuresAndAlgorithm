package DataStructures.Tree.binaryTree;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        //创建一颗二叉树
        BinaryTree binaryTree = new BinaryTree();
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");

        //先手动创建该二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        binaryTree.setRoot(root);
//
//        //测试
//        System.out.println("前序遍历:");
//        binaryTree.perOrder();
//
//        System.out.println("中序遍历:");
//        binaryTree.infixOrder();
//
//        System.out.println("后序遍历:");
//        binaryTree.postOrder();
//
//        //前序遍历查找
//        System.out.println("前序遍历查找方式：");
//        HeroNode resnode = binaryTree.preOrderSearch(5);
//        if(resnode != null){
//            System.out.println("找到了 " + resnode);
//        }else{
//            System.out.println("没找到");
//        }

        //删除节点
        System.out.println("删除前，前序遍历");
        binaryTree.perOrder();
        binaryTree.deleteNode(3);
        System.out.println("删除后，前序遍历");
        binaryTree.perOrder();


    }
    
    
}

//定义一个BinaryTree 二叉树
class BinaryTree{
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
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
