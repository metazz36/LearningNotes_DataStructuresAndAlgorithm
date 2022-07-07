package DataStructures.LinkedList;

public class DoubleLinkListDemo {

    public static void main(String[] args) {

        //先创建节点
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2,"卢俊义","玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3,"吴用","智多星");
        HeroNode2 hero4 = new HeroNode2(4,"林冲","豹子头");

        //创建一个双向链表
        DoubleLinkList doubleLinkList = new DoubleLinkList();
        doubleLinkList.add(hero1);
        doubleLinkList.add(hero2);
        doubleLinkList.add(hero3);
        doubleLinkList.add(hero4);

        doubleLinkList.list();

        //修改
        HeroNode2 newHeroNode = new HeroNode2(4,"公孙胜","入云龙");
        doubleLinkList.updateByNo(newHeroNode);
        System.out.println("______________________修改_____________________");
        doubleLinkList.list();

        //删除
        doubleLinkList.deleteByNo(3);
        System.out.println("______________________删除_____________________");
        doubleLinkList.list();
    }


}

//创建一个双向链表的类
class DoubleLinkList{
    //先初始化头节点
    private HeroNode2 head = new HeroNode2(0,"","");

    //返回头节点
    public HeroNode2 getHead() {
        return head;
    }

    //遍历双向链表
    public void list(){
        //先判断链表是否为空
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        //因为头节点不能动，所以我们需要一个辅助变量来遍历
        HeroNode2 temp = head.next;
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

    //添加节点到双向链表
    public void add(HeroNode2 heroNode){
        //因为head节点不能动，因此我们需要一个辅助遍历temp
        HeroNode2 temp = head;
        while(true){
            if(temp.next == null){
                break;
            }
            //如果没有找到最后，将temp后移
            temp = temp.next;
        }
        //当退出while循环时，temp就指向了链表的最后
        temp.next = heroNode;
        heroNode.pre = temp;
    }

    //修改
    public void updateByNo(HeroNode2 heroNode) {
        //判断是否为空
        if(head.next == null){
            System.out.println("链表为空,修改失败！");
            return;
        }
        //找到需要修改的节点,根据no编号
        //定义一个辅助变量
        HeroNode2 temp = head.next;
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

    //删除
    //对于双向链表，可以直接找到要删除的节点实现自我删除
    public void deleteByNo(int no){
        if(head.next == null){
            System.out.println("链表为空,无法删除");
            return;
        }

        HeroNode2 temp = head.next;//辅助指针（变量）
        boolean flag = false;
        while(true){
            if(temp == null){
                //已经到链表的最后
                break;
            }
            if(temp.no == no){
                flag = true ;
                break;
            }
            temp = temp.next;
        }

        if(flag){
            temp.pre.next = temp.next;
            //特殊情况（删除最后一个节点，加个if判断）
            if(temp.next != null){
                temp.next.pre = temp.pre;
            }
        }else{
            System.out.printf("没有%d编号的节点\n",no);
        }
    }

}


//定义HeroNode2，每个HeroNode对象就是一个节点
class HeroNode2{
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next; //指向下一个节点，默认为null

    public HeroNode2 pre;//指向前一个节点，默认为null

    //构造器
    public HeroNode2(int no,String name,String nickname){
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