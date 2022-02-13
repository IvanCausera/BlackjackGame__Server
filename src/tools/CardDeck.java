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
						new Card( 1, "Ace of Clubs",0,
								" _____\n"
							  + "|A    |\n"
							  + "|     |\n"
							  + "|  \u2663  |\n"
							  + "|     |\n"
							  + "|____V| "),
						new Card( 2, "Two of Clubs",2,
								" _____ \n"
							  + "|2    |\n"
							  + "|  \u2663  |\n"
							  + "|     |\n"
						   	  + "|  \u2663  |\n"
							  + "|____Z|"),
						new Card( 3, "Three of Clubs",3,
								" _____\n"
							  + "|3    |\n"
							  + "|  \u2663  |\n"
							  + "|  \u2663  |\n"
							  + "|  \u2663  |\n"
							  + "|____E| "),
						new Card( 4, "Four of Clubs",4,
								" _____ |\n"
							  + "|4    |\n"
							  + "| \u2663 \u2663 |\n"
							  + "|     |\n"
							  + "| \u2663 \u2663 |\n"
							  + "|____h|"),
						new Card( 5, "Five of Clubs",5,
								" _____\n"
							  + "|5    |\n"
							  + "| \u2663 \u2663 |\n"
							  + "|  \u2663  |\n"
							  + "| \u2663 \u2663 |\n"
							  + "|____S|"),
						new Card( 6, "Six of Clubs",6,
								" _____\n"
							  + "|6    |\n"
					   		  + "| \u2663 \u2663 |\n"
							  + "| \u2663 \u2663 |\n"
							  + "| \u2663 \u2663 |\n"
							  + "|____9|"),
						new Card( 7, "Seven of Clubs",7,
								" _____\n"
							  + "|7    |\n"
							  + "| \u2663 \u2663 |\n"
							  + "|\u2663 \u2663 \u2663|\n"
							  + "| \u2663 \u2663 |\n"
							  + "|____L|"),
						new Card( 8, "Eight of Clubs",8,
								" _____\n"
							  + "|8    |\n"
							  + "|\u2663 \u2663 \u2663|\n"
							  + "| \u2663 \u2663 |\n"
							  + "|\u2663 \u2663 \u2663|\n"
							  + "|____8|"),
						new Card( 9, "Nine of Clubs",9,
								" _____\n"
							  + "|9    |\n"
							  + "|\u2663 \u2663 \u2663|\n"
							  + "|\u2663 \u2663 \u2663|\n"
							  + "|\u2663 \u2663 \u2663|\n"
							  + "|____6|"),
						new Card( 10, "Ten of Clubs",10,
								" _____\n"
							  + "|10 \u2663 |\n"
							  + "|\u2663 \u2663 \u2663|\n"
							  + "|\u2663 \u2663 \u2663|\n"
							  + "|\u2663 \u2663 \u2663|\n"
							  + "|____0I|"),
						new Card( 11, "Jack of Clubs",10,
								" _____\n"
							  + "|J    |\n"
							  + "|     |\n"
							  + "|  \u2663  |\n"
							  + "|     |\n"
							  + "|_____| "),
						new Card( 12, "Queen of Clubs",10,
								" _____\n"
							  + "|Q    |\n"
							  + "|     |\n"
						      + "|  \u2663  |\n"
							  + "|     |\n"
							  + "|_____| "),
						new Card( 13, "King of Clubs",10,
								" _____\n"
							  + "|K    |\n"
							  + "|     |\n"
							  + "|  \u2663  |\n"
							  + "|     |\n"
							  + "|_____| "),
						
						new Card( 14, "Ace of Diamonds",0,
								" _____\n"
							  + "|A    |\n"
							  + "|     |\n"
							  + "|  \u2666  |\n"
							  + "|     |\n"
							  + "|____V| "),
						new Card( 15, "Two of Diamonds",2,
								" _____ \n"
							  + "|2    |\n"
							  + "|  \u2666  |\n"
							  + "|     |\n"
							  + "|  \u2666  |\n"
							  + "|____Z|"),
						new Card( 16, "Three of Diamonds",3,
								" _____\n"
							  + "|3    |\n"
							  + "|  \u2666  |\n"
							  + "|  \u2666  |\n"
							  + "|  \u2666  |\n"
							  + "|____E| "),
						new Card( 17, "Four of Diamonds",4,
								" _____ |\n"
							  + "|4    |\n"
							  + "| \u2666 \u2666 |\n"
							  + "|     |\n"
							  + "| \u2666 \u2666 |\n"
							  + "|____h|"),
						new Card( 18, "Five of Diamonds",5,
								" _____\n"
							  + "|5    |\n"
							  + "| \u2666 \u2666 |\n"
							  + "|  \u2666  |\n"
							  + "| \u2666 \u2666 |\n"
							  + "|____S|"),
						new Card( 19, "Six of Diamonds",6,
								" _____\n"
							  + "|6    |\n"
							  + "| \u2666 \u2666 |\n"
							  + "| \u2666 \u2666 |\n"
							  + "| \u2666 \u2666 |\n"
							  + "|____9|"),
						new Card( 20, "Seven of Diamonds",7,
								" _____\n"
							  + "|7    |\n"
							  + "| \u2666 \u2666 |\n"
							  + "|\u2666 \u2666 \u2666|\n"
							  + "| \u2666 \u2666 |\n"
							  + "|____L|"),
						new Card( 21, "Eight of Diamonds",8,
								" _____\n"
							  + "|8    |\n"
							  + "|\u2666 \u2666 \u2666|\n"
							  + "| \u2666 \u2666 |\n"
							  + "|\u2666 \u2666 \u2666|\n"
							  + "|____8|"),
						new Card( 22, "Nine of Diamonds",9,
								" _____\n"
							  + "|9    |\n"
							  + "|\u2666 \u2666 \u2666|\n"
							  + "|\u2666 \u2666 \u2666|\n"
							  + "|\u2666 \u2666 \u2666|\n"
							  + "|____6|"),
						new Card( 23, "Ten of Diamonds",10,
								" _____\n"
							  + "|10 \u2666 |\n"
							  + "|\u2666 \u2666 \u2666|\n"
							  + "|\u2666 \u2666 \u2666|\n"
							  + "|\u2666 \u2666 \u2666|\n"
							  + "|____0I|"),
						new Card( 24, "Jack of Diamonds",10,
								" _____\n"
							  + "|J    |\n"
							  + "|     |\n"
							  + "|  \u2666  |\n"
							  + "|     |\n"
							  + "|_____| "),
						new Card( 25, "Queen of Diamonds",10,
								" _____\n"
							  + "|Q    |\n"
							  + "|     |\n"
							  + "|  \u2666  |\n"
							  + "|     |\n"
							  + "|_____| "),
						new Card( 26, "King of Diamonds",10,
								" _____\n"
							  + "|K    |\n"
							  + "|     |\n"
							  + "|  \u2666  |\n"
							  + "|     |\n"
							  + "|_____| "),
						
						new Card( 27, "Ace of Hearts",0,
								" _____\n"
							  + "|A    |\n"
							  + "|     |\n"
							  + "|  \u2665  |\n"
							  + "|     |\n"
							  + "|____V| "),
						new Card( 28, "Two of Hearts",2,
								" _____ \n"
							  + "|2    |\n"
							  + "|  \u2665  |\n"
							  + "|     |\n"
							  + "|  \u2665  |\n"
							  + "|____Z|"),
						new Card( 29, "Three of Hearts",3,
								" _____\n"
							  + "|3    |\n"
							  + "|  \u2665  |\n"
							  + "|  \u2665  |\n"
							  + "|  \u2665  |\n"
							  + "|____E| "),
						new Card( 30, "Four of Hearts",4,
								" _____ |\n"
							  + "|4    |\n"
							  + "| \u2665 \u2665 |\n"
							  + "|     |\n"
							  + "| \u2665 \u2665 |\n"
							  + "|____h|"),
						new Card( 31, "Five of Hearts",5,
								" _____\n"
							  + "|5    |\n"
							  + "| \u2665 \u2665 |\n"
							  + "|  \u2665  |\n"
							  + "| \u2665 \u2665 |\n"
							  + "|____S|"),
						new Card( 32, "Six of Hearts",6,
								" _____\n"
							  + "|6    |\n"
							  + "| \u2665 \u2665 |\n"
							  + "| \u2665 \u2665 |\n"
							  + "| \u2665 \u2665 |\n"
							  + "|____9|"),
						new Card( 33, "Seven of Hearts",7,
								" _____\n"
							  + "|7    |\n"
							  + "| \u2665 \u2665 |\n"
							  + "|\u2665 \u2665 \u2665|\n"
							  + "| \u2665 \u2665 |\n"
							  + "|____L|"),
						new Card( 34, "Eight of Hearts",8,
								" _____\n"
							  + "|8    |\n"
							  + "|\u2665 \u2665 \u2665|\n"
							  + "| \u2665 \u2665 |\n"
							  + "|\u2665 \u2665 \u2665|\n"
							  + "|____8|"),
						new Card( 35, "Nine of Hearts",9,
								" _____\n"
							  + "|9    |\n"
							  + "|\u2665 \u2665 \u2665|\n"
							  + "|\u2665 \u2665 \u2665|\n"
							  + "|\u2665 \u2665 \u2665|\n"
							  + "|____6|"),
						new Card( 36, "Ten of Hearts",10,
								" _____\n"
							  + "|10 \u2665 |\n"
							  + "|\u2665 \u2665 \u2665|\n"
							  + "|\u2665 \u2665 \u2665|\n"
							  + "|\u2665 \u2665 \u2665|\n"
							  + "|____0I|"),
						new Card( 37, "Jack of Hearts",10,
								" _____\n"
							  + "|J    |\n"
							  + "|     |\n"
							  + "|  \u2665  |\n"
							  + "|     |\n"
							  + "|_____| "),
						new Card( 38, "Queen of Hearts",10,
								" _____\n"
							  + "|Q    |\n"
							  + "|     |\n"
							  + "|  \u2665  |\n"
							  + "|     |\n"
							  + "|_____| "),
						new Card( 39, "King of Hearts",10,
								" _____\n"
							  + "|K    |\n"
							  + "|     |\n"
							  + "|  \u2665  |\n"
							  + "|     |\n"
							  + "|_____| "),
						
						new Card( 40, "Ace of Spades",0,
								" _____\n"
							  + "|A    |\n"
							  + "|     |\n"
							  + "|  \u2660  |\n"
							  + "|     |\n"
							  + "|____V| "),
						new Card( 41, "Two of Spades",2,
								" _____ \n"
							  + "|2    |\n"
							  + "|  \u2660  |\n"
							  + "|     |\n"
							  + "|  \u2660  |\n"
							  + "|____Z|"),
						new Card( 42, "Three of Spades",3,
								" _____\n"
							  + "|3    |\n"
							  + "|  \u2660  |\n"
							  + "|  \u2660  |\n"
							  + "|  \u2660  |\n"
							  + "|____E| "),
						new Card( 43, "Four of Spades",4,
								" _____ |\n"
							  + "|4    |\n"
							  + "| \u2660 \u2660 |\n"
							  + "|     |\n"
							  + "| \u2660 \u2660 |\n"
							  + "|____h|"),
						new Card( 44, "Five of Spades",5,
								" _____\n"
							  + "|5    |\n"
							  + "| \u2660 \u2660 |\n"
							  + "|  \u2660  |\n"
							  + "| \u2660 \u2660 |\n"
							  + "|____S|"),
						new Card( 45, "Six of Spades",6,
								" _____\n"
							  + "|6    |\n"
							  + "| \u2660 \u2660 |\n"
							  + "| \u2660 \u2660 |\n"
							  + "| \u2660 \u2660 |\n"
							  + "|____9|"),
						new Card( 46, "Seven of Spades",7,
								" _____\n"
							  + "|7    |\n"
							  + "| \u2660 \u2660 |\n"
							  + "|\u2660 \u2660 \u2663|\n"
							  + "| \u2660 \u2660 |\n"
							  + "|____L|"),
						new Card( 47, "Eight of Spades",8,
								" _____\n"
							  + "|8    |\n"
							  + "|\u2660 \u2660 \u2660|\n"
							  + "| \u2660 \u2660 |\n"
							  + "|\u2660 \u2660 \u2660|\n"
							  + "|____8|"),
						new Card( 48, "Nine of Spades",9,
								" _____\n"
							  + "|9    |\n"
							  + "|\u2660 \u2660 \u2660|\n"
							  + "|\u2660 \u2660 \u2660|\n"
							  + "|\u2660 \u2660 \u2660|\n"
							  + "|____6|"),
						new Card( 49, "Ten of Spades",10,
								" _____\n"
							  + "|10 \u2660 |\n"
							  + "|\u2660 \u2660 \u2660|\n"
							  + "|\u2660 \u2660 \u2660|\n"
							  + "|\u2660 \u2660 \u2660|\n"
							  + "|____0I|"),
						new Card( 50, "Jack of Spades",10,
								" _____\n"
							  + "|J    |\n"
							  + "|     |\n"
							  + "|  \u2660  |\n"
							  + "|     |\n"
							  + "|_____| "),
						new Card( 51, "Queen of Spades",10,
								" _____\n"
							  + "|Q    |\n"
							  + "|     |\n"
							  + "|  \u2660  |\n"
							  + "|     |\n"
							  + "|_____| "),
						new Card( 52, "King of Spades",10,
								" _____\n"
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
