package structure_model;

/**
 * 适配器模式
 * 定义：
 *      将一个类的接口转换成客户希望的另外一个接口，使的原本由于接口不兼容而不能一起工作的那些类能一起工作
 * 优点；
 *      1.客户端通过适配器可以透明的调用目标接口
 *      2.复用了现存的类，程序员不需要修改原有代码而重用现有的适配者类
 *      3.将目标类和适配者类解耦，解决了目标类和适配者类接口不一致问题
 * 缺点：
 *      对类适配器来说，更换适配器的实现过程比较复杂
 * 结构：
 *      1.目标（Target）接口：当前系统业务所期待的接口，它可以是抽象类或接口
 *      2.适配者（Adaptee）类：它是被访问和适配的现存组件库中的组件接口
 *      3.适配器（Adapter）类：它是一个转换器，通过继承或引用适配者的对象，把适配者接口转换成目标接口，让客户按目标接口的格式访问适配者
 * 应用场景：
 *      1.以前开发的系统存在满足新系统功能需求的类，但其接口同新系统的接口不一致
 *      2.使用第三方提供的组件，但组件接口定义与自己要求的定义不同
 */
public class AdapterDemo{

    public static void main(String[] args) {
        AdapterDemo demo = new AdapterDemo();
//        demo.setTarget();
//        demo.setObject();
        demo.setTwoWay();
    }

    private void setTarget(){
        Target target = new ClassAdapter();
        target.request();
    }

    private void setObject(){
        Adapter adapter = new Adapter();
        Target target = new ObjectAdapter(adapter);
        target.request();
    }

    private void setTwoWay(){
        System.out.println("目标通过双向适配器访问适配者：");
        TwoWayAdaptee adaptee = new AdapteeRealize();
        TwoWayTarget target = new TwoWayAdapter(adaptee);
        target.request();
        System.out.println("-------------------");
        System.out.println("适配者通过双向适配器访问目标：");
        target = new TargetRealize();
        adaptee = new TwoWayAdapter(target);
        adaptee.specificRequest();

    }


    /**
     * 类适配器代码实现
     */
    //目标接口
    interface Target{
        void request();
    }
    //适配者接口
    class Adapter{
        public void specificRequest(){
            System.out.println("适配者中的业务代码被调用");
        }
    }
    //类适配器类
    class ClassAdapter extends Adapter implements Target{

        @Override
        public void request() {
            specificRequest();
        }
    }

    /**
     * 对象适配器代码实现
     */

    //对象适配器
    class ObjectAdapter implements Target{

        private Adapter adapter;
        public ObjectAdapter(Adapter adapter){
            this.adapter = adapter;
        }

        @Override
        public void request() {
            adapter.specificRequest();
        }
    }
    /**
     * 双向适配器代码实现
     */
    //目标接口
    interface TwoWayTarget{
        public void request();
    }

    //适配者接口
    interface TwoWayAdaptee{
        public void specificRequest();
    }
    //目标实现
    class TargetRealize implements TwoWayTarget{

        @Override
        public void request() {
            System.out.println("目标代码被调用");
        }
    }
    //适配者实现
    class AdapteeRealize implements TwoWayAdaptee{

        @Override
        public void specificRequest() {
            System.out.println("适配器代码被调用");
        }
    }

    class TwoWayAdapter implements TwoWayTarget,TwoWayAdaptee{

        private TwoWayTarget target;
        private TwoWayAdaptee adaptee;
        public TwoWayAdapter(TwoWayTarget target){
            this.target = target;
        }
        public TwoWayAdapter(TwoWayAdaptee adaptee){
            this.adaptee = adaptee;
        }

        @Override
        public void request() {
            adaptee.specificRequest();
        }

        @Override
        public void specificRequest() {
            target.request();
        }
    }


}
