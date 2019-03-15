package war;

import java.util.ListIterator;

import adts.list.LinkedList;

public class Card
{
	private String rank;
	private String suit;
	private static final LinkedList<String> RANKS = setUpRanks();
	private static final LinkedList<String> SUITS = setUpSuits();
	
	private static ListIterator<String> currentRank = RANKS.listIterator(0);
	private static ListIterator<String> currentSuit = SUITS.listIterator(0);
	public static Card getNextCard()
	{
		if (!currentRank.hasNext()) //If the end of ranks is reached
		{
			currentRank = RANKS.listIterator(0); //Loop back to beginning
			//Then change suit
			if (currentSuit.hasNext())
			{
				currentSuit.next();
				if (!currentSuit.hasNext()) //Check to see if nextIndex is able to be sued
				{
					currentSuit = SUITS.listIterator(0); //If not, loop back to beginning
				}
			}
		}
		return new Card(currentRank.next(), SUITS.get(currentSuit.nextIndex()));
	}
	public static void resetCards()
	{
		currentRank = RANKS.listIterator(0);
		currentSuit = SUITS.listIterator(0);
	}
	
	public static int getCardSetSize()
	{
		return RANKS.size() * SUITS.size();
	}
	
	public static LinkedList<String> setUpRanks()
	{
		LinkedList<String> ranks = new LinkedList<String>();

		ranks.add("2");
		ranks.add("3");
		ranks.add("4");
		ranks.add("5");
		ranks.add("6");
		ranks.add("7");
		ranks.add("8");
		ranks.add("9");
		ranks.add("10");
		ranks.add("J");
		ranks.add("Q");
		ranks.add("K");
		ranks.add("A");
		
		return ranks;
	}
	public static LinkedList<String> setUpSuits()
	{
		LinkedList<String> suits = new LinkedList<String>();
		
		suits.add("S");
		suits.add("H");
		suits.add("C");
		suits.add("D");
		
		return suits;
	}
	
	public Card(String rank, String suit)
	{
		this.setRank(rank);
		this.setSuit(suit);
	}
	
	public String getRank()
	{
		return this.rank;
	}
	public void setRank(String rank)
	{
		rank = rank.toUpperCase();
		if (RANKS.contains(rank))
			this.rank = rank;
		else
			throw new IllegalArgumentException(rank + " is not an acceptable rank.");
	}
	
	public String getSuit()
	{
		return this.suit;
	}
	public void setSuit(String suit)
	{
		suit = suit.toUpperCase();
		if (SUITS.contains(suit))
			this.suit = suit;
		else
			throw new IllegalArgumentException(suit + " is not an acceptable suit.");
	}
	
	public int compare(Card card)
	{
		int difference = RANKS.indexOf(this.getRank()) - RANKS.indexOf(card.getRank());
		
		if (difference < 0)
		{
			return -1; //This card is smaller
		}
		else if (difference > 0)
			return 1; //This card is bigger
		else
			return 0; //The card is the same rank
	}
	
	public boolean equals(Object o)
	{
		boolean same = false;
		if (o != null)
		{
			if (o.getClass() == this.getClass())
			{
				same = ( ((Card) o).getRank() == this.getRank() ) &&
						( ((Card) o).getSuit() == this.getSuit() );
			}
		}
		return same;
	}
	
	public String toString()
	{
		return this.rank + this.suit;
	}
}
