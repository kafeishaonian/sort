package structure_module;

/**
 * 外观模式
 * 外观模式的结构比较简单，主要是定义了一个高层接口。包含了对各个子系统的引用，客户端可以通过它访问各个子系统的功能。
 * 定义：
 *      是一种通过为多个复杂的子系统提供一个一致的接口，而使这些子系统更加容易被访问的模式。
 *      该模式对外有一个统一接口，外部应用程序不用关心内部子系统的具体细节，大大降低应用程序的复杂度，提高了程序的可维护性
 * 优点：
 *      1.降低了子系统与客户端之间的耦合度，使得子系统的变化不会影响调用它的客户类
 *      2.对客户屏蔽了子系统组件，减少了客户处理的对象数目，并使得子系统使用起来更加容易
 *      3.降低了大型软件系统中的编译依赖性，简化了系统在不同平台之间的移植过程，因为编译一个子系统不会影响其他的子系统，也不会影响外观对象
 * 缺点:
 *      1.不能很好地限制客户使用子系统类
 *      2.增加新的子系统可能需要修改外观类和客户端的源代码，违背了“开闭原则”
 *
 * 结构：
 *      1.外观（Facade）角色：为多个子系统对外提供一个共同的接口
 *      2.子系统（Sub System）角色：实际系统的部分功能，客户可以通过外观角色访问它。
 *      3.客户（Client）角色：通过一个外观角色访问各个子系统的功能。
 *
 * 应用场景：
 *      1.对分层结构系统构建时，使用外观模式定义子系统中每层的入口点可以简化子系统之间的依赖关系
 *      2.当一个复杂系统的子系统很多时，外观模式可以为系统设计一个简单的接口供外界访问
 *      3.当客户端与子系统之间存在很大的联系时，引入外观模式可以将他们分离，从而提高子系统的独立性和可移植性
 */
public class FacadeDemo {

    public static void main(String[] args) {
        FacadeDemo demo = new FacadeDemo();
        demo.setFacade();
    }

    private void setFacade(){
        Facade facade = new Facade();
        facade.method();
    }


    //外观角色
    class Facade{
        private SubSystem01 obj1 = new SubSystem01();
        private SubSystem02 obj2 = new SubSystem02();
        private SubSystem03 obj3 = new SubSystem03();

        public void method(){
            obj1.method1();
            obj2.method2();
            obj3.method3();
        }
    }

    //子系统角色
    class SubSystem01{
        public void method1(){
            System.out.println("子系统01的method1()被调用");
        }
    }

    class SubSystem02{
        public void method2(){
            System.out.println("子系统02的method2()被调用");
        }
    }

    class SubSystem03{
        public void method3(){
            System.out.println("子系统03的method3()被调用");
        }
    }

}
