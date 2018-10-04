
/*
   last edited in 19 May 2018
   The University of Melbourne
   School of Computing and Information Systems
   COMP90041 Programming and Software Development
   Project C
   Copyright Hongming Yi
   Username: HongmingY1
   Student Id:917352
 */
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;


/*
 * This class Nimsys is used to manage the game playing process
 */
public class Nimsys {
	static int count = 0; // to record the number of players
	static int sign1 = 0; // record in command 'remove', the user is not exist
	static int sign2 = 0; // record in command 'edit', the user is not exist
	static int sign3 = 0; // record in command 'reset', the user is not exist
	static int sign4 = 0; // record in command 'display', the user is not exist
	static String userInputName;
	static String userGivenName;
	static String userFamilyName;
	static String userInputName2;
	static int inialStone; // to pass the value to NimGame
	static int upperBound;
	static int flag1 = 0;
	static int flag2 = 0;
	static int p1 = 0;
	static int p2 = 0;
	static NimPlayer[] player = new NimPlayer[100]; //create a array is the type of NimPlayer
	static Boolean determineUser = false; // to determine whether the user is exist or not

	/* This method is used to add player by the user input
	 * add HumanPlayer and add AI player both call this method
	 * the catchCommand is a array that catch the argument inputed by user
	 * storePlayer is a NimPlayer class to store every new player information and pass it to array player[]
	 * */
	public static boolean addplayer(String[] catchCommand, NimPlayer storePlayer) {
		// tempName is a array used to store the user name, given name, family name
		String[] tempName = catchCommand[1].split(","); // when determine the command is 'addplayer',
		// the information must be three strings split by comma,so using split method to
		// split three parts into(first) (jr) (smith)
		
		userInputName = tempName[0]; // e.g username = first
		userFamilyName = tempName[1]; // e.g family_name = jr
		userGivenName = tempName[2]; // e.g given_name = smith
		
		for (int i = 0; i < player.length; i++) {
			if (player[i] != null) {
				if (userInputName.equals(player[i].getUserName())) {
					System.out.println("The player already exists.\n"); // if finding the same username in array
					determineUser = true;
					break;
				}
			}
		}
		if (determineUser == true) {
			return determineUser; // to determine whether the user is exist or not
		} else {
			if(catchCommand[0].equals("addplayer")) {
				storePlayer.addInfo(userInputName, userFamilyName, userGivenName); // Syntax: username, family_name, given_name
				player[count] = storePlayer; // pass the information to player[0], player[1]...
				count++;
				System.out.println();
				return determineUser;
			}else{
				NimAIPlayer ai = new NimAIPlayer();
				ai.addInfo(userInputName, userFamilyName, userGivenName);
				player[count] = ai;
				count++; 
				System.out.println();
				return determineUser;
			}
		}

	}

	/* this method is used to edit player by the user input */
	public static void editplayer(String[] catchCommand) {
		String[] tempName = catchCommand[1].split(","); // when determine the command is 'editplayer',
		// the information must be three strings split by comma,so using split method to
		// split three parts into(first) (jr) (smith)
		userInputName = tempName[0];
		userFamilyName = tempName[1]; // userFamilyName
		userGivenName = tempName[2]; // userGivenName
		for (int i = 0; i < count; i++) {
			if (player[i] != null) {
				if (player[i].getUserName().equals(userInputName)) {
					player[i].setNewGivenName(userGivenName);
					player[i].setNewFamilyName(userFamilyName);
					sign2 = 0;
					break;
				} else
					sign2 = 1;
			}
		}
		if (sign2 == 1)
			System.out.println("The player does not exist.");
		System.out.println();
	}

	/* this method is used to display all the player information
	 * userInfo is the information after the command
	 * */
	public static void displayplayer(String userInfo) {
		// if the information after the command is not null, then showing that information is username
		if (userInfo != null) {
			for (int i = 0; i < player.length; i++) {
				if (player[i] != null) {
					if (player[i].getUserName().equals(userInfo)) {
						System.out.println(player[i].displayInfo());
						sign4 = 0;
						break;
					} else
						sign4 = 1;
				}
			}
			if (sign4 == 1)
				System.out.println("The player does not exist.");
			System.out.println(); // give a blank line before the new command
		} else if (userInfo == null) {
			Order(player); // firstly, order the player by their username
			for (int i = 0; i < player.length; i++) {
				if (player[i] != null) {
					System.out.println(player[i].displayInfo()); // display username, givenname, familyname
				}
			}
			System.out.println();// give a blank line before the new command
		}
	}

	public static void resetplayer(String userInfo, Scanner keyboard) {
		if (userInfo != null) {
			for (int i = 0; i < player.length; i++) {
				if (player[i] != null) {
					if (player[i].getUserName().equals(userInfo)) {
						player[i].setNumberiOfGames(0);
						player[i].setNumberOfWinGames(0);
						sign3 = 0;
						break;
					} else
						sign3 = 1;
				}
			}
			if (sign3 == 1)
				System.out.println("The player does not exist.");
			System.out.println(); // give a blank line before the new command
		} else if (userInfo == null) {
			System.out.println("Are you sure you want to reset all player statistics? (y/n)");
			String a = keyboard.nextLine();
			if (a.equals("y")) {
				for (int i = 0; i < player.length; i++) {
					if (player[i] != null) {
						player[i].setNumberiOfGames(0);
						player[i].setNumberOfWinGames(0);
					}

				}
			}
			System.out.println(); // give a blank line before the new command
		}
	}

	public static void removeplayer(String userInfo, Scanner keyboard) {
		if (userInfo != null) {
			for (int i = 0; i < player.length; i++) {
				if (player[i] != null) {
					if (player[i].getUserName().equals(userInfo)) {
						player[i] = null;
						sign1 = 0;
						break;
					} else
						sign1 = 1;
				}
			}
			if (sign1 == 1)
				System.out.println("The player does not exist.");
			System.out.println();
		} else if (userInfo == null) {
			System.out.println("Are you sure you want to remove all players? (y/n)");
			String a = keyboard.nextLine();
			if (a.equals("y")) {
				for (int i = 0; i < player.length; i++) {
					if (player[i] != null)
						player[i] = null;
				}
			}
			System.out.println();
		}
	}

	public static void startgame(String[] catchCommand, Scanner keyboard) throws IOException {
		String[] tempGameInfo = catchCommand[1].split(","); // when determine startgame,
		// then the information muset be four strings,so using split method to split
		// three parts into(10) (3) (first) (second)
		inialStone = Integer.parseInt(tempGameInfo[0]); // e.g(10)
		upperBound = Integer.parseInt(tempGameInfo[1]); // e.g(3)
		userInputName = tempGameInfo[2]; // e.g(first)
		userInputName2 = tempGameInfo[3]; // e.g(second)
		for (int i = 0; i < count; i++) {
			if (player[i] != null) {
				// if the username of player1 can be found in player[],return flag1,and the
				// location in player[], p1
				if (player[i].getUserName().equals(userInputName)) {
					flag1 = 1;
					p1 = i;
				}
				// if the username of player2 can be found in player[],return flag1,and the
				// location in player[], p2
				if (player[i].getUserName().equals(userInputName2)) {
					flag2 = 1;
					p2 = i;
				}
			}
		}
		//
		if (flag1 == 1 && flag2 == 1) {
			flag1 = 0;
			flag2 = 0;
			NimGame newGame = new NimGame(inialStone, upperBound, player[p1], player[p2]);

			System.out.println();
			System.out.println("Initial stone count: " + newGame.TotalStone);
			// SetUpperBound
			System.out.println("Maximum stone removal: " + newGame.UpperBound);

			// display play1's name
			System.out.println("Player 1: " + newGame.player1.getGivenName() + " " + newGame.player1.getFamilyName());
			// display play2's name
			System.out.println("Player 2: " + newGame.player2.getGivenName() + " " + newGame.player2.getFamilyName());
			System.out.println();

			System.out.print(newGame.TotalStone + " stones left: ");
			for (int i = 1; i < newGame.TotalStone; i++)
				System.out.print("* ");
			System.out.print("*");
			System.out.println();
			newGame.flag = 1;
			if(newGame.player1 instanceof NimAIPlayer) {
				int c = ((NimAIPlayer) newGame.player1).detectFirst(newGame.TotalStone, upperBound);
				if(c == 1) {
					newGame.flag=0;
				}
			}else if(newGame.player2 instanceof NimAIPlayer) {
				int c = ((NimAIPlayer) newGame.player2).detectFirst(newGame.TotalStone, upperBound);
				if(c == 0) {
					newGame.flag=0;
				}
			}
			while (newGame.RestStone != 0) {
				if (newGame.flag == 1) {
					System.out.println(newGame.player1.getGivenName() + "'s turn " + "- remove how many?");
					if(newGame.player1 instanceof NimAIPlayer) {
						newGame.rmStone(((NimAIPlayer) newGame.player1).removeStone(newGame.RestStone,upperBound));
					}else{
						newGame.rmStone(keyboard.nextInt());
					}
					//humanplayer.move();

				} else {
					System.out.println(newGame.player2.getGivenName() + "'s turn " + "- remove how many?");
					if(newGame.player2 instanceof NimAIPlayer) {
						newGame.rmStone(((NimAIPlayer) newGame.player2).removeStone(newGame.RestStone,upperBound));
					}else{
						newGame.rmStone(keyboard.nextInt());
					}
					//AIPlayer.move();

				}
			}
			newGame.player2.setNumberiOfGames(newGame.player2.getNumOfGames() + 1);
			newGame.player1.setNumberiOfGames(newGame.player1.getNumOfGames() + 1);
			if (newGame.Player1Icon == 1) {
				System.out.println();
				System.out.println("Game Over");
				System.out.println(newGame.player2.getGivenName() + " " + newGame.player2.getFamilyName() + " wins!\n");
				newGame.player2.setNumberOfWinGames(newGame.player2.getNumOfWinGames() + 1);// if player2 win, then add
																							// the winning count of
				// player2
				// System.out.println(player2.getNumOfWinGames());
				// break;

			} else if (newGame.Player2Icon == 1) {
				System.out.println();
				System.out.println("Game Over");
				System.out.println(newGame.player1.getGivenName() + " " + newGame.player1.getFamilyName() + " wins!\n");
				newGame.player1.setNumberOfWinGames(newGame.player1.getNumOfWinGames() + 1);// if player1 win, then add
																							// the winning count of
				// player1
				// System.out.println(player1.getNumOfWinGames());
				// break;

			}
			player[p1] = newGame.player1;
			player[p2] = newGame.player2;
		} else
			System.out.println("One of the players does not exist.");
	}
	/* This method is to read the file from disk before the game start*/
	public static void beforeGame() throws IOException {
		File f = new File("players.dat");
		if(!f.exists()) {
			return;
		}
		FileReader file = new FileReader("players.dat");
		BufferedReader br = new BufferedReader(file); // br is a instance of BufferedReader class
		int x = 0;
		while(br.ready()) {
			String ppl = br.readLine(); //ppl(player per line) is to indicate the message in each line from the file
			String[] aiSign = ppl.split(";");// use a method to distinguish AI player and Human player
			//when store the AI player, add a sign'AI' and plus semicolon before the username of AI player
			//e.g(AI player: (AI;t,t,t,0 wins, 0 games)), (human player a,b,c, 0 wins, 0 games)
			if(!aiSign[0].equals("AI")) {
				String[] personInfo = aiSign[0].split(",");
				String[] temp = aiSign[1].split(" ");
				int numberGame = Integer.parseInt(temp[0]);
				temp = aiSign[2].split(" ");
				int numberWin = Integer.parseInt(temp[0]);
				NimPlayer a = new NimPlayer();
				a.setNewFamilyName(personInfo[2]);
				a.setNewGivenName(personInfo[1]);
				a.setNewUserName(personInfo[0]);
				a.setNumberiOfGames(numberGame);
				a.setNumberOfWinGames(numberWin);
				player[x] = a;
				x++;
			}else {
				String[] personInfo = aiSign[1].split(",");
				String[] temp = aiSign[2].split(" ");
				int numberGame = Integer.parseInt(temp[0]);
				temp = aiSign[3].split(" ");
				int numberWin = Integer.parseInt(temp[0]);
				NimAIPlayer a = new NimAIPlayer();
				a.setNewFamilyName(personInfo[2]);
				a.setNewGivenName(personInfo[1]);
				a.setNewUserName(personInfo[0]);
				a.setNumberiOfGames(numberGame);
				a.setNumberOfWinGames(numberWin);
				player[x] = a;
				x++;
			}
		}
	}
	public static void ranking(String userInfo) {
		ratioOrder(player);
		if (userInfo != null) {
			if (userInfo.equals("asc")) {
				for (int i = player.length - 1; i >= 0; i--) {
					if (player[i] != null) {
						player[i].rankInfo();
					}

				}
				System.out.println(); // give a blank line before the new command
			}
			if (userInfo.equals("desc")) {
				for (int i = 0; i < player.length; i++) {
					if (player[i] != null) {
						player[i].rankInfo();
					}
				}
				System.out.println();
			}
		} else {
			for (int i = 0; i < player.length; i++) {
				if (player[i] != null) {
					player[i].rankInfo();
				}
			}
			System.out.println();
		}
	}

	// this method is to order username by alphabetically
	public static NimPlayer[] Order(NimPlayer[] comparePlayer) {
		NimPlayer temp = new NimPlayer();
		for (int i = 0; i < comparePlayer.length; i++)
			for (int j = i + 1; j < comparePlayer.length; j++) {
				if (comparePlayer[i] != null && comparePlayer[j] != null) {
					boolean countPlayer1 = false;
					boolean countPlayer2 = false;
					if (comparePlayer[i].getUserName().length() >= comparePlayer[j].getUserName().length()) {
						for (int a = 0; a < comparePlayer[j].getUserName().length(); a++) {
							if (comparePlayer[i].getUserName().charAt(a) > comparePlayer[j].getUserName().charAt(a)) {
								countPlayer1 = true;
								break;
							}
							if (comparePlayer[i].getUserName().charAt(a) < comparePlayer[j].getUserName().charAt(a)) {
								countPlayer2 = true;
								break;
							}
						}
					} else if (comparePlayer[i].getUserName().length() < comparePlayer[j].getUserName().length()) {
						for (int a = 0; a < comparePlayer[i].getUserName().length(); a++) {
							if (comparePlayer[i].getUserName().charAt(a) > comparePlayer[j].getUserName().charAt(a)) {
								countPlayer1 = true;
								break;
							}
							if (comparePlayer[i].getUserName().charAt(a) < comparePlayer[j].getUserName().charAt(a)) {
								countPlayer2 = true;
								break;
							}
						}
					}
					if (countPlayer1 == true) {
						temp = comparePlayer[i];
						comparePlayer[i] = comparePlayer[j];
						comparePlayer[j] = temp;
					}
				}
			}
		return comparePlayer;
	}

	// this method is used to rank the ratio, if the ration is the same between two
	// players, then rank by their username
	public static NimPlayer[] ratioOrder(NimPlayer[] comparePlayer) {
		NimPlayer temp = new NimPlayer();
		for (int i = 0; i < comparePlayer.length; i++)
			for (int j = i + 1; j < comparePlayer.length; j++) {
				if (comparePlayer[i] != null && comparePlayer[j] != null) {
					if (comparePlayer[j].getRation() > comparePlayer[i].getRation()) {
						temp = comparePlayer[i];
						comparePlayer[i] = comparePlayer[j];
						comparePlayer[j] = temp;
					} else if (comparePlayer[j].getRation() == comparePlayer[i].getRation()) {
						Order(comparePlayer);
					}
				}
			}
		return comparePlayer;
	}

	public static void main(String[] args) throws IOException {
		System.out.println("Welcome to Nim");
		System.out.println();
		beforeGame();
		for(int i = 0; i < player.length; i++) {
			if(player[i] == null) {
				count = i;
				break;
			}
		}
		int flag = 1;
		Scanner keyboard = new Scanner(System.in);
		System.out.print("$");
		
		while (flag == 1) {
			NimPlayer storePlayer = new NimPlayer(); // a NimPlayer class to store every new player information and pass it to array player[]
			// catchString is to record the information from user input
			String catchString = keyboard.nextLine(); // command from user input, listing the sentence given by user
			if (catchString.equals(" ") || catchString == null || catchString.equals("")) {
				continue;
			}
			// catchCommand is an array used to catch command from user
			String[] catchCommand = catchString.split(" "); // split a sentence inputed by user into two parts
			// such as (add first,jr,smith) becomes (add) and (first,jr,smith)
			String command = catchCommand[0]; // using comand to read 'comand' given by user,e.g(addplayer,removeplayer)
			String userInfo = null; // the information after the command
			if (catchString.contains(" ")) {
				userInfo = catchCommand[1]; // the information after the command
			}
			try {
				// case1 command = addplayer
				if (command.equals("addplayer") || command.equals("addaiplayer")) {
					if(catchCommand[1].split(",").length != 3) {
						throw new Exception("Incorrect number of arguments supplied to command.");
					}
					addplayer(catchCommand, storePlayer); // addplayer by this method
					if (determineUser == true) {
						System.out.print("$");
						continue; // if finding the same username in array player[], then skip saving and do a new
									// loop
					}
				}
				// case2 command = displayplayer
				else if (command.equals("displayplayer")) {
					//String tempDisplay = 
					displayplayer(userInfo);
				}
				// case3 command = reset //Syntax: resetstats [username]
				else if (command.equals("resetstats")) {
					resetplayer(userInfo, keyboard);
				}
				// case4 command = remove //Syntax: removeplayer [username]
				else if (command.equals("removeplayer")) {
					removeplayer(userInfo, keyboard);
				}
				// case5 command = editplayer // Syntax: editplayer username, new_family_name,
				// new_given_name
				else if (command.equals("editplayer")) {
					if(catchCommand[1].split(",").length != 3) {
						throw new Exception("Incorrect number of arguments supplied to command.");
					}
					editplayer(catchCommand);
				}
				// case6 command = startgame
				else if (command.equals("startgame")) {
					startgame(catchCommand, keyboard);
				}
				// case7 command = ranking
				else if (command.equals("rankings")) {
					ranking(userInfo);
				}
				else if (command.equals("exit")) {
					
					PrintWriter outputStream = null;
					try {
						outputStream = new PrintWriter(new FileOutputStream("players.dat"));
					}
					catch(FileNotFoundException e) {
						System.out.println("Error opening the file players.dat");
						System.exit(0);
					}
					
					for(int i = player.length - 1; i >= 0; i--) {
						if (player[i] != null) {
							boolean a = (player[i] instanceof NimAIPlayer);
							if(!a) {
								outputStream.println(player[i].getUserName()+","+player[i].getGivenName()+ "," +player[i].getFamilyName() + 
										";" +player[i].getNumOfGames() + " games;"+ player[i].getNumOfWinGames() + " wins" );
							}else {
								outputStream.println("AI;"+player[i].getUserName()+","+player[i].getGivenName()+ "," +player[i].getFamilyName() + 
										";" +player[i].getNumOfGames() + " games;"+ player[i].getNumOfWinGames() + " wins" );
							}
					}
					}
					outputStream.close();
					System.out.println();
					System.exit(0);
				
					}
				else throw new Exception( "'" + command + "'" + " is not a valid command.");
			}
			catch(Exception e)
	        {
	            String message = e.getMessage( );
	            System.out.println(message);
	            System.out.println();
	            //System.exit(0);
	        }
			
			System.out.print("$");
		}

	} 

  } 
//}

