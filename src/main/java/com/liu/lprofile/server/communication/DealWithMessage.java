package com.liu.lprofile.server.communication;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

import com.liu.lprofile.server.util.ZIPUtil;

public class DealWithMessage implements Runnable {

	private Socket socket;

	public DealWithMessage(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			InputStream inputStream = socket.getInputStream();
			int len;
			byte[] b = new byte[2048];
			while ((len = inputStream.read(b)) != -1) {
				System.out.println(new String(ZIPUtil.decoder(b)));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			try {
				socket.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

}
