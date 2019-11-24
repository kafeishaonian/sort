package principle;

/**
 * 迪米特法则（最少知识原则）
 * 定义：
 *      如果两个软件实体无须直接通信，那么就不应当发生直接的相互调用，
 *      可以通过第三方转发该调用。其目的是降低类之间的耦合度，提高模块的相对独立性
 * 优点：
 *      1.降低了类之间的耦合度，提高了模块的相对独立性
 *      2.由于亲和度降低，从而提高了类的可复用率和系统的扩展性
 * 实现方法:
 *      1.从依赖者的角度说，只依赖应该依赖的对象
 *      2.从被依赖者的角度说，只暴露应该暴露的方法
 * 注意事项：
 *      1.在类的划分上，应该创建弱耦合的类。类与类之间的耦合越弱，就越有利于实现可复用的目标
 *      2.在类的结构设计上，尽量降低类成员的访问权限。
 *      3.在类的设计上，尽量将一个类设计成为不变类。
 *      4.在对其他引用上，将引用其他对象的次数降到最低。
 *      5.不暴露类的属性成员，而应该提供相应的访问器。
 *      6.谨慎使用序列化功能。
 */
public class LoDemeter {

    public static void main(String[] args) {
        Agent agent = new Agent();
        agent.setStar(new Star("林心如"));
        agent.setFans(new Fans("粉丝韩城"));
        agent.setCompany(new Company("中国传媒有限公司"));
        agent.meeting();
        agent.business();
    }
}


class Agent{

    private Star mStar;
    private Fans mFans;
    private Company mCompany;

    public void setStar(Star mStar) {
        this.mStar = mStar;
    }

    public void setFans(Fans mFans) {
        this.mFans = mFans;
    }

    public void setCompany(Company mCompany) {
        this.mCompany = mCompany;
    }

    public void meeting(){
        System.out.println(mFans.getName() + "与明星" +mStar.getName() + "见面了");
    }

    public void business(){
        System.out.println(mCompany.getName() + "与明星" + mStar.getName() +"洽谈业务");
    }
}

class Star{

    private String name;

    Star(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Fans{
    private String name;

    Fans(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Company{

    private String name;

    Company(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
