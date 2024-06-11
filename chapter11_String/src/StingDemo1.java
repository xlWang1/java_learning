import org.junit.Test;


/*
* 常量 + 常量：结果仍在字符串常量池中
* 常量+变量或者变量+常量 ：通过new的方式新建一个字符串，返回堆空间中此字符串对象的地址
* 调用字符串intern（）：返回字符串常量池中字面量的地址
*contact():不管由常量或变量调用，不管参数是常量还是变量，调用玩contact（）方法后，都会返回一个new的对象
*
* */

public class StingDemo1 {
    @Test
    public void stringtest(){
        String name1 = "吴家锋";//字面量定义方式
        String name2 = "吴家锋";
        String name3 = new String("吴家锋");
        String name4 = new String("吴家锋");
        System.out.println(name1 == name2);//true
        System.out.println(name3 == name4);//false
        System.out.println(name1 == name4);//false
        System.out.println(name1.equals(name4));//true
    }
    @Test
    public void test2(){
        Persion p1 = new Persion();
        Persion p2 = new Persion();
        p1.name = "小黑";
        p2.name = "小白";
        p1.name = "小黑子";
        System.out.println(p2.name);

    }
    //连接符
    @Test
    public void test3(){
        String s1 = "hello";
        String s2 = "world";
        String s3 = "helloworld";
        String s4 = "hello" + "world";
        String s5 = s1 + "world";
        String s6 = "hello" + s2;
        String s7 = s1 + s2;

        String s8 = s1.concat(s2);
        String s9 = "hello".concat(s2);
        String s10 = s1.concat("world");
        System.out.println(s8 == s9);//false
        System.out.println(s8 == s10);//false
        System.out.println(s9 == s10);//false
        System.out.println(s3 == s4);//true
        System.out.println(s3 == s5);//false
        System.out.println(s3 == s6);//false
        System.out.println(s3 == s7);//false
        System.out.println(s5 == s6);//false
        System.out.println(s5 == s7);//false




    }

}
