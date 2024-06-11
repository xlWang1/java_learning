import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListDemo1 {

    /*
    * List 的常见实现类
    *1.ArrayList：ArrayList 是基于数组实现的动态数组，它支持快速随机访问元素。如果需要频繁进行元素的访问，ArrayList是合适的选择。
    *2.LinkedList：LinkedList 是基于双向链表实现的列表，它适用于需要频繁进行插入和删除操作的情况。需要频繁进行插入和删除操作LinkedList是合适的选择
    *
    * */
    @Test
    public void test1(){
        List<String> arrayList = new ArrayList<>();
        arrayList.add("苹果");
        arrayList.add("香蕉");
        arrayList.add("橙子");
        String fruit = arrayList.get(1); // 快速访问第二个元素

    }
    @Test
    public void test2(){
        List<String> linkedList = new LinkedList<>();
        linkedList.add("苹果");
        linkedList.add("香蕉");
        linkedList.add("橙子");
        linkedList.add(1, "葡萄"); // 在第二个位置插入元素
        linkedList.remove(0); // 移除第一个元素


    }
}
