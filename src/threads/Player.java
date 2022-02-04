package threads;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Player{
	private static Scanner tec = new Scanner(System.in);
	
	private final int MINBET = 2;
	private final int MAXBET = 500;
	
	private final float JACKPOT = 1.5f;
	
	private String usuario;
	private DataOutputStream exit;
	private DataInputStream entry;
	
	private boolean playerFinish = false;
	private boolean coupierFinish = false;
	
	public void playerStart() {
		try {
			Socket s = new Socket(InetAddress.getByName("localhost"),2001);	
			exit = new DataOutputStream (s.getOutputStream ());
			entry = new DataInputStream (s.getInputStream ());
			
			System.out.println("Introduce Nombre Usuario: ");
			usuario = tec.next();
			
			//Welcome message
			System.out.println("Welcome player, How much do you want to bet?");
			
			int bet = 0;
			boolean ok = true;
			do {
				try {
					//Obtain your bet
					bet = tec.nextInt();
					
					if (bet < MINBET) {
						System.out.println("Your bet is under the minimum bet(2 $), how much do you want to bet?");
						ok = false;
					}
					if (bet > MAXBET) {
						System.out.println("Your bet is over the maximum bet(500 $), how much do you want to bet?");
						ok = false;
					}
				} catch (InputMismatchException e) {
					System.out.println("Incorrect Format"
							+ "\nhow much do you want to bet?");
				}
			} while (ok);
			
			//Send your bet
			exit.writeInt(bet);
			
			//Send jackpot muliply
			exit.writeFloat(JACKPOT);
			System.out.println("Ok player, you bet  " + bet + " $, and the Jackpot Prize are " + (bet * JACKPOT) + " $");
			
			//First card message
			playerRound();

			//Coupier first card message
			System.out.println(entry.readUTF());
			
			boolean gameOver = false;
			while (!gameOver) {
				String msgOver = entry.readUTF();
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
							playerFinish = playerRound();
						} else {
							playerFinish = true;
						}
						System.out.println("FIN P " + playerFinish);
					}
					
					if (!coupierFinish) {
						//Coupier message
						String msg = entry.readUTF();
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
	
	private boolean playerRound() throws IOException {
		boolean finish = false;
		String msgReturn = entry.readUTF();
		if (msgReturn.contains("want")) {
			System.out.println(msgReturn);
			int value = tec.nextInt();
			while (value != 1 && value != 11) {
				System.out.println("Do you want a 1 or a 11?");
				value = tec.nextInt();
			}
			msgReturn = entry.readUTF();
		}
		
		if (msgReturn.contains("stand")) {
			finish = true;
		}
		
		System.out.println(msgReturn);
		
		return finish;
	}
	
	private void sendMessage(String msg) throws IOException {
		exit.writeUTF(msg + System.lineSeparator());
		exit.flush();
	}

}
