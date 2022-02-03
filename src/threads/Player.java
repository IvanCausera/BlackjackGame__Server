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
import java.util.Scanner;

import tools.Tools;

public class Player{
	private static Scanner tec = new Scanner(System.in);
	
	
	private String usuario;
	private BufferedWriter exit;
	BufferedReader entry;
	
	public void playerStart() {
		try {
			Socket s = new Socket(InetAddress.getByName("localhost"),2001);	
			exit = new BufferedWriter (new OutputStreamWriter (s.getOutputStream ()));
			entry = new BufferedReader (new InputStreamReader (s.getInputStream ()));
			
			System.out.println("Introduce Nombre Usuario: ");
			usuario = tec.next();
			
			//Welcome message
			System.out.println(entry.readLine());
			String msgReturn;
			do {
				//Your bet
				sendMessage(tec.next());
				msgReturn = entry.readLine();
				//Error or info message
				System.out.println(msgReturn);
			} while (!msgReturn.contains("Ok"));
			
			//First card message
			readPlayerRound();

			
			//Coupier first card message
			System.out.println(entry.readLine());
			
			boolean playerFinish = false;
			boolean coupierFinish = false;
			boolean gameOver = false;
			while (!gameOver) {
				String msgOver = entry.readLine();
				if (msgOver.contains("OVER")) {
					// Stand or hit message
					System.out.println(msgOver);
					gameOver = true;
				} else {
					if (!playerFinish) {
						// Stand or hit message
						System.out.println(msgOver);
						String msg = tec.next();
						sendMessage(msg);
						if (msg.toUpperCase().equals("HIT")) {
							//Your card message
							playerFinish = readPlayerRound();
						} else {
							playerFinish = true;
						}
						System.out.println("FIN P " + playerFinish);
					}
					
					if (!coupierFinish) {
						//Coupier message
						String msg = entry.readLine();
						if (msg.contains("stands")) {
							coupierFinish = true;
						}
						System.out.println(msg);
						System.out.println("FIN C " + coupierFinish);
					}
					
				}
				
			}
			s.close();
		} catch (SocketException e) {
			e.printStackTrace(); 
		}catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private boolean readPlayerRound() throws IOException {
		boolean finish = false;
		String msgReturn = entry.readLine();
		if (msgReturn.contains("want")) {
			System.out.println(msgReturn);
			int value = tec.nextInt();
			while (value != 1 && value != 11) {
				System.out.println("Do you want a 1 or a 11?");
				value = tec.nextInt();
			}
			msgReturn = entry.readLine();
		}
		
		if (msgReturn.contains("stand")) {
			finish = true;
		}
		
		System.out.println(msgReturn);
		
		return finish;
	}
	
	private void sendMessage(String msg) throws IOException {
		exit.write(msg + System.lineSeparator());
		exit.flush();
	}

}
