package DemoStorage;

/**
 * @author 姚君彦
 * 2020/6/18,18:26
 * 奇怪的程序增加了
 */

/**
 * @author John
 *
 */
public class TestPrintNode {

    public static void main(String[] args){
        Node n = new Node("root");

        n.add(new Node("a1"),"父子");
        n.add(new Node("a2"),"父子");

        Node n2 = new Node("a3");
        n2.add(new Node("b1"),"父子");
        n2.add(new Node("b2"),"父子");
        n2.add(new Node("b3"),"父子");
        Node n3 = new Node("b4");
        n2.add(n3,"父子");
        n3.add(new Node("c1"),"父子");
        n3.add(new Node("c2"),"父子");

        n.add(n2,"父子");
        n.printAllNode(n);  //输出树
    }

}