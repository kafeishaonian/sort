package create_model;

import java.util.HashMap;
import java.util.Scanner;

/**
 * 原型模式
 * 定义：
 *      用一个已经创建的实例作为原型，通过复制该原型对象来创建一个和原型相同或相似的对象。
 * 特点：
 *      原型实例指定了要创建对象的种类，用这种方式创建对象非常高效，根本无序知道对象创建的细节
 * 结构：
 *      1.抽象原型类：规定了具体原型对象必须实现的接口
 *      2.具体原型类：实现抽象原型类clone()方法，它是可被复制对象
 *      3.访问类：使用具体原型类中的clone()方法来复制新的对象。
 * 应用场景：
 *      1.对象之间相同或相似，即只是个别的几个属性不同的时候，
 *      2.对象的创建过程比较麻烦，但复制比较简单的时候
 * 扩展：
 *      原型模式可以扩展为带原型管理器的原型模式，它在原型模式的基础上增加了一个原型管理器PrototypeManager类，
 *      该类用HashMap保存多个复制的原型，Clint类可以通过管理器的get(String id)方法从中获取复制的原型
 */
public class ProtoType {
    public static void main(String[] args) {
//        setRealizeTypeText();
//        setCitation();
        setShape();
    }

    private static void setRealizeTypeText(){
        try {
            RealizeType realize1 = new RealizeType();
            RealizeType realize2 = (RealizeType) realize1.clone();
            System.out.println("realize1 == realize2:" + (realize1 == realize2));
        } catch (CloneNotSupportedException e){
            e.printStackTrace();
        }
    }

    private static void setCitation(){
        try{
            Citation obj1 = new Citation("张三", "同学，在2016年表现突出，被评为三好学生", "莱芜一中");
            obj1.display();
            Citation obj2 = (Citation) obj1.clone();
            obj2.setName("李四");
            obj2.display();
        } catch (CloneNotSupportedException e){
            e.printStackTrace();
        }
    }

    private static void setShape(){
        ProtoTypeManager pm = new ProtoTypeManager();
        Shape obj1 = pm.getShape("Circle");
        obj1.countArea();
        Shape obj2 = pm.getShape("Square");
        obj2.countArea();
    }
}


/**
 * 具体原型类
 */
class RealizeType implements Cloneable{
    RealizeType(){
        System.out.println("具体原型创建成功");
    }

    public Object clone() throws CloneNotSupportedException{
        System.out.println("具体原型复制成功");
        return (RealizeType)super.clone();
    }
}

class Citation implements Cloneable{
    String name;
    String info;
    String collega;

    Citation(String name, String info, String collega){
        this.name = name;
        this.info = info;
        this.collega = collega;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    void display(){
        System.out.println(name + info + collega);
    }

    public Object clone() throws CloneNotSupportedException{
        System.out.println("奖状copy成功");
        return super.clone();
    }
}

//==========================================================
//用原型管理器的原型模式来生成包含“圆”和“正方形”等图形的原型
interface Shape extends Cloneable{
    public Object clone(); //拷贝
    public void countArea();  //计算面积
}

class Circle implements Shape{

    @Override
    public Object clone() {
        Circle w = null;
        try {
            w = (Circle) super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("拷贝圆失败");
        }
        return w;
    }

    @Override
    public void countArea() {
        int r = 0;
        System.out.println("这是一个圆，请输入圆的半径");
        Scanner input = new Scanner(System.in);
        r = input.nextInt();
        System.out.println("该圆的面积=" + 3.1415 * r * r + "\n");
    }
}

class Square implements Shape{

    @Override
    public Object clone() {
        Square b = null;
        try {
            b = (Square) super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("拷贝正方形失败");
        }
        return b;
    }

    @Override
    public void countArea() {
        int a = 0;
        System.out.println("这是一个正方形，请输入它的边长");
        Scanner input = new Scanner(System.in);
        a = input.nextInt();
        System.out.println("该正方形的面积" + a * a + "\n");
    }
}


class ProtoTypeManager{
    private HashMap<String, Shape> ht = new HashMap<>();
    public ProtoTypeManager(){
        ht.put("Circle", new Circle());
        ht.put("Square", new Square());
    }

    public void addShape(String key, Shape obj){
        ht.put(key, obj);
    }

    public Shape getShape(String key){
        Shape temp = ht.get(key);
        return (Shape) temp.clone();
    }
}

