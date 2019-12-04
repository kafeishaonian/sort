package behavior_model;

/**
 * 备忘录模式
 *      核心是设计备忘录以及用于管理备忘录的管理者类，
 * 定义：
 *      在不破坏封装性的前提下，捕获一个对象的内部状态，并在该对象之外保存这个状态，以便以后当需要时能将该对象恢复到原先保存的状态。
 * 优点：
 *      1.提供了一种可以恢复状态的机制。当用户需要时能够比较方便地将数据恢复到某个历史的状态。
 *      2.实现了内部状态的封装。除了创建它的发起人之外，其他对象都不能够访问这些状态信息。
 *      3.简化了发起人类。发起人不需要管理和保存其内部状态的各个备份，所有状态信息都保存在备忘录中，并由管理者进行管理，这符合单一职责原则。
 * 缺点：
 *      资源消耗大。如果要保存的内部状态信息过多或者特别频繁，将会占用比较大的内存资源。
 *
 * 结构：
 *      1.发起人（Originator）角色：记录当前时刻的内部状态信息，提供创建备忘录和恢复备忘录数据的功能，实现其他业务功能，它可以访问备忘录的所有信息。
 *      2.备忘录（Memento）角色：负责存储发起人的内部状态，在需要的时候提供这些内部状态给发起人。
 *      3.管理者（Caretaker）角色：对备忘录进行管理，提供保存与获取备忘录的功能，但其不能对备忘录的内容进行访问和修改。
 *
 * 场景：
 *      1.需要保存与恢复数据的场景
 *      2.需要提供一个可回滚操作的场景。
 *
 */
public class MementoMethodDemo {



    public static void main(String[] args) {
        MementoMethodDemo demo = new MementoMethodDemo();
//        demo.setMemento();
        demo.setOriginator();
    }


    private void setOriginator(){
        OriginatorPrototype or = new OriginatorPrototype();
        PrototypeCaretaker cr = new PrototypeCaretaker();
        or.setState("S0");
        System.out.println("初始状态:"+or.getState());
        cr.setMemento(or.creteMemento()); //保存状态
        or.setState("S1");
        System.out.println("新的状态:"+or.getState());
        or.restoreMemento(cr.getMemento()); //恢复状态
        System.out.println("恢复状态:"+or.getState());
    }

    private void setMemento(){
        Originator or = new Originator();
        Caretaker cr = new Caretaker();
        or.setState("S0");
        System.out.println("初始状态" + or.getState());
        cr.setMemento(or.creteMemento());//保存状态
        or.setState("S1");
        System.out.println("最新状态" + or.getState());
        or.restoreMemento(cr.getMemento());//恢复状态
        System.out.println("恢复状态" + or.getState());
    }

    class Memento{

        private String state;
        public Memento(String state){
            this.state = state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getState() {
            return state;
        }
    }

    class Originator{
        private String state;

        public void setState(String state) {
            this.state = state;
        }

        public String getState() {
            return state;
        }

        public Memento creteMemento(){
            return new Memento(state);
        }

        public void restoreMemento(Memento m){
            this.setState(m.getState());
        }
    }

    class Caretaker{
        private Memento memento;

        public void setMemento(Memento memento) {
            this.memento = memento;
        }

        public Memento getMemento() {
            return memento;
        }
    }

    //============================
    /**
     * 备忘录与原型模式
     */
    //发起人原型
    class OriginatorPrototype implements Cloneable{

        private String state;

        public void setState(String state) {
            this.state = state;
        }

        public String getState() {
            return state;
        }

        public OriginatorPrototype creteMemento(){
            return this.clone();
        }

        public void restoreMemento(OriginatorPrototype opt){
            this.setState(opt.getState());
        }

        public OriginatorPrototype clone(){
            try {
                return (OriginatorPrototype) super.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
    //原型管理者
    class PrototypeCaretaker{
        private OriginatorPrototype opt;

        public void setMemento(OriginatorPrototype opt){
            this.opt = opt;
        }

        public OriginatorPrototype getMemento(){
            return opt;
        }
    }

}
