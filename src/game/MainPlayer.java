package game;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainPlayer {
	private static Scanner tec = new Scanner(System.in);

	private final int MINBET = 2;
	private final int MAXBET = 500;
	private final int TIMEOUT = 5;

	private String user;
	private Socket s = null;
	private DataOutputStream writer;
	private DataInputStream reader;

	public static void main(String[] args) {
		MainPlayer player = new MainPlayer();

		
		player.playerStart(args[0], Integer.parseInt(args[1]));
	}

	public void playerStart(String address, int port) {
		try {
			ConnectServer(address, port);

			writer = new DataOutputStream(s.getOutputStream());
			reader = new DataInputStream(s.getInputStream());

			System.out.println("Enter your username: ");
			user = tec.next();

			// Welcome message
			System.out.println("Welcome " + user + ", how much do you want to bet?");

			int bet = 0;
			boolean ok = true;
			do {
				try {
					// Obtain your bet
					bet = tec.nextInt();

					if (bet < MINBET || bet > MAXBET) {
						System.out.println("Your bet has to be over 2 $ and under 500 $, how much do you want to bet?");
						ok = false;
					} else
						ok = true;
				} catch (InputMismatchException e) {
					tec.nextLine();
					System.out.println("Incorrect Format, how much do you want to bet?");
					ok = false;
				}

			} while (!ok);
			// Send your bet
			writer.writeInt(bet);

			// Read jackpot
			System.out.println(
					"Ok " + user + ", you bet " + bet + " $, and the Jackpot Prize is " + reader.readDouble() + " $");

			// First player round
			playerRound();
			playerRound();

			// First croupier round
			croupierRound();
			croupierRound();

			boolean gameOver = false;
			while (!gameOver) {

				// Read player finished
				if (!reader.readBoolean()) {
					System.out.println("Stand or hit?");

					// Send player choice
					if (tec.next().toUpperCase().equals("HIT")) {
						writer.writeBoolean(true);
						playerRound();

					} else
						writer.writeBoolean(false);
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
		} catch (SocketException e) {
			System.out.println("No response from server.");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void playerRound() throws IOException {
		// Read Card Name
		String cardName = reader.readUTF();

		// Ace Check
		if (reader.readBoolean()) {

			// Ask for number
			int score = 0;
			do {
				System.out.println("Your card is " + cardName + " Do you want 1 or 11?");
				try {
					score = tec.nextInt();
					tec.nextLine();
				} catch (InputMismatchException e) {
					tec.nextLine();
					System.out.println("You have to write a number");
				}
			} while (score != 1 && score != 11);

			// Send number
			writer.writeInt(score);
		}

		// Read actual Score
		System.out.println("Your card is " + cardName + " / Your score is " + reader.readInt());

		// Read player finished
		if (reader.readBoolean()) {
			System.out.println("Your score is over 21");
		}

		writer.flush();
	}

	private void croupierRound() throws IOException {
		// Read card name
		String cardName = reader.readUTF();

		// Read croupier score
		int cScore = reader.readInt();
		System.out.println("Croupier card is " + cardName + " / Croupier score is " + cScore);

		// Read croupier finished
		if (reader.readBoolean()) {
			System.out.println("Croupier stands");
		}
	}

	// This method checks if the address/port is correct, if not, the user reenters both
	private void ConnectServer(String address, int port) throws UnknownHostException, IOException {

		boolean ok = true;
		do {
			try {
				// Trying out the connection
				s = new Socket(InetAddress.getByName(address), port);
				ok = true;
				
			} catch (ConnectException | UnknownHostException e) {
				// Request for new address if it was invalid
				System.out.println("You wrote: " + address + ":" + port + ". No response from the server.");
				
				System.out.println("Reenter the server address please:");
				address = tec.nextLine();
				
				System.out.println("Reenter the port please:");

				port = tec.nextInt();
				tec.nextLine();
				ok = false;
			}
		} while (!ok);

		s.setSoTimeout(TIMEOUT * 1000);
	}
}
