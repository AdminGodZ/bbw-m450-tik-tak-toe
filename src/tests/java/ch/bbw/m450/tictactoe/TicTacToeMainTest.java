package ch.bbw.m450.tictactoe;

import static ch.bbw.m450.tictactoe.TicTacToePlayer.Stone.CROSS;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class TicTacToeMainTest {

    @Test
    void detectsHorizontalWin() {
        var board = new TicTacToePlayer.Stone[] {
                CROSS, CROSS, CROSS,
                null, null, null,
                null, null, null
        };

        assertThat(TicTacToeMain.isWin(board, CROSS)).isTrue();
    }

    @Test
    void detectsDiagonalWin() {
        var board = new TicTacToePlayer.Stone[] {
                null, null, CROSS,
                null, CROSS, null,
                CROSS, null, null
        };

        assertThat(TicTacToeMain.isWin(board, CROSS)).isTrue();
    }
}
