package DataStructures.Stack;

import java.util.Scanner;

public class LinkedListStackDemo {
    //测试
    public static void main(String[] args) {
        LinkedListStack linkedListStack = new LinkedListStack(5);
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);
        String key = "";
        while(loop){
            System.out.println("_____________________________");
            System.out.println("请输入你的选择：");
            System.out.println("push入栈");
            System.out.println("pop出栈");
            System.out.println("show遍历");
            System.out.println("exit退出");
            key = scanner.next();
            switch(key){
                case "push":
                    System.out.println("请输入一个数字:");
                    int value = scanner.nextInt();
                    linkedListStack.push(value);
                    break;
                case "pop":
                    linkedListStack.pop();
                    break;
                case "show":
                    linkedListStack.show();
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("退出程序");
    }
}

//创建用双向链表实现的栈
class LinkedListStack{
    private int maxsize;//栈最大容量
    private node head = new node(0);//头节点
    private node top = head;//用于指向栈顶
    int count = 0;//记录top移动的位置,同时也能记录栈中数据的个数

    //构造器
    public LinkedListStack(int maxsize){
        this.maxsize = maxsize;
    }

    //栈满
    //true为满
    public boolean isFull(){
        if(count == maxsize){
            return true;
        }
        return false;
    }

    //栈空
    //true为空
    public boolean isEmpty(){
        if(count == 0){
            return true;
        }
        return false;
    }

    //入栈
    public void push(int id){
        if(isFull()){
            System.out.println("栈满");
            return;
        }
        node newnode = new node(id);
        top.next = newnode;
        newnode.pro = top;
        top = newnode;
        count++;
    }

    //出栈
    public void pop(){
        if(isEmpty()){
            System.out.println("栈空");
            return;
        }
        System.out.printf("出栈的数据为%d\n",top.id);
        top = top.pro;
        top.next.pro = null;
        top.next = null;
        count--;
    }

    //遍历栈
    public void show(){
        if(isEmpty()){
            System.out.println("栈空，没有数据");
            return;
        }
        node temp = top;//辅助遍历
        for(int i = count;i > 0 ;i--){
            System.out.printf("[%d] =%d\n",i ,temp.id);
            temp = temp.pro;
        }
    }
}

//创建双向链表节点
class node{
    public int id;//存储编号数据
    public node next;//指向下一节点
    public node pro;//指向上一节点
    //构造器
    public node(int id){
        this.id = id;
    }
}
