package DataStructures.LinkedList;


public class SingleLinkedListDemo{


    //测试
    public static void main(String[] args) {
        //先创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2,"卢俊义","玉麒麟");
        HeroNode hero3 = new HeroNode(3,"吴用","智多星");
        HeroNode hero4 = new HeroNode(4,"林冲","豹子头");

        //创建链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();

        //加入1
//        singleLinkedList.addBy(hero1);
//        singleLinkedList.addBy(hero4);
//        singleLinkedList.addBy(hero3);
//        singleLinkedList.addBy(hero2);

        //加入2
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.list();

        System.out.println("------------------------------");
        reverseList(singleLinkedList.getHead());

        singleLinkedList.list();


/*        //输出链表
        singleLinkedList.list();

        //测试修改节点
        HeroNode hero11 = new HeroNode(1, "宋江1", "及时雨1");
        singleLinkedList.updateByNo(hero11);

        System.out.println("______________________________________________");

        //测试删除功能
        singleLinkedList.deleteByNo(5);

        //输出链表
        singleLinkedList.list();

        //测试单链表中节点个数的方法
        System.out.println("有效的节点个数为" + getLength(singleLinkedList.getHead()));


        System.out.println("----------------------------------------------------");
        //测试是否得到倒数第K个节点
        HeroNode res = findLastIndexNode(singleLinkedList.getHead(),1);
        System.out.println(res);*/





    }

    //获取到单链表节点的个数(如果是带头节点的链表，不统计头节点)
    public static int getLength(HeroNode head){
        if(head.next == null){
            return 0;
        }
        int length = 0;
        HeroNode cur = head.next;
        while(cur != null){
            length++;
            cur = cur.next;
        }
        return length;

    }

    //找到单链表中的倒数第K个节点
    public static HeroNode findLastIndexNode(HeroNode head , int index){
        if(head.next == null){
            return null;
        }
        //第一次遍历，得到链表的长度
        int size = getLength(head);
        //第二次遍历size - index 位置，就是所求
        //先做index校验
        if(index<=0||index>size){
            return null;
        }

        //定义一个辅助变量
        HeroNode cur = head.next;
        for(int i = 0;i < (size-index);i++){
            cur = cur.next;
        }
        return cur;
    }

    //反转单链表
    public static void reverseList(HeroNode head){
      //如果当前链表为空，或者只有一个节点，无需反转，直接返回
        if(head.next == null||head.next.next == null){
            return;
        }

        //定义一个辅助的指针（变量），帮助我们遍历原来的链表
        HeroNode cur = head.next;
        HeroNode next = null;//指向当前节点cur的下一个节点
        HeroNode reverseHead = new HeroNode(0,"","");
        //遍历原来的链表，每遍历一个节点，就将其取出，并放在新的链表reverseHead的最前端
        while(cur != null){
            next = cur.next;//先暂时保存当前节点的下一个节点，后面需要使用
            cur.next = reverseHead.next;//将cur的下一个节点指向新的链表的最前端
            reverseHead.next = cur ; //将cur连接到新的链表上
            cur = next ; //让cur后移
        }

        //将head.next指向reverseHead.next，实现单链表的反转
        head.next = reverseHead.next;

    }

}

//定义SingleLinkedList 管理我们的英雄
class SingleLinkedList{
    //先初始化头节点
    private HeroNode head = new HeroNode(0,"","");

    public HeroNode getHead() {
        return head;
    }

    //添加节点到单向链表
    //思路：当不考虑编号顺序时
    //1.找到当前链表的最后节点
    //2.将最后节点的next指向新的节点
    public void add(HeroNode heroNode){
        //因为head节点不能动，因此我们需要一个辅助遍历temp
        HeroNode temp = head;
        while(true){
            if(temp.next == null){
                break;
            }
            //如果没有找到最后，将temp后移
            temp = temp.next;
        }
        //当退出while循环时，temp就指向了链表的最后
        //将最后这个节点的next指向新的节点
        temp.next = heroNode;

    }

    //第二种方式添加，根据排名将英雄插入到指定位置
    //如果有这个排名，则添加失败，并给出提示
    public void addByOrder(HeroNode heroNode){
        //因为头节点不能动，因此仍然通过辅助指针（变量）来帮助找到添加位置
        //temp找的位置时位于添加位置的前一节点
        HeroNode temp = head;
        boolean flag = false;//标志添加的编号是否存在，默认为false

        while(true){
            if(temp.next == null){//说明temp已经在链表的最后
                break;
            }
            if(temp.next.no > heroNode.no){//位置找到，就在temp后面插入
               break;
            }else if(temp.next.no == heroNode.no){
                flag = true;//说明编号存在
                break;
            }
            temp = temp.next;//后移
        }
        //判断flag的值
        if(flag){
            System.out.printf("编号%d已存在，添加失败！\n",temp.next.no);
        }else {
            //插入到链表中
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    //修改节点的信息,根据编号no进行修改
    public void updateByNo(HeroNode heroNode) {
        //判断是否为空
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的节点,根据no编号
        //定义一个辅助变量
        HeroNode temp = head.next;
        boolean flag = false;//表示是否找到该节点
        while(true){
            if(temp == null){
                break;// 已经遍历完
            }
            if(temp.no == heroNode.no){
                //找到
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //根据flag判断是否找到
        if(flag){
            temp.name = heroNode.name;
            temp.nickname = heroNode.nickname;
        }else{
            System.out.printf("没有找到编号为%d的节点，不能修改\n",heroNode.no);
        }
    }


    //删除指定编号节点
    public void deleteByNo(int no){
        HeroNode temp = head;
        boolean flag = false;
        while(true){
            if(temp.next == null){
                //已经到链表的最后
                break;
            }
            if(temp.next.no == no){
                flag = true ;
                break;
            }
            temp = temp.next;
        }

        if(flag){
            temp.next = temp.next.next;
        }else{
            System.out.printf("没有%d编号的节点\n",no);
        }
    }

    //显示链表【遍历】
    public void list(){
        //先判断链表是否为空
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        //因为头节点不能动，所以我们需要一个辅助变量来遍历
        HeroNode temp = head.next;
        while(true){
            //判断是否到链表最后
            if(temp == null){
                break;
            }
            //输出节点的信息
            System.out.println(temp);
            //将temp后移
            temp = temp.next;
        }


    }
}


//定义HeroNode，每个HeroNode对象就是一个节点
class HeroNode{
    public int no;
    public String name;
    public String nickname;
    public HeroNode next; //指向下一个节点

    //构造器
    public HeroNode(int no,String name,String nickname){
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
