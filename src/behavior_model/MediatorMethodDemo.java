package behavior_model;

import java.util.ArrayList;
import java.util.List;

/**
 * 中介者模式
 * 定义：
 * 定义一个中介对象来封装一系列对象之间的交互，使原有对象之间的耦合松散，且可以独立地改变他们之间的交互。
 * 优点:
 * 1.降低了对象之间的耦合性，使得对象易于独立地被复用。
 * 2.将对象之间的一对多关联转变为一对一的关联，提高系统的灵活性，使得系统易于维护和扩展。
 * <p>
 * 缺点：
 * 当同事类太多，中介者的职责将很大，它会变得复杂而庞大，以至于系统难以维护
 * 结构：
 * 1.抽象中介者（Mediator）角色: 它是中介者的接口，提供了同事对象注册与转发同事对象信息的抽象方法。
 * 2.具体中介者（Concrete Mediator）角色：实现中介者接口，定义一个List来管理同事对象，协调各个同事角色之间的交互关系，因此它依赖于同事角色。
 * 3.抽象同事类（Colleague）角色：定义同事类的接口，保存中介者对象，提供同事对象交互的抽象方法，实现所有相互影响的同事类的公共功能。
 * 4.具体同事类（Concrete Colleague）角色：是抽象同事类的实现者，当需要与其他同事对象交互时，由中介者对象负责后续的交互。
 * 应用场景：
 * 1.当对象之间存在复杂的网状结构关系而导致依赖关系混乱且难以复用时
 * 2.当想创建一个运行于多个类之间的对象，又不想生成新的子类时。
 * <p>
 * 扩展：
 * 1.不定义中介者接口，把具体中介者对象实现成为单例。
 * 2.同事对象不持有中介者，而是在需要的时候直接获取中介者对象并调用。
 */
public class MediatorMethodDemo {

    public static void main(String[] args) {
        MediatorMethodDemo demo = new MediatorMethodDemo();
//        demo.setMediator();

        SimpleColleague c1 = new SimpleConcreteColleague1();
        SimpleColleague c2 = new SimpleConcreteColleague2();
        c1.send();
        System.out.println("=================");
        c2.send();
    }

    private void setMediator() {
        Mediator md = new ConcreteMediator();
        Colleague c1 = new ConcreteColleague1();
        Colleague c2 = new ConcreteColleague2();
        Colleague c3 = new ConcreteColleague3();
        md.register(c1);
        md.register(c2);
        md.register(c3);
        c1.send();
        System.out.println("========");
        c2.send();
        System.out.println("===========");
        c3.send();
    }

    /**
     * 实现
     */
    //抽象中介者
    abstract class Mediator {
        public abstract void register(Colleague colleague);

        public abstract void relay(Colleague colleague); //转发
    }

    class ConcreteMediator extends Mediator {

        private List<Colleague> colleagues = new ArrayList<>();

        @Override
        public void register(Colleague colleague) {
            if (!colleagues.contains(colleague)) {
                colleagues.add(colleague);
                colleague.setMediator(this);
            }
        }

        @Override
        public void relay(Colleague colleague) {
            for (Colleague obj : colleagues) {
                if (!obj.equals(colleague)) {
                    ((Colleague) obj).receive();
                }
            }
        }
    }

    //抽象同事类
    abstract class Colleague {

        protected Mediator mediator;

        public void setMediator(Mediator mediator) {
            this.mediator = mediator;
        }

        public abstract void receive();

        public abstract void send();
    }

    class ConcreteColleague1 extends Colleague {

        @Override
        public void receive() {
            System.out.println("具体同事类1收到请求");
        }

        @Override
        public void send() {
            System.out.println("具体同事类1发出请求");
            mediator.relay(this);//请中介者转发
        }
    }

    class ConcreteColleague2 extends Colleague {

        @Override
        public void receive() {
            System.out.println("具体同事类2收到请求。。");
        }

        @Override
        public void send() {
            System.out.println("具体同事类2发出请求");
            mediator.relay(this);//请中介转发
        }
    }

    class ConcreteColleague3 extends Colleague {

        @Override
        public void receive() {
            System.out.println("具体同事类3收到请求");
        }

        @Override
        public void send() {
            System.out.println("具体同事类3发出请求");
            mediator.relay(this);
        }
    }
}

/**
 * 简单单例中介者
 */
class SimpleMediator {
    private static SimpleMediator smd = new SimpleMediator();
    private List<SimpleColleague> colleagues = new ArrayList<>();

    private SimpleMediator() {
    }

    public static SimpleMediator getMedium() {
        return smd;
    }

    public void register(SimpleColleague colleague){
        if (!colleagues.contains(colleague)){
            colleagues.add(colleague);
        }
    }

    public void relay(SimpleColleague scl){
        for (SimpleColleague ob: colleagues){
            if (!ob.equals(scl)){
                ob.receive();
            }
        }
    }

}

//抽象同事类
interface SimpleColleague {
    void receive();

    void send();
}

class SimpleConcreteColleague1 implements SimpleColleague{


    SimpleConcreteColleague1(){
        SimpleMediator smd = SimpleMediator.getMedium();
        smd.register(this);
    }

    @Override
    public void receive() {
        System.out.println("具体同事类1：收到请求");
    }

    @Override
    public void send() {
        SimpleMediator smd = SimpleMediator.getMedium();
        System.out.println("具体同事类1，发送请求");
        smd.relay(this);
    }
}

class SimpleConcreteColleague2 implements SimpleColleague{

    SimpleConcreteColleague2(){
        SimpleMediator smd = SimpleMediator.getMedium();
        smd.register(this);
    }


    @Override
    public void receive() {
        System.out.println("具体同事类2：收到请求");
    }

    @Override
    public void send() {
        SimpleMediator smd = SimpleMediator.getMedium();
        System.out.println("具体同事类2，发送请求");
        smd.relay(this);
    }
}