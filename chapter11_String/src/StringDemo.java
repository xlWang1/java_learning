import org.junit.Test;

    /*
    Sting的不可变性
    1、当对字符串变量重新赋值时，需要重新指定一个字符串常量的位置进行赋值，不能在原有的位置上进行修改
    2、当对现有的字符串进行拼接操作时，需要重新开辟空间保存拼接后的字符串，不能在原有的位置上进行修改
    3、当调用字符串得到replace（）替换现有的某个字符时，要重新开辟空间保存修改后的字符串，不能在原有的位置上进行修改

 */

public class StringDemo {
    @Test
    public void stringtest(){
        String name1 = "吴家锋";//字面量定义方式
        String name2 = "吴家锋";
        System.out.println(name1 == name2);
    }

    @Test
    public void test2(){
        String name1 = "吴家锋";
        String name2 = "吴家锋";
        name2 = "吴佳凤";
        System.out.println(name1);
    }
    @Test
    public void test3(){
        String name1 = "吴家锋";
        String name2 = "吴家锋";
        name2 += "来了";
        System.out.println(name1);
        System.out.println(name2);
    }
    @Test
    public void test4(){
        String name1 = "吴家锋";
        String name2 = "吴家锋";
        String name3 = name2.replace('吴','王');
        System.out.println(name1);
        System.out.println(name2);
        System.out.println(name3);
    }
}

