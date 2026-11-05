package ch.bbw.m450.tictactoe.players;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

import ch.bbw.m450.tictactoe.TicTacToeMain;
import ch.bbw.m450.tictactoe.TicTacToePlayer;

/**
 * Unbeatable player using the minimax algorithm: it tries every possible continuation of the game.
 */
public class PerfectPlayer implements TicTacToePlayer {

	@Override
	public int play(Stone[] board, Stone colorToPlay) {
		return freeFields(board).boxed()
				.max(Comparator.comparingInt(move -> score(board, move, colorToPlay)))
				.orElseThrow(() -> new IllegalStateException("cannot play at all"));
	}

	/**
	 * @return 1 if the move wins, 0 if it leads to a draw and -1 if it loses (assuming both play perfectly)
	 */
	private static int score(Stone[] board, int move, Stone color) {
		var next = Arrays.copyOf(board, board.length);
		next[move] = color;
		if (TicTacToeMain.isWin(next, color)) {
			return 1;
		}
		// the opponent picks its best move, which is the worst one for us
		return -freeFields(next).map(opponentMove -> score(next, opponentMove, color.opponent()))
				.max()
				.orElse(0); // board is full: draw
	}

	private static IntStream freeFields(Stone[] board) {
		return IntStream.range(0, TicTacToeMain.BOARD_SIZE)
				.filter(i -> board[i] == null);
	}
}
