package war;

import static org.junit.Assert.*;

import org.junit.*;

public class WarTest
{
	@Test
	public void deckInitTest()
	{
		Deck testDeck = new Deck();
		assertEquals("Make sure that the deck size starts at 52 cards.", testDeck.size(), 52);
	}
	
	@Test
	public void deckTest()
	{
		Deck testDeck = new Deck();
		Card drawnCard = testDeck.deal();
		assertEquals("Make sure the deck is unshuffled by checking the first card.", drawnCard, new Card("2", "S"));
		drawnCard = testDeck.deal();
		assertEquals("Make sure the deck is unshuffled by checking the second card.", drawnCard, new Card("3", "S"));
		assertEquals("Make sure deal successfully removes from the deck and changes the size.", testDeck.size(), 50);
	}
	
	@Test
	public void gameTest()
	{
		War game = new War();
		game.enableTesting(true);
		assertEquals("Make sure kitty starts at 0", game.getKittySize(), 0);
		assertEquals("Make sure game deck starts with 52 cards", game.getDrawDeck().size(), 52);
		game.start();
		assertEquals("Make sure each player gets the same amount of cards.", game.getPlayer1().getHandSize(), game.getPlayer2().getHandSize());
		assertEquals("Make sure the amount the players gets is 26", game.getPlayer1().getHandSize(), 26);
	}
	
	@Test
	public void winTest()
	{
		War game = new War();
		game.enableTesting(true);
		game.getDrawDeck().testSort(0, false, false, "KS", "JC");
		game.start(false);
		assertEquals("Make sure player 1 wins.", game.getPlayer1(), game.play());
	}
	
	@Test
	public void continueGameTest()
	{
		War game = new War();
		game.enableTesting(true);
		//                                            p1    p2    p1    p2    p1    p2
		game.getDrawDeck().testSort(0, false, false, "AS", "2H", "KS", "3H", "4H", "QS");
		game.start(false);
		game.play();
		game.play();
		System.out.println(game.getPlayer1());
		System.out.println(game.getPlayer2());
		game.play();
		System.out.println(game.getPlayer1());
		System.out.println(game.getPlayer2());
		assertFalse("Make sure game is not over when player only has 1 card left", game.isOver());
	}
	
	@Test
	public void insufficientCardsTest()
	{
		War game = new War();
		game.enableTesting(true);
		//                                            p1    p2    p1    p2    p1    p2    p1    p2
		game.getDrawDeck().testSort(0, false, false, "AS", "2H", "KS", "3H", "QH", "QS", "7S", "8H");
		//                                           |p1=5 p2=3| |p1=6 p2=2| |   War   | |Into kitty|
		game.start(false);
		game.play();
		game.play();
		game.play();
		assertTrue("Make sure game is over when player does not have enough card left for a war", game.isOver());
		assertEquals("Make sure player 1 won", game.play(), game.getPlayer1());
	}
	
	@Test
	public void manyTies()
	{
		War game = new War();
		game.enableTesting(true);
		//                                           p1    p2    p1    p2    p1    p2    p1    p2    p1    p2    p1    p2    p1    p2    p1    p2    p1    p2    p1    p2    p1    p2    p1    p2    p1    p2
		game.getDrawDeck().testSort(0, true, false, "AS", "AH", "2S", "3H", "3S", "2H", "4S", "5H", "KS", "KH", "2C", "3D", "3C", "2D", "4C", "5D", "QH", "QS", "7S", "8H", "8S", "7H", "9S", "6H", "6S", "9H");
		//                                          |   War   | |             Into kitty          | |   War   | |              Into kitty         | |   War   | |              Into kitty         | |   War   |
		game.start(false);
		assertEquals("make sure wars are happening properly and kitty is properly used.", game.getKittySize(), 0);
		game.play();
		assertEquals("make sure wars are happening properly and kitty is properly used.", game.getKittySize(), 8);
		game.play();
		assertFalse("Test to make sure tests are not just being faulty (Should be 16).", game.getKittySize() == 15);
		assertEquals("make sure wars are happening properly and kitty is properly used.", game.getKittySize(), 16);
		game.play();
		assertEquals("make sure wars are happening properly and kitty is properly used.", game.getKittySize(), 24);
		game.play(); //At this point player 2 won the war
		assertEquals("make sure wars are happening properly and kitty is properly used.", game.getKittySize(), 0);
	}
	
}
