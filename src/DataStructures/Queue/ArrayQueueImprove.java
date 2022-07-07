package DataStructures.Queue;


import java.util.Scanner;

//循环队列
//该队列的有效容量为最大容量-1，空出的一个位置用于判断队列是空还是满
public class ArrayQueueImprove {
    private int maxSize;//表示数组的最大容量
    private int front;//队头
    private int rear;//队尾
    private int[] arr;//该数组用于存放数据，模拟队列

    //创建队列的构造器
    public ArrayQueueImprove(int arrMaxSize){
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        //front和rear默认为0，不用初始化
    }

    //判断队列是否满
    public boolean isFull(){
        return (rear + 1 )% maxSize == front;
    }

    //判断队列是否为空
    public boolean isEmpty(){
        return rear == front;
    }

    //添加数据到队列
    public void addQueue(int n){
        //先判断队列是否满
        if(isFull()){
            System.out.println("队列已满");
            return;
        }
        arr[rear] = n;
        //rear后移，必须考虑取模
        rear = (rear+1)% maxSize;
    }

    //获取队列的数据，出队列
    public int getQueue(){
        //判断队列是否空
        if(isEmpty()){
            //通过抛出异常
            throw new RuntimeException("队列空，不能取数据");
        }
        //front是指向队列的第一个元素
        //1、先把front对应的值保留到一个临时变量
        //2、front后移
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    //求当前队列有效数据的个数
    public int size(){
        return (rear + maxSize - front) % maxSize;
    }

    //显示队列的所有数据
    public void show(){
        //遍历
        if(isEmpty()){
            System.out.println("队列空，没有数据");
            return;
        }
        //思路:从front开始遍历，遍历多少个元素
        for(int i = front ; i < front +size(); i++){
            System.out.printf("arr[%d]=%d\n",i % maxSize,arr[i % maxSize]);
        }
    }

    //显示队列的头数据,注意不是取出数据
    public int headQueue(){
        if(isEmpty()){
            throw new RuntimeException("队列空无数据");
        }
        return arr[front];
    }

    //测试
    public static void main(String[] args) {
        //创建一个队列
        ArrayQueueImprove arrayQueue = new ArrayQueueImprove(6);
        char key = ' ';//接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        //输出一个菜单
        while(loop){
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):退出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列取出数据");
            System.out.println("h(head):查看队列头的数据");
            key = scanner.next().charAt(0);//接收一个字符
            switch(key){
                case 's':
                    arrayQueue.show();
                    break;
                case 'a':
                    System.out.println("输入一个数");
                    int value = scanner.nextInt();
                    arrayQueue.addQueue(value);
                    break;
                case 'g':
                    try{
                        int res=arrayQueue.getQueue();
                        System.out.printf("取出的数据是%d\n",res);

                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h'://查看队列头的数据
                    try{
                        int res = arrayQueue.headQueue();
                        System.out.printf("队列头的数据是%d\n",res);
                        break;
                    }catch(Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出！");
    }
}
