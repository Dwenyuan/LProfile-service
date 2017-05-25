package com.liu.lprofile.server.entity;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class MessageBucket {
	private static BlockingQueue<byte[]> blockingQueue = new ArrayBlockingQueue<byte[]>(64);
	private static MessageBucket messageBucket = new MessageBucket();

	private MessageBucket() {
		super();
	}

	public static MessageBucket getInstance() {
		return messageBucket;
	}

	public void putMessage(byte[] src) {
		try {
			blockingQueue.put(src);
		} catch (InterruptedException e) {
			// TODO 压入消息失败如何处理
			e.printStackTrace();
		}
	}

	public String getMessage() {
		byte[] take = null;
		try {
			take = blockingQueue.take();
		} catch (InterruptedException e) {
			// TODO 获取消息失败
			e.printStackTrace();
		}
//		byte[] decoder = ZIPUtil.decoder(take);
		return new String(take);
	}
}
