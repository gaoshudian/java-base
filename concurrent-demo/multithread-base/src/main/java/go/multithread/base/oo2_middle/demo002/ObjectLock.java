package go.multithread.base.oo2_middle.demo002;

/**
 * 使用synchronized代码块加锁,比较灵活
 * @author Gao
 *
 */
public class ObjectLock {

	public void method1(){
		synchronized (this) {	//对象锁
			try {
				System.out.println("do method1..");
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void method2(){		//类锁
		synchronized (ObjectLock.class) {
			try {
				System.out.println("do method2..");
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	private Object lock = new Object();
	public void method3(){		//任何对象锁
		synchronized (lock) {
			try {
				System.out.println("do method3..");
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		final ObjectLock objLock = new ObjectLock();
		Thread t1 = new Thread(()->objLock.method1(),"t1");
		Thread t2 = new Thread(()->objLock.method2(),"t2");
		Thread t3 = new Thread(()->objLock.method3(),"t3");
		t1.start();
		t2.start();
		t3.start();
	}
}