package create_module;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.*;

/**
 * 工厂模式
 * 定义：
 *      定义一个创建产品对象的工厂接口，将产品对象的实际创建工作推迟到具体子工厂类当中。
 * 优点：
 *      1.用户只需要知道工厂的名称就可以得到所需要的产品，无序知道产品的创建过程。
 *      2.在系统增加新的产品时只需要添加具体产品类和对应的具体工厂，无序对原工厂进行任何修改，满足开闭原则
 * 缺点:
 *      每增加一个产品就要增加一个具体产品类和一个对应的具体工厂类，这增加了系统的复杂度
 * 结构：
 *      1.抽象工厂(Abstract Factory):提供了创建产品的接口，调用者通过它访问具体工厂的工厂方法newProduct()来创建产品。
 *      2.具体工厂(Concrete Factory):主要是实现抽象工厂中的抽象方法，完成具体产品的创建。
 *      3.抽象产品(Product):定义了产品的规范，描述了产品的主要特性和功能
 *      4.具体产品(Concrete):实现了抽象产品角色所定义的接口，由具体工厂来创建，它同具体工厂之间一一对应。
 * 应用场景：
 *      1.客户之中的创建产品的工厂名，而不知道具体的产品名
 *      2.创建对象的任务由多个具体子工厂某一个完成，而抽象工厂只提供创建产品的接口
 *      3.客户不关心创建产品的细节，只关心产品的品牌
 */
public class FactoryMethod {

    public static void main(String[] args) {
//        setProduct();
        setAnimal();
    }


    private static void setProduct(){
        String file = "src/resources/config1.xml";
        Product a;
        AbstractFactory af;
        af = (AbstractFactory) ReadXML1.getObject(file);
        a = af.newProduct();
        a.show();
    }

    private static void setAnimal(){
        String file = "src/resources/config2.xml";
        Animal animal;
        AnimalFarm farm;
        farm = (AnimalFarm) ReadXML1.getObject(file);
        animal = farm.newAnimal();
        animal.show();
    }

}

/**
 * 抽象产品， 提供产品的接口
 */
interface Product{
    public void show();
}

class ConcreteProduct1 implements Product{

    @Override
    public void show() {
        System.out.println("具体产品1显示。。。。");
    }
}

class ConcreteProduct2 implements Product{

    @Override
    public void show() {
        System.out.println("具体产品2显示。。。。。");
    }
}

/**
 * 抽象工厂： 提供了厂品的生成方法
 */
interface AbstractFactory{
    public Product newProduct();
}

class ConcreteFactory1 implements AbstractFactory {

    @Override
    public Product newProduct() {
        System.out.println("具体工厂1生成产品1");
        return new ConcreteProduct1();
    }
}

class ConcreteFactory2 implements AbstractFactory {

    @Override
    public Product newProduct() {
        System.out.println("具体工厂2生产产品2");
        return new ConcreteProduct2();
    }
}

class ReadXML1{
    public static Object getObject(String file){
        try {
            DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dFactory.newDocumentBuilder();
            Document doc = builder.parse(file);
            NodeList nl = doc.getElementsByTagName("className");
            Node classNode = nl.item(0).getFirstChild();
            String cName = "module." + classNode.getNodeValue();

            System.out.println(cName);

            Class<?> clazz = Class.forName(cName);
            Object obj = clazz.newInstance();
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
            return  null;
        }
    }
}

//=======================================

/**
 * 抽象产品 动物
 */
interface Animal{
    public void show();
}

class Horse implements Animal{

    JScrollPane sp;
    JFrame jf = new JFrame("工厂方法模式测试");

    public Horse(){
        Container contentPance = jf.getContentPane();
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(1, 1));
        p1.setBorder(BorderFactory.createTitledBorder("动物：马"));
        sp = new JScrollPane(p1);
        contentPance.add(sp, BorderLayout.CENTER);
        JLabel j1 = new JLabel(new ImageIcon("src/resources/A_Horse.jpg"));
        p1.add(j1);
        jf.pack();
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void show() {
        jf.setVisible(true);
    }
}

class Cattle implements Animal{

    JScrollPane sp;
    JFrame jf = new JFrame("工厂方法模式测试");

    public Cattle(){
        Container contentPance = jf.getContentPane();
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(1, 1));
        p1.setBorder(BorderFactory.createTitledBorder("动物：牛"));
        sp = new JScrollPane(p1);
        contentPance.add(sp, BorderLayout.CENTER);
        JLabel j1 = new JLabel(new ImageIcon("src/resources/A_Cattle.jpg"));
        p1.add(j1);
        jf.pack();
        jf.setVisible(false);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void show() {
        jf.setVisible(true);
    }
}

interface AnimalFarm{
    public Animal newAnimal();
}

class HorseFarm implements AnimalFarm{

    @Override
    public Animal newAnimal() {
        System.out.println("新马出生！");
        return new Horse();
    }
}

class CattleFarm implements AnimalFarm{

    @Override
    public Animal newAnimal() {
        System.out.println("新牛出生");
        return new Cattle();
    }
}