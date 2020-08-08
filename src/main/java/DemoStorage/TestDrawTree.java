package DemoStorage;

/**
 * @author Ҧ����
 * 2020/6/18,18:31
 * ��ֵĳ���������
 */

import util.JsonUtil;

import javax.swing.*;
import java.awt.*;

/**
 * @author John
 *
 */
//�������û�н��н��̹رգ���Ҫ�������������ɱ��
public class TestDrawTree extends JFrame{
    
    public TestDrawTree(){
        super("Test Draw Tree");
        initComponents();
    }
    
    public static void main(String[] args){
        TestDrawTree frame = new TestDrawTree();
        
        frame.setSize(800, 600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void initComponents(){
        /*
         * ��ʼ����������
         */
        Node root = new Node("root");
        
        Node a1 = new Node("a1");
        Node a2 = new Node("a2");
        Node a3 = new Node("a3");
        
        Node b1 = new Node("b1");
        Node b2= new Node("b2");
        Node b3 = new Node("b3");
        Node b4 = new Node("b4");
        
        Node c1 = new Node("c1");
        Node c2 = new Node("c2");
        
        Node d1 = new Node("d1");
        Node d2 = new Node("d2");
        
        Node d5 = new Node("d5");
        Node d6 = new Node("d6");
        Node d7 = new Node("d7");
        
        
        root.add(a1,"����");
        root.add(a2,"����");
        root.add(a3,"����");
//        a1.add(a2,"����");
//        a2.add(a3,"�ֵ�");
        
        a3.add(b1,"����");
        a3.add(b2,"����");
        a3.add(b3,"����");
        a3.add(b4,"����");
        
        b1.add(d1,"����");
        b1.add(d2,"����");
        
        b2.add(d5,"����");
        b2.add(d6,"����");
        b2.add(d7,"����");
        
        b4.add(c1,"����");
        b4.add(c2,"����");
        
        root.printAllNode(root);    //�����
        
        System.out.println( JsonUtil.toJson(root));
        
        /*
         * ����һ�����ڻ���������岢��������,ʹ����Զ��뷽ʽ
         */
        TreePanel panel1 = new TreePanel(TreePanel.CHILD_ALIGN_RELATIVE);
        panel1.setTree(root);
        
        /*
         * ����һ�����ڻ���������岢��������,ʹ�þ��Զ��뷽ʽ
         */
        TreePanel panel2 = new TreePanel(TreePanel.CHILD_ALIGN_ABSOLUTE);
        panel2.setTree(root);
        panel2.setBackground(Color.BLACK);
        panel2.setGridColor(Color.WHITE);
        panel2.setLinkLineColor(Color.WHITE);
        panel2.setStringColor(Color.BLACK);
        
        JPanel contentPane = new JPanel();
        contentPane.setLayout(new GridLayout(2,1));
        contentPane.add(panel1);
        contentPane.add(panel2);
        
        add(contentPane,BorderLayout.CENTER);
    }
}