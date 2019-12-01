package behavior_model;

import java.util.HashMap;

/**
 * 状态模式
 * 定义：
 *      对有状态的对象，把复杂的“判断逻辑”提取到不同的状态对象中，允许状态对象在其内部状态发生改变时改变其行为。
 * 优点：
 *      1.状态模式将于待定状态相关的行为局部化到一个状态中，并且将不同状态的行为分割开来，满足”单一职责原则“；
 *      2.减少对象间的相互依赖。将不同的状态引入独立的对象中会使得状态转换变得更加明确。且减少对象间的相互依赖。
 *      3.有利于程序的扩展。通过定义新的子类很容易地增加新的状态和转换。
 * 缺点：
 *      1.状态模式的使用必然会增加系统的类与对象的个数。
 *      2.状态模式的结构与实现都较为复杂，如果使用不当会导致程序结构和代码的混乱。
 * 结构：
 *      1.环境角色（Context）:也称为上下文，它定义了客户感兴趣的接口，维护一个当前状态，并将与状态相关的操作委托给当前状态对象来处理。
 *      2.抽象状态（State）角色：定义一个接口，用于封装环境对象中的特定状态所对应的行为。
 *      3.实现状态（Concrete State）角色：实现抽象状态所对应的行为。
 * 应用场景：
 *      1.当一个对象的行为取决于它的状态，并且它必须在运行时根据状态改变它的行为时，就可以考虑使用状态模式。
 *      2.一个操作中含有庞大的分支结构，并且这些分支决定于对象的状态时。
 *
 */
public class StateMethodDemo {

    public static void main(String[] args) {
        StateMethodDemo demo = new StateMethodDemo();
//        demo.setState();
//        demo.setThreadState();
        demo.setShare();
    }

    private void setState(){
        Context context = new Context();
        context.setHandler();
        context.setHandler();
        context.setHandler();
        context.setHandler();
    }

    private void setThreadState(){
        ThreadContext context = new ThreadContext();
        context.start();
        context.setCPU();
        context.suspend();
        context.resume();
        context.setCPU();
        context.stop();
    }

    private void setShare(){
        ShareContext context = new ShareContext();
        context.handler();
        context.handler();
        context.handler();
        context.handler();
    }


    /**
     * 实现
     */
    //环境类
    class Context{

        private State state;
        public Context(){
            state = new ConcreteStateA();
        }

        public State getState() {
            return state;
        }

        public void setState(State state) {
            this.state = state;
        }

        public void setHandler(){
            state.Handler(this);
        }

    }
    //抽象状态类
    abstract class State{
        public abstract void Handler(Context context);
    }
    //具体状态A类
    class ConcreteStateA extends State{

        @Override
        public void Handler(Context context) {
            System.out.println("当前状态是A");
            context.setState(new ConcreteStateB());
        }
    }
    //具体状态B类
    class ConcreteStateB extends State{

        @Override
        public void Handler(Context context) {
            System.out.println("当前状态是B");
            context.setState(new ConcreteStateA());
        }
    }
    //================================
    /**
     * 线程类代码书写
     */
    //线程环境类
    class ThreadContext{
        private ThreadState state;
        public ThreadContext(){
            state = new NewThread();
        }

        public ThreadState getState() {
            return state;
        }

        public void setState(ThreadState state) {
            this.state = state;
        }

        public void start(){
            ((NewThread) state).start(this);
        }

        public void setCPU(){
            ((RunnableThread) state).setCPU(this);
        }

        public void suspend(){
            ((RunningThread) state).suspend(this);
        }

        public void stop(){
            ((RunningThread) state).stop(this);
        }

        public void resume(){
            ((BlockedThread) state).resume(this);
        }

    }


    //抽象状态类，线程状态
    abstract class ThreadState{
        protected String stateName;
    }
    //具体状态类，新建状态
    class NewThread extends ThreadState{
        public NewThread(){
            stateName = "新建状态";
            System.out.println("当前线程处于新建状态");
        }

        public void start(ThreadContext context){
            System.out.println("调用start方法。。。。");
            if (stateName.equals("新建状态")){
                context.setState(new RunnableThread());
            } else {
                System.out.println("当前线程不是新建状态，不能调用start方法");
            }
        }
    }
    //就绪状态
    class RunnableThread extends ThreadState{

        public RunnableThread(){
            stateName = "就绪状态";
            System.out.println("当前线程进入就绪状态");
        }

        public void setCPU(ThreadContext context){
            System.out.println("获取CPU时间。。。。");
            if (stateName.equals("就绪状态")){
                context.setState(new RunningThread());
            } else {
                System.out.println("当前线程不是就绪状态，不能获取CPU");
            }
        }

    }
    //运行状态
    class RunningThread extends ThreadState{

        public RunningThread(){
            stateName = "运行状态";
            System.out.println("当前线程进入运行状态。。。");
        }

        public void suspend(ThreadContext context){
            System.out.println("调用suspend方法。。。。。");
            if (stateName.equals("运行状态")){
                context.setState(new BlockedThread());
            } else {
                System.out.println("当前线程不是运行状态，不能调用suspend方法");
            }
        }

        public void stop(ThreadContext context){
            System.out.println("调用stop方法。。。");
            if (stateName.equals("运行状态")){
                context.setState(new DeadThread());
            } else {
                System.out.println("当前线程不是运行状态，不能调用Stop方法");
            }
        }
    }

    //阻塞状态
    class BlockedThread extends ThreadState{

        public BlockedThread(){
            stateName = "阻塞状态";
            System.out.println("当前线程进入阻塞状态。。。");
        }

        public void resume(ThreadContext context){
            System.out.println("调用resume方法。。。");
            if (stateName.equals("阻塞状态")){
                context.setState(new RunnableThread());
            } else {
                System.out.println("当前线程不是阻塞状态，不能调用resume方法");
            }
        }
    }

    //死亡状态
    class DeadThread extends ThreadState{

        public DeadThread(){
            stateName = "死亡状态";
            System.out.println("当前线程进入死亡状态");
        }
    }

//=============================================
    /**
     * 状态模式与享元模式相结合
     */
    //环境类
    class ShareContext{

        private ShareState state;
        private HashMap<String, ShareState> stateSet = new HashMap<>();

        public ShareContext(){
            state = new ConcreteState1();
            stateSet.put("1", state);
            state = new ConcreteState2();
            stateSet.put("2", state);
            state = getState("1");
        }

        public ShareState getState(String key) {
            ShareState state = stateSet.get(key);
            return state;
        }

        public void setState(ShareState state) {
            this.state = state;
        }

        public void handler(){
            state.handler(this);
        }
    }
    //抽象状态类
    abstract class ShareState{
        public abstract void handler(ShareContext context);
    }

    class ConcreteState1 extends ShareState{


        @Override
        public void handler(ShareContext context) {
            System.out.println("当前状态是状态1");
            context.setState(context.getState("2"));
        }
    }

    class ConcreteState2 extends ShareState{

        @Override
        public void handler(ShareContext context) {
            System.out.println("当前状态是状态2");
            context.setState(context.getState("1"));
        }
    }


}
