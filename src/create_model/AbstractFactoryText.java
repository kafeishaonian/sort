package create_model;

/**
 * 抽象工厂
 * 定义：
 *      是一种为访问类提供一个创建一组相关或相互依赖对象的接口多个
 *      且访问类无须指定所要产品的具体类能得到同族的不同等级的产品的模式结构。
 * 满足条件：
 *      1.系统中有多个产品族，每个具体工厂创建同一族但属于不同等级结构的产品
 *      2.系统一次只可能消费其中某一族产品，即同族的产品一起使用
 * 优点：
 *      1.可以在类的内部对产品族中相关联的多等级产品共同管理，而不必专门引入多个新的类来进行管理
 *      2.当增加一个新的产品族时不需要修改源码，满足开闭原则。
 * 缺点:
 *      当产品族中增加一个新产品时，所有的工厂类都需要更改
 * 结构：
 *      1.抽象工厂(Abstract Factory):提供了创建产品的接口，它包含多个创建产品的方法newProduct(),可以创建多个不同等级的产品
 *      2.具体工厂(Concrete Factory):主要是实现抽象工厂中的多个抽象方法，完成具体产品的创建。
 *      3.抽象产品(Product): 定义了产品的规范，描述了产品的主要特性和功能，抽象工厂模式有多个抽象产品
 *      4.具体产品(Concrete Product): 实现了抽象产品角色所定义的接口，由具体工厂来创建，它同具体工厂之间是多对一的关系
 * 使用场景：
 *      1.当需要创建的对象是一系列相互关联或相互依赖的产品族时，
 *      2.系统中有多个产品族，但每次只使用其中的某一个产品族。
 *      3.系统中提供了产品的类库，且所有的产品接口相同，客户端不依赖产品实例的创建细节和内部结构。
 * 扩展：
 *      1.当增加一个新的产品种族时只需要增加一个新的具体工厂，不需要修改源代码，满足开闭原则。
 *      2.当产品族中需要增加一个新种类的产品时，则所有的工厂类都需要进行修改，不满足开闭原则。
 */
public class AbstractFactoryText {




    interface Product1{

    }
    interface Product2{

    }

    class ConcreteProduct11 implements Product1{

    }

    class ConcreteProduct12 implements Product2{

    }

    interface AbstractFactory{
        public Product1 newProduct1();
        public Product2 newProduct2();
    }

    class ConcreteFactory implements AbstractFactory{

        @Override
        public Product1 newProduct1() {
            return new ConcreteProduct11();
        }

        @Override
        public Product2 newProduct2() {
            return new ConcreteProduct12();
        }
    }
}
