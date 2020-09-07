package datastruct.skiplist;

import java.util.Random;

/**
 * 跳表
 * code from https://mp.weixin.qq.com/s/Ok0laJMn4_OzL-LxPTHawQ
 *
 * @author dingchenchen
 * @since 2020/9/3
 */
public class SkipList {

    /**
     * 结点"晋升"的概率: 50%
     */
    private static final double PROMOTE_RATE = 0.5;

    private Node head, tail;

    private int maxLevel;

    public SkipList() {
        this.head = new Node(Integer.MIN_VALUE);
        this.tail = new Node(Integer.MAX_VALUE);
        head.right = tail;
        tail.left = head;
    }

    /**
     * 查找结点方法
     */
    public Node search(int data) {
        Node p = findNode(data);
        if (p.data == data) {
            System.out.println("找到结点：" + data);
            return p;
        }
        System.out.println("未找到结点：" + data);
        return null;
    }

    /**
     * 找到值data对应的前置结点
     */
    private Node findNode(int data) {
        Node node = head;
        while(true){
            while (node.right.data!=Integer.MAX_VALUE && node.right.data<=data) {
                node = node.right;
            }
            if (node.down == null) {
                break;
            }
            node = node.down;
        }
        return node;
    }

    /**
     * 插入结点
     */
    public void insert(int data) {
        Node preNode = findNode(data);
        //如果data相同，直接返回
        if (preNode.data == data) {
            return;
        }
        Node node = new Node(data);
        appendNode(preNode, node);
        int currentLevel = 0;
        //随机决定是否"晋升"
        Random random = new Random();
        while (random.nextDouble() < PROMOTE_RATE) {
            //如果当前层已经是最高层，需要增加一层
            if (currentLevel == maxLevel) {
                addLevel();
            }
            //找到上一层的前置结点
            while (preNode.up == null) {
                preNode = preNode.left;
            }
            preNode = preNode.up;
            //把“晋升”的新结点插入到上一层
            Node upperNode = new Node(data);
            appendNode(preNode, upperNode);
            upperNode.down = node;
            node.up = upperNode;
            // 可能还会向上"晋升，所以要将node指向upperNode
            node = upperNode;
            currentLevel++;
        }
    }

    /**
     * 在前置结点后面新加结点
     */
    private void appendNode(Node preNode, Node newNode) {
        newNode.left = preNode;
        newNode.right = preNode.right;
        preNode.right.left = newNode;
        preNode.right = newNode;
    }

    /**
     * 增加一层
     */
    private void addLevel() {
        maxLevel++;
        Node p1 = new Node(Integer.MIN_VALUE);
        Node p2 = new Node(Integer.MAX_VALUE);
        p1.right = p2;
        p2.left = p1;
        p1.down = head;
        head.up = p1;
        p2.down = tail;
        tail.up = p2;
        head = p1;
        tail = p2;
    }

    /**
     * 删除节点
     */
    public boolean remove(int data) {
        Node removeNode = search(data);
        if (removeNode == null) {
            return false;
        }
        int currentLevel = 0;
        while (removeNode != null) {
            removeNode.right.left = removeNode.left;
            removeNode.left.right = removeNode.right;
            //如果不是最底层，且只有无穷小和无穷大结点，删除该层
            if (currentLevel != 0 && removeNode.left.data == Integer.MIN_VALUE && removeNode.right.data == Integer.MAX_VALUE) {
                removeLevel(removeNode.left);
            } else {
                currentLevel++;
            }
            removeNode = removeNode.up;
        }
        return true;
    }

    /**
     * 删除一层
     */
    private void removeLevel(Node leftNode) {
        Node rightNode = leftNode.right;
        //如果删除层是最高层
        if (leftNode.up == null) {
            leftNode.down.up = null;
            rightNode.down.up = null;
        } else {
            leftNode.up.down = leftNode.down;
            leftNode.down.up = leftNode.up;
            rightNode.up.down = rightNode.down;
            rightNode.down.up = rightNode.up;
        }
        maxLevel--;
    }

    /**
     * 输出底层链表
     */
    public void printList() {
        Node q = head;
        Node p = q;
        while (q.down != null) {
            while (p.right.data != Integer.MAX_VALUE) {
                System.out.print(p.right.data + " ");
                p = p.right;
            }
            System.out.println();
            q = q.down;
            p = q;
        }
    }

    /**
     * 链表结点类
     */
    public class Node {
        public int data;
        //跳表的结点上下前后都有都有指针
        public Node up, down, left, right;

        public Node(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        SkipList list = new SkipList();
        list.insert(50);
        list.insert(15);
        list.insert(13);
        list.insert(20);
        list.insert(100);
        list.insert(75);
        list.insert(99);
        list.insert(76);
        list.insert(83);
        list.insert(65);
        list.printList();
        list.search(50);
        list.remove(50);
        list.search(50);
    }
}
