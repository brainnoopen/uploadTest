
public class NimHumanPlayer extends NimPlayer{
	public void rankInfo() {
		super.rankInfo();
	}
	public int removeStone(int number) {
		if (number > RestStone || number == 0 || number > UpperBound) // check whether input is correct
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
			NimPlayer.Player1Icon = 1;
		return RestStone;
	}
}
