package game;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainPlayer implements Runnable {
	private static Scanner tec = new Scanner(System.in);

	private final int MINBET = 2;
	private final int MAXBET = 500;

	private String user;
	private DataOutputStream writer;
	private DataInputStream reader;

	public static void main(String[] args) {
		MainPlayer player = new MainPlayer();

		player.playerStart();
	}

	public void playerStart() {
		try {
			// Initialize connection
			Socket s = new Socket(InetAddress.getByName("localhost"), 2001);
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
					tec.nextLine();
					// Obtain your bet
					bet = tec.nextInt();

					if (bet < MINBET || bet > MAXBET) {
						System.out.println("Your bet has to be over 2 $ and under 500 $, how much do you want to bet?");
						ok = false;
					} else
						ok = true;
				} catch (InputMismatchException e) {
					System.out.println("Incorrect Format, how much do you want to bet?");
					ok = false;
				}

			} while (!ok);
			// Send your bet
			writer.writeInt(bet);

			// Read jackpot
			System.out.println(
					"Ok " + user + ",you bet " + bet + " $, and the Jackpot Prize is " + reader.readDouble() + " $");

			// First player round
			playerRound();

			// First croupier round
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
			e.printStackTrace();
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
					tec.nextLine();
					score = tec.nextInt();
					tec.nextLine();
				} catch (InputMismatchException e) {
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

	// This method is for the automatic execution of several player threads

	@Override
	public void run() {

		System.out.println("Thread running");
		try {
			// Initialize connection
			Socket s = new Socket(InetAddress.getByName("localhost"), 2001);
			writer = new DataOutputStream(s.getOutputStream());
			reader = new DataInputStream(s.getInputStream());

			int bet = 0;

			// Obtain bet
			bet = tools.Tools.randomNumber(MINBET, MAXBET);

			// Send your bet
			writer.writeInt(bet);

			// First player round
			
			//TODO se atasca en el método de playerRound, siendo concreato en el reader del Strin cardName
			// no sé que puede ser exactamente, si alguien ve el error que me avisa y lo arreglo asap
			playerRound();

			// First croupier round
			croupierRound();

			boolean gameOver = false;
			while (!gameOver) {

				// Read player finished
				if (!reader.readBoolean()) {
					//Randomly stands or hits
					System.out.println("Hit or stand");
						writer.writeBoolean(tools.Tools.randomBoolean());
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
		} catch (

		SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
