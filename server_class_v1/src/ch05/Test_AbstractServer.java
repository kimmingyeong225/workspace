package ch05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public abstract class Test_AbstractServer {

	private ServerSocket serverSocket;
	private Socket socket;
	private BufferedReader readerStream;
	private PrintWriter writerStream;
	private BufferedReader keyboardReader;

	// set
	protected void setserverSocket(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}

	protected void setsocket(Socket socket) {
		this.socket = socket;
	}

	// get

	protected ServerSocket getServerSocket() {
		return this.serverSocket;
	}

	// 실행에 흐름이 필요하다
	public final void run() {
		try {
			setupServer();
			connection();
			setupStream();
			startService();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			cleanup();
		}
	}

	// 연결
	protected abstract void setupServer() throws IOException;

	protected abstract void connection() throws IOException;

	private void setupStream() throws IOException {
		readerStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		writerStream = new PrintWriter(socket.getOutputStream());
		keyboardReader = new BufferedReader(new InputStreamReader(System.in));
	}

	private void startService() {
		Thread readThread = createReadThread();
		Thread writeThread = createWriteThread();

		readThread.start();
		writeThread.start();
		try {
			readThread.join();
			writeThread.join();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private Thread createReadThread() {
		return new Thread(() -> {
			try {
				String msg;
				while ((msg = readerStream.readLine()) != null) {
					System.out.println("클라이언트 측 msg : " + msg);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

	}

	private Thread createWriteThread() {
		return new Thread(() -> {
			try {
				String msg;
				while ((msg = keyboardReader.readLine()) != null) {
					writerStream.println(msg);
					writerStream.flush();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	// 캡슐화 - 소켓 자원 종료
	private void cleanup() {
		try {
			if (socket != null) {
				socket.close();
			}
			if (serverSocket != null) {
				serverSocket.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
