package ch.bbw.m450.tictactoe.players;

import static ch.bbw.m450.tictactoe.Boards.board;
import static ch.bbw.m450.tictactoe.TicTacToePlayer.Stone.CIRCLE;
import static ch.bbw.m450.tictactoe.TicTacToePlayer.Stone.CROSS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import ch.bbw.m450.tictactoe.TicTacToeMain;
import ch.bbw.m450.tictactoe.TicTacToePlayer.Stone;

class PerfectPlayerTest {

	private final PerfectPlayer player = new PerfectPlayer();

	@ParameterizedTest(name = "{1} plays {2} on {0}")
	@CsvSource({
			"XX. OO. ..., CROSS, 2", // win instead of blocking
			"OO. X.. ..X, CROSS, 2", // block the opponent
			"XX. .O. ..., CIRCLE, 2", // block the opponent
			"X.. ... ..., CIRCLE, 4" // only the center avoids a loss
	})
	void findsBestMove(String board, Stone color, int expectedMove) {
		assertThat(player.play(board(board), color)).isEqualTo(expectedMove);
	}

	@Test
	void beatsGreedyPlayer() {
		assertThat(TicTacToeMain.play(player, new GreedyPlayer())).isEqualTo(CROSS);
		assertThat(TicTacToeMain.play(new GreedyPlayer(), player)).isEqualTo(CIRCLE);
	}

	@Test
	void cannotPlayOnFullBoard() {
		var board = board("XOX OXO OXO");
		assertThatIllegalStateException().isThrownBy(() -> player.play(board, CROSS));
	}
}
