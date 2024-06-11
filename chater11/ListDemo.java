import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListDemo {
    /*List 接口的基本方法：
    * 1.添加元素：你可以使用 add() 方法将元素添加到列表的末尾;add(index, element)将元素插入到指定索引
    * 2.获取元素：使用 get() 方法根据索引获取列表中的元素。索引从 0 开始计数，表示第一个元素
    * 3.删除元素：使用 remove() 方法根据索引或元素值删除列表中的元素
    * 4.获取列表大小：使用 size() 方法获取列表中元素的数量。
    * 5.遍历列表：可以使用循环或迭代器来遍历列表中的元素
    * 6.修改元素：set(index, element)将索引为index的元素修改为element
    * */
    @Test
    public void test1(){
        List list1 = new ArrayList();
        list1.add("1");
        list1.add("2");
        list1.add("3");
        list1.add("4");
        list1.add(4,"5");
        list1.set(0,"f");

        System.out.println(list1.get(3));
        System.out.println(list1);
        /*遍历元素
        * 1.循环
        * 2.迭代器
        * */

        for (int i = 0; i < list1.size(); i++) {//for循环
            System.out.println(list1.get(i));
        }
        for(Object li: list1){//for-each
            System.out.println(li);
        }

        Iterator<String> iterator = list1.iterator();
        while (iterator.hasNext()) {
            String li = iterator.next();
            System.out.println(li);
        }

    }
    @Test
    public void test2(){
        List list1 = new ArrayList();
        list1.add("1");
        list1.add("2");
        list1.add("3");
        list1.add("4");
        System.out.println(list1);
        list1.remove("3");
        System.out.println(list1);
        list1.remove(2);
        System.out.println(list1);
        System.out.println(list1.indexOf("r"));

    }


}
