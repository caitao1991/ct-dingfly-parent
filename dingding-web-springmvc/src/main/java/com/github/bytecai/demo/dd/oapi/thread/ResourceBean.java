package com.github.bytecai.demo.dd.oapi.thread;

import java.io.Serializable;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @title ct-dingfly-parent
 * @Description CT的fly系列项目
 * @Author CT
 * @Date 2020/7/10 8:52
 * @Version 1.0
 */
public class ResourceBean implements Serializable {


	private Integer capacity = 100;

	public  AtomicInteger count = new AtomicInteger();
	public AtomicInteger comsumerCount = new AtomicInteger(0);

	public Integer expect_count = 0;

	public static BlockingQueue<Object> blockingQueue = null;

	public ResourceBean(Integer capacity, Integer count) {
		this.capacity = capacity;
		this.expect_count = count;
		this.blockingQueue = new LinkedBlockingQueue<>(capacity);
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public static BlockingQueue<Object> getBlockingQueue() {
		return blockingQueue;
	}

	public static void setBlockingQueue(BlockingQueue<Object> blockingQueue) {
		ResourceBean.blockingQueue = blockingQueue;
	}
}
