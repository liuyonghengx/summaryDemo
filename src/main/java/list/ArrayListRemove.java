package list;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: ArrayListRemove
 * @Description: remove（int index）方法移除此列表中指定位置的元素。向左移动所有后续元素(将其索引减1)。
 * 因此在做删除操作时集合的大小为动态变化的，为了防止漏删(相邻的元素删不掉)，必须从后往前删！
 * @author: liuyongheng
 * @date: 2018/8/15 16:58
 */
public class ArrayListRemove {

    public static void main(String[] args) {
        List list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        for (int j = 0; j < list.size(); j++) {
            list.remove(j);
        }
        for (int i = 0; i < list.size(); i++) {
            //相邻的元素删不掉，剩余246
            System.out.println(list.get(i));
        }
//        for (int k = list.size() - 1; k >= 0; k--) {
//            list.remove(k);
//        }
//        for (int i = 0; i < list.size(); i++) {
//            //控制台为空
//            System.out.println(list.get(i));
//        }

    }

}
