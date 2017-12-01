package com.gao.base_002.demo001;

/**
 * synchronized异常
 *
 */
public class SyncException {

	private int i = 0;
	public synchronized void operation(){
		while(true){
			try {
				i++;
				Thread.sleep(100);
				System.out.println(Thread.currentThread().getName() + " , i = " + i);
				if(i == 10){
					Integer.parseInt("a");
//					throw new RuntimeException();
				}
			} catch (Exception e) {
//				e.printStackTrace();
				System.err.println("log info i="+i);
				continue;
			}
		}
	}
	
	public static void main(String[] args) {
		
		final SyncException se = new SyncException();
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				se.operation();
			}
		});
		t1.start();
	}
	
	
}
