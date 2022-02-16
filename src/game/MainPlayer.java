package game;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.InputMismatchException;
import java.util.Scanner;

// This class will be executed by the player, it simulates the game and also manages the connection
public class MainPlayer {
	private static Scanner tec = new Scanner(System.in);

	private final int MINBET = 2;
	private final int MAXBET = 500;
	private final int TIMEOUT = 5;

	private Socket s = null;
	private DataOutputStream writer;
	private DataInputStream reader;

	// This method creates and executes a new player
	public static void main(String[] args) {
		MainPlayer player = new MainPlayer();
		String user;

		// Checking if we get the variables through the arguments
		try {
			if (args.length >= 2)
				player.ConnectServer(args[0], Integer.parseInt(args[1]));
			else
				player.ConnectServer(null, 0);

		} catch (NumberFormatException e) {
			System.out.println("The port format is incorrect.");
			player.ConnectServer(null, 0);
		}

		// Welcome the player

		System.out.println("  / _ \\______________/`/\\+-/\\'\\'\\" + "\n" + "\\_\\(_)/_/ Black jack -+-    -+-+-"
				+ "\n" + " _//o\\\\_             \\'\\/+-\\/`/`/" + "\n" + "  /   \\               \\/-+--\\/`/ ");

		System.out.println("Enter your username: ");
		user = tec.nextLine();

		String welcomeMessage = "Welcome " + user + ", how much do you want to bet?";
		boolean playAgain = false;

		// Welcome again if the player still wants to play
		do {
			System.out.println(welcomeMessage);
			// Start the round
			playAgain = player.playerStart(user);
			welcomeMessage = "----------------------------------------------\n" + "Hello again " + user
					+ ", how much do you want to bet?";
		} while (playAgain);

		// Closing the connection if the player quits playing
		player.closePlayer();
	}

	// This method runs the basic game structure from the client perspective
	private boolean playerStart(String user) {
		boolean playAgain = false;
		try {
			// Make the bet
			int bet = introduceBet();
			
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

					// Send player choice;
					if (tec.nextLine().toUpperCase().equals("HIT")) {
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

					System.out.println("Do you want to play again?(Y/N)");
					String answer = tec.nextLine();
					if (answer.toUpperCase().equals("Y"))
						playAgain = true;
					else
						playAgain = false;

					// Write play Again
					writer.writeBoolean(playAgain);
				}
				writer.flush();
			}
		} catch (SocketTimeoutException e) {
			System.out.println("No response from server.");
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return playAgain;
	}

	private void playerRound() throws IOException {
		// Read card depiction
		String cardDepiction = reader.readUTF();

		// Ace Check
		if (reader.readBoolean()) {

			// Ask for number
			int score = 0;
			do {
				System.out.println("Your card is " + cardDepiction + "\n Do you want 1 or 11?");
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
		System.out.println("Your card is " + cardDepiction + " Your score is " + reader.readInt());

		// Read player finished
		if (reader.readBoolean()) {
			System.out.println("Your score is over 21");
		}

		writer.flush();
	}

	private void croupierRound() throws IOException {
		// Read card depiction
		String cardDepiction = reader.readUTF();

		// Read croupier score
		int cScore = reader.readInt();

		System.out.println("Croupier card is " + cardDepiction + " Croupier score is " + cScore);

		// Read croupier finished
		if (reader.readBoolean()) {
			// Read croupier is bust
			if (reader.readBoolean())
				System.out.println("Croupier score is over 21");
			else
				System.out.println("Croupier stands");
		}
	}

	// This method checks if the address/port is correct, if not, the user reenters
	// both
	private void ConnectServer(String address, int port) {

		try {
			boolean ok = true;
			do {
				try {
					// Trying out the connection
					if (address != null) {
						s = new Socket(InetAddress.getByName(address), port);
						ok = true;
					} else {
						// Request for an address if there're no arguments
						System.out.println("Write the server address please:");
						address = tec.nextLine();

						System.out.println("Write the port please:");
						port = tec.nextInt();
						tec.nextLine();

						s = new Socket(InetAddress.getByName(address), port);
						ok = true;
					}

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

			writer = new DataOutputStream(s.getOutputStream());
			reader = new DataInputStream(s.getInputStream());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// The method asks for the bet until a valid one is introduced by the user
	private int introduceBet() {
		int bet = 0;
		boolean ok = true;
		do {
			try {
				// Obtain your bet
				bet = tec.nextInt();
				tec.nextLine();

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
		
		return bet;
		
	}

	private void closePlayer() {
		try {
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}