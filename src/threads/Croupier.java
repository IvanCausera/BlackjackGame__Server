package threads;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import tools.Card;
import tools.CardDeck;

public class Croupier implements Runnable {
	
	private Socket p1;
	private final int MINBET;
	private final int MAXBET;
	private CardDeck cardDeck;
	
	private int p1Score;
	private int cScore;
	private boolean p1Finished;
	private boolean cFinished;
	
	private BufferedWriter p1Writer;
	private BufferedReader p1Read;
	
	public Croupier(Socket p1) {
		this.p1 = p1;
		MINBET = 2;
		MAXBET = 500;
		
		p1Score = 0;
		cScore = 0;
		
		p1Finished = false;
		cFinished = false;	
		
		cardDeck = new CardDeck();
	}
	
	@Override
	public void run() {
		try {
			p1Writer = new BufferedWriter(new OutputStreamWriter(p1.getOutputStream()));
			p1Read = new BufferedReader(new InputStreamReader(p1.getInputStream()));
			boolean gameOver = false;
			double jackpotPrize = 0;
			int p1Bet = 0;
			
			sendMessage("Welcome player, How much do you want to bet?");
			try {
				p1Bet = Integer.valueOf(p1Read.readLine());
			} catch (NumberFormatException e) {
				p1Bet = 2;
			}
			while (!checkBet(p1Bet)) {
				p1Bet = Integer.valueOf(p1Read.readLine());
			}
			jackpotPrize = p1Bet * 1.5;
			sendMessage("Ok player, you bet  " + p1Bet + " $, and the Jackpot Prize are " + jackpotPrize + " $");
			
			playerRound();
			croupierRound();

			while (!gameOver) {
				if (!p1Finished) {
					sendMessage("Stand or hit?");
					if (p1Read.readLine().toUpperCase().equals("HIT")) playerRound();
					else p1Finished = true;	
				}
				
				if (!cFinished) {
					croupierRound();
				}
				
				if (p1Finished && cFinished) {
					boolean p1BlackJack = p1Score == 21;
					boolean cBlackJack = cScore == 21;
					
					if (p1BlackJack && cBlackJack) sendMessage("It's a draw, GAME OVER");
					else {
						boolean p1Bust = p1Score > 21;
						boolean cBust = cScore > 21;
						
						if (p1Bust && cBust) sendMessage("Everyone has lost, GAME OVER");
						else if (!p1Bust && cBust) sendMessage("Congratulations player, you have won " + jackpotPrize + " $, GAME OVER");
						else if (p1Bust && !cBust) sendMessage("Croupier has won " + jackpotPrize + " $, GAME OVER");
						else {
							if (p1Score > cScore) sendMessage("Congratulations player, you have won " + jackpotPrize + " $, GAME OVER");
							else sendMessage("Croupier has won " + jackpotPrize + " $, GAME OVER");
						}
					}
					gameOver = true;
				}
			}
			p1.close();
		} catch (SocketException e) {
			e.printStackTrace(); 
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void sendMessage(String msg) throws IOException {
		p1Writer.write(msg + System.lineSeparator());
		p1Writer.flush();
	}
	
	private boolean checkBet(int bet) throws IOException {
		boolean ok = true;
		if (bet < MINBET) {
			sendMessage("Your bet is under the minimum bet(2 $), how much do you want to bet?");
			ok = false;
		}
		if (bet > MAXBET) {
			sendMessage("Your bet is over the maximum bet(500 $), how much do you want to bet?");
			ok = false;
		}
		return ok;
	}
	
	private void playerRound() throws IOException {
		if (!p1Finished) {
			Card p1Card = cardDeck.removeCard();
			if (p1Card.getScore() == 0) {
				sendMessage("Your card is " + p1Card.getName() + " Do you want a 1 or a 11?" );
				if (p1Read.readLine().equals("11")) {
					p1Score += 11;
				} else 
					p1Score += 1;
			} else {
				p1Score += p1Card.getScore();
			}
			if (p1Score >= 21) {
				p1Finished = true;
				sendMessage("Your card is " + p1Card.getName()+ " / Your score is " + p1Score 
						+ "  -  You stand");
			} else {
				sendMessage("Your card is " + p1Card.getName()+ " / Your score is " + p1Score);
			}
		
		}
		
	}
	
	
	private void croupierRound() throws IOException {
		if (!cFinished) {
			Card cCard = cardDeck.removeCard();
			if (cCard.getScore() == 0) {		
				if (((cScore + 11) <= 17) && ((cScore + 11) <= 21)) cScore += 11;
				else cScore++;
			} else cScore += cCard.getScore();
			

			if (cScore > 16 || cScore >= 21) {
				cFinished = true;
				sendMessage("Croupier card is " + cCard.getName() + " / Croupier score is " + cScore
						+ "  -  Coupier stands");
			} else {
				sendMessage("Croupier card is " + cCard.getName() + " / Croupier score is " + cScore);
			}
		} else {
			sendMessage("Coupier stands");
		}
	}

}
