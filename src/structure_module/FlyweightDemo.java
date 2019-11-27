package structure_module;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 享元模式
 * 定义：
 *      运用共享技术来有效的支持大量细粒度对象的复用。它通过共享已经存在的又想大幅度减少需要创建的对象数量，
 *      避免大量相似类的开销，从而提高系统资源的利用率。
 * 优点：
 *      相同对象只要保存一份，这降低了系统中对象的数量，从而降低了系统中细粒度对象给内存带来的压力。
 * 缺点：
 *      1.为了使对象可以共享，需要将一些不能共享的状态外部化，这将增加程序的复杂性。
 *      2.读取享元模式的外部状态会使得运行时间稍微变长。
 * 状态：
 *      1.内部状态：即不会随着环境的改变而改变的可共享部分。
 *      2.外部状态：指随环境改变而改变的不可以共享的部分，
 * 结构：
 *      1.抽象享元（Flyweight）角色:是所有享元类的基类，为具体享元规范需要实现的公共接口，非享元的外部状态以参数的形式通过方法传入。
 *      2.具体享元（Concrete Flyweight）角色：实现抽象享元角色中所规定的接口。
 *      3.非享元（Unsharable Flyweight）角色：是不可以共享的外部状体，它以参数的形式注入具体享元的相关方法中。
 *      4.享元工厂（Flyweight Factory）角色：负责创建和管理享元角色。当客户对象请求一个享元对象时，
 *      享元工厂检查系统中是否存在符合要求的享元对象，如果存在则提供给客户，如果不存在的话，则创建一个新的享元对象。
 *
 * 应用场景：
 *      1.系统中存在大量相同或相似对象，这些对象耗费大量的内存资源。
 *      2.大部分的对象可以按照内部状态进行分组，且可将不同部分外部化，这样每一个组只需保存一个内部状态。
 *      3.由于享元模式需要额外维护一个保存享元的数据结构，所以应该在足够多的享元实例时才值得使用享元模式。
 *
 * 扩展：
 *      1.单纯享元模式：这种享元模式中的所有的具体享元类都是可以共享的，不存在非共享的具体享元类
 *      2.复合享元模式：这种享元模式中的有些享元对象是由一些单纯享元对象组合而成，他们就是复合享元对象。虽然复合享元对象本身不能共享，
 *      但他们可以被分解成单纯享元对象再被共享。
 *
 */
public class FlyweightDemo {


    public static void main(String[] args) {
        FlyweightDemo demo = new FlyweightDemo();
//        demo.setFlyweight();
        demo.setPiessace();
    }

    private void setPiessace(){
        new Chessboard();
    }

    private void setFlyweight(){
        FlyweightFactory factory = new FlyweightFactory();
        Flyweight f01 = factory.getFlyweight("a");
        Flyweight f02 = factory.getFlyweight("a");
        Flyweight f03 = factory.getFlyweight("a");
        Flyweight f11 = factory.getFlyweight("b");
        Flyweight f12 = factory.getFlyweight("b");
        f01.operation(new UnsharableConcreteFlyweight("第1次调用a"));
        f02.operation(new UnsharableConcreteFlyweight("第2次调用a"));
        f03.operation(new UnsharableConcreteFlyweight("第3次调用a"));
        f11.operation(new UnsharableConcreteFlyweight("第1次调用b"));
        f12.operation(new UnsharableConcreteFlyweight("第2次调用b"));
    }


    //非享元角色
    class UnsharableConcreteFlyweight{
        private String info;
        UnsharableConcreteFlyweight(String info){
            this.info = info;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }
    }

    //抽象享元角色
    interface Flyweight{
        public void operation(UnsharableConcreteFlyweight state);
    }

    //具体享元角色
    class ConcreteFlyweight implements Flyweight{

        private String key;
        ConcreteFlyweight(String key){
            this.key = key;
            System.out.println("具体享元" +key+ "被创建");
        }

        @Override
        public void operation(UnsharableConcreteFlyweight state) {
            System.out.println("具体享元" +key+ "被调用");
            System.out.println("非享元信息" + state.getInfo());
        }
    }
    //享元工厂角色
    class FlyweightFactory{
        private HashMap<String, Flyweight> flyweights = new HashMap<>();
        public Flyweight getFlyweight(String key){
            Flyweight flyweight = flyweights.get(key);
            if (flyweight != null){
                System.out.println("具体享元"+key+"已经存在，被成功获取");
            } else {
                flyweight = new ConcreteFlyweight(key);
                flyweights.put(key, flyweight);
            }
            return flyweight;
        }
    }


    /**
     * 享元模式五子棋实现
     */
    //棋盘
    class Chessboard extends MouseAdapter{
        WeiqiFactory wf;
        JFrame f;
        Graphics g;
        JRadioButton wz;
        JRadioButton bz;
        private final int x = 50;
        private final int y = 50;
        private final int w = 40;
        private final int rw = 400;

        Chessboard(){
            wf = new WeiqiFactory();
            f = new JFrame("享元模式在五子棋游戏中的应用");
            f.setBounds(100, 100, 500, 550);
            f.setVisible(true);
            f.setResizable(false);
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JPanel SouthJP=new JPanel();
            f.add("South",SouthJP);
            wz=new JRadioButton("白子");
            bz=new JRadioButton("黑子",true);
            ButtonGroup group=new ButtonGroup();
            group.add(wz);
            group.add(bz);
            SouthJP.add(wz);
            SouthJP.add(bz);
            JPanel CenterJP=new JPanel();
            CenterJP.setLayout(null);
            CenterJP.setSize(500, 500);
            CenterJP.addMouseListener(this);
            f.add("Center",CenterJP);
            try
            {
                Thread.sleep(500);
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
            g=CenterJP.getGraphics();
            g.setColor(Color.BLUE);
            g.drawRect(x, y, rw, rw);
            for(int i=1;i<10;i++)
            {
                //绘制第i条竖直线
                g.drawLine(x+(i*w),y,x+(i*w),y+rw);
                //绘制第i条水平线
                g.drawLine(x,y+(i*w),x+rw,y+(i*w));
            }
        }
        @Override
        public void mouseClicked(MouseEvent e)
        {
            Point pt=new Point(e.getX()-15,e.getY()-15);
            if(wz.isSelected())
            {
                ChessPieces c1=wf.getChessPieces("w");
                c1.DownPieces(g,pt);
            }
            else if(bz.isSelected())
            {
                ChessPieces c2=wf.getChessPieces("b");
                c2.DownPieces(g,pt);
            }
        }


    }
    //抽象享元角色：棋子
    interface ChessPieces{
        //下子
        public void DownPieces(Graphics g, Point pt);
    }
    //具体享元棋子： 白色
    class WhitePieces implements ChessPieces{

        @Override
        public void DownPieces(Graphics g, Point pt) {
            g.setColor(Color.WHITE);
            g.fillOval(pt.x, pt.y,30, 30);
        }
    }
    //具体享元棋子： 黑色
    class BlackPieces implements ChessPieces{

        @Override
        public void DownPieces(Graphics g, Point pt) {
            g.setColor(Color.BLACK);
            g.fillOval(pt.x, pt.y, 30, 30);
        }
    }
    //享元工厂
    class WeiqiFactory{

        private ArrayList<ChessPieces> qz;
        public WeiqiFactory(){
            qz = new ArrayList<>();
            ChessPieces w = new WhitePieces();
            qz.add(w);
            ChessPieces b = new BlackPieces();
            qz.add(b);
        }

        public ChessPieces getChessPieces(String type){
            if (type.equalsIgnoreCase("w")){
                return qz.get(0);
            } else if (type.equalsIgnoreCase("b")){
                return qz.get(1);
            } else {
                return null;
            }
        }
    }





}
