package go.multithread.base.oo2_middle.demo001;

/**
 * 业务整体需要使用完整的synchronized，保持业务的原子性。
 *
 */
public class DirtyRead {

	private String username = "张三";
	private String password = "123";

	public synchronized void setValue(String username, String password) {
		this.username = username;
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.password = password;
		System.out.println("setValue最终结果：username = " + username + " , password = " + password);
	}

	// 如果不加上synchronized关键字，则会引起脏读
	public synchronized void getValue() {
		System.out.println("getValue方法得到：username = " + this.username + " , password = " + this.password);
	}

	public static void main(String[] args) throws Exception {
		final DirtyRead dr = new DirtyRead();
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				dr.setValue("李四", "456");
			}
		});
		t1.start();
		Thread.sleep(1000);
		dr.getValue();
	}
}
