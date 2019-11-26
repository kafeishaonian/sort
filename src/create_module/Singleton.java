package create_module;

/**
 * 单例模式
 * 定义：
 *      指一个类只有一个实例，且该类自行创建这个实例的一种模式
 * 特点：
 *      1.单例类只有一个实例对象
 *      2.该单例对象必须有单例类自行创建
 *      3.单例类对外提供一个访问该单例的全局访问点
 *
 * 结构与实现：
 *      普通类的构造函数是公有的，外部类可以通过“new构造函数()”来生成多个实例。
 *      但是，将类的构造函数设置为私有的，外部类就无法调用该构造函数，也就无法生成多个实例，
 *      这时该类必须定义一个静态私有实例，并向外提供一个静态的共有函数用于创建或获取该静态私有实例。
 *  结构:
 *      1.单例类：包含一个实例切能自己创建这个实例的类。
 *      2.访问类：使用单例的类
 */
public class Singleton {
}


/**
 * 懒汉式单例的创建
 * 缺点：在多线程中，每次访问都需要同步，会影响性能，并且浪费更多的资源
 */
class LazySingleton{
    //保证instance所在的线程是同步的
    private static volatile LazySingleton instance = null;
    private LazySingleton(){}
    public synchronized static LazySingleton getInstance(){
        if (instance == null){
            instance = new LazySingleton();
        }
        return instance;
    }
}


/**
 * 饿汉式单例
 * 特点：饿汉式单例在类创建的同时就已经创建好单例对象共系统使用，以后不在改变，所以是线程安全的，可以用于多线程而不会出现问题。
 */
class HungrySingletom{

    private static final HungrySingletom instance = new HungrySingletom();
    private HungrySingletom(){

    }

    public static HungrySingletom getInstance(){
        return instance;
    }

}



