/*
   last edited in 20 May 2018
   The University of Melbourne
   School of Computing and Information Systems
   COMP90041 Programming and Software Development
   Project C
   Copyright Hongming Yi
   Username: HongmingY1
   Student Id:917352
 */

/* This class NimGame is to gave a rule for human player
 * */
import java.util.Scanner;

public class NimGame {
	public static int move = 1; 
	public static int Player1Icon = 0, Player2Icon = 0; // The icon is to show who removes the last stone
	public int RestStone;
	public int TotalStone;
	public int UpperBound;
	public static int flag = 1; // let player1 and player2 take turn to remove stone
	// private NimPlayer player;
	public static NimPlayer player1, player2;
	public static NimAIPlayer aiplayer;

	/* Constructors */
	public NimGame(int stone, int bound, NimPlayer p1, NimPlayer p2) {
		this.TotalStone = stone;
		this.UpperBound = bound;
		this.RestStone = stone;
		this.player1 = p1;
		this.player2 = p2;
	}

	/* Accessors */
	public int getNumCurrentStone() {
		return TotalStone;
	}
	/* Mutators */

//	
	public int rmStone(int move) {
		if (flag == 1) {
			// Invalid input, if next removal greater than reststone or remove equals to
			// zero
			if (move > RestStone || move == 0 || move > UpperBound) // check whether input is correct
			{
				System.out.println();
				if (UpperBound <= RestStone) {
					System.out.println("Invalid move. You must remove between 1 and " + UpperBound + " stones.");
				} else {
					System.out.println("Invalid move. You must remove between 1 and " + RestStone + " stones.");
				}
				System.out.println();
				System.out.print(RestStone + " stones left: ");
				for (int i = 1; i < RestStone; i++)
					System.out.print("* ");
				System.out.print("*\n");
				return RestStone;
			}
			RestStone = RestStone - move;
			if (RestStone > 0) {
				System.out.println();
				System.out.print(RestStone + " stones left: ");
				for (int i = 1; i < RestStone; i++)
					System.out.print("* ");
				System.out.print("*\n");
				flag = flag - 1;
			} else if (RestStone == 0)
				this.Player1Icon = 1;
		} else {
			if (move > RestStone || move == 0 || move > UpperBound) // check whether input is correct
			{
				System.out.println();
				if (UpperBound <= RestStone) {
					System.out.println("Invalid move. You must remove between 1 and " + UpperBound + " stones.");
				} else {
					System.out.println("Invalid move. You must remove between 1 and " + RestStone + " stones.");
				}
				System.out.println();
				System.out.print(RestStone + " stones left: ");
				for (int i = 1; i < RestStone; i++)
					System.out.print("* ");
				System.out.print("*\n");
				return RestStone;
			}
			RestStone = RestStone - move;
			if (RestStone > 0) {
				System.out.println();
				System.out.print(RestStone + " stones left: ");
				for (int i = 1; i < RestStone; i++)
					System.out.print("* ");
				System.out.print("*\n");
				flag = flag + 1;
			} else if (RestStone == 0)
				this.Player2Icon = 1;
		}
		return RestStone;
	}
}
