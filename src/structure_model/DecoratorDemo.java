package structure_model;

/**
 * 装饰模式
 * 通常情况下，扩展一个类的功能会使用继承方式来实现。单继承具有静态特征，耦合度高，并且随着扩展功能的增多，
 * 子类会很膨胀。如果使用组合关系来创建一个包装对象（即装饰对象）来包裹真实对象，并在保持真实对象的类结构不变的前提下，
 * 为期提供额外的功能，这就是装饰模式的目标。
 * 定义：
 *      指在不改变现有对象结构的情况下，动态地给该对象增加一些职责（增加其额外功能）的模式，它属于对象结构模式
 * 优点：
 *      1.采用装饰模式扩展对象的功能比采用继承方式更加灵活
 *      2.可以设计出多个不同的具体装饰类，创造出多个不同行为的组合
 * 缺点：
 *      装饰模式增加了许多子类，如果过度使用会使程序变得很复杂
 *
 * 结构：
 *      1.抽象构件（Component）角色：定义一个抽象接口以规范准备接收附加责任的对象
 *      2.具体构件（Concrete Component）角色： 实现抽象构件，通过装饰角色为其添加一些职责
 *      3.抽象装饰（Decorator）角色： 继承抽象构件，并包含具体构件的实例，可以通过其子类扩展具体构件的功能
 *      4.具体装饰（Concrete Decorator）角色：实现抽象装饰的相关方法，并给出具体构件对象添加附加的责任
 * 应用场景：
 *      1.当需要给一个现有类添加附加指责，而又不能采用生成子类的方法进行扩充时，
 *      2.当需要通过对现有的一组基本功能进行排列组合而产生非常多的功能时，采用继承关系很难实现，而采用装饰模式却很好实现。
 *      3.当对象的功能要求可以动态地添加，也可以在动态地撤销时
 *
 */
public class DecoratorDemo {

    public static void main(String[] args) {
        DecoratorDemo demo = new DecoratorDemo();
        demo.setDecorator();
    }

    private void setDecorator(){
        Component p = new ConcreteComponent();
        p.operation();
        System.out.println("=============");
        Component d = new ConcreteDecorator(p);
        d.operation();
    }
    /**
     * 实现
     */
    //抽象构件角色
    interface Component{
        public void operation();
    }
    //具体构件角色
    class ConcreteComponent implements Component{

        public ConcreteComponent(){
            System.out.println("创建具体构件角色");
        }

        @Override
        public void operation() {
            System.out.println("调用具体构件角色的方法operation");
        }
    }
    //抽象装饰角色
    class Decorator implements Component{

        private Component component;
        public Decorator(Component component){
            this.component = component;
        }

        @Override
        public void operation() {
            component.operation();
        }
    }
    //具体装饰角色
    class ConcreteDecorator extends Decorator{

        public ConcreteDecorator(Component component) {
            super(component);
        }

        @Override
        public void operation() {
            super.operation();
            addFunction();
        }

        public void addFunction(){
            System.out.println("为具体构件角色增加额外的功能addedFunction()");
        }
    }

}
