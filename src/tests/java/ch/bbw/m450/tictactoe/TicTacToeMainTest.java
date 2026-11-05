package ch.bbw.m450.tictactoe;

import static ch.bbw.m450.tictactoe.Boards.board;
import static ch.bbw.m450.tictactoe.TicTacToePlayer.Stone.CROSS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junitpioneer.jupiter.StdIo;
import org.junitpioneer.jupiter.StdOut;

import ch.bbw.m450.tictactoe.players.GreedyPlayer;
import ch.bbw.m450.tictactoe.players.PerfectPlayer;

class TicTacToeMainTest {

	@ParameterizedTest(name = "detects win on {0}")
	@ValueSource(strings = { "XXX ... ...", "... XXX ...", "... ... XXX", "X.. X.. X..", ".X. .X. .X.", "..X ..X ..X",
			"X.. .X. ..X", "..X .X. X.." })
	void detectsWin(String board) {
		assertThat(TicTacToeMain.isWin(board(board), CROSS)).isTrue();
	}

	@ParameterizedTest(name = "detects no win on {0}")
	@ValueSource(strings = { "... ... ...", "XX. ... ...", "X.X ... ...", "OOO ... ...", "XOX XOO OXX" })
	void detectsNoWin(String board) {
		assertThat(TicTacToeMain.isWin(board(board), CROSS)).isFalse();
	}

	@Test
	void printsStonesAndIndicesOfFreeFields() {
		var printed = TicTacToeMain.toString(board("XO. ... ..O"));
		assertThat(printed).startsWith("\033[1mX\033[0m");
		assertThat(printed.replaceAll("\033\\[\\d+m", "")) // without colors
				.isEqualTo("X  O  2  \n3  4  5  \n6  7  O  \n");
	}

	@Test
	@StdIo
	void announcesWinner(StdOut out) {
		assertThat(TicTacToeMain.play(new GreedyPlayer(), new GreedyPlayer())).isEqualTo(CROSS);
		assertThat(out.capturedString()).endsWith("...and the winner is: CROSS\n");
	}

	@Test
	@StdIo
	void announcesDraw(StdOut out) {
		assertThat(TicTacToeMain.play(new PerfectPlayer(), new PerfectPlayer())).isNull();
		assertThat(out.capturedString()).isEqualTo("it's a draw!\n");
	}

	@Test
	void rejectsSamePlayerTwice() {
		var player = new GreedyPlayer();
		assertThatIllegalArgumentException().isThrownBy(() -> TicTacToeMain.play(player, player))
				.withMessage("players must differ");
	}

	@ParameterizedTest(name = "rejects move to {0}")
	@ValueSource(ints = { -1, 9, 0 }) // 0 is taken by X when O tries to play there
	@StdIo
	void rejectsInvalidMove(int move, StdOut out) {
		var xPlayer = alwaysPlays(move);
		var oPlayer = alwaysPlays(move);
		assertThatIllegalStateException().isThrownBy(() -> TicTacToeMain.play(xPlayer, oPlayer))
				.withMessage("cannot play to position " + move);
		assertThat(out.capturedString()).as("prints the board").isNotBlank();
	}

	@Test
	@StdIo({ "6", "7", "8" })
	void humanCanBeatGreedyPlayer(StdOut out) {
		TicTacToeMain.main(new String[0]);
		assertThat(out.capturedString()).endsWith("...and the winner is: CROSS\n");
	}

	private static TicTacToePlayer alwaysPlays(int move) {
		return (board, color) -> move;
	}
}
