package war;

import adts.queue.ListQueue;

public class Player
{
	private String name;
	private ListQueue<Card> hand;
	
	public Player()
	{
		this("Unknown");
	}
	public Player(String name)
	{
		this.name = name;
		this.hand = new ListQueue<Card>();
	}
	
	public String getName()
	{
		return this.name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	
	public int getHandSize()
	{
		return this.hand.size();
	}
	
	public void pickUp(Card card)
	{
		this.hand.enqueue(card);
	}
	public Card putDown()
	{
		if (!this.hand.isEmpty())
			return this.hand.dequeue();
		else
			return null;
	}
	
	public String toString()
	{
		return this.getName() + " has " + this.getHandSize() + " cards left.";
	}
	
	public boolean equals(Object o)
	{
		boolean same = false;
		if (o != null)
			if (o instanceof Player)
				if (((Player) o).getName().equals(this.getName()) && ((Player) o).getHandSize() == this.getHandSize())
					same = true;
		return same;
	}
}
