package ch05;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

// 1단계 - 함수로 분리 해서 리팩토링 진행
public class MultiThreadClient2 {

	// 메인 함수
	public static void main(String[] args) {
		System.out.println("### 클라이언트 실행 ###");

		try (Socket socket = new Socket("localhost", 5000)) { // 클라이언트는 socket만 있으면 됨
			System.out.println("connected to the server !!");

			// 서버와 통신을 위한 스트림 초기화
			BufferedReader bufferedReader = 
					new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter printWriter = 
					new PrintWriter(socket.getOutputStream());
			BufferedReader keyboardReader =
					new BufferedReader(new InputStreamReader(System.in));
			
			// 메소드 호출 -> main가 함  // 워커 1,2가 생성됨 
			startReadThread(bufferedReader);
			startWriteThread(printWriter, keyboardReader); 
			// 메인 스레드 기다려는 어디에 있지?? -> 가독성이 떨어짐
			// startWriteThread() <-- 내부에 있음
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}// end of main

	// 1. 클라이언트로부터 데이터를 읽는 스레드 시작 메서드 생성
	private static void startReadThread(BufferedReader reader) {

		Thread readThread = new Thread(() -> {
			try {
				String msg;
				while ((msg = reader.readLine()) != null) {
					System.out.println("서버에서 온 msg : " + msg);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		readThread.start();
	}

	// 2. 키보드에서 입력을 받아 클라이언트 측으로 데이터를 전송하는 스레드
	private static void startWriteThread(PrintWriter writer, BufferedReader keyboardReader) {

		Thread writerThread = new Thread(() -> {
			try {
				String msg;
				while ((msg = keyboardReader.readLine()) != null) {
					// 전송
					writer.println(msg);
					writer.flush();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		writerThread.start();
		
		
	}

}// end of class
