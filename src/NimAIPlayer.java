/*
   last edited in 17 May 2018
   The University of Melbourne
   School of Computing and Information Systems
   COMP90041 Programming and Software Development
   Project C
   Copyright Hongming Yi
   Username: HongmingY1
   Student Id:917352
 */

/* This class NimAIPlayer inherited from NimPlayer
 * AI player has his own method to remove stone to ensure win the game
 * */
public class NimAIPlayer extends NimPlayer{
	public void rankInfo() {
		super.rankInfo(); //inherited from NimPlayer
	}
	/* 
	 * the variable 'ls' is to represent the restStone
	 * 'max' is to represent the maximum stone can be removed 
	 * */
	public int removeStone(int ls,int max) {
		int a = (ls-1)/(max+1);
		int nextMove = ls-((max+1)*a+1);// nextMove is a value that the AI player will remove in the next turn
		return nextMove;
	}
	/* 
	 * this method is to detect which player move stone first,
	 * to ensure the AI player win the game 
	 * */
	public int detectFirst(int ls, int max) {
		int a = (ls-1)/(max+1);
		int nextMove = ls-((max+1)*a+1);
		if(nextMove == 0) {
			return 1;
		}else {
			return 0;
		}
	}
}
