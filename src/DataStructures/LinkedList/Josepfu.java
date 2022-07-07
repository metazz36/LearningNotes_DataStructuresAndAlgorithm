package DataStructures.LinkedList;

public class Josepfu {
    public static void main(String[] args) {
        CircleSingleLinkList circleSingleLinkList = new CircleSingleLinkList();
        circleSingleLinkList.addBoy(5);
        circleSingleLinkList.showBoy();
        System.out.println("____________________测试________________");
        circleSingleLinkList.countBoy(1,2,5);
    }


}


//创建环形单向链表
class CircleSingleLinkList{
    //创建一个first节点
    private Boy first = null;

    //添加小孩节点，构建环形链表
    public void addBoy(int nums){
        //对nums做一个校验
        if(nums < 1){
            System.out.println("nums的值不合理!");
            return;
        }
        //创建一个辅助指针（变量）
        Boy curBoy = null;
        for(int i = 1;i <= nums; i++){
            Boy boy = new Boy(i);
            //如果是第一个小孩
            if(i == 1){
                first = boy;
                first.setNext(first);
                curBoy = first;
            }else{
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy = boy;
            }
        }
    }

    //遍历当前链表
    public void showBoy(){
        //判断链表是否为空
        if(first == null){
            System.out.println("链表为空");
            return;
        }
        //因为first不能动，仍然使用一个辅助指针（变量）
        Boy curBoy = first ;
        while(true){
            System.out.printf("小孩的编号%d\n",curBoy.getNo());
            if(curBoy.getNext() == first){//遍历完毕
                break;
            }
            curBoy = curBoy.getNext();
        }

    }

    //根据用户的输入，计算出小孩出圈的顺序
    //startNo表示从第几个小孩开始数数
    //countNum表示数几下
    //nums表示最初有多少小孩
    public void countBoy(int startNo,int countNum,int nums){
        //先数据校验
        if(first == null || startNo < 1 || startNo > nums){
            System.out.println("参数输入有错误");
            return;
        }
        Boy helper = first ;
        //先让helper指向环形链表的最后
        while(true){
            if(helper.getNext() == first){
                break;
            }
            helper = helper.getNext();
        }
        //小孩报数前，先让first和helper移动startNo-1次
        for(int j = 0;j < startNo-1; j++){
            first = first.getNext();
            helper = helper.getNext();
        }
        //报数时，让first和helper同时移动countNum - 1次,然后出圈
        while(true){
            if(helper == first){//说明圈中只有一个节点
                break;
            }
            for(int j = 0;j < countNum -1 ;j++){
                first = first.getNext();
                helper = helper.getNext();
            }
            //这时first指向的节点，就是出圈小孩节点
            System.out.printf("小孩%d出圈\n",first.getNo());
            first = first.getNext();
            helper.setNext(first);
        }
        // 下面两行结果是一样的
        // System.out.printf("最后留在圈中的小孩编号%d\n",first.getNo());
        System.out.printf("最后留在圈中的小孩编号%d\n",first.getNext().getNo());
    }

}




//创建一个Boy类，表示一个节点
class Boy{
    private int no;
    private Boy next;

    public Boy(int no){
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}