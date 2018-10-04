/*
   last edited in 19 May 2018
   The University of Melbourne
   School of Computing and Information Systems
   COMP90041 Programming and Software Development
   Project A
   Copyright Hongming Yi
   Username: HongmingY1
   Student Id:917352
 */
import java.util.Scanner;
/*
 * This class is used to create 
 * human player's name and the method for removing stone
 */
public class NimPlayer {
	public static int move = 1; 
	public static int Player1Icon = 0, Player2Icon = 0; // The icon is to show who removes the last stone
	public int RestStone;
	public int TotalStone;
	public int UpperBound;
	public static int flag = 1; // let player1 and player2 take turn to remove stone	
	
	//This is to define the player's username, given name, family name, number of games 
	//and numbers of winning
	private String userName;
	private String givenName;
	private String familyName;
	private int numOfGames;
	private int numOfWinGames;
	private double ratio = 0 ;
	/* Constructors */
	public NimPlayer() {
		userName = "abc";
		givenName = null;
		familyName = null;
		numOfGames = 0;
		numOfWinGames = 0;
	}
	/* Accessors */
	public String getUserName() {
		return userName;
	}
	public String getGivenName() {
		return givenName;
	}
	public String getFamilyName() {
		return familyName;
	}
	public int getNumOfGames() {
		return numOfGames;
	}
	public double getRation() {
		return ratio = 100*(this.numOfWinGames/(double)(this.numOfGames));
	}
	public int getNumOfWinGames() {
		return numOfWinGames;
	}
	
	//This method is used for removing stone
	public int removeStone(Scanner k) {
		int removeNumber = k.nextInt();
		System.out.println();
		return removeNumber;
	}
	
	/* Mutators */
	public void setNewUserName(String inputUserName) {
		this.userName = inputUserName;
	}
	public void setNewGivenName(String inputGivenName) {
		this.givenName = inputGivenName;
	}
	public void setNewFamilyName(String inputFamilyName) {
		this.familyName = inputFamilyName;
	}
	public void setNumberiOfGames( int numberGames) {
		this.numOfGames = numberGames;
	}
	public void setNumberOfWinGames(int Winnumber) {
		this.numOfWinGames = Winnumber;
	}
	public void setRatio(double tempRatio) {
		this.ratio = tempRatio;
	}
	public void removeInfo() {
		userName = null;
		givenName = null;
		familyName = null;	
	}
	public void addInfo(String UserName, String FamilyName, String GivenNmae) {
		this.userName = UserName;
		this.familyName = FamilyName;
		this.givenName = GivenNmae;
	}
	public void rankInfo() {
		if(this.numOfGames == 0) {
			System.out.println("0%   " +"| " + "00 games " + "| " + this.givenName + " " + this.familyName);
		}
		else {
			 this.ratio = 100*(this.numOfWinGames/(double)(this.numOfGames));
			 //String r = String.format("%.2f",3.1415926);
			if(ratio == 100) {
				if(this.numOfGames < 10) {
					System.out.println((int)(Math.round(this.ratio)) + "%" +" | 0" + this.numOfGames + " games " + "| " + this.givenName + " " + this.familyName);
				}else {
					System.out.println((int)(Math.round(this.ratio)) + "%" +" | " + this.numOfGames + " games " + "| " + this.givenName + " " + this.familyName);
				}
			}else if(ratio < 100 && ratio >= 10) {
				if(this.numOfGames < 10) {
					System.out.println((int)(Math.round(this.ratio)) + "%" +"  | 0" + this.numOfGames + " games " + "| " + this.givenName + " " + this.familyName);
				}else {
					System.out.println((int)(Math.round(this.ratio)) + "%" +"  | " + this.numOfGames + " games " + "| " + this.givenName + " " + this.familyName);
				}
			}else if(ratio < 10) {
				if(this.numOfGames < 10) {
					System.out.println((int)(Math.round(this.ratio)) + "%" +"   | 0" + this.numOfGames + " games " + "| " + this.givenName + " " + this.familyName);
				}else {
					System.out.println((int)(Math.round(this.ratio)) + "%" +"   | " + this.numOfGames + " games " + "| " + this.givenName + " " + this.familyName);
				}
			}
		}
	}
	public String displayInfo() {
			return (this.userName+","+this.givenName+ "," +this.familyName + 
				"," +this.numOfGames + " games,"+ this.numOfWinGames + " wins" ) ;
		
	}
	
}
