package ch05;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

// 1단계 - 함수로 분리 해서 리팩토링 진행
public class MultiThreadClient {

	public static void main(String[] args) {

		System.out.println("==== 서버 실행 ====");

		try {
			//ServerSocket serverSocket = new ServerSocket(5000);
			Socket socket = new Socket("localhost", 5000);
			System.out.println("---- connected ----");
			
			// -> socket만 적어주면 됨 ! 클라이언트는 

			BufferedReader socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter socketWriter = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader keyboardReader = new BufferedReader(new InputStreamReader(System.in));

			startReadThread(socketReader);
			startWriteThread(socketWriter, keyboardReader);

			System.out.println("main 스레드 작업 완료...");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}// end of main

	// 데이터 읽는 스레드 분리
	private static void startReadThread(BufferedReader bufferedReader) { // -> 읽는 거 
		Thread readerThread = new Thread(() -> {
			try {
				String serverMessage;
				while ((serverMessage = bufferedReader.readLine()) != null) {
					System.out.println("서버에서 온 MSG : " + serverMessage);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		readerThread.start(); // 스레드 실행
		waitForThreadToEnd(readerThread);

	}

	private static void startWriteThread(PrintWriter printWriter, BufferedReader keyboardReader) {
		Thread writeThread = new Thread(() -> {
			try {
				String clientMessage;
				while ((clientMessage = keyboardReader.readLine()) != null) {
					printWriter.println(clientMessage);
					printWriter.flush(); // 물을 내리다

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		writeThread.start();
		waitForThreadToEnd(writeThread); // waitForThreadToEnd -> 제거 대상/리팩토링 -> (writeThread)

	}

	private static void waitForThreadToEnd(Thread thread) {
		try {
			thread.join();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}// end of class
