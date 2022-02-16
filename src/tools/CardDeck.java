package tools;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * This class sotres the information of a Card
 * 
 * @author Frank Vanegas, Jukka Rivas, Ivan Causera
 */
public class CardDeck {
	private ArrayList<Card> cardDeck;
	private String club;
	private String heart;
	private String spade;
	private String diamond;
	
	public CardDeck() {
		loadSymbols();
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
											+ "|  "+club+"|\n"
											+ "|     |\n"
											+ "|____V| "),
						new Card( 2,2,
								 "TWO OF CLUBS \n"
											+  " _____ \n"
											+ "|2    |\n"
											+ "|  "+club+"  |\n"
											+ "|     |\n"
											+ "|  "+club+"  |\n"
											+ "|____Z|"),
						new Card( 3,3,
								"THREE OF CLUBS\n"
										+ " _____\n"
										+ "|3    |\n"
										+ "|  "+club+"  |\n"
										+ "|  "+club+"  |\n"
										+ "|  "+club+"  |\n"
										+ "|____E| "),
						new Card( 4,4,
								"FOUR OF CLUBS\n"
										+ " _____ |\n"
										+ "|4    |\n"
										+ "| "+club+" "+club+" |\n"
										+ "|     |\n"
										+ "| "+club+" "+club+" |\n"
										+ "|____h|"),
						new Card( 5,5,
								"FIVE OF CLUBS\n"
										+ " _____\n"
										+ "|5    |\n"
										+ "| "+club+" "+club+" |\n"
										+ "|  "+club+"  |\n"
										+ "| "+club+" "+club+" |\n"
										+ "|____S|"),
						new Card( 6,6,
								"SIX OF CLUBS\n"
										+ " _____\n"
										+ "|6    |\n"
										+ "| "+club+" "+club+" |\n"
										+ "| "+club+" "+club+" |\n"
										+ "| "+club+" "+club+" |\n"
										+ "|____9|"),
						new Card( 7,7,
								"SEVEN OF CLUBS\n"
										+ " _____\n"
										+ "|7    |\n"
										+ "| "+club+" "+club+" |\n"
										+ "|"+club+" "+club+" "+club+"|\n"
										+ "| "+club+" "+club+" |\n"
										+ "|____L|"),
						new Card( 8,8,
								"EIGHT OF CLUBS\n"
										+ " _____\n"
										+ "|8    |\n"
										+ "|"+club+" "+club+" "+club+"|\n"
										+ "| "+club+" "+club+" |\n"
										+ "|"+club+" "+club+" "+club+"|\n"
										+ "|____8|"),
						new Card( 9,9,
								"NINE OF CLUBS\n"
										+ " _____\n"
										+ "|9    |\n"
										+ "|"+club+" "+club+" "+club+"|\n"
										+ "|"+club+" "+club+" "+club+"|\n"
										+ "|"+club+" "+club+" "+club+"|\n"
										+ "|____6|"),
						new Card( 10,10,
								"TEN OF CLUBS\n"
										+ " _____\n"
										+ "|10 "+club+" |\n"
										+ "|"+club+" "+club+" "+club+"|\n"
										+ "|"+club+" "+club+" "+club+"|\n"
										+ "|"+club+" "+club+" "+club+"|\n"
										+ "|____0I|"),
						new Card( 11,10,
								"JACK OF CLUBS\n"
										+ " _____\n"
										+ "|J    |\n"
										+ "|     |\n"
										+ "|  "+club+"  |\n"
										+ "|     |\n"
										+ "|_____| "),
						new Card( 12,10,
								"QUEEN OF CLUBS\n"
										+ " _____\n"
										+ "|Q    |\n"
										+ "|     |\n"
										+ "|  "+club+"  |\n"
										+ "|     |\n"
										+ "|_____| "),
						new Card( 13,10,
								"KING OF CLUBS\n"
										+ " _____\n"
										+ "|K    |\n"
										+ "|     |\n"
										+ "|  "+club+"  |\n"
										+ "|     |\n"
										+ "|_____| "),
						
						new Card( 1,0,
								  "ACE OF DIAMONDS\n"
											+ " _____\n"
											+ "|A    |\n"
											+ "|     |\n"
											+ "|  "+diamond+"|\n"
											+ "|     |\n"
											+ "|____V| "),
						new Card( 2,2,
								 "TWO OF DIAMONDS \n"
											+  " _____ \n"
											+ "|2    |\n"
											+ "|  "+diamond+"  |\n"
											+ "|     |\n"
											+ "|  "+diamond+"  |\n"
											+ "|____Z|"),
						new Card( 3,3,
								"THREE OF DIAMONDS\n"
										+ " _____\n"
										+ "|3    |\n"
										+ "|  "+diamond+"  |\n"
										+ "|  "+diamond+"  |\n"
										+ "|  "+diamond+"  |\n"
										+ "|____E| "),
						new Card( 4,4,
								"FOUR OF DIAMONDS\n"
										+ " _____ |\n"
										+ "|4    |\n"
										+ "| "+diamond+" "+diamond+" |\n"
										+ "|     |\n"
										+ "| "+diamond+" "+diamond+" |\n"
										+ "|____h|"),
						new Card( 5,5,
								"FIVE OF DIAMONDS\n"
										+ " _____\n"
										+ "|5    |\n"
										+ "| "+diamond+" "+diamond+" |\n"
										+ "|  "+diamond+"  |\n"
										+ "| "+diamond+" "+diamond+" |\n"
										+ "|____S|"),
						new Card( 6,6,
								"SIX OF DIAMONDS\n"
										+ " _____\n"
										+ "|6    |\n"
										+ "| "+diamond+" "+diamond+" |\n"
										+ "| "+diamond+" "+diamond+" |\n"
										+ "| "+diamond+" "+diamond+" |\n"
										+ "|____9|"),
						new Card( 7,7,
								"SEVEN OF DIAMONDS\n"
										+ " _____\n"
										+ "|7    |\n"
										+ "| "+diamond+" "+diamond+" |\n"
										+ "|"+diamond+" "+diamond+" "+diamond+"|\n"
										+ "| "+diamond+" "+diamond+" |\n"
										+ "|____L|"),
						new Card( 8,8,
								"EIGHT OF DIAMONDS\n"
										+ " _____\n"
										+ "|8    |\n"
										+ "|"+diamond+" "+diamond+" "+diamond+"|\n"
										+ "| "+diamond+" "+diamond+" |\n"
										+ "|"+diamond+" "+diamond+" "+diamond+"|\n"
										+ "|____8|"),
						new Card( 9,9,
								"NINE OF DIAMONDS\n"
										+ " _____\n"
										+ "|9    |\n"
										+ "|"+diamond+" "+diamond+" "+diamond+"|\n"
										+ "|"+diamond+" "+diamond+" "+diamond+"|\n"
										+ "|"+diamond+" "+diamond+" "+diamond+"|\n"
										+ "|____6|"),
						new Card( 10,10,
								"TEN OF DIAMONDS\n"
										+ " _____\n"
										+ "|10 "+diamond+" |\n"
										+ "|"+diamond+" "+diamond+" "+diamond+"|\n"
										+ "|"+diamond+" "+diamond+" "+diamond+"|\n"
										+ "|"+diamond+" "+diamond+" "+diamond+"|\n"
										+ "|____0I|"),
						new Card( 11,10,
								"JACK OF DIAMONDS\n"
										+ " _____\n"
										+ "|J    |\n"
										+ "|     |\n"
										+ "|  "+diamond+"  |\n"
										+ "|     |\n"
										+ "|_____| "),
						new Card( 12,10,
								"QUEEN OF DIAMONDS\n"
										+ " _____\n"
										+ "|Q    |\n"
										+ "|     |\n"
										+ "|  "+diamond+"  |\n"
										+ "|     |\n"
										+ "|_____| "),
						new Card( 13,10,
								"KING OF DIAMONDS\n"
										+ " _____\n"
										+ "|K    |\n"
										+ "|     |\n"
										+ "|  "+diamond+"  |\n"
										+ "|     |\n"
										+ "|_____| "),
						
						//HEARTS
						
						new Card( 27,0,
								"ACE OF HEARTS\n"
										+ " _____\n"
										+ "|A    |\n"
										+ "|     |\n"
										+ "|  "+heart+"  |\n"
										+ "|     |\n"
										+ "|____V| "),
						new Card( 28,2,
								"TWO OF HEARTS \n"
										+  " _____ \n"
										+ "|2    |\n"
										+ "|  "+heart+"  |\n"
										+ "|     |\n"
										+ "|  "+heart+"  |\n"
										+ "|____Z|"),
						new Card( 29,3,
								"THREE OF HEARTS\n"
										+ " _____\n"
										+ "|3    |\n"
										+ "|  "+heart+"  |\n"
										+ "|  "+heart+"  |\n"
										+ "|  "+heart+"  |\n"
										+ "|____E| "),
						new Card( 30,4,
								"FOUR OF HEARTS\n"
										+ " _____ |\n"
										+ "|4    |\n"
										+ "| "+heart+" "+heart+" |\n"
										+ "|     |\n"
										+ "| "+heart+" "+heart+" |\n"
										+ "|____h|"),
						new Card( 31,5,
								"FIVE OF HEARTS\n"
										+ " _____\n"
										+ "|5    |\n"
										+ "| "+heart+" "+heart+" |\n"
										+ "|  "+heart+"  |\n"
										+ "| "+heart+" "+heart+" |\n"
										+ "|____S|"),
						new Card( 32,6,
								"SIX OF HEARTS\n"
										+ " _____\n"
										+ "|6    |\n"
										+ "| "+heart+" "+heart+" |\n"
										+ "| "+heart+" "+heart+" |\n"
										+ "| "+heart+" "+heart+" |\n"
										+ "|____9|"),
						new Card( 33,7,
								"SEVEN OF HEARTS\n"
										+ " _____\n"
										+ "|7    |\n"
										+ "| "+heart+" "+heart+" |\n"
										+ "|"+heart+" "+heart+" "+heart+"|\n"
										+ "| "+heart+" "+heart+" |\n"
										+ "|____L|"),
						new Card( 34,8,
								"EIGHT OF HEARTS\n"
										+ " _____\n"
										+ "|8    |\n"
										+ "|"+heart+" "+heart+" "+heart+"|\n"
										+ "| "+heart+" "+heart+" |\n"
										+ "|"+heart+" "+heart+" "+heart+"|\n"
										+ "|____8|"),
						new Card( 35,9,
								"NINE OF HEARTS\n"
										+ " _____\n"
										+ "|9    |\n"
										+ "|"+heart+" "+heart+" "+heart+"|\n"
										+ "|"+heart+" "+heart+" "+heart+"|\n"
										+ "|"+heart+" "+heart+" "+heart+"|\n"
										+ "|____6|"),
						new Card( 36,10,
								"TEN OF HEARTS\n"
										+ " _____\n"
										+ "|10 "+heart+" |\n"
										+ "|"+heart+" "+heart+" "+heart+"|\n"
										+ "|"+heart+" "+heart+" "+heart+"|\n"
										+ "|"+heart+" "+heart+" "+heart+"|\n"
										+ "|____0I|"),
						new Card( 37,10,
								"JACK OF HEARTS\n"
										+ " _____\n"
										+ "|J    |\n"
										+ "|     |\n"
										+ "|  "+heart+"  |\n"
										+ "|     |\n"
										+ "|_____| "),
						new Card( 38,10,
								"QUEEN OF HEARTS\n"
										+ " _____\n"
										+ "|Q    |\n"
										+ "|     |\n"
										+ "|  "+heart+"  |\n"
										+ "|     |\n"
										+ "|_____| "),
						new Card( 39,10,
								"KING OF HEARTS\n"
										+ " _____\n"
										+ "|K    |\n"
										+ "|     |\n"
										+ "|  "+heart+"  |\n"
										+ "|     |\n"
										+ "|_____| "),
						
						
						//SPADES
						
						
						new Card( 40,0,
								"ACE OF SPADES\n"
										+ " _____\n"
										+ "|A    |\n"
										+ "|     |\n"
										+ "|  "+spade+"  |\n"
										+ "|     |\n"
										+ "|____V| "),
						new Card( 41,2,
								"TWO OF SPADES \n"
										+  " _____ \n"
										+ "|2    |\n"
										+ "|  "+spade+"  |\n"
										+ "|     |\n"
										+ "|  "+spade+"  |\n"
										+ "|____Z|"),
						new Card( 42,3,
								"THREE OF SPADES\n"
										+ " _____\n"
										+ "|3    |\n"
										+ "|  "+spade+"  |\n"
										+ "|  "+spade+"  |\n"
										+ "|  "+spade+"  |\n"
										+ "|____E| "),
						new Card( 43,4,
								"FOUR OF SPADES\n"
										+ " _____ |\n"
										+ "|4    |\n"
										+ "| "+spade+" "+spade+" |\n"
										+ "|     |\n"
										+ "| "+spade+" "+spade+" |\n"
										+ "|____h|"),
						new Card( 44,5,
								"FIVE OF SPADES\n"
										+ " _____\n"
										+ "|5    |\n"
										+ "| "+spade+" "+spade+" |\n"
										+ "|  "+spade+"  |\n"
										+ "| "+spade+" "+spade+" |\n"
										+ "|____S|"),
						new Card( 45,6,
								"SIX OF SPADES\n"
										+ " _____\n"
										+ "|6    |\n"
										+ "| "+spade+" "+spade+" |\n"
										+ "| "+spade+" "+spade+" |\n"
										+ "| "+spade+" "+spade+" |\n"
										+ "|____9|"),
						new Card( 46,7,
								"SEVEN OF SPADES\n"
										+ " _____\n"
										+ "|7    |\n"
										+ "| "+spade+" "+spade+" |\n"
										+ "|"+spade+" "+spade+" "+spade+"|\n"
										+ "| "+spade+" "+spade+" |\n"
										+ "|____L|"),
						new Card( 47,8,
								"EIGHT OF SPADES\n"
										+ " _____\n"
										+ "|8    |\n"
										+ "|"+spade+" "+spade+" "+spade+"|\n"
										+ "| "+spade+" "+spade+" |\n"
										+ "|"+spade+" "+spade+" "+spade+"|\n"
										+ "|____8|"),
						new Card( 48,9,
								"NINE OF SPADES\n"
										+ " _____\n"
										+ "|9    |\n"
										+ "|"+spade+" "+spade+" "+spade+"|\n"
										+ "|"+spade+" "+spade+" "+spade+"|\n"
										+ "|"+spade+" "+spade+" "+spade+"|\n"
										+ "|____6|"),
						new Card( 49,10,
								"TEN OF SPADES\n"
										+ " _____\n"
										+ "|10 "+spade+" |\n"
										+ "|"+spade+" "+spade+" "+spade+"|\n"
										+ "|"+spade+" "+spade+" "+spade+"|\n"
										+ "|"+spade+" "+spade+" "+spade+"|\n"
										+ "|____0I|"),
						new Card( 50,10,
								"JACK OF SPADES\n"
										+ " _____\n"
										+ "|J    |\n"
										+ "|     |\n"
										+ "|  "+spade+"  |\n"
										+ "|     |\n"
										+ "|_____| "),
						new Card( 51,10,
								"QUEEN OF SPADES\n"
										+ " _____\n"
										+ "|Q    |\n"
										+ "|     |\n"
										+ "|  "+spade+"  |\n"
										+ "|     |\n"
										+ "|_____| "),
						new Card( 52,10,
								"KING OF SPADES\n"
										+ " _____\n"
										+ "|K    |\n"
										+ "|     |\n"
										+ "|  "+spade+"  |\n"
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
	
	public void loadSymbols() {
		if (System.getProperty("os.name").equals("Linux")) {
			club = "\u2663";
			heart = "\u2665";
			spade = "\u2660";
			diamond = "\u2666";
		} else {
			club = "C";
			heart = "H";
			spade = "S";
			diamond = "D";
		}
	}
}
