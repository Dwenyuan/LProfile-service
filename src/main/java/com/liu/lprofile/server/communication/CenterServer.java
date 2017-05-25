package com.liu.lprofile.server.communication;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 主要负责与探针和web端通信
 * 
 * @author liu
 *
 */
public class CenterServer {

	private int PORT = 12017;
	private ServerSocket serverSocket;
	private ExecutorService executorService;

	public CenterServer() throws IOException {
		super();
		serverSocket = new ServerSocket(PORT);
		executorService = Executors.newCachedThreadPool();
		System.out.println("服务器监听端口" + PORT);
	}

	public void listener() {
		try {
			while (true) {
				Socket socket = serverSocket.accept();
				executorService.execute(new DealWithMessage(socket));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
