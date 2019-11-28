package structure_model;

/**
 * 桥接模式
 * 定义：
 *      将抽象与实现分离，使他们可以单独变化。它是用组合关系代替继承关系来实现，从而降低了抽象和实现这两个可变维度的耦合度
 * 优点：
 *      1.由于抽象与实现分离，所以扩展能力强
 *      2.其实现细节对客户透明
 * 缺点：
 *      由于聚合关系建立的抽象层，要求开发者针对抽象化进行设计与编程，增加了系统的理解与设计难度
 *
 * 结构：
 *      1.抽象化（Abstraction）角色：定义抽象类，并包含一个对实现化对象的引用
 *      2.扩展抽象化（Refined Abstraction）角色：是抽象化角色的子类，实现父类中的业务方法，并通过组合关系调用实现化角色中的业务方法
 *      3.实现化（Implementor）角色： 定义实现化角色的接口，供扩展抽象化角色调用
 *      4.具体实现化（Concrete Implementor）角色：给出实现化接口的具体实现
 *
 * 应用场景：
 *      1.当一个类存在两个独立变化的维度，且这两个维度都需要扩展时
 *      2.当一个系统不希望使用继承或因为多层次继承导致系统类的个数急剧增加时。
 *      3.当一个系统需要在构件的抽象化角色和具体化角色之间增加更多的灵活性时。
 */
public class BridgeDemo {


    public static void main(String[] args) {
        BridgeDemo demo = new BridgeDemo();
        demo.setBridge();
    }

    private void setBridge(){
        Implementor impl = new ConcreteImplementor();
        Abstraction abs = new RefinedAbstraction(impl);
        abs.Operation();
    }


    //实现角色
    interface Implementor{
        public void OperationImpl();
    }
    //具体实现化接口
    class ConcreteImplementor implements Implementor{

        @Override
        public void OperationImpl() {
            System.out.println("具体实现化（Concrete Implementor）角色被访问");
        }
    }
    //抽象化角色
    abstract class Abstraction{
        protected Implementor impl;
        protected Abstraction(Implementor impl){
            this.impl = impl;
        }
        public abstract void Operation();
    }

    //扩展抽象化角色
    class RefinedAbstraction extends Abstraction{

        protected RefinedAbstraction(Implementor impl) {
            super(impl);
        }

        @Override
        public void Operation() {
            System.out.println("扩展抽象化（Refined Abstraction）角色被访问");
            impl.OperationImpl();
        }
    }


}
