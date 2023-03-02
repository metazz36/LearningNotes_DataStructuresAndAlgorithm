package LeetCode;

import java.util.ArrayDeque;
import java.util.Deque;

public class P16_用两个栈实现队列 {
    public static void main(String[] args) {
        P16_用两个栈实现队列 a = new P16_用两个栈实现队列();
        a.appendTail(1);
        a.appendTail(2);
        a.appendTail(3);
        System.out.println(a.deleteHead());
        System.out.println(a.deleteHead());
        System.out.println(a.deleteHead());
        System.out.println(a.deleteHead());
    }

    private Deque<Integer> stack1;
    private Deque<Integer> stack2;
    private int size;
    private int record;

    public P16_用两个栈实现队列() {
        stack1 = new ArrayDeque<Integer>();
        stack2 = new ArrayDeque<Integer>();
        size = 0;
        record = 0;
    }

    public void appendTail(int value) {
        stack1.push(value);
        size ++;
    }

//    public int deleteHead() {
//        int length = size;
//        if(length == 0){
//            return -1;
//        }
//        while(length != 1){
//            stack2.push(stack1.pop());
//            length--;
//        }
//        record = stack1.pop();
//        while(!stack2.isEmpty()){
//            stack1.push(stack2.pop());
//        }
//        size--;
//        return record;
//    }

    public int deleteHead() {
        if(size == 0){
            return -1;
        }
        if(stack2.isEmpty()){
            while(!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
        }
        size --;
        return stack2.pop();
    }


}
