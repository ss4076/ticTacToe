package com.acme.tictactoe.model;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

/**
 * There are a lot more tests we can and should write but for now, just a few smoke tests.
 */
public class TicTacToeTests {

    private Board board;

    @Before
    public void setup() {
        board = new Board();
    }

    /**
     * Model 테스트 - 1
     * x가 게임에서 이기는 테스트
     *
     *    X | X | X
     *    O |   |
     *      | O |
     */
    @Test
    public void test3inRowAcrossTopForX() {

        board.mark(0,0); // x
        assertNull(board.getWinner());

        board.mark(1,0); // o
        assertNull(board.getWinner());

        board.mark(0,1); // x
        assertNull(board.getWinner());

        board.mark(2,1); // o
        assertNull(board.getWinner());

        board.mark(0,2); // x
        assertEquals(Player.X, board.getWinner());
    }

    /**
     * Model 테스트 - 2
     * O이 게임에서 이기는 테스트
     *
     *    O | X | X
     *      | O |
     *      | X | O
     */
    @Test
    public void test3inRowDiagonalFromTopLeftToBottomForO() {

        board.mark(0,1); // x
        assertNull(board.getWinner());

        board.mark(0,0); // o
        assertNull(board.getWinner());

        board.mark(2,1); // x
        assertNull(board.getWinner());

        board.mark(1,1); // o
        assertNull(board.getWinner());

        board.mark(0,2); // x
        assertNull(board.getWinner());

        board.mark(2,2); // o
        assertEquals(Player.O, board.getWinner());
    }
}
