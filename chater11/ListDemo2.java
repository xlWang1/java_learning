import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListDemo2 {
    /*
     * List 的其他常用操作
     *1.boolean isEmpty = fruits.isEmpty(); 判断列表是否为空
     * int index = fruits.indexOf("香蕉"); // 返回元素 "香蕉" 的索引，如果不存在则返回 -1
     * Collections.reverse(fruits); // 反转列表中的元素顺序
     * List<String> subList = fruits.subList(1, 3); // 获取索引 1 到 2 之间的子列表
     * fruits.set(0, "葡萄"); // 将第一个元素替换为 "葡萄"
     *Collections.sort(fruits)//排序
     * */

    @Test
    public void test1() {
        List<String> arrayList = new ArrayList<>();
        arrayList.add("苹果");
        arrayList.add("香蕉");
        arrayList.add("橙子");
        System.out.println(arrayList.isEmpty());
        System.out.println(arrayList.indexOf("sa"));
        System.out.println(arrayList.indexOf("苹果"));
        Collections.reverse(arrayList);
        System.out.println(arrayList);
        List<String> sub = arrayList.subList(1,3);
        System.out.println(sub);

    }
}
