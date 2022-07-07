package DataStructures.hashtab;

import java.util.Scanner;

public class HashTabDemo {

    public static void main(String[] args) {

        //创建哈希表
        HashTab hashTab = new HashTab(7);

        //写一个简单菜单
        String key = "";
        boolean flag = true;
        Scanner scanner = new Scanner(System.in);
        while(flag){
            System.out.println("add：添加雇员");
            System.out.println("list:显示雇员");
            System.out.println("find:查找雇员");
            System.out.println("exit:退出系统");
            key = scanner.next();
            switch(key){
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    //创建雇员
                    Emp emp = new Emp(id,name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "find":
                    System.out.println("请输入要查找的id");
                    id =scanner.nextInt();
                    hashTab.findEmpById(id);
                    break;
                case "exit":
                   flag = false;
                   break;

            }
        }
        System.out.println("已退出系统");


    }

}


//创建HashTab，管理多条链表
class HashTab{
    private EmpLinkedList[] empLinkedListArray;
    private int size;//表示共有多少条链表

    //构造器
    public HashTab(int size){
        this.size = size;
        //初始化链表数组的长度
        empLinkedListArray = new EmpLinkedList[size];
        for(int i = 0 ; i < size ; i++){
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    //添加雇员
    public void add(Emp emp){
        //根据员工id得到该员工应该添加到哪条链表
        int empLinkedListNo = hashFun(emp.id);
        //将emp添加到对应的链表中
        empLinkedListArray[empLinkedListNo].add(emp);
    }

    //编写一个散列函数,使用一个简单取模法
    public int hashFun(int id){
        return id % size;
    }

    //遍历所有的链表
    public void list(){
        for(int i = 0;i < size ; i++){
            empLinkedListArray[i].list(i);
        }
    }

    //根据输入的id，查找雇员
    public void findEmpById(int id){
        //使用散列确定到哪条链表
        int empLinkedListNo = hashFun(id);
        Emp emp = empLinkedListArray[empLinkedListNo].findEmpById(id);
        if(emp != null){
            System.out.printf("在第%d条链表中找到雇员id = %d \n ",empLinkedListNo + 1 , id);
        }else{
            System.out.println("在哈希表中没有找到该雇员");
        }
    }

}


//创建EmpLinkdList，表示链表
class EmpLinkedList{
    //头指针
    private Emp head;

    //添加雇员到链表
    //假定添加雇员时，id自增长
    public void add(Emp emp){
        //如果是添加第一个雇员
        if(head == null){
            head = emp;
            return;
        }

        //如果不是第一个雇员，使用一个辅助指针，帮我们定位到链表最后
        Emp curEmp = head;
        while(true){
            if(curEmp.next == null){//说明链表到最后
                break;
            }
            curEmp = curEmp.next;//后移
        }
        curEmp.next = emp;
    }

    //遍历链表的雇员信息
    public void list(int no){
        if(head == null){
            System.out.println("第" + (no + 1) + "链表为空");
            return;
        }
        System.out.print("第" + (no + 1) + "链表的信息为：");
        Emp curEmp = head;
        while(true){
            System.out.printf("=> id = %d name = %s \t",curEmp.id,curEmp.name);
            if(curEmp.next == null){//说明链表到最后
                break;
            }
            curEmp = curEmp.next;//后移
        }
        System.out.println();
    }

    //根据id查找雇员
    public Emp findEmpById(int id){
        if(head == null){
            System.out.println("链表为空");
            return null;
        }

        //使用辅助指针
        Emp curEmp = head;
        while(true){
            if(curEmp.id == id){
                break;//这是curEmp就找到要查找的雇员
            }
            if(curEmp.next == null){
                curEmp = null;
                break;
            }
            curEmp = curEmp.next;

        }

        return curEmp;
    }
}




//雇员类
class Emp{
    public int id;
    public String name;
    public Emp next;//默认为null

    public Emp(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }
}
