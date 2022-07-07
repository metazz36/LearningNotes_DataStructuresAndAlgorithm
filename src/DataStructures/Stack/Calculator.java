package DataStructures.Stack;

//此代码是有问题的，当expression连着的-号中间出现*会出现计算错误
public class Calculator {

    public static void main(String[] args) {
        String expression = "100-10-10-10-3*10-10-10";
        //创建两个栈，数栈和符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        //定义需要的相关变量
        int index = 0;//用于扫描
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' ';//将每次扫描得到的char保存到ch
        String keepNum = "";//用于拼接多位数
        //开始while循环的扫描expression
        while(true){
            //依次得到expression的每一个字符
            ch = expression.substring(index,index + 1).charAt(0);
            //判断ch是什么，然后做相应的处理
            if(operStack.isOper(ch)) { //如果是运算符
                //判断当前的符号栈是否为空
                if(!operStack.isEmpty()){
                    //符号栈有操作符，就进行比较，如果当前操作符的优先级小于或者等于栈中的操作符，就需要从数栈pop出两个数
                    //再从符号栈中pop出一个符号，进行运算，将得到的结果push数栈，然后将当前的操作符push符号栈
                    if(operStack.priority(ch) <= operStack.priority(operStack.peek())){
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = numStack.cal(num1,num2,oper);
                        //把运算结果push数栈
                        numStack.push(res);
                        //将当前的操作符push符号栈
                        operStack.push(ch);
                    }else{
                        //如果当前操作符的优先级大于栈中的操作符，直接push符号栈
                        operStack.push(ch);
                    }
                }else{
                    //为空直接入栈
                    operStack.push(ch);
                }
            }else{
                //如果是数，则push数栈
                keepNum += ch;
                //如果ch已经是expression的最后一位，就直接入栈
                if(index == expression.length() -1 ){
                    numStack.push(Integer.parseInt(keepNum));
                }else{
                    //判断下一个字符是不是数字，如果是数字，就继续扫描，如果是运算符则入栈

                    if(operStack.isOper(expression.substring(index+1,index+2).charAt(0))){
                        //如果后一位是运算符，入栈
                        numStack.push(Integer.parseInt(keepNum));
                        //清空keepNum
                        keepNum = "";
                    }
                }

            }
            //让index+1并判断是否扫描到最后
            index++;
            if(index >= expression.length()){
                break;
            }
        }

        //当表达式扫描完毕，就顺序的从数栈和符号栈中pop出相应的数和符号。并运算
        while(true){
            //如果符号栈为空，则计算到最后的结果，数栈中只有一个数字，即为结果
            if(operStack.isEmpty()){
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            oper = operStack.pop();
            res = numStack.cal(num1,num2,oper);
            numStack.push(res);
        }
        //将数栈最后的数pop，就是结果
        System.out.printf("表达式%s = %d",expression,numStack.pop());
    }
}

//先创建一个栈
//需要扩展功能
class ArrayStack2{
    private int maxSize;//栈的大小
    private int[] stack;//数组模拟栈
    private int top = -1; //栈顶，初始化为-1

    public ArrayStack2(int maxSize){
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }


    //增加一个方法，可以返回当前栈顶的值，但不是真正的pop
    public int peek(){
        return stack[top];

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

    //返回运算符的优先级,优先级是程序员来确定的，优先级使用数字来表示
    public int priority(int oper){
        if(oper == '*'||oper =='/'){
            return 1;
        }else if(oper == '+'|| oper =='-'){
            return 0;
        }
        else{
            return -1;
        }
    }

    //判断是不是一个运算符
    public  boolean isOper(char val){
        return val == '+' || val == '-' ||  val == '*' ||  val == '/';
    }

    //计算方法
    public int cal(int num1,int num2,int oper){
        int res = 0;//用于存放计算结果
        switch(oper){
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1; //注意顺序
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;//注意顺序
                break;
        }
        return res;
    }


}
