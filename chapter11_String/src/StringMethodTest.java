import org.junit.Test;

import java.io.UnsupportedEncodingException;

public class StringMethodTest {
    /*
    * String构造器的使用
    *
    * */
    @Test
    public void test1(){
        String s1 = new String();
        String s2 = new String("");
        String s3 = new String(new  char[]{'r','a','p'});
        System.out.println(s3);
    }

    /*
     * String与其他结构的转变
     * 1.与基本数据类型
     * 2.与char[]之前的转换
     * 3.//与byte[]之间转化
     * 4.
     * */
    @Test
    public void test2(){
        int num = 10;
        //基本数据类型--String
        //方式1：
        String s1 = num + "";
        //方式2：
        String s2 = String.valueOf(num);
        //String--基本数据类型 调用包装类的parseXxx（String str）
        String s3 = "250";
        int num1 =  Integer.parseInt(s3);
    }
    @Test
    public void test3(){
        //String -- char[]:调用String的toCharArry（）方法
        String s1 = "hello";
        char[] arr = s1.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);

        }



    }
    @Test
    public void test4() throws UnsupportedEncodingException {
        //与byte[]之间转化
        //String -- byte[]:调用String的toCharArry（）方法
        String str = new String("hello美女");
        byte[] byte1 = str.getBytes();//使用默认字符集utf-8
        for (int i = 0; i < byte1.length; i++) {
            System.out.println(byte1[i]);

        }
        System.out.println();
        //getBytes(String charSetName):使用指定字符集
        byte[] byte2 = str.getBytes("gbk");//使用默认字符集
        for (int i = 0; i < byte2.length; i++) {
            System.out.println(byte2[i]);

        }
    }

}
