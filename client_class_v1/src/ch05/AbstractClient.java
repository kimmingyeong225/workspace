package ch05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

// 2단계 - 상속 활용 리팩토링 단계
public abstract class AbstractClient {

	
	private Socket socket;
	private BufferedReader readerStream;
	private PrintWriter writerStream;
	private BufferedReader keyboardReader;

	// set
	protected void setSocket(Socket socket) {
		this.socket = socket;
	}


	// get
	protected Socket getSocket() {
		return this.socket;
	}

	public final void run() {
		try {
			connection();
			setupStream();
			startService();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			cleanup();
		}
	}
	

	// 연결 대기
	protected abstract void connection() throws IOException;
	
	// 스트림 초기화
	private void setupStream() throws IOException {
		readerStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		writerStream = new PrintWriter(socket.getOutputStream());
		keyboardReader = new BufferedReader(new InputStreamReader(System.in));
	}

	// 서비스 시작
	private void startService() {
		Thread readThread = createReadThread();
		Thread writeThread = createWriteThread();

		readThread.start();
		writeThread.start();

		try {
			readThread.join();
			writeThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	private Thread createReadThread() {
		return new Thread(() -> {
			try {
				String msg;
				while((msg = readerStream.readLine()) != null) {
					System.out.println("서버 측 msg : " + msg);
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
				while((msg = keyboardReader.readLine()) != null) {
					writerStream.println(msg);
					writerStream.flush();
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		});
	}
	
	private void cleanup() {
		try {
			if(socket != null) {
				socket.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}// end of class
