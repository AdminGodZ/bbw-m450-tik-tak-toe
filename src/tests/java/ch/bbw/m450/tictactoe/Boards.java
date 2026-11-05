package ch.bbw.m450.tictactoe;

import ch.bbw.m450.tictactoe.TicTacToePlayer.Stone;

/**
 * Test helper to write boards in a readable way.
 */
public final class Boards {

	private Boards() {
	}

	/**
	 * @param rows the fields row by row, e.g. "XO. ... ..X" where '.' is an empty field (spaces are ignored)
	 */
	public static Stone[] board(String rows) {
		var fields = rows.replace(" ", "");
		var board = new Stone[TicTacToeMain.BOARD_SIZE];
		for (var i = 0; i < board.length; i++) {
			board[i] = switch (fields.charAt(i)) {
				case 'X' -> Stone.CROSS;
				case 'O' -> Stone.CIRCLE;
				default -> null;
			};
		}
		return board;
	}
}
