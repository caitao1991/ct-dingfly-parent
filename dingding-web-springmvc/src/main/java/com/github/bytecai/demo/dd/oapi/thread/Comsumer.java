package com.github.bytecai.demo.dd.oapi.thread;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @title ct-dingfly-parent
 * @Description CT的fly系列项目
 * @Author CT
 * @Date 2020/7/10 8:48
 * @Version 1.0
 */
public class Comsumer implements Runnable {

	private ResourceBean resourceBean;

	private static final int DEFAULT_RANGE_FOR_SLEEP = 1000;

	public Comsumer(ResourceBean resourceBean) {
		this.resourceBean = resourceBean;
	}

	@Override
	public void run() {
		System.out.println("启动消费者线程！");
		Random r = new Random();
		boolean isRunning = true;
		try {
			while (this.resourceBean.comsumerCount.get() < this.resourceBean.expect_count) {
				System.out.println("正从队列获取数据...");
				String data = (String) this.resourceBean.blockingQueue.poll(2,TimeUnit.SECONDS);
				if (null != data) {
//					System.out.println("拿到数据：" + data + this.resourceBean.comsumerCount.incrementAndGet());
					System.out.println("正在消费数据：" + data + "comsumer=" + this.resourceBean.comsumerCount.incrementAndGet());
					Thread.sleep(r.nextInt(DEFAULT_RANGE_FOR_SLEEP));
				} else {
					// 超过2s还没数据，认为所有生产线程都已经退出，自动退出消费线程。
					isRunning = false;
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		} finally {
			System.out.println("退出消费者线程！");
		}
	}

}
