package com.liu.lprofile.server.communication;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import com.liu.lprofile.server.entity.MessageBucket;
import com.liu.lprofile.server.util.ZIPUtil;

public class DealWithMessage implements Runnable {

	private Socket socket;

	public DealWithMessage(Socket socket) {
		this.socket = socket;
	}

	private static byte[] header = new byte[4];

	@Override
	public void run() {
		try {
			InputStream inputStream = socket.getInputStream();
			while (true) {
				byte[] message = readMessage(inputStream);
				MessageBucket.getInstance().putMessage(message);
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

	private int readHeader(InputStream inputStream, int len) throws IOException {
		len += inputStream.read(header, len, header.length - len);
		if (len == header.length) {
			return ZIPUtil.byteArrayToInt(header);
		} else {
			// 没有读完头文件
			return readHeader(inputStream, len);
		}
	}

	public int readHeader(InputStream inputStream) throws IOException {
		return readHeader(inputStream, 0);
	}

	/**
	 * 读取消息 1.读取消息头 2.根据消息头给出的message长度初始化一个对应长度的字节数组，准备接受数据 3.读取message
	 * 数据，如果中途出错则尝试重新读取
	 * 
	 * @param b
	 * @return
	 * @throws IOException
	 */
	private byte[] readMessage(InputStream inputStream, int len, byte[] content) throws IOException {
		len += inputStream.read(content, len, content.length - len);
		if (len == content.length) {
			return content;
		} else {
			// 没有读完，还有数据没有读到 接着读取
			return readMessage(inputStream, len, content);
		}
	}

	public byte[] readMessage(InputStream inputStream) throws IOException {
		int messageLenght = readHeader(inputStream);
		byte[] content = new byte[messageLenght];
		return readMessage(inputStream, 0, content);
	}

}
