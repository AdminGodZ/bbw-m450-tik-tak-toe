package ch.bbw.m450.tictactoe.players;

import static ch.bbw.m450.tictactoe.Boards.board;
import static ch.bbw.m450.tictactoe.TicTacToePlayer.Stone.CIRCLE;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junitpioneer.jupiter.StdIo;
import org.junitpioneer.jupiter.StdOut;

class HumanPlayerTest {

	@Test
	@StdIo("4")
	void playsMoveFromInput(StdOut out) {
		assertThat(new HumanPlayer().play(board("X.. ... ..."), CIRCLE)).isEqualTo(4);
		assertThat(out.capturedString()).contains("where to put the next CIRCLE? (0-8)");
	}
}
