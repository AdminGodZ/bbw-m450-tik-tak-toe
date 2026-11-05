package ch.bbw.m450.tictactoe.players;

import static ch.bbw.m450.tictactoe.Boards.board;
import static ch.bbw.m450.tictactoe.TicTacToePlayer.Stone.CROSS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class GreedyPlayerTest {

	private final GreedyPlayer player = new GreedyPlayer();

	@ParameterizedTest(name = "plays {1} on {0}")
	@CsvSource({ "... ... ..., 0", "XO. ... ..., 2", "XOX OXO ..., 6" })
	void playsFirstFreeField(String board, int expectedMove) {
		assertThat(player.play(board(board), CROSS)).isEqualTo(expectedMove);
	}

	@Test
	void cannotPlayOnFullBoard() {
		var board = board("XOX OXO OXO");
		assertThatIllegalStateException().isThrownBy(() -> player.play(board, CROSS));
	}
}
