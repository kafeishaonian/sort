package principle;

/**
 * 里氏替换原则（Liskov Substitution Principle）
 * 定义：
 *      继承必须确保超类所拥有的性质在子类中仍然成立
 * 作用：
 *      1.里氏替换原则是实现开闭原则的重要实现方式之一
 *      2.它克服了继承中重写父类造成的可复用性变差的缺点
 *      3.它是动作正确的保证。及类的扩展不会给系统引入新的错误，降低了代码出错的可能性
 * 实现方法：
 *      子类可以扩展父类的功能，但不能改变父类原有的功能
 */
public class LSPrinciple {

    public static void main(String[] args) {
        Animal animal1 = new Swallow();
        Animal animal2 = new BrownKiwi();
        animal1.setRunSpeed(120);
        animal2.setRunSpeed(60);
        System.out.println("如果走300公里");
        System.out.println("燕子将飞行" + animal1.getRunTime(300) + "小时");
        System.out.println("几维鸟将行走" + animal2.getRunTime(300) + "小时");

    }

}
//动物类
class Animal{

    double runSpeed;

    public void setRunSpeed(double runSpeed) {
        this.runSpeed = runSpeed;
    }

    public double getRunTime(double distance) {
        return distance / runSpeed;
    }
}
//鸟类
class Bird extends Animal{

    double flySpeed;

    public void setSpeed(double speed){
        flySpeed = speed;
    }

    public double getFlySpeed(double distance) {
        return distance / flySpeed;
    }
}

class Swallow extends Bird{

}

class BrownKiwi extends Animal{

}