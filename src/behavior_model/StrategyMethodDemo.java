package behavior_model;

/**
 * 策略模式
 *      属于对象行为模式，通过对算法进行封装，把使用算法的责任和算法的实现分割开来，并委派给不同的对象对这些算法进行管理。
 *      策略模式是准备一组算法，并将这组算法封装到一系列的策略类里面，作为一个抽象策略的子类。策略模式的重心不是如何实现算法，
 *      而是如何组织这些算法，从而让程序结构更加灵活，具有更好的维护性和扩展性，现在我们来分析其基本结构和实现方法。
 * 定义：
 *      该模式定义了一系列算法，并将每个算法封装起来，使它们可以相互替换，且算法的变化不会影响使用算法的客户。
 * 优点：
 *      1.多重条件语句不易维护，而使用策略模式可以避免使用多重条件语句。
 *      2.策略模式提供了一系列的可供重用的算法族，恰当使用继承可以把算法族的公共代码转移到父类里面，从而避免重复的代码
 *      3.策略模式可以提供相同行为的不同实现，客户可以根据不同时间或空间要求选择不同的。
 *      4.策略模式提供了对开闭原则的完美支持，可以在不修改源代码的情况下，灵活增加新算法。
 *      5.策略模式把算法的使用放到环境类中，而算法的实现移到具体策略类中，实现了两者的分离。
 * 缺点：
 *      1.客户端必须理解所有策略算法的区别，以便适时选择恰当的算法类
 *      2.策略模式造成很多的策略类。
 *
 * 结构：
 *      1.抽象策略（Strategy）类：定义了一个公共接口，各种不同的算法以不同的方式实现这个接口，
 *      环境角色使用这个接口调用不同的算法，一般使用接口或抽象类实现。
 *      2.具体策略（Concrete Strategy）类：实现了抽象策略定义的接口，提供具体的算法实现。
 *      3.环境（Context）类：持有一个策略类的引用，最终给客户端调用。
 *
 * 应用场景：
 *      1.一个系统需要动态地在几种算法中选择一种时，可将每个算法封装到策略类中。
 *      2.一个类定义了多种行为，并且这些行为在这个类的操作中以多个条件语句的形式出现，可将每个条件分支移入它们各自的策略类中以代替这些条件语句
 *      3.系统中各算法彼此完全独立，且要求对客户隐藏具体算法的实现细节时。
 *      4.系统要求使用算法的客户不应该知道其操作的数据时，可使用策略模式来隐藏与算法相关的数据结构。
 *      5.多个类只区别在表现行为不同，可以使用策略模式，在运行时动态选择具体要执行的行为。
 *
 */
public class StrategyMethodDemo {


    public static void main(String[] args) {
        StrategyMethodDemo demo = new StrategyMethodDemo();
        demo.setStrategy();
    }

    private void setStrategy(){
        Context c = new Context();
        Strategy s = new ConcreteStrategyA();
        c.setStrategy(s);
        c.strategyMethod();
        System.out.println("=============");
        s = new ConcreteStrategyB();
        c.setStrategy(s);
        c.strategyMethod();
    }



    //抽象策略类
    interface Strategy{
        //策略方法
        public void strategyMethod();
    }
    //具体策略类
    class ConcreteStrategyA implements Strategy{

        @Override
        public void strategyMethod() {
            System.out.println("具体策略A的策略方法被访问！");
        }
    }

    //具体策略类
    class ConcreteStrategyB implements Strategy{

        @Override
        public void strategyMethod() {
            System.out.println("具体策略B的策略方法被访问！");
        }
    }

    //环境类
    class Context{

        private Strategy strategy;
        public Strategy getStrategy(){
            return strategy;
        }

        public void setStrategy(Strategy strategy){
            this.strategy = strategy;
        }

        public void strategyMethod(){
            strategy.strategyMethod();
        }

    }

}
