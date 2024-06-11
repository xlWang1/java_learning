//同步方法解决线程安全问题
public class SaleTicket1 implements Runnable{
    public static int tickets = 100;
    public static int temp = 0;
    static boolean flag = true;
    @Override
    public void run() {
        while(flag){

            show();
        }

    }
    public synchronized void show(){
        try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if(tickets > 0){
            System.out.println(Thread.currentThread().getName() + "售票，票号为：" + tickets);
            tickets--;
            temp++;
        } else if (tickets == 0) {
            System.out.println(temp);
            flag  = false;
        }

    }

}

class SaleTest1{
    public static void main(String[] args){
        SaleTicket1 saleTicket = new SaleTicket1();
        Thread window1 = new Thread(saleTicket,"窗口1");
        Thread window2 = new Thread(saleTicket,"窗口2");
        Thread window3 = new Thread(saleTicket,"窗口3");
        window1.start();
        window2.start();
        window3.start();
    }
}

