package Calc;

import java.util.InputMismatchException;
import java.util.Scanner;

public class main {
    public static void main(String[] args)  {

        System.out.println("请选择运算方法\n1.加法\n2.减法\n3.乘法\n4.除法");
        Scanner input = new Scanner(System.in);
        int in = input.nextInt();
        if (in == 1){
            System.out.println("请输入加数1：\n");
            double jiashu1 = input.nextDouble();
            System.out.println("请输入加数2：\n");
            double jiashu2 = input.nextDouble();
            Calc calc = new Calc(jiashu1,jiashu2);
            System.out.println(jiashu1+ " + " +jiashu2+"="+calc.jiafa(jiashu1,jiashu2));
        } else if (in==2) {
            System.out.println("请输入被减数：\n");
            double beijianshu = input.nextDouble();
            System.out.println("请输入减数：\n");
            double jianshu = input.nextDouble();
            Calc calc = new Calc(beijianshu,jianshu);
            System.out.println(beijianshu+ " - " +jianshu+"="+calc.jianfa(beijianshu,jianshu));
        } else if (in==3) {
            System.out.println("请输入乘数1：\n");
            double chengshu1 = input.nextDouble();
            System.out.println("请输入乘数2：\n");
            double chengshu2 = input.nextDouble();
            Calc calc = new Calc(chengshu1,chengshu2);
            System.out.println(chengshu1+ " x " +chengshu2+"="+calc.chengfa(chengshu1,chengshu2));
        } else if (in==4) {
            System.out.println("请输入被除数：\n");
            double beichushu = input.nextDouble();
            System.out.println("请输入除数：\n");
            double chushu = input.nextDouble();
            Calc calc = new Calc(beichushu,chushu);
            System.out.println(beichushu+ " / " +chushu+"="+calc.chufa(beichushu,chushu));

        }else {
            System.out.println("非法输入");
        }
        input.close();
    }
}
