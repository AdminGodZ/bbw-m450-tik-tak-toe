package ch.bbw.m450.tictactoe;

import static ch.bbw.m450.tictactoe.TicTacToePlayer.Stone.CROSS;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import ch.bbw.m450.tictactoe.TicTacToePlayer.Stone;

class TicTacToeMainTest {

    @ParameterizedTest(name = "detects win at positions {0}")
    @MethodSource("winningPositions")
    void detectsWin(int[] positions) {
        var board = new Stone[TicTacToeMain.BOARD_SIZE];
        for (int position : positions) {
            board[position] = CROSS;
        }

        assertThat(TicTacToeMain.isWin(board, CROSS)).isTrue();
    }

    static int[][] winningPositions() {
        return new int[][] {
                { 0, 1, 2 },
                { 3, 4, 5 },
                { 6, 7, 8 },
                { 0, 3, 6 },
                { 1, 4, 7 },
                { 2, 5, 8 },
                { 0, 4, 8 },
                { 2, 4, 6 }
        };
    }
}
