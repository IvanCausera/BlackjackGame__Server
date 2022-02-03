package tools;

import java.util.ArrayList;
import java.util.Arrays;

public class CardDeck {
	private ArrayList<Card> cardDeck;
	
	public CardDeck() {
		RefreshDeck();
	}
	
	/**
	 * Refresh the card deck
	 */
	public void RefreshDeck() {
		cardDeck = new ArrayList<Card>(
				Arrays.asList(
						new Card( 1, "Ace of Clubs",0),
						new Card( 2, "Two of Clubs",2),
						new Card( 3, "Three of Clubs",3),
						new Card( 4, "Four of Clubs",4),
						new Card( 5, "Five of Clubs",5),
						new Card( 6, "Six of Clubs",6),
						new Card( 7, "Seven of Clubs",7),
						new Card( 8, "Eight of Clubs",8),
						new Card( 9, "Nine of Clubs",9),
						new Card( 10, "Ten of Clubs",10),
						new Card( 11, "Jack of Clubs",10),
						new Card( 12, "Queen of Clubs",10),
						new Card( 13, "King of Clubs",10),
						
						new Card( 14, "Ace of Diamonds",0),
						new Card( 15, "Two of Diamonds",2),
						new Card( 16, "Three of Diamonds",3),
						new Card( 17, "Four of Diamonds",4),
						new Card( 18, "Five of Diamonds",5),
						new Card( 19, "Six of Diamonds",6),
						new Card( 20, "Seven of Diamonds",7),
						new Card( 21, "Eight of Diamonds",8),
						new Card( 22, "Nine of Diamonds",9),
						new Card( 23, "Ten of Diamonds",10),
						new Card( 24, "Jack of Diamonds",10),
						new Card( 25, "Queen of Diamonds",10),
						new Card( 26, "King of Diamonds",10),
						
						new Card( 27, "Ace of Hearts",0),
						new Card( 28, "Two of Hearts",2),
						new Card( 29, "Three of Hearts",3),
						new Card( 30, "Four of Hearts",4),
						new Card( 31, "Five of Hearts",5),
						new Card( 32, "Six of Hearts",6),
						new Card( 33, "Seven of Hearts",7),
						new Card( 34, "Eight of Hearts",8),
						new Card( 35, "Nine of Hearts",9),
						new Card( 36, "Ten of Hearts",10),
						new Card( 37, "Jack of Hearts",10),
						new Card( 38, "Queen of Hearts",10),
						new Card( 39, "King of Hearts",10),
						
						new Card( 40, "Ace of Spades",0),
						new Card( 41, "Two of Spades",2),
						new Card( 42, "Three of Spades",3),
						new Card( 43, "Four of Spades",4),
						new Card( 44, "Five of Spades",5),
						new Card( 45, "Six of Spades",6),
						new Card( 46, "Seven of Spades",7),
						new Card( 47, "Eight of Spades",8),
						new Card( 48, "Nine of Spades",9),
						new Card( 49, "Ten of Spades",10),
						new Card( 50, "Jack of Spades",10),
						new Card( 51, "Queen of Spades",10),
						new Card( 52, "King of Spades",10)
						));
		
	}

	/**
	 * Removes a random card from the deck
	 * @return removed card
	 */
	public Card removeCard() {
		return cardDeck.remove(Tools.randomNumber(0, cardDeck.size()));
	}
}
