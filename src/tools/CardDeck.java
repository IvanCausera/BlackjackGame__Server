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
						new Card( 1,0,
								  "ACE OF CLUBS\n"
											+ " _____\n"
											+ "|A    |\n"
											+ "|     |\n"
											+ "|  \u2663  |\n"
											+ "|     |\n"
											+ "|____V| "),
						new Card( 2,2,
								 "TWO OF CLUBS \n"
											+  " _____ \n"
											+ "|2    |\n"
											+ "|  \u2663  |\n"
											+ "|     |\n"
											+ "|  \u2663  |\n"
											+ "|____Z|"),
						new Card( 3,3,
								"THREE OF CLUBS\n"
										+ " _____\n"
										+ "|3    |\n"
										+ "|  \u2663  |\n"
										+ "|  \u2663  |\n"
										+ "|  \u2663  |\n"
										+ "|____E| "),
						new Card( 4,4,
								"FOUR OF CLUBS\n"
										+ " _____ |\n"
										+ "|4    |\n"
										+ "| \u2663 \u2663 |\n"
										+ "|     |\n"
										+ "| \u2663 \u2663 |\n"
										+ "|____h|"),
						new Card( 5,5,
								"FIVE OF CLUBS\n"
										+ " _____\n"
										+ "|5    |\n"
										+ "| \u2663 \u2663 |\n"
										+ "|  \u2663  |\n"
										+ "| \u2663 \u2663 |\n"
										+ "|____S|"),
						new Card( 6,6,
								"SIX OF CLUBS\n"
										+ " _____\n"
										+ "|6    |\n"
										+ "| \u2663 \u2663 |\n"
										+ "| \u2663 \u2663 |\n"
										+ "| \u2663 \u2663 |\n"
										+ "|____9|"),
						new Card( 7,7,
								"SEVEN OF CLUBS\n"
										+ " _____\n"
										+ "|7    |\n"
										+ "| \u2663 \u2663 |\n"
										+ "|\u2663 \u2663 \u2663|\n"
										+ "| \u2663 \u2663 |\n"
										+ "|____L|"),
						new Card( 8,8,
								"EIGHT OF CLUBS\n"
										+ " _____\n"
										+ "|8    |\n"
										+ "|\u2663 \u2663 \u2663|\n"
										+ "| \u2663 \u2663 |\n"
										+ "|\u2663 \u2663 \u2663|\n"
										+ "|____8|"),
						new Card( 9,9,
								"NINE OF CLUBS\n"
										+ " _____\n"
										+ "|9    |\n"
										+ "|\u2663 \u2663 \u2663|\n"
										+ "|\u2663 \u2663 \u2663|\n"
										+ "|\u2663 \u2663 \u2663|\n"
										+ "|____6|"),
						new Card( 10,10,
								"TEN OF CLUBS\n"
										+ " _____\n"
										+ "|10 \u2663 |\n"
										+ "|\u2663 \u2663 \u2663|\n"
										+ "|\u2663 \u2663 \u2663|\n"
										+ "|\u2663 \u2663 \u2663|\n"
										+ "|____0I|"),
						new Card( 11,10,
								"JACK OF CLUBS\n"
										+ " _____\n"
										+ "|J    |\n"
										+ "|     |\n"
										+ "|  \u2663  |\n"
										+ "|     |\n"
										+ "|_____| "),
						new Card( 12,10,
								"QUEEN OF CLUBS\n"
										+ " _____\n"
										+ "|Q    |\n"
										+ "|     |\n"
										+ "|  \u2663  |\n"
										+ "|     |\n"
										+ "|_____| "),
						new Card( 13,10,
								"KING OF CLUBS\n"
										+ " _____\n"
										+ "|K    |\n"
										+ "|     |\n"
										+ "|  \u2663  |\n"
										+ "|     |\n"
										+ "|_____| "),
						
						new Card( 14,0,
								"ACE OF DIAMONDS\n"
										+ " _____\n"
										+ "|A    |\n"
										+ "|     |\n"
										+ "|  \u2666  |\n"
										+ "|     |\n"
										+ "|____V| "),
						new Card( 15,2,
								"TWO OF DIAMONDS \n"
										+  " _____ \n"
										+ "|2    |\n"
										+ "|  \u2666  |\n"
										+ "|     |\n"
										+ "|  \u2666  |\n"
										+ "|____Z|"),
						new Card( 16,3,
								"THREE OF DIAMONDS\n"
										+ " _____\n"
										+ "|3    |\n"
										+ "|  \u2666  |\n"
										+ "|  \u2666  |\n"
										+ "|  \u2666  |\n"
										+ "|____E| "),
						new Card( 17,4,
								"FOUR OF DIAMONDS\n"
										+ " _____ |\n"
										+ "|4    |\n"
										+ "| \u2666 \u2666 |\n"
										+ "|     |\n"
										+ "| \u2666 \u2666 |\n"
										+ "|____h|"),
						new Card( 18,5,
								"FIVE OF DIAMONDS\n"
										+ " _____\n"
										+ "|5    |\n"
										+ "| \u2666 \u2666 |\n"
										+ "|  \u2666  |\n"
										+ "| \u2666 \u2666 |\n"
										+ "|____S|"),
						new Card( 19,6,
								"SIX OF DIAMONDS\n"
										+ " _____\n"
										+ "|6    |\n"
										+ "| \u2666 \u2666 |\n"
										+ "| \u2666 \u2666 |\n"
										+ "| \u2666 \u2666 |\n"
										+ "|____9|"),
						new Card( 20,7,
								"SEVEN OF DIAMONDS\n"
										+ " _____\n"
										+ "|7    |\n"
										+ "| \u2666 \u2666 |\n"
										+ "|\u2666 \u2666 \u2666|\n"
										+ "| \u2666 \u2666 |\n"
										+ "|____L|"),
						new Card( 21,8,
								"EIGHT OF DIAMONDS\n"
										+ " _____\n"
										+ "|8    |\n"
										+ "|\u2666 \u2666 \u2666|\n"
										+ "| \u2666 \u2666 |\n"
										+ "|\u2666 \u2666 \u2666|\n"
										+ "|____8|"),
						new Card( 22,9,
								"NINE OF DIAMONDS\n"
										+ " _____\n"
										+ "|9    |\n"
										+ "|\u2666 \u2666 \u2666|\n"
										+ "|\u2666 \u2666 \u2666|\n"
										+ "|\u2666 \u2666 \u2666|\n"
										+ "|____6|"),
						new Card( 23,10,
								"TEN OF DIAMONDS\n"
										+ " _____\n"
										+ "|10 \u2666 |\n"
										+ "|\u2666 \u2666 \u2666|\n"
										+ "|\u2666 \u2666 \u2666|\n"
										+ "|\u2666 \u2666 \u2666|\n"
										+ "|____0I|"),
						new Card( 24,10,
								"JACK OF DIAMONDS\n"
										+ " _____\n"
										+ "|J    |\n"
										+ "|     |\n"
										+ "|  \u2666  |\n"
										+ "|     |\n"
										+ "|_____| "),
						new Card( 25,10,
								"QUEEN OF DIAMONDS\n"
										+ " _____\n"
										+ "|Q    |\n"
										+ "|     |\n"
										+ "|  \u2666  |\n"
										+ "|     |\n"
										+ "|_____| "),
						new Card( 26,10,
								"KING OF DIAMONDS\n"
										+ " _____\n"
										+ "|K    |\n"
										+ "|     |\n"
										+ "|  \u2666  |\n"
										+ "|     |\n"
										+ "|_____| "),
						
						new Card( 27,0,
								"ACE OF HEARTS\n"
										+ " _____\n"
										+ "|A    |\n"
										+ "|     |\n"
										+ "|  \u2665  |\n"
										+ "|     |\n"
										+ "|____V| "),
						new Card( 28,2,
								"TWO OF HEARTS \n"
										+  " _____ \n"
										+ "|2    |\n"
										+ "|  \u2665  |\n"
										+ "|     |\n"
										+ "|  \u2665  |\n"
										+ "|____Z|"),
						new Card( 29,3,
								"THREE OF HEARTS\n"
										+ " _____\n"
										+ "|3    |\n"
										+ "|  \u2665  |\n"
										+ "|  \u2665  |\n"
										+ "|  \u2665  |\n"
										+ "|____E| "),
						new Card( 30,4,
								"FOUR OF HEARTS\n"
										+ " _____ |\n"
										+ "|4    |\n"
										+ "| \u2665 \u2665 |\n"
										+ "|     |\n"
										+ "| \u2665 \u2665 |\n"
										+ "|____h|"),
						new Card( 31,5,
								"FIVE OF HEARTS\n"
										+ " _____\n"
										+ "|5    |\n"
										+ "| \u2665 \u2665 |\n"
										+ "|  \u2665  |\n"
										+ "| \u2665 \u2665 |\n"
										+ "|____S|"),
						new Card( 32,6,
								"SIX OF HEARTS\n"
										+ " _____\n"
										+ "|6    |\n"
										+ "| \u2665 \u2665 |\n"
										+ "| \u2665 \u2665 |\n"
										+ "| \u2665 \u2665 |\n"
										+ "|____9|"),
						new Card( 33,7,
								"SEVEN OF HEARTS\n"
										+ " _____\n"
										+ "|7    |\n"
										+ "| \u2665 \u2665 |\n"
										+ "|\u2665 \u2665 \u2665|\n"
										+ "| \u2665 \u2665 |\n"
										+ "|____L|"),
						new Card( 34,8,
								"EIGHT OF HEARTS\n"
										+ " _____\n"
										+ "|8    |\n"
										+ "|\u2665 \u2665 \u2665|\n"
										+ "| \u2665 \u2665 |\n"
										+ "|\u2665 \u2665 \u2665|\n"
										+ "|____8|"),
						new Card( 35,9,
								"NINE OF HEARTS\n"
										+ " _____\n"
										+ "|9    |\n"
										+ "|\u2665 \u2665 \u2665|\n"
										+ "|\u2665 \u2665 \u2665|\n"
										+ "|\u2665 \u2665 \u2665|\n"
										+ "|____6|"),
						new Card( 36,10,
								"TEN OF HEARTS\n"
										+ " _____\n"
										+ "|10 \u2665 |\n"
										+ "|\u2665 \u2665 \u2665|\n"
										+ "|\u2665 \u2665 \u2665|\n"
										+ "|\u2665 \u2665 \u2665|\n"
										+ "|____0I|"),
						new Card( 37,10,
								"JACK OF HEARTS\n"
										+ " _____\n"
										+ "|J    |\n"
										+ "|     |\n"
										+ "|  \u2665  |\n"
										+ "|     |\n"
										+ "|_____| "),
						new Card( 38,10,
								"QUEEN OF HEARTS\n"
										+ " _____\n"
										+ "|Q    |\n"
										+ "|     |\n"
										+ "|  \u2665  |\n"
										+ "|     |\n"
										+ "|_____| "),
						new Card( 39,10,
								"KING OF HEARTS\n"
										+ " _____\n"
										+ "|K    |\n"
										+ "|     |\n"
										+ "|  \u2665  |\n"
										+ "|     |\n"
										+ "|_____| "),
						
						new Card( 40,0,
								"ACE OF SPADES\n"
										+ " _____\n"
										+ "|A    |\n"
										+ "|     |\n"
										+ "|  \u2660  |\n"
										+ "|     |\n"
										+ "|____V| "),
						new Card( 41,2,
								"TWO OF SPADES \n"
										+  " _____ \n"
										+ "|2    |\n"
										+ "|  \u2660  |\n"
										+ "|     |\n"
										+ "|  \u2660  |\n"
										+ "|____Z|"),
						new Card( 42,3,
								"THREE OF SPADES\n"
										+ " _____\n"
										+ "|3    |\n"
										+ "|  \u2660  |\n"
										+ "|  \u2660  |\n"
										+ "|  \u2660  |\n"
										+ "|____E| "),
						new Card( 43,4,
								"FOUR OF SPADES\n"
										+ " _____ |\n"
										+ "|4    |\n"
										+ "| \u2660 \u2660 |\n"
										+ "|     |\n"
										+ "| \u2660 \u2660 |\n"
										+ "|____h|"),
						new Card( 44,5,
								"FIVE OF SPADES\n"
										+ " _____\n"
										+ "|5    |\n"
										+ "| \u2660 \u2660 |\n"
										+ "|  \u2660  |\n"
										+ "| \u2660 \u2660 |\n"
										+ "|____S|"),
						new Card( 45,6,
								"SIX OF SPADES\n"
										+ " _____\n"
										+ "|6    |\n"
										+ "| \u2660 \u2660 |\n"
										+ "| \u2660 \u2660 |\n"
										+ "| \u2660 \u2660 |\n"
										+ "|____9|"),
						new Card( 46,7,
								"SEVEN OF SPADES\n"
										+ " _____\n"
										+ "|7    |\n"
										+ "| \u2660 \u2660 |\n"
										+ "|\u2660 \u2660 \u2663|\n"
										+ "| \u2660 \u2660 |\n"
										+ "|____L|"),
						new Card( 47,8,
								"EIGHT OF SPADES\n"
										+ " _____\n"
										+ "|8    |\n"
										+ "|\u2660 \u2660 \u2660|\n"
										+ "| \u2660 \u2660 |\n"
										+ "|\u2660 \u2660 \u2660|\n"
										+ "|____8|"),
						new Card( 48,9,
								"NINE OF SPADES\n"
										+ " _____\n"
										+ "|9    |\n"
										+ "|\u2660 \u2660 \u2660|\n"
										+ "|\u2660 \u2660 \u2660|\n"
										+ "|\u2660 \u2660 \u2660|\n"
										+ "|____6|"),
						new Card( 49,10,
								"TEN OF SPADES\n"
										+ " _____\n"
										+ "|10 \u2660 |\n"
										+ "|\u2660 \u2660 \u2660|\n"
										+ "|\u2660 \u2660 \u2660|\n"
										+ "|\u2660 \u2660 \u2660|\n"
										+ "|____0I|"),
						new Card( 50,10,
								"JACK OF SPADES\n"
										+ " _____\n"
										+ "|J    |\n"
										+ "|     |\n"
										+ "|  \u2660  |\n"
										+ "|     |\n"
										+ "|_____| "),
						new Card( 51,10,
								"QUEEN OF SPADES\n"
										+ " _____\n"
										+ "|Q    |\n"
										+ "|     |\n"
										+ "|  \u2660  |\n"
										+ "|     |\n"
										+ "|_____| "),
						new Card( 52,10,
								"KING OF SPADES\n"
										+ " _____\n"
										+ "|K    |\n"
										+ "|     |\n"
										+ "|  \u2660  |\n"
										+ "|     |\n"
										+ "|_____| ")
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
