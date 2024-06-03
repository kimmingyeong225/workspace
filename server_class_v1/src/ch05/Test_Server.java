package ch05;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Test_Server {

	public static void main(String[] args) {
		
		// 서버측 소켓 만들기
		try(ServerSocket serverSocket = new ServerSocket(5000)) {
			// 클라이언트 대기
			Socket socket = serverSocket.accept();
			System.out.println("--- client conneted ---");
			
			BufferedReader bufferedReader = 
					new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter writerStream = 
					new PrintWriter(socket.getOutputStream(), true);
			BufferedReader keyboardReader =
					new BufferedReader(new InputStreamReader(System.in));
			
			startReadThread(bufferedReader);
			startWiterThread(writerStream, keyboardReader);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
	}// end of main
	
	// 데이터 읽는 스레드
	private static void startReadThread(BufferedReader bufferedReader) {
		
		Thread readThread = new Thread(() -> {
			try {
				String clientmsg;
				while((clientmsg = bufferedReader.readLine()) != null) {
					System.out.println("클라이언트에서 온 msg : " + clientmsg);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		readThread.start();
		waitForThreadToEnd(readThread);
		
	}
	
	private static void startWiterThread(PrintWriter printWriter, BufferedReader keyboardReader) {
		Thread witerThread = new Thread(() -> {
			try {
				String servermsg;
				while((servermsg = keyboardReader.readLine()) != null) {
					printWriter.println(servermsg);
					printWriter.flush();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		});
		witerThread.start();
	}
	private static void waitForThreadToEnd(Thread thread) {
		try {
			thread.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}// end of class
