/**
 * 树：
 *  定义：树是n(大于等于0)个结点的有限集T，并且当n>0时满足下列条件：
 *      1.有且仅有一个特定的称为根（Root）的结点。
 *      2.当N > 1时，其余结点可以划分为m(m>0)个互不相交的有限集T1、T2...Tm,每个集Ti均为树，且称为树T的子树
 *  空树：
 *      不含任何结点的数为空树。
 *  基本术语：
 *      结点：存储数据元素和指向子树的链接，由数据元素和构造数据元素之间关系的引用组成。
 *      孩子结点：树中一个结点的子树的跟结点称为这个结点的孩子结点。
 *      双亲结点：树种某个结点有孩子结点，该结点称为孩子结点的双亲结点，也叫前驱结点。双亲结点和孩子结点是相互的，也可以称为孩子结点的跟结点
 *      兄弟结点：具有相同双亲结点的结点称为兄弟结点。
 *      结点的度：结点所有子树的个数称为该结点的度。
 *      树的度：树中所有结点的度的最大值称为树的度。
 *      叶子结点：度为0的结点称为叶子结点，也叫终端结点。
 *      分支结点：度不为0的结点称为分支结点，也叫非终端结点。
 *      结点的层次：从根节点到树中某结点所经路径的分支树称为该结点的层次。跟结点的层次一般为1，这样，其它结点的层次是其双亲结点的层次加1。
 *      树的深度：树中所有结点的层次的最大值称为该树的深度。
 *      有序树和无序树：树中任意一个结点的各子树按从左到右是有序的，称为有序数，否则称为无序树。
 *
 *  树的抽象数据类型描述：
 *      数据元素：具有相同特性的数据元素的集合
 *      结构关系：树中数据元素间的结构关系由树的定义确定。
 *      基本操作：
 */

import the_sorting.Barrel;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 树的定义和实现
 */
public class Tree {

    private Object data;
    private List<Tree> childs;

    public Tree(){
        data = null;
        childs = new ArrayList<>();
        childs.clear();
    }

    public Tree(Object data){
        this.data = data;
        childs = new ArrayList<>();
        childs.clear();
    }

    /**
     * 添加子树
     * @param tree
     */
    public void addNode(Tree tree){
        childs.add(tree);
    }

    /**
     * 置空树
     */
    public void clearTree(){
        data = null;
        childs.clear();
    }

    /**
     * 求树的深度
     * @return
     */
    public int dept(){
        return dept(this);
    }

    /**
     * 求树的深度
     * @param tree
     * @return
     */
    private int dept(Tree tree){
        if (tree.isEmpty()){
            return 0;
        } else if (tree.isLeaf()){
            return 1;
        } else {
            int n = childs.size();
            int[] a = new int[n];
            for (int i = 0 ; i < n; i++){
                if (childs.get(i).isEmpty()){
                    a[i] = 1;
                } else{
                    a[i] = dept(childs.get(i)) + 1;
                }
            }
            Arrays.sort(a);
            return a[n-1];
        }
    }

    /**
     * 返回第i个子树
     * @param i
     * @return
     */
    public Tree getChild(int i){
        return childs.get(i);
    }

    /**
     * 求第一个孩子结点
     * @return
     */
    public Tree getFirstChild(){
        return childs.get(0);
    }

    /**
     * 判断是否为空树
     * @return
     */
    private boolean isEmpty(){
        if (childs.isEmpty() && data == null){
            return true;
        }
        return false;
    }

    /**
     * 求最后一个孩子结点
     * @return
     */
    public Tree getLastChild(){
        return childs.get(childs.size() - 1);
    }


    public List<Tree> getChilds(){
        return childs;
    }

    /**
     * 判断是否为叶子结点
     * @return
     */
    private boolean isLeaf(){
        if (childs.isEmpty()){
            return true;
        }
        return false;
    }

    /**
     * 获取根结点的数据
     * @return
     */
    public Object getRootData(){
        return data;
    }


    /**
     * 获取树根
     * @return
     */
    public Tree root(){
        return this;
    }

    /**
     * 设置根结点的数据
     * @param data
     */
    public void setRootData(Object data){
        this.data = data;
    }
}