package go.multithread.base.oo1_base.base003_bank;

public class BankDemo {

	public static void main(String[] args) {

		Cust c=new Cust();
		Thread t1=new Thread(c);
		Thread t2=new Thread(c);
		t1.start();
		t2.start();
	}

}
