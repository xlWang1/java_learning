import org.junit.Test;

public class StringMethodTest1 {
    /*
    * String的常用方法：
    * 1.boolean isEmpty（）：判断是否为空
    * 2.int length（）：返回字符串的长度
    * 3.String concat（xx）：拼接
    * 4.boolean equals（Object obj）：比较字符串是否相等，区分大小写
    * 5.boolean equalsIgnoreCase（Object obj）：比较字符串是否相等，不区分大小写
    * 6.int comPareTo（String other）：比较字符串大小，区分大小写，按照Unicode值比较大小
    * 7.int comPareToIgnoreCase（String other）：比较字符串大小，不区分大小写
    * 8.String ToLowerCase（）：大写转小写
    * 9.String ToUpperCase（）：小写转大写
    * 10.String trim（）：去掉字符串前后空白符
    * 11.public String intern（）：结果在常量池中共享
    * 12.boolean contains(xx):是否包含xx
    * 13.int indexOf（String str，int fromIndex）：从指定索引开始查找，返回指定子字符串第一次在字符串中出现的索引，没有返回-1
    * 14.int indexOf（xx）：从前往后找当前字符串中xx，如果有返回第一次出现的下标，没有返回-1
    * 15.int lastIndexOf（xx）:从后往前找当前字符串中xx，有则返回最后一次出现时的下标，没有返回-1
    * 16.int lastIndexOf（String str，int fromIndex）：从指定索引开始查找，从后往前找，返回指定子字符串最后一次在字符串中出现的索引，没有返回-1
    *
    * 17.String substring(int beginIndex):返回一个新的字符串，他是从此字符串的beginIndex开始接取的
    * 18.String substring(int beginIndex，int endIndex)返回一个新的字符串，他是从此字符串的beginIndex开始接取的，到endIndex结束
    *
    * 19.char charAt（index）：返回[index]位置的字符
    * 20.char[] toCharArry():将此字符串转化为一个新的字符串返回
    * 21.static String valueOf(char[] data):返回指定数组中表示该字符序列的String
    * 22.static String copyValueOf(char[] data):返回指定数组中表示该字符序列的String
    * 23.static String valueOf(char[] data,int offSet,int count):
    * 24.static String copyValueOf(char[] data,int offSet,int count):
    * 25.boolean startWith(xx):测试字符串是否以xx开始
    * 26.boolean startWith(String prefix，int toffset):测试字符串从指定索引是否以xx开始
    * 27.boolean endWith(xx)
    * 28.String replace（char oldChar，char newChar）
    * 29.String replace（CharSequence target，CharSequence replacement）：使用指定的字面替换序列
    * 30.String replaceAll（String regex，String replacement）：使用给定的replacement替换此字符串所有匹配的给定的正则表达式
    * 31.String replaceFirst（String regex，String replacement）：使用给定的replacement替换此字符串替换此字符串中匹配给定的正则表达式的第一个子字符串
    *
    * */
    @Test
    public void test1(){
        String s1 = "abc";
        String s2 = "acd";
        int rec = s1.compareTo(s2);//返回结果为负数则后面的数大，反之前面的大,0则相等
        System.out.println(rec);

        String s3 = "     a b c     ";
        System.out.println(s3.trim());

    }
}
