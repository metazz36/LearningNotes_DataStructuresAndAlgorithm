package LeetCode;

//题目：剑指 Offer II 031. 最近最少使用缓存 or (LRU 缓存)
//难度：中等
/*题目描述：
请你设计并实现一个满足LRU(最近最少使用)缓存约束的数据结构。
实现 LRUCache 类：
LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存
int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value ；如果不存在，则向缓存中插入该组 key-value 。如果插入操作导致关键字数量超过 capacity ，则应该逐出最久未使用的关键字。
函数 get 和 put 必须以 O(1) 的平均时间复杂度运行!!!
 */

import java.util.HashMap;
import java.util.Map;

public class P4_LRU缓存 {
    public static void main(String[] args) {
        LRUCache lRUCache = new LRUCache(2);
        lRUCache.put(1, 1); // 缓存是 {1=1}
        lRUCache.show();
        lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
        lRUCache.show();
        lRUCache.get(1);    // 返回 1
        lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        lRUCache.show();
        lRUCache.get(2);    // 返回 -1 (未找到)
        lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        lRUCache.show();
        lRUCache.get(1);    // 返回 -1 (未找到)
        lRUCache.get(3);    // 返回 3
        lRUCache.get(4);    // 返回 4
    }
}

//双向链表+哈希表
class LRUCache {

    public void show(){
        for(Map.Entry<Integer, DLinkedNode> data : cache.entrySet()){
            System.out.print(data.getKey() + "=" + data.getValue().value+ " ");
        }
        System.out.println();

    }

    //节点类
    class DLinkedNode {
        int key;
        int value;
        DLinkedNode prev;
        DLinkedNode next;
        public DLinkedNode() {}
        public DLinkedNode(int _key, int _value) {key = _key; value = _value;}
    }

    private Map<Integer,DLinkedNode> cache = new HashMap<Integer,DLinkedNode>();
    private int size;//记录当前双向链表节点个数
    private int capacity;//记录最大容量（即最多的节点个数）
    private DLinkedNode head,tail;

    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        //使用伪头部和伪尾部节点
        head = new DLinkedNode();
        tail = new DLinkedNode();
        head.next = tail;
        tail.prev = head;
    }


    public int get(int key) {
        DLinkedNode node = cache.get(key);
        if(node == null){
            return -1;
        }else{
            //如果key存在，先通过哈希表定位，再移动到头部
            moveToHead(node);
            return node.value;
        }

    }

    public void put(int key, int value) {
        DLinkedNode node = cache.get(key);
        if(node == null){
            //如果key不存在，创建一个新节点
            DLinkedNode newNode = new DLinkedNode(key,value);
            cache.put(key,newNode);//添加进哈希表
            addToHead(newNode);//添加至双向链表头部
            ++size;
            if(size > capacity){
                //如果超出容量，删除双向链表的尾部节点
                DLinkedNode tail = removeTail();
                cache.remove(tail.key);//删除哈希表中对应得项
                --size;
            }
        }else{
            //如果key存在，先通过哈希表定位，再修改value，并移到头部
            node.value = value;
            moveToHead(node);
        }
    }


    private void addToHead(DLinkedNode node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(DLinkedNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void moveToHead(DLinkedNode node) {
        removeNode(node);
        addToHead(node);
    }

    private DLinkedNode removeTail() {
        DLinkedNode res = tail.prev;
        removeNode(res);
        return res;
    }

}




