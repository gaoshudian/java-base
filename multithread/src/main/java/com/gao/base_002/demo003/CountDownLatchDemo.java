package com.gao.base_002.demo003;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch实例
 * @author gaoshudian
 * @date 2017年4月6日 下午5:09:37
 */
public class CountDownLatchDemo {
	private volatile static List<String> list = new ArrayList<String>();

	public void add() {
		list.add("bjsxt");
	}

	public int size() {
		return list.size();
	}

	public static void main(String[] args) {

		final CountDownLatchDemo list2 = new CountDownLatchDemo();

		final CountDownLatch countDownLatch = new CountDownLatch(1);

		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					for (int i = 0; i < 10; i++) {
						list2.add();
						System.out.println("当前线程：" + Thread.currentThread().getName() + "添加了一个元素..");
						Thread.sleep(500);
						if (list2.size() == 5) {
							System.out.println("已经发出通知..");
							countDownLatch.countDown();
						}
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}, "t1");

		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				if (list2.size() != 5) {
					try {
                        System.out.println("t2进入...");
                        countDownLatch.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println("当前线程：" + Thread.currentThread().getName() + "收到通知线程停止..");
				throw new RuntimeException();
			}
		}, "t2");

		t2.start();
		t1.start();

	}

}
