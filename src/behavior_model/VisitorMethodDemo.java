package behavior_model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 访问者模式
 *      实现的关键是如果将作用于元素的操作分离出来封装称独立的类。
 * 定义：
 *      将作用于某种数据结构中的各元素的操作分离出来封装成独立的类，使其在不改变数据结构的前提下可以添加作用于这些元素的新的操作，
 *      为数据结构猴子那个的每个元素提供多种访问方式。
 * 优点：
 *      1.扩展性好。能够在不修改对象结构中的元素的情况下，为对象结构中的元素添加新的功能。
 *      2.复用性好。可以通过访问者来定义整个对象结构通用的功能，从而提高系统的复用程度。
 *      3.灵活性好。访问者模式将数据结构与作用于结构上的操作解耦，使得操作集合可相对自由地演化而不影响系统的数据结构。
 *      4.符合单一职责原则。访问者模式把相关的行为封装在一起，构成一个访问者，使每一个访问者的功能都比较单一。
 *
 * 缺点：
 *      1.增加新的元素类很困难。在访问者模式中，每增加一个新的元素，都要在每一个具体访问者类中增加相应的具体操作，这违背了“开闭原则”。
 *      2.破坏封装。访问者模式中具体元素对访问者公布细节，这破坏了对象的封装性。
 *      3.违反了依赖倒置原则。访问者模式依赖了具体类，而没有依赖抽象类。
 * 结构：
 *      1.抽象访问者（Visitor）角色：定义一个访问具体元素的接口，为每个具体元素类对应一个访问操作visit(),该操作中的参数类型标识了被访问的具体元素。
 *      2.具体访问者（Concrete Visitor）角色：实现抽象访问者角色中声明的各个访问操作，确定访问者访问一个元素时该做什么。
 *      3.抽象元素（Element）角色：声明一个包含接收操作accept()的接口，被接受的访问者对象作为accept()方法的参数。
 *      4.具体元素（Concrete Element）角色：实现抽象元素角色提供的accept()操作，其方法体通常都是visitor.visit(this),
 *      另外具体元素中可能还包含本身业务逻辑的相关操作。
 *      5.对象结构（Object Structure）角色：是一个包含元素角色的容器，提供让访问者对象遍历容器中所有的元素方法，通常由List, Set,Map等聚合类实现。
 *
 * 应用场景：
 *      1.对象结果相对稳定，但其操作算法经常变化的程序。
 *      2.对象结构中的对象需要提供多种不同且不相关的操作，而且要避免让这些操作的变化影响对象的结构。
 *      3.对象结构包含很多类型的对象，希望对这些对象实施一些依赖于其具体类型的操作。
 */
public class VisitorMethodDemo {

    public static void main(String[] args) {
        VisitorMethodDemo demo = new VisitorMethodDemo();
        demo.setVisitor();
    }

    private void setVisitor(){
        ObjectStructure os = new ObjectStructure();
        os.add(new ConcreteElementA());
        os.add(new ConcreteElementB());
        Visitor v = new ConcreteVisitorA();
        os.accept(v);
        System.out.println("===============");
        v  = new ConcreteVisitorB();
        os.accept(v);
    }
    /**
     * 具体实现
     */
    //抽象访问者
    interface Visitor{
        void visit(ConcreteElementA element);
        void visit(ConcreteElementB element);
    }

    //具体访问者
    class ConcreteVisitorA implements Visitor{

        @Override
        public void visit(ConcreteElementA element) {
            System.out.println("具体访问者A访问===" + element.operationA());
        }

        @Override
        public void visit(ConcreteElementB element) {
            System.out.println("具体访问者A访问===" + element.operationB());
        }
    }

    class ConcreteVisitorB implements Visitor{

        @Override
        public void visit(ConcreteElementA element) {
            System.out.println("具体访问者B访问===" + element.operationA());
        }

        @Override
        public void visit(ConcreteElementB element) {
            System.out.println("具体访问者B访问===" + element.operationB());
        }
    }

    //抽象元素类
    interface Element{

        void accept(Visitor visitor);
    }
    //具体元素类
    class ConcreteElementA implements Element{

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(this);
        }

        public String operationA(){
            return "具体元素A的操作";
        }
    }

    class ConcreteElementB implements Element{

        @Override
        public void accept(Visitor visitor) {
            visitor.visit(this);
        }

        public String operationB(){
            return "具体元素B的操作";
        }
    }

    class ObjectStructure{

        private List<Element> lists = new ArrayList<>();
        public void accept(Visitor visitor){
            Iterator<Element> i = lists.iterator();
            while (i.hasNext()){
                i.next().accept(visitor);
            }
        }

        public void add(Element element){
            lists.add(element);
        }

        public void remove(Element element){
            lists.remove(element);
        }

    }



}
