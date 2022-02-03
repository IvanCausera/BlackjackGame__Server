package threads;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import tools.Tools;

public class Croupier implements Runnable {
	
	private Socket p1;
	private final int MINBET;
	private final int MAXBET;
	private Map<String,Integer> cardDeck;
	
	public Croupier(Socket p1) {
		this.p1 = p1;
		MINBET = 2;
		MAXBET = 500;
		cardDeck = new HashMap<String, Integer>() {{
			put("Ace of Clubs",1);
			put("Two of Clubs",2);
			put("Three of Clubs",3);
			put("Four of Clubs",4);
			put("Five of Clubs",5);
			put("Six of Clubs",6);
			put("Seven of Clubs",7);
			put("Eight of Clubs",8);
			put("Nine of Clubs",9);
			put("Ten of Clubs",10);
			put("Jack of Clubs",10);
			put("Queen of Clubs",10);
			put("King of Clubs",10);
			put("Ace of Diamonds",1);
			put("Two of Diamonds",2);
			put("Three of Diamonds",3);
			put("Four of Diamonds",4);
			put("Five of Diamonds",5);
			put("Six of Diamonds",6);
			put("Seven of Diamonds",7);
			put("Eight of Diamonds",8);
			put("Nine of Diamonds",9);
			put("Ten of Diamonds",10);
			put("Jack of Diamonds",10);
			put("Queen of Diamonds",10);
			put("King of Diamonds",10);
			put("Ace of Hearts",1);
			put("Two of Hearts",2);
			put("Three of Hearts",3);
			put("Four of Hearts",4);
			put("Five of Hearts",5);
			put("Six of Hearts",6);
			put("Seven of Hearts",7);
			put("Eight of Hearts",8);
			put("Nine of Hearts",9);
			put("Ten of Hearts",10);
			put("Jack of Hearts",10);
			put("Queen of Hearts",10);
			put("King of Hearts",10);
			put("Ace of Spades",1);
			put("Two of Spades",2);
			put("Three of Spades",3);
			put("Four of Spades",4);
			put("Five of Spades",5);
			put("Six of Spades",6);
			put("Seven of Spades",7);
			put("Eight of Spades",8);
			put("Nine of Spades",9);
			put("Ten of Spades",10);
			put("Jack of Spades",10);
			put("Queen of Spades",10);
			put("King of Spades",10);
		}};
	}
	
	@Override
	public void run() {
		try {
			BufferedWriter p1Exit = new BufferedWriter(new OutputStreamWriter(p1.getOutputStream()));
			BufferedReader p1Entry = new BufferedReader(new InputStreamReader(p1.getInputStream()));
			boolean gameOver = false;
			Boolean p1Finished = false;
			Boolean cFinished = false;	
			double jackpotPrize = 0;
			int p1Bet = 0;
			Integer p1Score = 0;
			Integer cScore = 0;
			
			sendMessage(p1Exit, "Welcome player, How much do you want to bet?");
			p1Bet = Integer.valueOf(p1Entry.readLine());
			while (!checkBet(p1Bet, p1Exit)) {
				p1Bet = Integer.valueOf(p1Entry.readLine());
			}
			jackpotPrize = p1Bet * 1.5;
			sendMessage(p1Exit, "Ok player, you bet  " + p1Bet + " $, and the Jackpot Prize are " + jackpotPrize + " $");
			
			playerRound(p1Exit, p1Entry, p1Score, p1Finished);
			playerRound(p1Exit, p1Entry, p1Score, p1Finished);
			croupierRound(p1Exit, cScore, cFinished);

			while (!gameOver) {
				sendMessage(p1Exit, "Stand or hit?");
				if (p1Entry.readLine().toUpperCase().equals("HIT")) playerRound(p1Exit, p1Entry, p1Score, p1Finished);
				else p1Finished = true;
				croupierRound(p1Exit, cScore, cFinished);
				
				if (p1Finished && cFinished) {
					boolean p1BlackJack = p1Score == 21;
					boolean cBlackJack = cScore == 21;
					
					if (p1BlackJack && cBlackJack) sendMessage(p1Exit, "It's a draw, GAME OVER");
					else {
						boolean p1Bust = p1Score > 21;
						boolean cBust = cScore > 21;
						
						if (p1Bust && cBust) sendMessage(p1Exit, "Everyone has lost, GAME OVER");
						else if (!p1Bust && cBust) sendMessage(p1Exit, "Congratulations player, you have won" + jackpotPrize + " $, GAME OVER");
						else if (p1Bust && !cBust) sendMessage(p1Exit, "Croupier has won" + jackpotPrize + " $, GAME OVER");
						else {
							if (p1Score > cScore) sendMessage(p1Exit, "Congratulations player, you have won" + jackpotPrize + " $, GAME OVER");
							else sendMessage(p1Exit, "Croupier has won" + jackpotPrize + " $, GAME OVER");
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
	
	private void sendMessage(BufferedWriter exit, String msg) throws IOException {
		exit.write(msg + System.lineSeparator());
		exit.flush();
	}
	
	private boolean checkBet(int bet, BufferedWriter exit) throws IOException {
		boolean ok = true;
		if (bet < MINBET) {
			sendMessage(exit, "Your bet is under the minimum bet(2 $), how much do you want to bet?");
			ok = false;
		}
		if (bet > MAXBET) {
			sendMessage(exit, "Your bet is over the maximum bet(500 $), how much do you want to bet?");
			ok = false;
		}
		return ok;
	}
	
	private String getCard() {
		return (String) cardDeck.keySet().toArray()[Tools.randomNumber(0, cardDeck.size()-1)];
	}
	
	private void playerRound(BufferedWriter p1Exit, BufferedReader p1Entry, Integer p1Score, Boolean p1Finished) throws IOException {
		if (!p1Finished) {
			String p1Card = getCard();
			if (p1Card.equals("Ace of Clubs") || p1Card.equals("Ace of Diamonds") || p1Card.equals("Ace of Hearts") || p1Card.equals("Ace of Spades")) {
				sendMessage(p1Exit, "Your card is " + p1Card + " Do you want a 1 or a 11?" );
				p1Score = Integer.valueOf(p1Entry.readLine());
			} else {
				sendMessage(p1Exit, "Your card is " + p1Card + " / Your score is " + cardDeck.get(p1Card));
				p1Score = cardDeck.get(p1Card);
			}
			cardDeck.remove(p1Card);
			if (p1Score >= 21) p1Finished = true;
		}
	}
	
	private void croupierRound(BufferedWriter p1Exit, Integer cScore, Boolean cFinished) throws IOException {
		if (!cFinished) {
			String cCard = getCard();
			if (cCard.equals("Ace of Clubs") || cCard.equals("Ace of Diamonds") || cCard.equals("Ace of Hearts") || cCard.equals("Ace of Spades")) {		
				if (((cScore + 11) <= 17) && ((cScore + 11) <= 21)) cScore += 11;
				else cScore++;
			} else cScore += cardDeck.get(cCard);
			sendMessage(p1Exit, "Croupier card is " + cCard + " / Croupier score is " + cScore);
			cardDeck.remove(cCard);
			if (cScore > 16 || cScore >= 21) cFinished = true;
		}
	}

}
