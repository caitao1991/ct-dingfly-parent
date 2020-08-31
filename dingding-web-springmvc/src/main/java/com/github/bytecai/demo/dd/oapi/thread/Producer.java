package com.github.bytecai.demo.dd.oapi.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @title ct-dingfly-parent
 * @Description CT的fly系列项目
 * @Author CT
 * @Date 2020/7/10 8:48
 * @Version 1.0
 */
public class Producer implements Runnable {

	private volatile boolean isRunning = true;
	private ResourceBean resourceBean;
	private static final int DEFAULT_RANGE_FOR_SLEEP = 1000;



	public Producer(ResourceBean resourceBean) {
		this.resourceBean = resourceBean;
	}

	@Override
	public void run() {

		String data = null;
		Random r = new Random();

		System.out.println("启动生产者线程！");
		try {
//			while (isRunning) {
				System.out.println("正在生产数据...");
				Thread.sleep(3000);	// r.nextInt(DEFAULT_RANGE_FOR_SLEEP)
				int count = resourceBean.count.incrementAndGet();
				data = "data:" + count;

				System.out.println("将数据：" + data + "放入队列...");
				if (!resourceBean.blockingQueue.offer(data, 10, TimeUnit.SECONDS)) {
					System.out.println("放入数据失败：" + data);
					resourceBean.count.decrementAndGet();
				}

//				if(count >= expectCount) {
//					this.isRunning = false;
//				}
//			}
		} catch (InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		} finally {
			System.out.println("退出生产者线程！");
		}
	}

	public void stop() {
		isRunning = false;
	}

}
