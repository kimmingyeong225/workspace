package ch05;

import java.io.IOException;
import java.net.ServerSocket;

public class Test_MyServer extends Test_AbstractServer {

	@Override
	protected void setupServer() throws IOException {
		super.setserverSocket(new ServerSocket(5000));
		System.out.println("Server started on port 5000");
	}

	@Override
	protected void connection() throws IOException {
		super.setsocket(super.getServerSocket().accept());
	}

	public static void main(String[] args) {
		Test_AbstractServer test_AbstractServer = new Test_MyServer();
		test_AbstractServer.run();
	}

}
