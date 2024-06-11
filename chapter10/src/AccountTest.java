import com.sun.tools.javac.Main;

public class AccountTest {
    public static void main(String[] args) {
        Account acc = new Account();
        Customer customer1 = new Customer(acc);
        Customer customer2 = new Customer(acc);
        Thread t1 = new Thread(customer1,"小黑");
        Thread t2 = new Thread(customer2,"小白");
        t1.start();
        t2.start();
    }

}
class Account{
    private double balance = 1000;

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

//    public synchronized void cunqian(double money){
//        if(money>0){
//            balance =  balance+money;
//
//            System.out.println(Thread.currentThread().getName()+"存钱成功，当前余额为"+balance);
//
//        }
//    }


}
class Customer implements Runnable{
    Account account;
    public Customer(Account account) {
        this.account = account;
    }

    @Override
    public void run() {

        for (int i = 3; i > 0 ; i--) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
//            account.cunqian(1000);

            System.out.println(cunqian(1000));
        }

    }
    public String cunqian(double money){
        synchronized (account) {
            account.setBalance(account.getBalance()+money);
            return Thread.currentThread().getName()+"存钱"+money+"元成功,当前账户余额为："+account.getBalance();
        }
    }
}
