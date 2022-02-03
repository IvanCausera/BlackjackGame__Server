package threads;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import tools.Tools;

public class Player implements Runnable {

	@Override
	public void run() {
		try {
			Socket s = new Socket(InetAddress.getByName("localhost"),2001);	
			BufferedWriter exit = new BufferedWriter (new OutputStreamWriter (s.getOutputStream ()));
			BufferedReader entry = new BufferedReader (new InputStreamReader (s.getInputStream ()));
			
			System.out.println(entry.readLine());
			int c = 0;
			String msgReturn;
			do {
				sendMessage(exit, String.valueOf(Tools.randomNumber(1, 750)));
				msgReturn = entry.readLine();
				System.out.println(msgReturn);
			} while (!msgReturn.contains("Ok"));
			
			
			for (int i = 0; i < 2; i++) {
				msgReturn = entry.readLine();
				while (msgReturn.contains("?")) {
					sendMessage(exit, String.valueOf(Tools.randomNumber(1, 11)));
				}
			}
			System.out.println(entry.readLine());
			
			boolean gameOver = false;
			while (!gameOver) {
				msgReturn = entry.readLine();
				System.out.println(msgReturn);
				while (msgReturn.contains("?")) {
					sendMessage(exit, String.valueOf(Tools.randomNumber(1, 11)));
				}
				System.out.println(entry.readLine());
			}
			
		} catch (SocketException e) {
			e.printStackTrace(); 
		}catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void sendMessage(BufferedWriter exit, String msg) throws IOException {
		exit.write(msg + System.lineSeparator());
		exit.flush();
	}

}
