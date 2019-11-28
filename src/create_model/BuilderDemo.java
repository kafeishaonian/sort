package create_model;

/**
 * 建造者模式
 * 定义：
 *      将一个复杂对象的构造与它的表示分离，使同样的构建过程可以创建不同的表示，这样的设计模式被称为建造者模式
 * 优点：
 *      1.各个具体的建造者相互独立，有利于系统的扩展。
 *      2.客户端不必知道产品内部组成的细节，便于控制细节风险。
 * 缺点：
 *      1.产品的组成部分必须相同，这限制了其使用范围。
 *      2.如果产品的内部变化复杂，该模式会增加更多的建造者类。
 * 建造者模式与工厂模式关注点不同：
 *      建造者模式关注零部件的组装过程，而工厂方法模式更注重零部件的创建过程，但两者可以结合使用
 * 结构:
 *      1.产品角色(Product):它是包含多个组成部件的复杂对象，有具体建造者来创建其各个组成部件。
 *      2.抽象建造者(Builder): 它是一个包含创建产品各个子部件的抽象方法的接口，通常还包含一个返回复杂产品的方法getResult()
 *      3.具体建造者(Concrete Builder)： 实现Builder接口，完成复杂产品的各个部件的具体创建方法。
 *      4.指挥者(Director):它调用建造者对象中的部件构造与装配方法完成复杂对象的创建，在指挥者中不涉及具体产品信息。
 * 应用场景：
 *      1.创建的对象较复杂，由多个部件构成，各部件面临着复杂的变化，但构件间的建造顺序是稳定的
 *      2.创建复杂对象的算法独立于该对象的组成部分以及他们的装配方式，即产品的构建过程和最终的表示是独立的。
 * 扩展：
 *      建造者模式在应用过程中可以根据需求改变，如果创建的产品种类只有一种，只需要一个具体的建造者，这时可以省略掉抽象建造者，甚至可以省略掉指挥者角色
 *
 */
public class BuilderDemo {

    public static void main(String[] args) {
        BuilderDemo demo = new BuilderDemo();
        demo.setBuilder();
    }

    private void setBuilder(){
        Builder builder = new ConcreteBuilder();
        Director director = new Director(builder);
        Product product = director.construct();
        product.show();
    }

    //产品角色，包含多个组成部件的复杂对象
    class Product{
        private String partA;
        private String partB;
        private String partC;

        public void setPartA(String partA) {
            this.partA = partA;
        }

        public void setPartB(String partB) {
            this.partB = partB;
        }

        public void setPartC(String partC) {
            this.partC = partC;
        }
        public void show(){
            System.out.println("产品的特性" + partA + partB + partC);
        }
    }
    //抽象建造者：包含创建产品各个子部件的抽象方法。
    abstract class Builder{
        protected Product product = new Product();
        public abstract void buildPartA();
        public abstract void buildPartB();
        public abstract void buildPartC();
        //返回产品对象
        public Product getResult(){
            return product;
        }
    }

    //具体建造者：实现了抽象建造者接口
    class ConcreteBuilder extends Builder{

        @Override
        public void buildPartA() {
            product.setPartA("建造PartA");
        }

        @Override
        public void buildPartB() {
            product.setPartB("建造PartB");
        }

        @Override
        public void buildPartC() {
            product.setPartC("建造PartC");
        }
    }

    //指挥者：调用建造者中的方法完成复杂对象的创建
    class Director{
        private Builder builder;
        public Director(Builder builder){
            this.builder = builder;
        }
        public Product construct(){
            builder.buildPartA();
            builder.buildPartB();
            builder.buildPartC();
            return builder.getResult();
        }
    }

}
