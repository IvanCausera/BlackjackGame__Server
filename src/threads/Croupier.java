package threads;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import tools.Card;
import tools.CardDeck;

/**
 * This class manages the game with the players.
 * 
 * @author Ivan Causera, Frank Vanegas, Jukka Rivas
 */
public class Croupier implements Runnable {
	
	private Socket p1;
	private CardDeck cardDeck;
	
	private int p1Score;
	private int cScore;
	private boolean p1Finished;
	private boolean cFinished;
	boolean p1Bust;
	boolean cBust;
	
	private DataOutputStream writer;
	private DataInputStream reader;
	
	public Croupier(Socket p1) {
		this.p1 = p1;
		
		initialize();
	}
	
	/**
	 * Initialize all the variables
	 */
	private void initialize() {
		p1Score = 0;
		cScore = 0;
		
		p1Finished = false;
		cFinished = false;	
		
		p1Bust = false;
		cBust = false;
		
		cardDeck = new CardDeck();
	}
	
	/**
	 * This method runs the basic game structure from the server perspective
	 */
	@Override
	public void run() {
		try {
			writer = new DataOutputStream(p1.getOutputStream());
			reader = new DataInputStream(p1.getInputStream());
			
			boolean playAgain = false;
			do {
				boolean gameOver = false;
				double jackpotPrize = 0;
				int p1Bet = 0;
				
				//Read player bet
				p1Bet = reader.readInt();

				//Send jackpot 
				jackpotPrize = p1Bet * 1.5;
				writer.writeDouble(jackpotPrize);
				
				//First player round
				playerRound();
				playerRound();
				
				//First croupier round
				croupierRound();
				croupierRound();

				while (!gameOver) {
					
					//Send player finished
					writer.writeBoolean(p1Finished);
					if (!p1Finished) {
						//Read player choice
						if(reader.readBoolean()) 
							playerRound();
						else p1Finished = true;
					}
					
					//Send croupier finished
					writer.writeBoolean(cFinished);
					if (!cFinished) 
						croupierRound();
					
					//Send game over
					boolean end = p1Finished && cFinished;
					writer.writeBoolean(end);
					if (end) {
						//Write game over message
						
						// Both get 21
						if ((p1Score == 21) && (cScore == 21)) writer.writeUTF("It's a draw, GAME OVER");
						else {
							// Both go bust
							if (p1Bust && cBust) writer.writeUTF("Everyone has lost, GAME OVER");
							// The player goes bust
							else if (!p1Bust && cBust) writer.writeUTF("Congratulations player, you have won " + jackpotPrize + " $, GAME OVER");
							// The croupier goes bust
							else if (p1Bust && !cBust) writer.writeUTF("Croupier has won " + jackpotPrize + " $, GAME OVER");
							// Both get the same score (under 21)
							else if (p1Score == cScore) writer.writeUTF("It's a draw, GAME OVER");
							else {
								// Player wins by higher score
								if (p1Score > cScore) writer.writeUTF("Congratulations player, you have won " + jackpotPrize + " $, GAME OVER");
								// Croupier has the higher score
								else writer.writeUTF("Croupier has won " + jackpotPrize + " $, GAME OVER");
							}
						}
						
						gameOver = true;
						
						//Read play Again
						if (reader.readBoolean()) {
							playAgain = true;
							initialize();
						} else 
							playAgain = false;
					}
					
					writer.flush();
				}
			} while (playAgain);
			p1.close();
		} catch (SocketException e) {
			e.printStackTrace(); 
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Starts one player round
	 * @throws IOException
	 */
	private void playerRound() throws IOException {
		Card p1Card = cardDeck.removeCard();
		//Send card depiction
		writer.writeUTF(p1Card.getDepiction());
		
		//Ace check
		if (p1Card.getScore() == 0) {
			//Send is Ace true
			writer.writeBoolean(true);
			
			//Read number
			p1Score += reader.readInt();
		} else {
			//Send is Ace false
			writer.writeBoolean(false);
			p1Score += p1Card.getScore();
		}
		
		//Send player actual score
		writer.writeInt(p1Score);
		
		if (p1Score > 21) {
			p1Finished = true;
			cFinished = true;
			p1Bust = true;
		}
		//Send player finished
		writer.writeBoolean(p1Finished);
		
		writer.flush();
	}
	
	/**
	 * Starts one croupier round
	 * @throws IOException
	 */
	private void croupierRound() throws IOException {
		// Get the card
		Card cCard = cardDeck.removeCard();
		
		// Choose the value of the Ace depending on the score
		if (cCard.getScore() == 0) {
			if (((cScore + 11) <= 17) && ((cScore + 11) <= 21)) cScore += 11;
			else cScore++;
		} else cScore += cCard.getScore();

		//Send card depiction
		writer.writeUTF(cCard.getDepiction());
		//Send croupier actual score
		writer.writeInt(cScore);

		// Checking if croupier goes bust
		if (cScore > 21) {
			p1Finished = true;
			cFinished = true;
			cBust = true;
		} else if (cScore > 16) {
			cFinished = true;
		}
		//Send croupier finished
		writer.writeBoolean(cFinished);
		//Send croupier is bust
		if (cFinished) {
			writer.writeBoolean(cBust);	
		}
		writer.flush();
	}

}
