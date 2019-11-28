package structure_model;

import java.util.ArrayList;

/**
 * 组合模式
 * 定义：
 *      有时又叫做部分-整体模式，它是一种将对象组合成树状的层次结构模式，
 *      用来表示“部分-整体”的关系，使用户对单个对象和组合对象具有一致的访问性
 * 优点：
 *      1.组合模式使得客户端代码可以一致地处理单个对象和组合对象，无序关心自己处理的是单个对象，还是组合对象，这简化了客户端代码
 *      2.更容易在组合体内加入新的对象，客户端不会因为加入新的对象而更改源代码，满足“开闭原则”；
 * 缺点：
 *      1.设计较复杂，客户端需要花更多时间清理类之间的层次关系。
 *      2.不容易限制容器中的构件。
 *      3.不容易用继承的方式来增加构件的新功能
 * 结构：
 *      1.抽象构件（Component）角色：它的主要作用是为树叶构件和树枝构件声明公共接口，并实现它们的默认功能。
 *      在透明式的组合模式中抽象构件还声明访问和管理子类的接口，在安全式的组合模式中不声明访问和管理子类的接口，管理工作由树枝构件完成.
 *      2.树叶构件（Leaf）角色：是组合中的叶节点对象，它没有子节点，用于实现抽象构件角色中声明的公共接口。
 *      3.树枝构件（Composite）角色：是组合中的分支节点对象，它有子节点。它实现了抽象构件角色中声明的接口，
 *      它的主要作用是存储和管理子部件，通常包含Add(),Remove().GetChild()等方法
 * 透明模式的组合模式：
 *      由于抽象构件声明了所有子类中的全部方法，所以客户端无须区别树叶对象和树枝对象，对客户端来说是透明的。
 *      但缺点是：树叶构件本来没有Add(),Remove()及GetChild()方法，却要实现他们，这样会带来一些安全问题。
 * 安全方式：
 *      将管理子构件的方法移到树枝构件中，抽象构件和树叶构件没有对子对象的管理方法，这样就避免了上一种方式的安全性问题，
 *      但由于叶子和分支有不同的接口，客户端在调用时要知道树叶对象和树枝对象的存在。所以失去了透明性。
 *
 * 应用场景：
 *      1.在需要表示一个对象整体与部分的层次结构的场合
 *      2.要求对用户隐藏组合对象与单个对象的不同，用户可以用统一的接口使用组合结构中的所有对象的场合。
 *
 */
public class CompositeDemo {

    public static void main(String[] args) {
        CompositeDemo demo = new CompositeDemo();
        demo.setComposite();
    }

    private void setComposite(){
        Component c0 = new Composite();
        Component c1 = new Composite();
        Component leaf1 = new Leaf("1");
        Component leaf2 = new Leaf("2");
        Component leaf3 = new Leaf("3");
        c0.add(leaf1);
        c0.add(c1);
        c1.add(leaf2);
        c1.add(leaf3);
        c0.operation();
    }

    /**
     * 实现
     */
    //抽象构件
    interface Component{
        public void add(Component t);
        public void remove(Component c);
        public Component getChild(int i);
        public void operation();
    }
    //树叶构件
    class Leaf implements Component{

        private String name;
        public Leaf(String name){
            this.name = name;
        }

        @Override
        public void add(Component t) { }

        @Override
        public void remove(Component c) { }

        @Override
        public Component getChild(int i) {
            return null;
        }

        @Override
        public void operation() {
            System.out.println("树叶"+ name +"被访问！");
        }
    }
    //树枝构件
    class Composite implements Component{

        private ArrayList<Component> children = new ArrayList<>();

        @Override
        public void add(Component t) {
            children.add(t);
        }

        @Override
        public void remove(Component c) {
            children.remove(c);
        }

        @Override
        public Component getChild(int i) {
            return children.get(i);
        }

        @Override
        public void operation() {
            for (Object obj: children){
                ((Component) obj).operation();
            }
        }
    }
}
