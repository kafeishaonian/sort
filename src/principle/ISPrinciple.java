package principle;

/**
 * 接口隔离原则
 * 定义：
 *      客户端不应该被迫依赖于它不适用的方法
 *      一个类对另一个类的依赖应该应该建立在最小接口上
 * 总结：
 *      要为个各类建立他们需要的专用接口，而不要试图去建立一个很庞大的接口供所有依赖它的类去调用
 * 优点：
 *      1.将臃肿庞大的接口去分解为颗粒度小的接口，可以预防外来变更的扩散，提高系统的灵活性和可维护性。
 *      2.接口隔离提高了系统的内聚性，减少了对外交互，降低了系统的耦合性
 *      3.如果接口的颗粒度大小定义合理，能够保证系统的稳定性，但是：
 * 如果定义过小，则会造成接口数量过多，使设计复杂化；
 * 如果定义太大，灵活性降低，无法提供定制服务，给整个项目带来无法预料的风险
 *      4.使用多个专门的接口还能体现对象的层次，因为可以通过对接口的继承，实现对总接口的定义
 *      5.能减少项目工程中的代码沉余。过大的大接口里面通常放置这不用的方法，当实现这个接口的时候，被迫设计沉余的代码
 * 实现方式：
 *      1.接口尽量小，但是要有限度。一个接口只服务于一个子模块或业务逻辑
 *      2.为依赖接口的类定制服务。只提供调用者需要的方法，屏蔽不需要的方法
 *      3.了解环境，拒绝盲从。每个项目或产品都有选定的环境因素，环境不同，接口拆分的标准就不同深入了解业务逻辑
 *      4.提高内聚，减少对外交互。使接口用最少的方法去完成最多的事情。
 */
public class ISPrinciple {

    public static void main(String[] args) {
        InputModule inputModule = StuScoreList.getInputModel();
        CountModel countModel = StuScoreList.getCountModel();
        PrintModel printModel = StuScoreList.getPrintModel();
        inputModule.insert();
        countModel.countTotalScore();
        printModel.printStuInfo();
    }
}

//输入模块接口
interface InputModule{
    void insert();
    void delete();
    void modify();
}

//统计模块接口
interface CountModel{
    void countTotalScore();
    void countAverage();
}

//打印模块接口
interface PrintModel{
    void printStuInfo();
    void queryStuInfo();
}

class StuScoreList implements InputModule, CountModel, PrintModel{

    private StuScoreList(){}

    public static InputModule getInputModel(){
        return new StuScoreList();
    }

    public static CountModel getCountModel(){
        return new StuScoreList();
    }

    public static PrintModel getPrintModel(){
        return new StuScoreList();
    }

    @Override
    public void insert() {
        System.out.println("输入模块的insert()方法被调用");
    }

    @Override
    public void delete() {
        System.out.println("输入模块的delete()方法被调用");
    }

    @Override
    public void modify() {
        System.out.println("输入模块的modify()方法被调用");
    }

    @Override
    public void countTotalScore() {
        System.out.println("统计模块的countTotalScore()方法被调用");
    }

    @Override
    public void countAverage() {
        System.out.println("统计模块的countAverage()方法被调用");
    }

    @Override
    public void printStuInfo() {
        System.out.println("打印模块的printStuInfo()方法被调用");
    }

    @Override
    public void queryStuInfo() {
        System.out.println("打印模块的queryStuInfo()方法被调用");
    }
}