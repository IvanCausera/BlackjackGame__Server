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
	private Socket p2;
	private final int MINBET;
	private final int MAXBET;
	private Map<String,Integer> cardDeck;
	
	public Croupier(Socket p1, Socket p2) {
		this.p1 = p1;
		this.p2 = p2;
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
			BufferedWriter p2Exit = new BufferedWriter(new OutputStreamWriter(p2.getOutputStream()));
			BufferedReader p2Entry = new BufferedReader(new InputStreamReader(p2.getInputStream()));
			boolean gameOver = false;
			Boolean p1Finished = false;
			Boolean p2Finished = false;
			Boolean cFinished = false;	
			int jackpotPrize = 0;
			int p1Bet = 0;
			int p2Bet = 0;
			Integer p1Score = 0;
			Integer p2Score = 0;
			Integer cScore = 0;
			
			sendMessage(p1Exit, "Welcome player 1, How much do you want to bet?");
			sendMessage(p2Exit, "Welcome player 2, How much do you want to bet?");
			
			p1Bet = Integer.valueOf(p1Entry.readLine());
			p2Bet = Integer.valueOf(p2Entry.readLine());
			
			while (!checkBet(p1Bet, p1Exit)) {
				p1Bet = Integer.valueOf(p1Entry.readLine());
			}
			sendMessage(p1Exit, "Ok player 1, you bet  " + p1Bet + " $");
			while (!checkBet(p2Bet, p2Exit)) {
				p2Bet = Integer.valueOf(p2Entry.readLine());
			}
			sendMessage(p2Exit, "Ok player 2, you bet  " + p2Bet + " $");
			jackpotPrize = p1Bet + p2Bet;
			
			oneRound(p1Exit, p1Entry, p2Exit, p2Entry, p1Score, p2Score, cScore, p1Finished, p2Finished, cFinished);
			
			while (!gameOver) {
				oneRound(p1Exit, p1Entry, p2Exit, p2Entry, p1Score, p2Score, cScore, p1Finished, p2Finished, cFinished);
				if (p1Finished && p2Finished && cFinished) {
					boolean p1BlackJack = p1Score == 21;
					boolean p2BlackJack = p2Score == 21;
					boolean cBlackJack = cScore == 21;
					
					if (p1BlackJack && p2BlackJack && cBlackJack) {
						sendMessage(p1Exit, "It's a complete draw, GAME OVER");
						sendMessage(p2Exit, "It's a complete draw, GAME OVER");
					} else if (p1BlackJack && p2BlackJack) {
						sendMessage(p1Exit, "It's a draw between the players, GAME OVER");
						sendMessage(p2Exit, "It's a draw between the players, GAME OVER");
					} else if (p1BlackJack && cBlackJack) {
						sendMessage(p1Exit, "It's a draw between player 1 and the croupier, GAME OVER");
						sendMessage(p2Exit, "It's a draw between player 1 and the croupier, GAME OVER");
					} else if (p2BlackJack && cBlackJack) {
						sendMessage(p1Exit, "It's a draw between player 2 and the croupier, GAME OVER");
						sendMessage(p2Exit, "It's a draw between player 2 and the croupier, GAME OVER");
					} else {
						boolean p1Bust = p1Score > 21;
						boolean p2Bust = p2Score > 21;
						boolean cBust = cScore > 21;
						
						if (p1Bust && p2Bust && cBust) {
							sendMessage(p1Exit, "Everyone has lost, GAME OVER");
							sendMessage(p2Exit, "Everyone has lost, GAME OVER");
						} else if (!p1Bust && p2Bust && cBust) {
							sendMessage(p1Exit, "Player 1 has won" + jackpotPrize + " $, GAME OVER");
							sendMessage(p2Exit, "Player 1 has won" + jackpotPrize + " $, GAME OVER");
						} else if (p1Bust && !p2Bust && cBust) {
							sendMessage(p1Exit, "Player 2 has won" + jackpotPrize + " $, GAME OVER");
							sendMessage(p2Exit, "Player 2 has won" + jackpotPrize + " $, GAME OVER");
						} else if (p1Bust && p2Bust && !cBust) {
							sendMessage(p1Exit, "Croupier has won" + jackpotPrize + " $, GAME OVER");
							sendMessage(p2Exit, "Croupier has won" + jackpotPrize + " $, GAME OVER");
						} else if (!p1Bust && !p2Bust && cBust) {
							if (p1Score > p2Score) {
								sendMessage(p1Exit, "Player 1 has won" + jackpotPrize + " $, GAME OVER");
								sendMessage(p2Exit, "Player 1 has won" + jackpotPrize + " $, GAME OVER");
							} else {
								sendMessage(p1Exit, "Player 2 has won" + jackpotPrize + " $, GAME OVER");
								sendMessage(p2Exit, "Player 2 has won" + jackpotPrize + " $, GAME OVER");
							}
						} else if (!p1Bust && p2Bust && !cBust) {
							if (p1Score > cScore) {
								sendMessage(p1Exit, "Player 1 has won" + jackpotPrize + " $, GAME OVER");
								sendMessage(p2Exit, "Player 1 has won" + jackpotPrize + " $, GAME OVER");
							} else {
								sendMessage(p1Exit, "Croupier has won" + jackpotPrize + " $, GAME OVER");
								sendMessage(p2Exit, "Croupier has won" + jackpotPrize + " $, GAME OVER");
							}
						} else if (p1Bust && !p2Bust && !cBust) {
							if (p2Score > cScore) {
								sendMessage(p1Exit, "Player 2 has won" + jackpotPrize + " $, GAME OVER");
								sendMessage(p2Exit, "Player 2 has won" + jackpotPrize + " $, GAME OVER");
							} else {
								sendMessage(p1Exit, "Croupier has won" + jackpotPrize + " $, GAME OVER");
								sendMessage(p2Exit, "Croupier has won" + jackpotPrize + " $, GAME OVER");
							}
						} else if (!p1Bust && !p2Bust && !cBust) {
							if (p1Score > p2Score) {
								if (p1Score > cScore) {
									sendMessage(p1Exit, "Player 1 has won" + jackpotPrize + " $, GAME OVER");
									sendMessage(p2Exit, "Player 1 has won" + jackpotPrize + " $, GAME OVER");
								} else {
									sendMessage(p1Exit, "Croupier has won" + jackpotPrize + " $, GAME OVER");
									sendMessage(p2Exit, "Croupier has won" + jackpotPrize + " $, GAME OVER");
								}
							} else {
								if (p2Score > cScore) {
									sendMessage(p1Exit, "Player 2 has won" + jackpotPrize + " $, GAME OVER");
									sendMessage(p2Exit, "Player 2 has won" + jackpotPrize + " $, GAME OVER");
								} else {
									sendMessage(p1Exit, "Croupier has won" + jackpotPrize + " $, GAME OVER");
									sendMessage(p2Exit, "Croupier has won" + jackpotPrize + " $, GAME OVER");
								}
							}
						}
					}
					gameOver = true;
				}
			}
			
			p1.close();
			p2.close();
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
	
	private void oneRound(BufferedWriter p1Exit, BufferedReader p1Entry, BufferedWriter p2Exit, BufferedReader p2Entry, Integer p1Score, Integer p2Score, Integer cScore, Boolean p1Finished, Boolean p2Finished, Boolean cFinished) throws IOException {
		if (!p1Finished) {
			sendMessage(p1Exit, "Stand or hit?");
			if (p1Entry.readLine().toUpperCase().equals("HIT")) {
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
			} else p1Finished = true;
		}
		
		if (!p2Finished) {
			sendMessage(p2Exit, "Stand or hit?");
			if (p2Entry.readLine().toUpperCase().equals("HIT")) {
				String p2Card = getCard();
				if (p2Card.equals("Ace of Clubs") || p2Card.equals("Ace of Diamonds") || p2Card.equals("Ace of Hearts") || p2Card.equals("Ace of Spades")) {
					sendMessage(p2Exit, "Your card is " + p2Card + " Do you want a 1 or a 11?" );
					p2Score += Integer.valueOf(p2Entry.readLine());
				} else {
					sendMessage(p2Exit, "Your card is " + p2Card + " / Your score is " + cardDeck.get(p2Card));
					p2Score += cardDeck.get(p2Card);
				}
				cardDeck.remove(p2Card);
				if (p2Score >= 21) p2Finished = true;
			} else p2Finished = true;
		}
		
		if (!cFinished) {
			String cCard = getCard();
			if (cCard.equals("Ace of Clubs") || cCard.equals("Ace of Diamonds") || cCard.equals("Ace of Hearts") || cCard.equals("Ace of Spades")) {		
				if (((cScore + 11) <= 17) && ((cScore + 11) <= 21)) cScore += 11;
				else cScore++;
			} else cScore += cardDeck.get(cCard);
			sendMessage(p1Exit, "Croupier card is " + cCard + " / Croupier score is " + cScore);
			sendMessage(p2Exit, "Croupier card is " + cCard + " / Croupier score is " + cScore);
			cardDeck.remove(cCard);
			if (cScore > 16 || cScore >= 21) cFinished = true;
		}
	}

}
