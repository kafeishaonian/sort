package behavior_model;

import java.util.*;

/**
 * 观察者模式
 * 定义：
 *      指对个对象间存在一对多的依赖关系，当一个对象的状态发生改变时，所有依赖于它的对象都得到通知并被自动更新。
 * 优点:
 *      1.降低了目标与观察者之间的耦合关系，两者之间是抽象耦合关系。
 *      2.目标与观察者之间建立了一套出发机制。
 * 缺点：
 *      1.目标与观察者之间的依赖关系并没有完全解除，而且有可能出现循环引用。
 *      2.当观察者对象很多时，通知的发布会花费很多时间，影响程序的效率。
 * 注意：
 *      具体目标对象与具体观察者对象之间不能直接调用，否则将使两者之间紧密耦合起来，这违反了面向对象设计原则。
 *
 * 结构：
 *      1.抽象主题（Subject）角色；也叫抽象目标类，它提供了一个用于保存观察者对象的聚集类和增加、删除观察者对象的方法，以及通知所有观察者的抽象方法。
 *      2.具体主题（Concrete Subject）角色：也叫具体目标类，它实现抽象目标中的通知方法，当具体主题的内部状态发生改变时，通知所有注册过的观察者对象。
 *      3.抽象观察者（Observer）角色：是一个抽象类或接口，它包含了一个更新自己的抽象方法，当接到具体主题的更改通知时被通知。
 *      4.具体观察者（Concrete Subject）角色：实现抽象观察者中定义的抽象方法，以便在得到目标的更改通知时更新自身的状态。
 *
 * 应用场景：
 *      1.对象间存在一对多关系，一个对象的状态发生改变会影响其他状态。
 *      2.当一个抽象模型有两个方面，其中一个方面依赖于另一个方面，可将这两者封装在独立的对象中以使它们可以各自独立低改变和复用
 *
 * 扩展:
 *      1.Observable类是抽象目标类，它有一个Vector向量，用于保存所有要通知的观察者对象。
 *         (1):void addObserver(Observer o)方法：用于将新的观察者对象添加到向量中。
 *         (2):void notifyObservers(Object arg)方法：调用向量中的所有观察者对象的update方法，通知他们数据发生改变，通常越晚加入向量的观察者越早得到通知。
 *         (3):void setChange()方法：用来设置一个boolean类型的内部标志位，注明目标对象发生了变化。
 *      2.Observer接口是抽象观察者，它监视目标对象的变化，当目标对象发生变化时，观察者得到通知，并调用void update(Observable o, Object arg)方法，进行相应的工作。
 */
public class ObserverMethodDemo {


    public static void main(String[] args) {
        ObserverMethodDemo demo = new ObserverMethodDemo();
//        demo.setObserver();
//        demo.setBell();
        demo.setObser();
    }

    private void setObserver(){
        Subject subject = new ConcreteSubject();
        Observer observer1 = new ConcreteObserver1();
        Observer observer2 = new ConcreteObserver2();
        subject.add(observer1);
        subject.add(observer2);
        subject.notifyObserver();
    }

    private void setBell(){
        BellEventSource bell=new BellEventSource();    //铃（事件源）
        bell.addPersonListener(new TeachEventListener()); //注册监听器（老师）
        bell.addPersonListener(new StuEventListener());    //注册监听器（学生）
        bell.ring(true);   //打上课铃声
        System.out.println("------------");
        bell.ring(false);  //打下课铃声
    }

    private void setObser(){
        OilFutures oil=new OilFutures();
        java.util.Observer bull=new Bull(); //多方
        java.util.Observer bear=new Bear(); //空方
        oil.addObserver(bull);
        oil.addObserver(bear);
        oil.setPrice(10);
        oil.setPrice(-8);
    }
    /**
     * 实现
     */
    //抽象目标
    abstract class Subject{
        protected List<Observer> observers = new ArrayList<>();

        public void add(Observer observer){
            observers.add(observer);
        }

        public void remove(Observer observer){
            observers.remove(observer);
        }

        public abstract void notifyObserver();

    }

    class ConcreteSubject extends Subject{

        @Override
        public void notifyObserver() {
            System.out.println("具体目标发生改变时");
            System.out.println("-----------------------");

            for (Object obj : observers){
                ((Observer) obj).response();
            }
        }
    }

    //抽象观察者
    interface Observer{
        void response();
    }

    class ConcreteObserver1 implements Observer{

        @Override
        public void response() {
            System.out.println("具体观察1做出反应");
        }
    }

    class ConcreteObserver2 implements Observer{

        @Override
        public void response() {
            System.out.println("具体观察2做出反应");
        }
    }

    //==================================教学响铃
    //铃声事件类
    class RingEvent extends EventObject {

        private boolean sound;
        public RingEvent(Object source, boolean sound) {
            super(source);
            this.sound = sound;
        }

        public void setSound(boolean sound) {
            this.sound = sound;
        }

        public boolean isSound() {
            return sound;
        }
    }

    //目标类，事件源，铃
    class BellEventSource{

        private List<BellEventListener> listeners;
        public BellEventSource(){
            listeners = new ArrayList<>();
        }

        //给事件源绑定监视器
        public void addPersonListener(BellEventListener listener){
            listeners.add(listener);
        }

        //设置触发器
        public void ring(boolean sound){
            String type = sound ? "上课" : "下课";
            System.out.println(type + "响");
            RingEvent event = new RingEvent(this, sound);
            notifies(event);
        }

        protected void notifies(RingEvent event){
            BellEventListener listener = null;
            Iterator<BellEventListener> iterator = listeners.iterator();
            while (iterator.hasNext()){
                listener = iterator.next();
                listener.heardBell(event);
            }
        }

    }

    //抽象观察者类，铃声事件监听器
    interface BellEventListener extends EventListener{
        public void heardBell(RingEvent event);
    }

    class TeachEventListener implements BellEventListener{

        @Override
        public void heardBell(RingEvent event) {
            if (event.isSound()){
                System.out.println("老师上课了");
            } else {
                System.out.println("老师下课了");
            }
        }
    }

    class StuEventListener implements BellEventListener{

        @Override
        public void heardBell(RingEvent event) {
            if (event.isSound()){
                System.out.println("同学们上课了");
            } else {
                System.out.println("同学们下课了");
            }
        }
    }

    //================扩展应用====================
    //具体目标类：原油期货
    class OilFutures extends Observable{
        private float price;

        public float getPrice() {
            return price;
        }

        public void setPrice(float price) {
            super.setChanged();   //设置内部标志位，注明数据发生变化
            super.notifyObservers(price);  //通知观察者价格改变了
            this.price = price;
        }
    }

    //具体观察者类：多方
    class Bull implements java.util.Observer{

        @Override
        public void update(Observable o, Object arg) {
            Float price = ((Float) arg).floatValue();
            if (price > 0){
                System.out.println("油价上涨"+price+"元，多方高兴");
            } else {
                System.out.println("油价下跌"+ price+"元，多方伤心");
            }
        }
    }

    //具体观察者类：空方
    class Bear implements java.util.Observer{

        @Override
        public void update(Observable o, Object arg) {
            Float price = ((Float) arg).floatValue();
            if (price > 0){
                System.out.println("油价上涨"+price+"元，空房伤心");
            } else {
                System.out.println("油价下跌"+ price+"元，空房高兴    ");
            }
        }
    }
}
