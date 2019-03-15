package war;

import adts.stack.ListStack;

public class War
{
	private Deck drawDeck;
	private ListStack<Card> kitty;
	private Player player1;
	private Player player2;
	private Card player1LastCard;
	private Card player2LastCard;
	private boolean testing;
	private static final int NUM_CARDS_ON_WAR = 3;
	
	public War()
	{
		this.drawDeck = new Deck();
		this.testing = false;
		this.kitty = new ListStack<Card>();
	}
	
	
	public Deck getDrawDeck()
	{
		if (testing)
			return this.drawDeck;
		else
			return null;
	}
	public Player getPlayer1()
	{
		return this.player1;
	}
	public Player getPlayer2()
	{
		return this.player2;
	}
	
	public boolean enableTesting(boolean on)
	{
		this.testing = on;
		return on;
	}
	
	public static int getNumCardsOnWar()
	{
		return War.NUM_CARDS_ON_WAR;
	}
	
	public boolean isOver()
	{
		return this.getPlayer1().getHandSize() == 0 || this.getPlayer2().getHandSize() == 0;
	}
	
	public void start()
	{
		this.start(true);
	}
	
	public void start(boolean shuffle)
	{
		this.start("Player 1", "Player 2", shuffle);
	}
	
	public void start(String name1, String name2, boolean shuffle)
	{
		if (shuffle)
			this.drawDeck.shuffle();
		this.player1 = new Player(name1);
		this.player2 = new Player(name2);
		
		while (!drawDeck.isEmpty())
		{
			this.player1.pickUp(this.drawDeck.deal());
			this.player2.pickUp(this.drawDeck.deal());
		}
	}
	
	public Card getPlayer1LastCard()
	{
		return this.player1LastCard;
	}
	
	public Card getPlayer2LastCard()
	{
		return this.player2LastCard;
	}
	
	public Player play()
	{
		if (this.getPlayer1().getHandSize() == 0)
			return this.getPlayer2();
		if (this.getPlayer2().getHandSize() == 0)
			return this.getPlayer1();
			
		player1LastCard = this.player1.putDown();
		player2LastCard = this.player2.putDown();
		this.kitty.push(player1LastCard);
		this.kitty.push(player2LastCard);
		Player winner;
		
		switch (player1LastCard.compare(player2LastCard))
		{
			case 1:
				winner = this.player1;
				break;
			case -1:
				winner = this.player2;
				break;
			default: //0
				tie();
				winner = null;
		}
		
		if (winner != null)
			while (!kitty.isEmpty())
				winner.pickUp(kitty.pop());
		
		return winner;
	}
	
	public Player tie()
	{
		for (int i = 0; i < NUM_CARDS_ON_WAR; i++)
		{
			if (this.player1.getHandSize() != 0)
				this.kitty.push(this.player1.putDown());
			else
				return this.player2;
			
			if (this.player2.getHandSize() != 0)
				this.kitty.push(this.player2.putDown());
			else
				return this.player1;
		}
		return null;
	}
	
	public int getKittySize()
	{
		return this.kitty.size();
	}
}
