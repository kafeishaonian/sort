package behavior_model;

/**
 * 模板方法模式
 *      是一种类行为型模式，需要注意抽象类与子类之间的协作，它用到了虚函数的多态性计技术以及“不用调用我，让我来调用你”的反向控制技术。
 * 定义：
 *      定义一个操作中的算法骨架，而将算法的一些步骤延迟到子类中，使得子类可以在不改变该算法结构的情况下重定义该算法的某些特定步骤。
 * 优点：
 *      1.它封装了不变部分，扩展可变部分。它把认为是不变部分的算法封装到父类中实现，而把可变部分算法由子类继承实现，便于子类继续扩展。
 *      2.它在父类中提取了公共的部分代码，便于代码复用。
 *      3.部分方法是由子类实现的，因此子类可以通过扩展方式增加相应的功能，复合开闭原则。
 * 缺点：
 *      1.对每个不同的实现都需要定义一个子类，这会导致类的个数增加，系统更加庞大，设计也更加抽象。
 *      2.父类中的抽象方法由子类实现，子类执行的结果会影响父类的结果，这导致一种反向的控制结构，它提高了代码阅读难度。
 *
 * 结构：
 *      1.抽象类（Abstract class）:负责给出一个算法的轮廓和骨架。它由一个模板方法和若干个基本方法构成。
 *          （1）模板方法：定义了算法的骨架，按某种顺序调用其包含的基本方法。
 *          （2）基本方法：是整个算法中的一个步骤，包含以下类型：
 *                  抽象方法:在抽象类中申明，由具体子类实现
 *                  具体方法：在抽象类中已经实现，在具体子类中可以继承和重写它。
 *                  钩子方法：在抽象类中已经实现，包括用于判断的逻辑方法和需要子类重写的空方法两种。
 *      2.具体子类（Concrete class）：实现抽象类中所定义的抽象方法和钩子方法，它们是一个顶级逻辑的一个组成步骤。
 * 应用场景：
 *      1.算法的整体步骤很固定，但其中个别部分易变时，这时候可以使用模板方法模式，将容易变得部分抽象出来，供子类实现。
 *      2.当多个子类存在公共的行为时，可以将其提取出来并集中到一个公共父类中以避免代码重复。
 *      首先，要识别现有代码中的不同之处，并且将不同之处分离为新的操作。最后，用一个调用这些新的操作的模板方法来替换这些不同的代码。
 *      3.当需要控制子类的扩展时，模板方法只在特定点调用钩子操作，这样就只允许在这些点进行扩展。
 *
 *
 */
public class TemplateMethodDemo {

    public static void main(String[] args) {
        TemplateMethodDemo demo = new TemplateMethodDemo();
//        demo.setTemplate();
        demo.setHookTemplate();
    }


    private void setTemplate(){
        AbstractClass abst = new ConcreteClass();
        abst.templateMethod();
    }

    private void setHookTemplate(){
        HookAbstractClass clazz = new HookConcreteClass();
        clazz.TemplateMethod();
    }


    /**
     * 实现
     */
    //抽象类
    abstract class AbstractClass{

        //模板方法
        public void templateMethod(){
            specificMethod();
            abstractMethod1();
            abstractMethod2();
        }

        //具体方法
        public void specificMethod(){
            System.out.println("抽象类中的具体方法被调用。。。");
        }
        //抽象方法
        public abstract void abstractMethod1();
        public abstract void abstractMethod2();
    }

    //具体子类
    class ConcreteClass extends AbstractClass{

        @Override
        public void abstractMethod1() {
            System.out.println("抽象方法1的实现被调用。。。。");
        }

        @Override
        public void abstractMethod2() {
            System.out.println("抽象方法2的实现被调用。。。。");
        }
    }

    //====================
    /**
     * 模式的扩展 包含钩子方法
     */
    //含钩子方法的抽象类
    abstract class HookAbstractClass{
        //模板方法
        public void TemplateMethod(){
            abstractMethod1();
            HookMethod1();
            if (HookMethod2()){
                specificMethod();
            }
            abstractMethod2();
        }

        public void specificMethod(){
            System.out.println("抽象类中的具体方法被调用");
        }

        public void HookMethod1(){}
        public boolean HookMethod2(){
            return true;
        }
        public abstract void abstractMethod1();
        public abstract void abstractMethod2();
    }

    //含钩子方法的具体子类
    class HookConcreteClass extends HookAbstractClass{

        @Override
        public void abstractMethod1() {
            System.out.println("抽象方法1的实现被调用。。。");
        }

        @Override
        public void abstractMethod2() {
            System.out.println("抽象方法2的实现被调用。。。。");
        }

        @Override
        public void HookMethod1() {
            super.HookMethod1();
            System.out.println("钩子方法1被重写");
        }

        @Override
        public boolean HookMethod2() {
            return false;
        }
    }
}
