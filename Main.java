
public class Main {

	public static void main(String[] args) {
		Board board = new Board();
		board.init();

		// 0 1 2
		// 3 4 5
		// 6 7 8

		try {
			board.play(Board.Player.ONE, 0);
			board.play(Board.Player.TWO, 1);
			board.play(Board.Player.ONE, 3);
			board.play(Board.Player.TWO, 2);
			board.play(Board.Player.ONE, 6);
			board.play(Board.Player.TWO, 2);

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

	}

}
