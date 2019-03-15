package war;

import adts.list.LinkedList;
import adts.queue.ListQueue;

public class Deck
{
	private ListQueue<Card> cards;
	
	public Deck()
	{
		cards = createCardQueue();
	}
	
	private LinkedList<Card> createCardList()
	{
		LinkedList<Card> cardSet = new LinkedList<Card>();
		for (int i = 0; i < Card.getCardSetSize(); i++)
		{
			Card newCard = Card.getNextCard();
			cardSet.add(cardSet.size(), newCard);
		}
		return cardSet;
	}
	private ListQueue<Card> createCardQueue()
	{
		ListQueue<Card> cardSet = new ListQueue<Card>();
		for (int i = 0; i < Card.getCardSetSize(); i++)
		{
			Card newCard = Card.getNextCard();
			cardSet.enqueue(newCard);
		}
		return cardSet;
	}
	
	public Card deal()
	{
		return cards.dequeue();
	}
	
	public void shuffle()
	{
		LinkedList<Card> listCards = new LinkedList<Card>();
		if (cards == null)
			cards = createCardQueue();
		
		while (!cards.isEmpty())
			listCards.add(cards.dequeue()); //Populate listCards with cards' remaining cards.
		
		//java.util.Collections.shuffle(listCards);
		//"It is commonly believed that 7 to 12 shuffles are required on a
        //'new deck order' deck of cards for them to be thoroughly shuffled."
		// - Allan
        int numOfShuffles = (int) (Math.floor(Math.random() * 5) + 7); //Random number between 7 and 12
        for (int k = 0; k < numOfShuffles; k++)
        {
            for (int i = listCards.size() - 1; i >= 0; i--)
            {
                int rand = (int) Math.floor(Math.random() * (i + 1));
                Card temp = listCards.get(i);
                listCards.set(i, listCards.get(rand));
                listCards.set(rand, temp);
            }
        }
        
        for (int i = 0; i < listCards.size(); i++)
        	cards.enqueue(listCards.get(i)); //Repopulate cards in shuffled order
	}
	
	public void testSort(int index, boolean fill, boolean preventWar, String...cardNames)
	{
		if (cardNames.length == 0) //Don't allow method to continue if cardNames is empty
			return;
		
		cards.clear();
		LinkedList<Card> tempCardList = new LinkedList<Card>();
		for (int i = 0; i < cardNames.length; i++)
		{
			String rank = cardNames[i].substring(0, cardNames[i].length() - 1); //All but the last character
			String suit = cardNames[i].substring(cardNames[i].length() - 1); //Last character
			tempCardList.add(new Card(rank, suit));
		}
		
		Card lastCard = null;
		
		for (int i = 0; i < tempCardList.size(); i++)
		{
			cards.enqueue(tempCardList.get(i));
			lastCard = tempCardList.get(i);
		}
		
		if (fill)
		{
			LinkedList<Card> fullCardDeck = createCardList();
			fullCardDeck.removeAll(tempCardList); //Make sure not to allow duplicate cards
			while (!fullCardDeck.isEmpty())
			{
				Card nextCard = fullCardDeck.remove(0);
				if (preventWar)
				{
					if (nextCard.equals(lastCard))
						fullCardDeck.add(fullCardDeck.size(), nextCard);
				}
				else
				{
					cards.enqueue(nextCard);
					lastCard = nextCard;
				}
			}
		}
	}
	
	public int size()
	{
		return cards.size();
	}
	
	public boolean isEmpty()
	{
		return cards.size() <= 0;
	}
	
	public String toString()
	{
		return "The deck is " + cards.size() + " cards thick.";
	}
}
