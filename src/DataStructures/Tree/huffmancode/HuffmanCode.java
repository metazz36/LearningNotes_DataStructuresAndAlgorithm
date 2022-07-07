package DataStructures.Tree.huffmancode;


import java.util.*;

public class HuffmanCode {
    public static void main(String[] args) {
        String content = "i like like like java do you like a java";
        byte[] contentBytes = content.getBytes();
        System.out.println("原始长度 = " + contentBytes.length);//40
//        List<Node> nodes = getNodes(contentBytes);
//        System.out.println("nodes" + nodes);
//
//        //测试创建的赫夫曼树
//        Node root = createHuffmanTree(nodes);
//        System.out.println("前序遍历赫夫曼树为:");
//        preOrder(root);
//
//        //测试一把是否生成了赫夫曼编码
//        Map<Byte, String> huffmancodes = getCodes(root);
//        System.out.println("生成的赫夫曼编码表" + huffmancodes);
//
//        //测试
//        byte[] huffmanCodeBytes = zip(contentBytes, huffmancodes);
//        System.out.println("huffmanCodeBytes =" + Arrays.toString(huffmanCodeBytes));
//        System.out.println(huffmanCodeBytes.length);//17

        byte[] huffmanCodeBytes = huffmanZip(contentBytes);
        System.out.println("压缩后的结果是: huffmanCodeBytes =" + Arrays.toString(huffmanCodeBytes));
        System.out.println("压缩后长度 = "  +  huffmanCodeBytes.length);//17

        byte[] decode = decode(huffmanCodes, huffmanCodeBytes);
        System.out.println(decode.length);
        System.out.println(new String(decode));
    }

    //完成数据的解压
    //huffmanCodes 赫夫曼编码表
    //huffmanBytes 赫夫曼编码后的字节数组
    //返回的是编码前的字节数组
    private static byte[] decode(Map<Byte,String> huffmanCodes,byte[] huffmanBytes){
        //先得到huffmanBytes对应的二进制的字符串
        StringBuilder stringBuilder = new StringBuilder();
        //将byte数组转成二进制的字符串
        for(int i = 0;i < huffmanBytes.length; i++) {
            //判断是不是最后一个字节
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToBitString(!flag, huffmanBytes[i]));
        }

        //把字符串按照赫夫曼编码表进行解码
        //把赫夫曼编码表进行调换，因为要反向查询
        Map<String, Byte> map = new HashMap<String, Byte>();
        for(Map.Entry<Byte,String> entry :huffmanCodes.entrySet()){
            map.put(entry.getValue(),entry.getKey());
        }

        //创建一个集合，存放byte
        List<Byte> list =new ArrayList<Byte>();
        for(int i = 0;i < stringBuilder.length();){
            int count = 1;
            boolean flag = true;
            Byte b = null;

            while(flag){
                String key =stringBuilder.substring(i,i+count);//i不动，让count移动,直到匹配到一个字符
                b = map.get(key);
                if(b == null){//说明没有匹配到
                    count++;
                }else{
                    //匹配到了
                    flag = false;
                }
            }
            list.add(b);
            i += count;//i直接移动到count的位置
        }
        //for循环结束后，list中就存放了所有解码后的字符

        //把list中的数据放入到byte[]并返回
        byte[] b = new byte[list.size()];
        for(int i = 0; i < b.length ; i++){
            b[i] = list.get(i);
        }
        return b;

    }



    //将一个byte转成一个二进制的字符串
    //传入的byte b ，返回的是对应的二进制字符串（是按补码返回）
    //flag标志是否需要补高位，如果为true表示需要，flase表示不需要
    public static String byteToBitString(boolean flag,byte b){
        //使用变量保存b
        int temp = b;//将b转成int类型
        //如果b是正数还需要补位
        if(flag){
            temp |= 256;//按位或256
        }
        String str = Integer.toBinaryString(temp);//返回的是temp对应的二进制的补码

        if(flag){
            return str.substring(str.length() - 8);
        }else {
            return str;
        }

    }


    //使用一个方法，将赫夫曼编码的几个步骤方法进行封装，便于调用
    public static byte[] huffmanZip(byte[] bytes){
        //1.接收bytes（原始byte数组），返回List<Node>
        List<Node> nodes = getNodes(bytes);

        //2.接收nodes(存储节点的list数组)，返回赫夫曼树的根节点
        Node huffmanTreeRoot = createHuffmanTree(nodes);

        //3.接收huffmanTreeRoot赫夫曼树的根节点)，返回赫夫曼编码表
        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);

        //4.接收bytes(原始的byte数组)和huffmanCodes(赫夫曼编码表),返回压缩后的赫夫曼编码字节数组
        byte[] huffmanCodeBytes = zip(bytes, huffmanCodes);

        return huffmanCodeBytes;

    }

    //将字符串对应的byte[]数组，通过利用赫夫曼编码表，返回压缩后的byte[]
    //bytes 原始的字符串对应的byte[]
    //huffmanCodes 生成的赫夫曼编码表
    //return值 返回赫夫曼编码处理后的byte[]
    public static byte[] zip(byte[] bytes,Map<Byte,String> huffmanCodes){

        //1.利用huffmanCodes将bytes转成赫夫曼编码对应的字符串
        StringBuilder stringBuilder = new StringBuilder();
        //遍历bytes数组
        for(byte b : bytes){
            stringBuilder.append(huffmanCodes.get(b));
        }

//        System.out.println(stringBuilder);

        //将字符串转成一个byte[]（重要！！）
        int len;
        if(stringBuilder.length() % 8 == 0){
            len = stringBuilder.length() / 8 ;
        }else{
            len = stringBuilder.length() / 8 + 1 ;
        }
        //创建存储压缩后的byte数组
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0;
        for(int i = 0;i < stringBuilder.length();i += 8){ //每八位对应一个byte，所以步长为8
            String strByte;
            if(i + 8 > stringBuilder.length()){//不够8位
                strByte = stringBuilder.substring(i);
            }else{
                strByte = stringBuilder.substring(i,i + 8);
            }
            //将strByte 转成 一个byte，放入到huffmanCodeBytes
            huffmanCodeBytes[index] = (byte)Integer.parseInt(strByte,2);
            index++;
        }
        return huffmanCodeBytes;


    }




    //为了调用方便，我们重载getCodes方法
    public static Map<Byte,String> getCodes(Node root){
        if(root == null){
            return null;
        }

        //处理root左子树
        getCodes(root.left,"0",stringBuilder);
        //处理右子树
        getCodes(root.right,"1",stringBuilder);
        return huffmanCodes;
    }

    //生成赫夫曼树对应的赫夫曼编码表
    //1、赫夫曼编码表(Map<Byte,String>)
    static Map<Byte,String> huffmanCodes = new HashMap<Byte,String>();
    //2、在生成赫夫曼编码表时，需要去拼接路径，定义一个StringBuilder，存储某个叶子节点的路径
    static StringBuilder stringBuilder = new StringBuilder();

    //功能：将传入的node节点的所有叶子节点的赫夫曼编码得到，并存放到huffmanCodes中
    //node 传入节点
    //code 路径：左子节点是0，右子节点是1
    //stringBuilder 用于拼接路径
    public static void getCodes(Node node,String code,StringBuilder stringBuilder){
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        //将code加入到stringBuilder2
        stringBuilder2.append(code);
        if(node != null){
            //判断当前node是叶子节点还是非叶子节点
            if(node.data == null){//非叶子节点
                //向左递归
                getCodes(node.left,"0",stringBuilder2);
                //向右递归
                getCodes(node.right,"1",stringBuilder2);
            }else{//叶子节点
                //表示找到了某个叶子节点
                huffmanCodes.put(node.data,stringBuilder2.toString());
            }
        }
    }




    public static void preOrder(Node root){
        if(root != null){
            root.preOrder();
        }else{
            System.out.println("树为空，无法遍历！");
        }
    }

    //接收一个字节数组，返回的是List<Node>
    public static List<Node>  getNodes(byte[] bytes){
        //1、创建一个ArrayList
        ArrayList<Node> nodes = new ArrayList<Node>();
        //2、遍历bytes，统计每一个byte出现的次数——》map[key，value]
        Map<Byte,Integer> counts = new HashMap();
        for(byte b : bytes){
            Integer count = counts.get(b);
            if(count == null){//Map还没有这个字符数据
                counts.put(b,1);
            }else{
                counts.put(b,count +1);
            }
        }
        //3、把每个键值对转成一个Node对象，并加入到nodes集合
        for(Map.Entry<Byte,Integer> entry : counts.entrySet()){
            nodes.add(new Node(entry.getKey(),entry.getValue()));
        }
        return nodes;
    }

    //通过List创建对应的赫夫曼树
    public static Node createHuffmanTree(List<Node> nodes){
        while(nodes.size() > 1){
            //从小到大排序
            Collections.sort(nodes);
            //取出第一颗最小的二叉树
            Node leftNode = nodes.get(0);
            //取出第二颗最小的二叉树
            Node RightNode = nodes.get(1);
            //创建一颗新的二叉树，它的根节点没有data，只有权值
            Node parent = new Node(null,leftNode.weight + RightNode.weight);
            parent.left = leftNode;
            parent.right = RightNode;
            //将已经处理的两颗二叉树从nodes移除
            nodes.remove(leftNode);
            nodes.remove(RightNode);
            //将新生成的二叉树添加到nodes
            nodes.add(parent);
        }
        return nodes.get(0);
    }

}

//创建Node，存放数据和权值
class Node implements Comparable<Node>{
    Byte data;//存放数据本身
    int weight;//权值，表示字符出现的次数
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }

    //前序遍历
    public void preOrder(){
        System.out.println(this);
        if(this.left != null){
            this.left.preOrder();
        }
        if(this.right != null){
            this.right.preOrder();
        }
    }

}
