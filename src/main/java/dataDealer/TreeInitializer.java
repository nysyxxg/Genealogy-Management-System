package dataDealer;

import dataLayer.GenderType;
import dataLayer.Member;
import dataLayer.Tree;

/**
 * @author Ҧ����
 * 2020/6/17,14:40
 * ��ֵĳ���������
 */
public class TreeInitializer {
    public static void main(String[] args) {
        String treeInfoPath = "src/data/tree.data";
        //ÿ������ӵ��һ���˽ڵ㣬���������Ͻ����޸���Ϣ�������˰���insert����
        Tree d = new Tree(new Member("���޸�����",0, GenderType.unknown));
        IODealer.writeObjectToFile(d,treeInfoPath);
    }
}
