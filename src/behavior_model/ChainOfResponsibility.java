package behavior_model;

/**
 * 责任链模式
 * 定义：
 *      为了避免请求发送者与多个请求处理者耦合在一起，将所有的请求处理着通过前一对象记住其下一个对象的引用而形成一条链；
 *      当有请求发送时，可将请求沿着这条链传递。直到有对象处理它为止。
 * 优点：
 *      1.降低了对象之间的耦合度，该模式使得一个对象无序知道到底是哪一个对象处理其请求以及链的结构，发送者和接收者也无需拥有对方的明确信息。
 *      2.增强了系统的可扩展性。可以根据需要增加新的请求处理类，满足开闭原则。
 *      3.增强了给对象指派职责的灵活性。当工作流程发生变化，可以动态地改变链内的成员或调动他们的次序，也可动态地新增或者删除责任。
 *      4.责任链简化了对象之间的链接。每个对象只需保持一个指向其后继者的引用，不需保持其他所有处理者的引用，这避免了使用众多if或者if..else语句
 *      5.责任分担，每个类主需要处理该处理的工作，不该处理的传递给下一个对象完成，明确各类的职责范围，符合类的单一职责原则。
 * 缺点：
 *      1.不能保证每个请求一定被处理。由于一个请求没有明确的接收者，所以不能保证它一定会被处理，该请求可能一直传到链的末端都得不到处理。
 *      2.对比较长的责任链，请求的处理可能涉及多个处理对象，系统性能将受到一定影响。
 *      3.责任链建立的合理性需要靠客户端来保证，增加了客户端的复杂性，可能会由于责任链的错误设置而导致系统出错，如可能会造成循环调用。
 *
 * 结构：
 *      1.抽象处理着（Handler）角色：定义一个处理请求的接口，包含抽象处理方法和一个后继链接。
 *      2.具体处理着（Concrete Handler）角色：实现抽象处理着的处理方法，判断能否处理本次请求，如果可以处理请求则处理，否则将该请求转给它的后继者。
 *      3.客户类（Client）类：创建处理链，并向链头的具体处理者对象提交请求，它不关系处理细节和请求的传递过程。
 *
 * 情景模式:
 *      1.有多个对象可以处理一个请求，哪个对象处理该请求由运行时刻自动确定
 *      2.可动态指定一组对象处理请求，或添加新的处理着。
 *      3.在不明确指定请求处理者的情况下，向多个处理者中的一个提交请求。
 *
 */
public class ChainOfResponsibility {


    public static void main(String[] args) {
        ChainOfResponsibility demo = new ChainOfResponsibility();
        demo.setHandler();
    }

    private void setHandler(){
        Handler handler1 = new ConcreteHandler();
        Handler handler2 = new ConcreteHandler2();
        handler1.setNext(handler2);
        handler1.handlerRequest("two");
    }

    /**
     * 实现
     */
    //抽象处理着角色
    abstract class Handler{

        private Handler next;

        public void setNext(Handler next) {
            this.next = next;
        }

        public Handler getNext() {
            return next;
        }
        //处理请求的方法
        public abstract void handlerRequest(String request);
    }

    //具体处理者角色1
    class ConcreteHandler extends Handler{

        @Override
        public void handlerRequest(String request) {
            if (request.equals("one")){
                System.out.println("具体处理者1负责处理该请求。。。");
            } else {
                if (getNext() != null){
                    getNext().handlerRequest(request);
                } else {
                    System.out.println("没人处理该请求");
                }
            }
        }
    }

    class ConcreteHandler2 extends Handler{

        @Override
        public void handlerRequest(String request) {
            if (request.equals("two")){
                System.out.println("具体处理者2负责处理该请求。。。");
            } else {
                if (getNext() != null){
                    getNext().handlerRequest(request);
                } else {
                    System.out.println("没人处理该请求");
                }
            }
        }
    }


}