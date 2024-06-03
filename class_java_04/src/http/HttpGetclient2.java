package http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpGetclient2 {

	public static void main(String[] args) {
		
		String urlString = "https://m.naver.com/";		
		
		try {
			URL url = new URL(urlString);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			
			int responseCode = conn.getResponseCode();
			System.out.println("HTTP CODE : " + responseCode);
			
			BufferedReader brIn = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			String inputLine;
			StringBuffer responBuffer = new StringBuffer();
			
			while((inputLine = brIn.readLine()) != null) {
				responBuffer.append(inputLine);
			}
			
			brIn.close();
			
			String[] strHtmls = responBuffer.toString().split("\\s");
			System.out.println("index count : " + strHtmls.length);
			for (String word : strHtmls) {
				System.out.println(word);
			}
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}// end of main

}
