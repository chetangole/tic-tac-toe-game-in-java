import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Board {

	// 0 1 2
	// 3 4 5
	// 6 7 8

	String[] board = new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8" };
	Set<Integer> validPositions = new HashSet<Integer>();

	State gameState = State.STOPPED;
	Player whosNext;
	Player whoWon;

	public void init() {
		gameState = State.STARTED;
		whosNext = Player.ONE;
		whoWon = null;
		validPositions.addAll(Arrays.asList(new Integer[] { 0, 1, 2, 3, 4, 5, 6, 7, 8 }));
		System.out.println("Board Initilized");
		printBoard();
	}

	enum State {
		STARTED, STOPPED, OVER;
	}

	enum Player {
		ONE("X"), TWO("O");

		private String symbol;

		Player(String symbol) {
			this.symbol = symbol;
		}

		public String getSymbol() {
			return symbol;
		}
	}

	public void play(Player player, int positionOnBoard) throws Exception {
		System.out.println(player.name() + " is playing for position " + positionOnBoard);

		if (gameState.equals(State.STOPPED) || gameState.equals(State.OVER)) {
			throw new Exception("Game is " + gameState);
		}

		if (!whosNext.equals(player)) {
			throw new Exception("Next turn is of " + whosNext);
		}

		if (!validPositions.contains(positionOnBoard)) {
			throw new Exception("Invalid position " + positionOnBoard);
		} else {
			validPositions.remove(positionOnBoard);
		}

		makeMove(player, positionOnBoard);

		printBoard();

		if (null != checkWinner(player, positionOnBoard)) {
			gameState = State.OVER;
			whoWon = player;
			throw new Exception("Winner is " + whoWon);
		}

	}

	private void printBoard() {
		int i = 0;
		for (String symbol : board) {
			System.out.print(symbol + " ");

			if (i == 2 || i == 5 || i == 8) {
				System.out.println("");
			}
			i = i + 1;
		}
	}

	private Player checkWinner(Player player, Integer position) {

		switch (position) {
		case 0:
			if ((board[0] == board[1] && board[1] == board[2]) || (board[0] == board[3] && board[3] == board[6])
					|| (board[0] == board[4] && board[4] == board[8])) {
				return player;
			}
			break;
		case 1:
			if ((board[0] == board[1] && board[1] == board[2]) || (board[1] == board[4] && board[4] == board[7])) {
				return player;
			}
			break;

		case 2:
			if ((board[0] == board[1] && board[1] == board[2]) || (board[2] == board[5] && board[5] == board[8])
					|| (board[2] == board[4] && board[4] == board[6])) {
				return player;
			}
			break;
		case 3:
			if ((board[0] == board[3] && board[3] == board[6]) || (board[3] == board[4] && board[4] == board[5])) {
				return player;
			}
			break;
		case 4:
			if ((board[1] == board[4] && board[4] == board[7]) || (board[3] == board[4] && board[4] == board[5])) {
				return player;
			}
			break;
		case 5:
			if ((board[2] == board[5] && board[5] == board[8]) || (board[3] == board[4] && board[4] == board[5])) {
				return player;
			}
			break;
		case 6:
			if ((board[0] == board[3] && board[3] == board[6]) || (board[6] == board[7] && board[7] == board[8])
					|| (board[7] == board[5] && board[5] == board[3])) {
				return player;
			}
			break;
		case 7:
			if ((board[6] == board[7] && board[7] == board[8]) || (board[1] == board[4] && board[4] == board[7])) {
				return player;
			}
			break;
		case 8:
			if ((board[6] == board[7] && board[7] == board[8]) || (board[2] == board[5] && board[5] == board[9])
					|| (board[0] == board[4] && board[4] == board[8])) {
				return player;
			}
			break;
		}

		return null;

	}

	private void makeMove(Player player, Integer position) {
		board[position] = player.getSymbol();
		switch (player) {
		case ONE:
			whosNext = Player.TWO;
			break;
		case TWO:
			whosNext = Player.ONE;
			break;
		default:
			break;
		}
	}

}
