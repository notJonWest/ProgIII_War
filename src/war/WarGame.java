package war;

import java.util.Scanner;

public class WarGame
{
	public static void main(String[] args)
	{
		War game = new War();
		System.out.println("Welcome to the game of war.");
		System.out.println("The object of the game is to force the other player to run out of cards.");
		System.out.println("All the cards are dealt at the beginning of the game.");
		System.out.println("Each play both players lay the top card of their pile face up. The player with the highest rank card, puts both cards on the bottom of his pile."); 
		System.out.println("If both cards have the same rank, each player plays three cards face down and plays another round.");
		System.out.println("The winner of the tie-breaking round gets all the played cards (the cards in the tie, the six face down and the two in the tie-breaking play.)");
		
		game.start();
		System.out.println("Both hands have been dealt.");
		System.out.println(game.getPlayer1().getName() + " has " + game.getPlayer1().getHandSize() + " cards to start.");
		System.out.println(game.getPlayer2().getName() + " has " + game.getPlayer2().getHandSize() + " cards to start.");
		
		System.out.println("Hit any key to continue or Q to quit.");
		
		Scanner in = new Scanner(System.in);
		
		String input = in.next();
		
		while (!input.equalsIgnoreCase("Q") && !game.isOver())
		{
			Player winner = game.play();
			System.out.print(game.getPlayer1().getName() + " played " + game.getPlayer1LastCard() + ". ");
			System.out.println(game.getPlayer2().getName() + " played " + game.getPlayer2LastCard() + ".");
			
			if (winner == null)
			{
				System.out.println("It's a tie! Each player lays " + War.getNumCardsOnWar() + " cards face down.");
				System.out.println("Kitty has " + game.getKittySize() + " cards.");
			}
			else
				System.out.println(winner.getName() + " won this hand.");
			System.out.println(game.getPlayer1());
			System.out.println(game.getPlayer2());
			
			System.out.println("Hit any key to continue or Q to quit.");
			input = in.next();
		}
		
		if (game.isOver())
		{
			Player winner = game.play();
			System.out.println(winner.getName() + " won the game!");
		}
		
		System.out.println("Thank you for playing!");
		in.close();
	}

}
