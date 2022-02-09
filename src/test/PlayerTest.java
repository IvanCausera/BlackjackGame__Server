package test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import tools.Tools;

public class PlayerTest implements Runnable {
	private final int MINBET = 2;
	private final int MAXBET = 500;
	private final int TIMEOUT = 5;
	private final int PORT = 2001;

	private Socket s = null;
	private DataOutputStream writer;
	private DataInputStream reader;
	// This method is for the automatic execution of several player threads
	@Override
	public void run() {

		System.out.println("Thread running");
		try {
			// Initialize connection
			s = new Socket(InetAddress.getByName("localhost"), PORT);
			writer = new DataOutputStream(s.getOutputStream());
			reader = new DataInputStream(s.getInputStream());
			s.setSoTimeout(TIMEOUT * 1000);

			int bet = 0;

			// Obtain bet
			bet = Tools.randomNumber(MINBET, MAXBET);

			// Send your bet
			writer.writeInt(bet);

			// Read jackpot
			System.out.println(
					"Ok thread, you bet " + bet + " $, and the Jackpot Prize is " + reader.readDouble() + " $");

			// First player round
			playerRound();

			// First croupier round
			croupierRound();

			boolean gameOver = false;
			while (!gameOver) {

				// Read player finished
				if (!reader.readBoolean()) {
					// Randomly stands or hits
					System.out.println("Hit or stand");
					writer.writeBoolean(Tools.randomBoolean());
				}

				// Read Croupier finished
				if (!reader.readBoolean()) {
					croupierRound();
				}

				// Read game over
				if (reader.readBoolean()) {
					// Read game over message
					System.out.println(reader.readUTF());
					gameOver = true;
				}
				writer.flush();
			}
			s.close();
		} catch ( SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private void croupierRound() {
		// TODO croupier automated round
		
	}
	private void playerRound() {
		// TODO player automated round
		
	}
}
