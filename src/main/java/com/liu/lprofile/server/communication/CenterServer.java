package com.liu.lprofile.server.communication;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 主要负责与探针和web端通信
 * 
 * @author liu
 *
 */
public class CenterServer {

	private int PORT = 12017;
	private ServerSocket serverSocket;

	public CenterServer() throws IOException {
		super();
		serverSocket = new ServerSocket(PORT);
		System.out.println("服务器监听端口" + PORT);
	}

	public void listener() {
		try {
			while (true) {
				Socket socket = serverSocket.accept();
				new Thread(new DealWithMessage(socket)).start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
