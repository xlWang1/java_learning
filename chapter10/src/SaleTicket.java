//同步代码块解决线程安全
public class SaleTicket implements Runnable{
    public static int tickets = 100;
    public static int temp = 0;
    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            synchronized (this){
                if(tickets > 0){
                    System.out.println(Thread.currentThread().getName() + "售票，票号为：" + tickets);
                    tickets--;
                    temp++;
                } else if (tickets == 0) {
                    System.out.println(temp);
                    break;
                }
            }
        }

    }

}
class SaleTest{
    public static void main(String[] args){
        SaleTicket saleTicket = new SaleTicket();
        Thread window1 = new Thread(saleTicket,"窗口1");
        Thread window2 = new Thread(saleTicket,"窗口2");
        Thread window3 = new Thread(saleTicket,"窗口3");
//        window1.setName("窗口1");
//        window2.setName("窗口2");
//        window3.setName("窗口3");
        window1.start();
        window2.start();
        window3.start();
    }
}

