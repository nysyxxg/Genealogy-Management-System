package DemoStorage;

/**
 * @author Ҧ����
 * 2020/6/18,18:27
 * ��ֵĳ���������
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;

/**
 * TODO ͬһ���������BUG��Ӧ�ö�ÿһ������н�㶼���и���ͳ�ƣ�֮��Ż��ơ�
 * @author John
 *
 */
public class TreePanel extends JPanel {

    private Node tree;              //����������
    private int gridWidth = 80;     //ÿ�����Ŀ��
    private int gridHeight = 20;    //ÿ�����ĸ߶�
    private int vGap = 50;          //ÿ2�����Ĵ�ֱ����
    private int hGap = 30;          //ÿ2������ˮƽ����

    private int startY = 20;        //������Y��Ĭ�Ͼ��붥��10����
    private int startX = 0;         //������X��Ĭ��ˮƽ���ж���

    private int childAlign;                     //���Ӷ��뷽ʽ
    public static int CHILD_ALIGN_ABSOLUTE = 0; //���Panel����
    public static int CHILD_ALIGN_RELATIVE = 1; //��Ը�������

    private Font font = new Font("΢���ź�",Font.BOLD,11);  //������������

    private Color gridColor = Color.BLACK;      //��㱳����ɫ
    private Color linkLineColor = Color.BLACK;  //���������ɫ
    private Color stringColor = Color.WHITE;    //����������ֵ���ɫ

    /**
     * Ĭ�Ϲ���
     */
    public TreePanel(){
        this(null,CHILD_ALIGN_ABSOLUTE);
    }

    /**
     * ���ݴ����Node���������Ծ��Ծ��еķ�ʽ����
     * @param n Ҫ���Ƶ���
     */
    public TreePanel(Node n){
        this(n,CHILD_ALIGN_ABSOLUTE);
    }

    /**
     * ����Ҫ����ʱ��Ķ������
     * @param childAlign �������
     * @see DemoStorage.TreePanel#CHILD_ALIGN_RELATIVE
     * @see DemoStorage.TreePanel#CHILD_ALIGN_ABSOLUTE
     */
    public TreePanel(int childAlign){
        this(null,childAlign);
    }

    /**
     * ���ݺ��Ӷ������childAlign���Ƶ����ĸ����n
     * @param n Ҫ���Ƶ����ĸ����
     * @param childAlign �������
     */
    private TreePanel(Node n, int childAlign){
        super();
        setTree(n);
        this.childAlign = childAlign;
    }

    /**
     * �������ڻ��Ƶ���
     * @param n ���ڻ��Ƶ�����
     */
    public void setTree(Node n) {
        tree = n;
    }

    //��д���ѣ������Լ��Ļ��Ʒ���
    public void paintComponent(Graphics g){
        startX = (getWidth()-gridWidth)/2;
        super.paintComponent(g);
        g.setFont(font);
        drawAllNode(tree, startX, g);
    }

    /**
     * �ݹ����������
     * @param n �����Ƶ�Node
//     * @param xPos ���ڵ�Ļ���Xλ��
     * @param g ��ͼ�����Ļ���
     */
    public void drawAllNode(Node n, int x, Graphics g){
        int y = n.getLayer()*(vGap+gridHeight)+startY;
        int fontY = y + gridHeight - 5;     //5Ϊ���Եó���ֵ�������ͨ��FM�������ȷ�ģ�����Ӱ���ٶ�

        g.setColor(gridColor);
        g.fillRect(x, y, gridWidth, gridHeight);    //�����ĸ���

        g.setColor(stringColor);
        g.drawString(n.toString(), x, fontY);       //����������

        if(n.hasChild()){
            List<Node> c = n.getChilds();
            int size = n.getChilds().size();
            int tempPosx = childAlign == CHILD_ALIGN_RELATIVE
                    ? x+gridWidth/2 - (size*(gridWidth+hGap)-hGap)/2
                    : (getWidth() - size*(gridWidth+hGap)+hGap)/2;

            int i = 0;
            for(Node node : c){
                int newX = tempPosx+(gridWidth+hGap)*i; //���ӽ����ʼX
                g.setColor(linkLineColor);
                g.drawLine(x+gridWidth/2, y+gridHeight, newX+gridWidth/2, y+gridHeight+vGap);   //�����ӽ�����
                drawAllNode(node, newX, g);
                i++;
            }
        }
    }

    public Color getGridColor() {
        return gridColor;
    }

    /**
     * ���ý�㱳����ɫ
     * @param gridColor ��㱳����ɫ
     */
    public void setGridColor(Color gridColor) {
        this.gridColor = gridColor;
    }

    public Color getLinkLineColor() {
        return linkLineColor;
    }

    /**
     * ���ý�������ߵ���ɫ
     * @param gridLinkLine ��������ߵ���ɫ
     */
    public void setLinkLineColor(Color gridLinkLine) {
        this.linkLineColor = gridLinkLine;
    }

    public Color getStringColor() {
        return stringColor;
    }

    /**
     * ���ý����������ɫ
     * @param stringColor �����������ɫ
     */
    public void setStringColor(Color stringColor) {
        this.stringColor = stringColor;
    }

    public int getStartY() {
        return startY;
    }

    /**
     * ���ø�����Yλ��
     * @param startY ������Yλ��
     */
    public void setStartY(int startY) {
        this.startY = startY;
    }

    public int getStartX() {
        return startX;
    }

    /**
     * ���ø�����Xλ��
     * @param startX ������Xλ��
     */
    public void setStartX(int startX) {
        this.startX = startX;
    }

}