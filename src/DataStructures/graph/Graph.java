package DataStructures.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Graph {
    public static void main(String[] args) {
        //测试
        int n = 5;
        String[] Vertexs ={"A","B","C","D","E"};
        //创建图对象
        Graph graph = new Graph(n);
        //添加顶点
        for(int i = 0; i < Vertexs.length ; i++){
            graph.insertVertex(Vertexs[i]);
        }
        //添加边
        //A-B A-C B-C B-D B-E
        graph.insertEdge(0,1,1);
        graph.insertEdge(0,2,1);
        graph.insertEdge(1,2,1);
        graph.insertEdge(1,3,1);
        graph.insertEdge(1,4,1);
        //显示邻接矩阵
        graph.showGraph();

        //深度优先遍历
        System.out.println("深度优先遍历");
        graph.dfs(0);

        //广度优先遍历
        System.out.println("\n广度优先遍历：");
        graph.bfs(0);


    }

    private ArrayList<String> vertexList;//存储顶点集合
    private int[][] edges;//存储图对应的邻接矩阵
    private int numOfEdges;//表示边的数目
    private boolean[] isVisited;//记录某个节点是否被访问

    //构造器
    public Graph(int n){
        //初始化矩阵和vertexList
        edges = new int[n][n];
        vertexList = new ArrayList<String>(n);
        numOfEdges = 0;//默认为0 可不写
        isVisited = new boolean[n];
    }

    //得到第一个邻接节点的下标w
    public int getFirstNeighbor(int index){
        for(int j = 0;j < vertexList.size();j++){
            if(edges[index][j] > 0){
                return j;
            }
        }
        return -1;
    }

    //根据前一个邻接节点的下标来获取下一个邻接节点
    public int getNextNeighbor(int v1,int v2){
        for(int j = v2 + 1; j < vertexList.size(); j++){
            if(edges[v1][j] > 0){
                return j;
            }
        }
        return -1;
    }


    //深度优先遍历算法
    public void dfs(int i){
        //首先我们访问该节点，输出
        System.out.print(getValueByIndex(i) + "->");
        //将该节点设置为已经访问
        isVisited[i] = true;
        //查找节点i的第一个邻接节点w
        int w = getFirstNeighbor(i);
        while(w != -1){ //说明有邻接节点
            if(!isVisited[w]){//没有被访问
                dfs(w);
            }
            //如果w节点已经被访问
            w = getNextNeighbor(i,w);

        }
    }
    
    //对一个节点进行广度优先遍历算法
    public void bfs(int i){
        isVisited = new boolean[getNumOfVertex()];
        int u;//表示队列的头节点对应的下标
        int w;//邻接节点w
        //需要一个队列，记录节点访问的一个顺序
        LinkedList queue = new LinkedList();
        //访问节点，输出节点信息
        System.out.print(getValueByIndex(i) + "=>");
        isVisited[i] = true;
        //将节点加入队列
        queue.addLast(i);

        while(!queue.isEmpty()){
            //取出队列的头节点
            u = (Integer)queue.removeFirst();
            //得到第一个邻接节点的下标
            w = getFirstNeighbor(u);
            while(w != -1){
                if(!isVisited[w]){//未访问
                    //访问节点，输出节点信息
                    System.out.print(getValueByIndex(w) + "=>");
                    isVisited[w] = true;
                    //入队
                    queue.addLast(w);
                }
                //查找结点u的继w邻接结点后的下一个邻接结点w
                w = getNextNeighbor(u,w);//体现出了广度优先

            }
        }
    }

    //对bfs进行一个重载，遍历所有的节点进行bfs
//    public void bfs(){
//        for(int i = 0; i < getNumOfVertex();i++){
//            if(!isVisited[i]){
//                bfs(i);
//            }
//        }
//    }

    //对dfs进行一个重载,遍历所有的节点进行dfs
//    public void dfs(){
//        //遍历所有的节点进行dfs[回溯]
//        for(int i=0; i < getNumOfVertex(); i++){
//            if(!isVisited[i]){
//                dfs(i);
//            }
//        }
//    }

    //插入节点
    public void insertVertex(String vertex){
        vertexList.add(vertex);
    }

    //添加边
    public void insertEdge(int v1,int v2,int weight){
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }

    //图中常用方法
    //1.返回节点的个数
    public int getNumOfVertex(){
        return vertexList.size();
    }

    //2.得到边的个数
    public int getNumOfEdges(){
        return numOfEdges;
    }

    //3.返回节点i(下标)对应的数据
    public String getValueByIndex(int i){
        return vertexList.get(i);
    }

    //4.返回v1和v2的权值
    public int getWeight(int v1,int v2){
        return edges[v1][v2];
    }

    //5.显示图对应的邻接矩阵
    public void showGraph(){
        for(int[] link : edges){
            System.out.println(Arrays.toString(link));
        }
    }


}


