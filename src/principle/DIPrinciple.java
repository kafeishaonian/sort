package principle;

/**
 * 依赖倒置原则
 * 定义：要面向接口编程， 而不要面向实现编程
 * 作用：
 *      1.可以降低类之间的耦合性
 *      2.可以提高系统的稳定性
 *      3.可以减少并行开发引起的风险
 *      4.可以提高代码的可读性和可维护性
 *
 * 实现方法：
 *      1.每个类都尽量提供接口或抽象类，或两者都具备
 *      2.变量的声明类型尽量是接口或是抽象类
 *      3.任何类都不应该从具体类派生
 *      4.使用继承时尽量使用里氏替换原则
 */
public class DIPrinciple {

    public static void main(String[] args){
        Customer model = new Customer();
        System.out.println("顾客购买了一下商品");
        model.shopping(new ShaoguanShop());
        model.shopping(new WuyuanShop());
    }

}

//商店
interface Shop{
    //购买
    public String call();
}

class ShaoguanShop implements Shop{

    @Override
    public String call() {
        return "韶关商店土特产，人参鹿茸和貂皮";
    }
}

class WuyuanShop implements Shop{

    @Override
    public String call() {
        return "婺源商店的土特产， 土豆小米和花生";
    }
}

class Customer{

    public void shopping(Shop shop){
        System.out.println(shop.call());
    }

}
