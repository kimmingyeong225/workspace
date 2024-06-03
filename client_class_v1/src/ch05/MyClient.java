package ch05;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

// 2-1 상속을 활용한 구현 클래스 설계 하기 
public class MyClient extends AbstractClient {

	@Override
	protected void connection() throws IOException {
		super.setSocket(new Socket("localhost", 5000));
	}

	public static void main(String[] args) {
		MyClient myClient = new MyClient();
		myClient.run();
	}

}
