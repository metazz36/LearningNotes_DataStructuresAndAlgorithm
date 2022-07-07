package DataStructures.Stack;

import javax.xml.soap.SOAPElementFactory;
import javax.xml.transform.Source;
import java.util.Scanner;

public class ArrayStackDemo {
    //测试
    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(4);
        String key = "";
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);
        while(loop){
            System.out.println("show:显示栈");
            System.out.println("exit:退出程序");
            System.out.println("push:添加数据到栈");
            System.out.println("pop:从栈取出数据");
            System.out.println("请输入你的选择");
            key = scanner.next();
            switch(key){
                case "show":
                    stack.list();
                    break;
                case "push":
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try{
                        int res = stack.pop();
                        System.out.printf("出栈的数据是%d\n",res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;

            }
        }
        System.out.println("程序退出");
    }
}

//定义一个栈
class ArrayStack{
    private int maxSize;//栈的大小
    private int[] stack;//数组模拟栈
    private int top = -1; //栈顶，初始化为-1

    public ArrayStack(int maxSize){
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    //栈满
    public boolean isFull(){
        return top == maxSize-1;
    }

    //栈空
    public boolean isEmpty(){
        return top == -1;
    }

    //入栈
    public void push(int value){
        //先判断栈是否满
        if(isFull()){
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }

    //出栈
    public int pop(){
        if(isEmpty()){
            //抛出异常
            throw new RuntimeException("栈空");
        }
        int value = stack[top];
        top--;
        return value;
    }

    //遍历栈
    public void list(){
        //遍历时需要从栈顶开始显示数据
        if(isEmpty()){
            System.out.println("栈空");
            return;
        }
        for(int i = top; i >= 0 ;i--){
            System.out.printf("stack[%d] = %d\n",i,stack[i]);
        }
    }
}
