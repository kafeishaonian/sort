package the_sorting;

import java.util.Stack;

/**
 *  二叉树的先序中序后序排序
 */
public class BinaryTree {

    //必须是逆序建立，先建立子节点，在逆序往上建立，因为非叶子结点会使用到下面的结点，而初始化是按照顺序初始化的，不逆序建立会报错
    public Node init(){
        Node J = new Node(8, null, null);
        Node H = new Node(4, null, null);
        Node G = new Node(2, null, null);
        Node F = new Node(7, null, J);
        Node E = new Node(5, H, null);
        Node D = new Node(1, null, G);
        Node C = new Node(9, F, null);
        Node B = new Node(3, D, E);
        Node A = new Node(6, B, C);
        return A;
    }

    public void printNode(Node node){
        System.out.println(node.getData());
    }

    /**
     * 先序遍历
     * @param root
     */
    public void theFirstTraversal(Node root){
        printNode(root);
        if (root.getLeftNode() != null){
            theFirstTraversal(root.getLeftNode());
        }
        if (root.getRightNode() != null){
            theFirstTraversal(root.getRightNode());
        }
    }

    /**
     * 中序遍历
     * @param root
     */
    public void theInOrderTraversal(Node root){
        if (root.getLeftNode() != null){
            theInOrderTraversal(root.getLeftNode());
        }
        printNode(root);
        if (root.getRightNode() != null){
            theInOrderTraversal(root.getRightNode());
        }
    }

    /**
     * 后序遍历
     * @param root
     */
    public void thePostOrderTraversal(Node root){
        if (root.getLeftNode() != null){
            thePostOrderTraversal(root.getLeftNode());
        }
        if (root.getRightNode() != null){
            thePostOrderTraversal(root.getRightNode());
        }
        printNode(root);
    }


    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        Node root = tree.init();
        System.out.println("先序遍历");
        tree.theFirstTraversal(root);
        System.out.println("中序遍历");
        tree.theInOrderTraversal(root);
        System.out.println("后序遍历");
        tree.thePostOrderTraversal(root);
        System.out.println("===================================");
        System.out.println("先序遍历");
        tree.theFirstTraversal_Stack(root);
        System.out.println("中序遍历");
        tree.theInOrderTraversal_Stack(root);
        System.out.println("后序遍历");
        tree.thePostOrderTraversal_Stack(root);
    }

    //================堆栈实现先序中序后序遍历=========================

    /**
     * 先序遍历
     * @param root
     */
    public void theFirstTraversal_Stack(Node root){
        Stack<Node> stack = new Stack<>();
        Node node = root;
        while (node != null || stack.size() > 0){
            //将所有左孩子压栈
            if (node != null){
                printNode(node);
                stack.push(node);
                node = node.getLeftNode();
            } else {
                node = stack.pop();
                node = node.getRightNode();
            }
        }
    }

    /**
     * 中序遍历
     * @param root
     */
    public void theInOrderTraversal_Stack(Node root){
        Stack<Node> stack = new Stack<>();
        Node node = root;
        while (node != null || stack.size() > 0){
            if (node != null){
                //压栈
                stack.push(node);
                node = node.getLeftNode();
            } else {
                //出栈
                node = stack.pop();
                printNode(node);
                node = node.getRightNode();
            }
        }
    }

    /**
     * 后序遍历
     * @param root
     */
    public void thePostOrderTraversal_Stack(Node root){
        if (root == null){
            return;
        }
        Stack<Node> stack = new Stack<>();
        Node curNode = root; //当前访问的结点
        Node lastVisitNode = null; //上次访问的结点

        while (curNode != null){
            stack.push(curNode);
            curNode = curNode.getLeftNode();
        }
        while (!stack.empty()){
            curNode = stack.pop();//弹出栈顶元素
            //一个根节点被访问的前提是；无右子树或右子树已被访问
            if (curNode.getRightNode() != null && curNode.getRightNode() != lastVisitNode){
                //根节点再次入栈
                stack.push(curNode);
                //进入右子树。且可肯定右子树一定不为空
                curNode = curNode.getRightNode();
                while (curNode != null){
                    //在走到右子树的最左边
                    stack.push(curNode);
                    curNode = curNode.getLeftNode();
                }
            } else {
                System.out.println(curNode.getData());
                lastVisitNode = curNode;
            }
        }

    }

}


/**
 * 创建一颗树
 */
class Node{

    private int data;
    private Node leftNode;
    private Node rightNode;

    public Node(int data, Node leftNode, Node rightNode){
        this.data = data;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

    public int getData() {
        return data;
    }

    public Node getLeftNode() {
        return leftNode;
    }

    public Node getRightNode() {
        return rightNode;
    }

    public void setData(int data) {
        this.data = data;
    }

    public void setLeftNode(Node leftNode) {
        this.leftNode = leftNode;
    }

    public void setRightNode(Node rightNode) {
        this.rightNode = rightNode;
    }
}
