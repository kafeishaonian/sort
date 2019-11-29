package behavior_model;

import java.util.ArrayList;

/**
 * 命令模式
 *      可以将系统中的相关操作抽象成命令，使调用者与实现者相关分离
 * 定义：
 *      将一个请求封装为一个对象，使发出请求的责任和执行请求的责任分割开。
 *      这样两者之间通过命令对象进行沟通，这样方便将命令对象进行存储、传递、调用、增加和管理
 * 优点：
 *      1.降低系统的耦合度。命令模式能将调用操作的对象与实现该操作的对象解耦。
 *      2.增加或删除命令非常方便。采用命令模式增加与删除命令不会影响其他类，它满足“开闭原则”,对扩展比较灵活。
 *      3.可以实现宏命令。命令模式可以与组合模式结合，将多个命令装配成一个组合命令，即宏命令。
 *      4.方便实现Undo和Redo操作。命令模式可以与备忘录模式结合，实现命令的撤销与恢复。
 * 缺点：
 *      可以产生大量具体命令类。因此计对每一个具体操作都需要设计一个具体命令类，这将增加系统的复杂性。
 *
 * 结构：
 *      1.抽象命令类（Command）角色：声明执行命令的接口，拥有执行命令的抽象方法execute()
 *      2.具体命令（Concrete Command）角色：是抽象命令类的具体实现类，它拥有接收者对象，并通过调用接收者的功能来完成命令要执行的操作。
 *      3.实现着/接收者（Receiver）角色：执行命令功能的相关操作，是具体命令对象业务的真正实现者。
 *      4.调用者/请求者（Invoker）角色：是请求的发送者，它通常拥有很多的命令对象，并通过访问命令对象来执行相关请求，它不直接访问接收者。
 *
 * 应用场景：
 *      1.当系统需要将请求调用者与请求接收者解耦时，命令模式使得调用者和接收者不直接交互。
 *      2.当系统需要随机请求命令或经常增加或删除命令时，命令模式比较方便实现这些功能
 *      3.当系统需要执行一组操作时，命令模式可以定义宏命令来实现该功能。
 *      4.当系统需要支持命令的撤销（Undo）操作和恢复（Redo）操作时，可以将命令对象存储起来，采用备忘录模式来实现。
 *
 */
public class CommandMethodDemo {

    public static void main(String[] args) {
        CommandMethodDemo demo = new CommandMethodDemo();
//        demo.setCommand();
        demo.setComposite();
        
    }

    private void setCommand(){
        Command command = new ConcreteCommand();
        Invoker inv = new Invoker(command);
        System.out.println("客户访问调用者的call()方法。。。");
        inv.call();
    }

    private void setComposite(){
        AbstractCommand command1 = new ConcreteCommand1();
        AbstractCommand command2 = new ConcreteCommand2();
        CompositeInvoker invoker = new CompositeInvoker();
        invoker.add(command1);
        invoker.add(command2);
        System.out.println("客户访问调用者的execute（）方法");
        invoker.execute();
    }

    /**
     * 实现
     */
    //调用者
    class Invoker{
        private Command command;
        public Invoker(Command command){
            this.command = command;
        }

        public void setCommand(Command command) {
            this.command = command;
        }

        public void call(){
            System.out.println("调用者执行命令command...");
            command.execute();
        }
    }


    //抽象命令类
    interface Command{
        public abstract void execute();
    }
    //具体命令类
    class ConcreteCommand implements Command{

        private Receiver receiver;
        ConcreteCommand(){
            receiver = new Receiver();
        }

        @Override
        public void execute() {
            receiver.action();
        }
    }

    //接收者
    class Receiver{

        public void action(){
            System.out.println("接收者的action()方法被调用。。。");
        }
    }

    //=====================================================
    /**
     * 有时将命令模式和组合模式联合使用，这就构成了宏命令模式，也叫组合命令模式。宏命令包含了一组命令，
     * 它充当了具体命令与调用者的双重角色，执行它时将递归调用它所包含的所有命令。
     */


    //抽象命令
    interface AbstractCommand{
        public abstract void execute();
    }
    //树叶构件：具体命令1
    class ConcreteCommand1 implements AbstractCommand{

        private CompositeReceiver receiver;

        ConcreteCommand1(){
            receiver = new CompositeReceiver();
        }

        @Override
        public void execute() {
            receiver.action1();
        }
    }

    class ConcreteCommand2 implements AbstractCommand{

        private CompositeReceiver receiver;

        ConcreteCommand2(){
            receiver = new CompositeReceiver();
        }


        @Override
        public void execute() {
            receiver.action2();
        }
    }

    //接收者
    class CompositeReceiver{

        public void action1(){
            System.out.println("接收者的action1方法被调用。。。。");
        }

        public void action2(){
            System.out.println("接收者的action2方法被调用。。。。。");
        }
    }

    class CompositeInvoker implements AbstractCommand{

        private ArrayList<AbstractCommand> children = new ArrayList<>();

        public void add(AbstractCommand command){
            children.add(command);
        }

        public void remove(AbstractCommand command){
            children.remove(command);
        }

        public AbstractCommand getChild(int l){
            return children.get(l);
        }

        @Override
        public void execute() {
            for (Object obj: children){
                ((AbstractCommand) obj).execute();
            }
        }
    }

}
