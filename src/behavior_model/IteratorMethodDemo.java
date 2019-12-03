package behavior_model;

import java.util.ArrayList;
import java.util.List;

/**
 * 迭代器模式
 *      通过将聚合对象的遍历行为分离出来，抽象成迭代器类来实现，其目的是在不暴露聚合对象的内部结构的情况下，
 *      让外部代码透明地访问聚合的内部数据。
 * 定义:
 *      提供一个对象来顺序访问聚合对象中的一系列数据，而不暴露聚合对象的内部表示。
 * 优点：
 *      1.访问一个聚合对象的内容而无须暴露它的内部表示。
 *      2.遍历任务交由迭代器完成，简化了聚合类。
 *      3.它支持以不同方式遍历一个聚合，甚至可以自定义迭代器的子类以支持新的遍历。
 *      4.增加新的聚合类和迭代器类都很方便，无序修改原有代码。
 *      5.封装性良好，为遍历不同的聚合结构提供一个统一的接口。
 * 缺点：
 *      增加了类的个数，这在一定程度上增加了系统的复杂性。
 * 结构：
 *      1.抽象聚合（Aggregate）角色：定义存储，添加，删除聚合对象以及创建迭代器类对象的接口。
 *      2.具体聚合（Concrete Aggregate）角色：实现抽象聚合类，返回一个具体迭代器的实例。
 *      3.抽象迭代器（Iterator）角色：定义访问和遍历聚合元素的接口，通过办好hasNext(),first(),next()等方法
 *      4.具体迭代器（Concrete Iterator）角色：实现抽象迭代器接口中所定义的方法，完成对聚合对象的遍历，记录遍历的当前位置。
 * 扩展：
 *      1.当需要为聚合对象提供多种遍历方式时。
 *      2.当需要为遍历不同的聚合结构提供一个统一的接口时。
 *      3.当访问一个聚合对象的内容而无序暴露其内部细节的表示时。
 */
public class IteratorMethodDemo {

    public static void main(String[] args) {
        IteratorMethodDemo demo = new IteratorMethodDemo();
        demo.setIterator();
    }

    private void setIterator(){
        Aggregate ag = new ConcreteAggregate();
        ag.add("中山大学");
        ag.add("华南理工");
        ag.add("韶关学院");
        System.out.println("聚合的内容有。。。。");
        Iterator it = ag.getIterator();
        while (it.hasNext()){
            Object obj = it.next();
            System.out.println(obj.toString() + "\t");
        }
        Object ob = it.first();
        System.out.println("\nFirst:" + ob.toString());
    }



    //抽象聚合
    interface Aggregate{
        public void add(Object obj);
        public void remove(Object obj);
        public Iterator getIterator();
    }

    //具体聚合
    class ConcreteAggregate implements Aggregate{

        private List<Object> lists = new ArrayList<>();

        @Override
        public void add(Object obj) {
            lists.add(obj);
        }

        @Override
        public void remove(Object obj) {
            lists.remove(obj);
        }

        @Override
        public Iterator getIterator() {
            return new ConcreteIterator(lists);
        }
    }

    //抽象迭代器
    interface Iterator{
        Object first();
        Object next();
        boolean hasNext();
    }

    //具体迭代器
    class ConcreteIterator implements Iterator{

        private List<Object> lists = null;
        private int index = -1;
        public ConcreteIterator(List<Object> lists){
            this.lists = lists;
        }

        @Override
        public Object first() {
            index = 0;
            Object obj = lists.get(index);
            return obj;
        }

        @Override
        public Object next() {
            Object obj = null;
            if (this.hasNext()){
                obj = lists.get(++index);
            }
            return obj;
        }

        @Override
        public boolean hasNext() {
            if (index < lists.size() -1){
                return true;
            } else {
                return false;
            }
        }
    }
}
