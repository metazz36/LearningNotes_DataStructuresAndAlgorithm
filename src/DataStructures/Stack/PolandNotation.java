package DataStructures.Stack;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
    //测试1
    public static void main(String[] args) {
        //定义一个逆波兰表达式
        //(30+4)*5-6 --> 30 4 + 5 * 6 -
        //为了方便，逆波兰表达式的数字和符号使用空格隔开
        String suffixExpression = "30 4 + 5 * 6 - ";
        //思路
        //1.先将suffixExpression放到ArrayList中
        //2.将ArrayList传递给一个方法，方法中遍历ArrayList并配合栈完成计算

        List<String> list = getListString(suffixExpression);
        System.out.println(list);

        int res = calculate(list);
        System.out.println(res);
    }

    //测试2
    @Test
    public void test01(){
        //将中缀表达式转成对应的List
        String suffixExpression = "1+((2+3)*4)-5";
        List<String> InfixExpressionList = toInfixExpressionList(suffixExpression);
        System.out.println("中缀表达式：" + InfixExpressionList);
        List<String> suffixExpressionList = parseSuffixExpressionList(InfixExpressionList);
        System.out.println("后缀表达式：" + suffixExpressionList);
        System.out.println("运算结果为" + calculate(suffixExpressionList) );

    }

    //将中缀表达式转成后缀表达式
    public static List<String> parseSuffixExpressionList(List<String> ls){
        //定义两个栈（）
        //因为s2这个栈，在整个过程中，并没有pop操作且最后还需要用他逆序输出，所以不如用ArrayList
        Stack<String> s1 = new Stack<String>();//符号栈
        List<String> s2 = new ArrayList<String>();//储存中间结果

        //遍历ls
        for(String item : ls){
            //如果是一个数，入栈
            if(item.matches("\\d+")){
                s2.add(item);
            }else if(item.equals("(")){
                s1.push(item);
            }else if(item.equals(")")){
                //如果是右括号，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
                while(!s1.peek().equals("(")){
                    s2.add(s1.pop());
                }
                s1.pop();//将 ( 弹出s1栈，消除这对括号
            }else{
                //当item的优先级小于等于s1栈顶优先级，将s1栈顶的运算符弹出并加入到s2中，再次转到(4.1)与s1中新的栈顶运算符相比较
                while(s1.size()!=0 && Operation.getValue(s1.peek()) >= Operation.getValue(item) ){
                    s2.add(s1.pop());
                }
                //还需要将item压入栈顶
                s1.push(item);
            }
        }

        //将s1中剩余的运算符依次弹出并加到s2
        while(s1.size()!=0){
            s2.add(s1.pop());

        }

        return s2;//因为是存放到List，因此按顺序输出就是对栈的逆序输出

    }

    //将中缀表达式转成对应的List
    public static List<String> toInfixExpressionList(String s){
        //定义一个List
        List<String> ls = new ArrayList<String>();
        int i =0;//这是一个指针，用于遍历中缀表达式字符串
        String str;//对多位数的拼接
        char c;//每遍历到一个字符，放入c
        do{
            //如果c是非数字，就需要加入到ls
            if((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57){
                ls.add("" + c);
                i++;
            }else{//如果c是数字,需要考虑多位数的问题
                str = "";//先将str置成空串
                while(i < s.length() && (c=s.charAt(i)) >= 48 && (c=s.charAt(i)) <= 57){
                    str += c;//拼接
                    i++;
                }
                ls.add(str);
            }
        }while(i < s.length());
        return ls;

    }
    //将一个逆波兰表达式，依次将数据和运算符放入到ArrayList中
    public static List<String> getListString(String suffixExpression){
        //将suffixExpression分割
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<String>();
        for(String ele : split){
            list.add(ele);
        }
        return list;
    }

    //完成对逆波兰表达式的运算
  /*  1．从左至右扫描，将3和4压入堆栈;
    2．遇到+运算符，因此弹出4和3(4为栈顶元素，3为次顶元素)，计算出3+4的值，得7，再将7入栈;
    3．将5入栈;
    4．接下来是×运算符，因此弹出5和7，计算出7×5=35，将35入栈;
    5．将6入栈;
    6．最后是-运算符，计算出35-6的值，即29，由此得出最终结果*/
    public static int calculate(List<String> ls){
        //创建栈，只需要一个栈
        Stack<String> stack = new Stack<String>();
        //遍历ls
        for(String item : ls){
            //使用正则表达式来取出数
            if(item.matches("\\d+")){//匹配的是多位数
                //入栈
                stack.push(item);
            }else{
                //pop出两个数并运算
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if(item.equals("+")){
                    res = num1 + num2 ;
                }else if(item.equals("-")){
                    res = num1 - num2 ;
                }else if(item.equals("*")){
                    res =num1 * num2;
                }else if(item.equals("/")){
                    res = num1 / num2;
                }else{
                    throw new RuntimeException("运算符有误");
                }
                //把res入栈
                stack.push(res + "");
            }
        }
        //最后留在stack中的数据就是运算结果
        return Integer.parseInt(stack.pop());
    }
}

//编写一个类Operation，可以返回一个运算符对应优先级
class Operation{
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    //写一个方法，返回对应的优先级数字
    public static int getValue(String operaton){
        int result = 0;
        switch(operaton){
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                break;
        }
        return result;
    }
}