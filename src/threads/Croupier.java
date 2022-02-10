package threads;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

import tools.Card;
import tools.CardDeck;

public class Croupier implements Runnable {
	
	private Socket p1;
	private CardDeck cardDeck;
	
	private int p1Score;
	private int cScore;
	private boolean p1Finished;
	private boolean cFinished;
	
	private DataOutputStream p1Writer;
	private DataInputStream p1Reader;
	
	public Croupier(Socket p1) {
		this.p1 = p1;
		
		p1Score = 0;
		cScore = 0;
		
		p1Finished = false;
		cFinished = false;	
		
		cardDeck = new CardDeck();
	}
	
	@Override
	public void run() {
		try {
			p1Writer = new DataOutputStream(p1.getOutputStream());
			p1Reader = new DataInputStream(p1.getInputStream());
			
			boolean gameOver = false;
			double jackpotPrize = 0;
			int p1Bet = 0;
			//Read player bet
			p1Bet = p1Reader.readInt();

			//Send jackpot 
			jackpotPrize = p1Bet * 1.5;
			p1Writer.writeDouble(jackpotPrize);
			
			//First player round
			playerRound();
			playerRound();
			
			//First croupier round
			croupierRound();
			croupierRound();

			while (!gameOver) {
				
				//Send player finished
				p1Writer.writeBoolean(p1Finished);
				if (!p1Finished) {
					//Read player choice
					if(p1Reader.readBoolean()) 
						playerRound();
					else p1Finished = true;
				}
				
				//Send croupier finished
				p1Writer.writeBoolean(cFinished);
				if (!cFinished) 
					croupierRound();
				
				//Send game over
				boolean end = p1Finished && cFinished;
				p1Writer.writeBoolean(end);
				if (end) {
					boolean p1BlackJack = p1Score == 21;
					boolean cBlackJack = cScore == 21;
					
					if (p1BlackJack && cBlackJack) p1Writer.writeUTF("It's a draw, GAME OVER");
					else {
						boolean p1Bust = p1Score > 21;
						boolean cBust = cScore > 21;
						
						if (p1Bust && cBust) p1Writer.writeUTF("Everyone has lost, GAME OVER");
						else if (!p1Bust && cBust) p1Writer.writeUTF("Congratulations player, you have won " + jackpotPrize + " $, GAME OVER");
						else if (p1Bust && !cBust) p1Writer.writeUTF("Croupier has won " + jackpotPrize + " $, GAME OVER");
						else {
							if (p1Score > cScore) p1Writer.writeUTF("Congratulations player, you have won " + jackpotPrize + " $, GAME OVER");
							else p1Writer.writeUTF("Croupier has won " + jackpotPrize + " $, GAME OVER");
						}
					}
					gameOver = true;
				}
				p1Writer.flush();
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
	
	private void playerRound() throws IOException {
		Card p1Card = cardDeck.removeCard();
		//Send Card Name
		p1Writer.writeUTF(p1Card.getName());
		
		//Ace check
		if (p1Card.getScore() == 0) {
			//Send is Ace true
			p1Writer.writeBoolean(true);
			
			//Read number
			p1Score += p1Reader.readInt();
		} else {
			//Send is Ace false
			p1Writer.writeBoolean(false);
			p1Score += p1Card.getScore();
		}
		
		//Send player actual score
		p1Writer.writeInt(p1Score);
		
		if (p1Score > 21) {
			p1Finished = true;
		}
		//Send player finished
		p1Writer.writeBoolean(p1Finished);
		
		p1Writer.flush();
	}
	
	
	private void croupierRound() throws IOException {
		Card cCard = cardDeck.removeCard();
		if (cCard.getScore() == 0) {
			if (((cScore + 11) <= 17) && ((cScore + 11) <= 21)) cScore += 11;
			else cScore++;
		} else cScore += cCard.getScore();

		//Send card name
		p1Writer.writeUTF(cCard.getName());
		//Send croupier actual score
		p1Writer.writeInt(cScore);

		if (cScore > 16 || cScore >= 21) {
			cFinished = true;
		} 
		//Send croupier finished
		p1Writer.writeBoolean(cFinished);
		p1Writer.flush();
	}

}
