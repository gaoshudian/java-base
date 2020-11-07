package go.concurrent.ch1.base.safeend;

/**
 *类说明：阻塞方法中抛出InterruptedException异常后，如果需要继续中断，需要手动再中断一次
 */
public class HasInterrputException {
	
	private static class UseThread extends Thread{
		
		public UseThread(String name) {
			super(name);
		}
		
		@Override
		public void run() {
			while(!isInterrupted()) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					System.out.println(Thread.currentThread().getName()
							+" in InterruptedException interrupt flag is " +isInterrupted());
					//资源释放
					interrupt();
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + " I am extends Thread.");
			}
			System.out.println(Thread.currentThread().getName() +" interrupt flag is "+isInterrupted());
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Thread endThread = new UseThread("HasInterrputEx");
		endThread.start();
		Thread.sleep(5000);
		endThread.interrupt();
		

	}

}
