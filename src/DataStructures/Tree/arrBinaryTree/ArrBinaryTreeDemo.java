package DataStructures.Tree.arrBinaryTree;

public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        arrBinaryTree.preOrder();

    }


}

//编写一个ArrBinaryTree，实现顺序存储二叉树
class ArrBinaryTree{
    private int[] arr;//存储数据节点的数组

    public ArrBinaryTree(int[] arr){
        this.arr = arr;
    }
    //重载preOrder
    public void preOrder(){
        this.preOrder(0);
    }

    //完成顺序存储二叉树的前序遍历
    //index表示数组的下标
    public void preOrder(int index){
        //如果数组为空，或者arr.length = 0
        if(arr == null || arr.length == 0){
            System.out.println("数组为空，不能按照二叉树的前序遍历");
        }
        System.out.println(arr[index]);
        if((2 * index + 1) < arr.length){
            preOrder(2 * index + 1);
        }
        if((2 * index + 2) < arr.length){
            preOrder(2 * index + 2);
        }
    }

}
