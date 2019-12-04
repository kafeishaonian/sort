package behavior_model;

/**
 * 解释器模式
 * 定义：
 *      给分析对象定义一个语言，并定义该语言的文法表示，在设计一个解释器来解释语言中的句子。
 *      也就是说，用编译语言的方式来分析应用中的实例。
 * 优点：
 *      1.扩展性好。由于在解释器模式中使用类来表示语言的文法规则，因此可以通过继承等机制来改变或扩展文法。
 *      2.容易实现。在语法树中的每个表达式节点类都是相似的，所以实现其文法较为容易。
 * 缺点：
 *      1.执行效率较低。解释器模式中通常使用大量的循环和递归调用，当要解释的句子较复杂时，其运行速度很慢，且代码的调试过程也比较麻烦。
 *      2.会引起类膨胀。解释器模式中的每条规则至少需要定义一个类，当包含的文法规则很多时，类的个数将急剧增加，导致系统难以管理与维护。
 *      3.可应用的场景比较少。在软件开发中，需要定义语言文法的应用实例非常少，所以这种模式很少被使用到。
 *
 * 原理：
 *      1.文法适用于描述语言的语法结构的形式规则。
 *      2.句子是语言的基本单位，是语言集中的一个元素，它有终结符构成，能有“文法”推导出。
 *      3.语法树是句子结构的一种树型表示，它代表了句子的推到结果，它有利于理解句子语法结构的层次。
 *
 * 结构：
 *      1.抽象表达式（Abstract Expression）角色：定义解释器的接口，约定解释器的解释操作，主要包含解释方法interpret()
 *      2.终结符表达式（Terminal Expression）角色：是抽象表达式的子类，用来实现文件中与终结符相关的操作，文法中的每一个终结符都有一个具体终结表达式与之相对应。
 *      3.非终结符表达式（Nonterminal Expression）角色：也是抽象表达式的子类，用来实现文法中与非终结符相关的操作，文法中的每条规则都对应于一个非终结符表达式。
 *      4.环境（Context）角色：通过包含各个解释器需要的数据或是公共的功能，一般用来传递被所有解释器共享的数据，后面的解释器可以从这里获取这些信息。
 *      5.客户端（Client）角色：主要任务是将需要分析的句子或表达式转换成使用解释器对象藐视的抽象语法树，然后调用解释器的解释方法，当然也可以通过环境角色间接访问解释器的解释方法。
 *
 * 场景：
 *      1.当语言的文法较为简单，且执行效率不是关键问题时，
 *      2.当问题重复出现，且可以用一种简单的语言来进行表达时。
 *      3.当一个语言需要解释执行，并且语言中的句子可以表示为一个抽象语法树的时候，如XML文档
 *
 */
public class InterpreterMethodDemo {



    //抽象表达式类
    interface AbstractExpression{
        public Object interpret(String info); //解释方法
    }
    //终结符表达式类
    class TerminalExpression implements AbstractExpression{

        @Override
        public Object interpret(String info) {
            //对终结符表达式的处理
            return null;
        }
    }
    //非终结符表达式类
    class NonterminalExpression implements AbstractExpression{

        private AbstractExpression exp1;
        private AbstractExpression exp2;

        @Override
        public Object interpret(String info) {
            //非对终结符表达式的处理
            return null;
        }
    }

    class Context{
        private AbstractExpression exp;
        public Context(){
            //数据初始化
        }

        public void operation(String info){
            //调用相关表达式类的解释方法
        }
    }
}
