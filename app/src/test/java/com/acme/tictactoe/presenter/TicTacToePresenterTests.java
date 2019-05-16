package com.acme.tictactoe.presenter;


import com.acme.tictactoe.model.Board;
import com.acme.tictactoe.model.Player;
import com.acme.tictactoe.view.TicTacToeView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * There are a lot more tests we can and should write but for now, just a few smoke tests.
 */
@RunWith(MockitoJUnitRunner.class)
public class TicTacToePresenterTests {

    private TicTacToePresenter presenter;

    @Mock
    private TicTacToeView view; // mock 으로 생성

    @Mock
    private Board board;

    @Before
    public void setup() {
        presenter = new TicTacToePresenter(view);


    }

    private void clickAndAssertValueAt(int row, int col, String expectedValue) {
        presenter.onButtonSelected(row, col);
        verify(view).setButtonText(row, col, expectedValue);
    }

    /**
     * Presenter 테스트 - 1
     * x가 게임에서 이기는 테스트
     *
     *    X | X | X
     *    O |   |
     *      | O |
     */
    @Test
    public void test3inRowAcrossTopForX() {
        // mock 인스턴스를 생성하여
        board = mock(Board.class);
//        board = new Board();
        board.mark(0,0); // x


        when(board.isValid(0,0)).thenReturn(true);
        assertEquals(true, board.isValid(0,0));


        clickAndAssertValueAt(0,0, "X");
        // showWinner 메소드가 호출되지 않았는지 검증 - 수행되었으면 오류로 간주
        verify(view, never()).showWinner(anyString());

        clickAndAssertValueAt(1,0, "O");
        verify(view, never()).showWinner(anyString());

        clickAndAssertValueAt(0,1, "X");
        verify(view, never()).showWinner(anyString());

        clickAndAssertValueAt(2,1, "O");
        verify(view, never()).showWinner(anyString());

        // showWinner 메소드가 호출되었는지 검증 - 수행되지 않았으면 오류로 간주
        clickAndAssertValueAt(0,2, "X");
        verify(view).showWinner("X");

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

        clickAndAssertValueAt(0,1, "X");
        // 원하는 메소드가 특정조건으로 실행되었는지 검증, 수행되지 않았는지 검증(수행했으면 오류로 간주)
        verify(view, never()).showWinner(anyString());

        clickAndAssertValueAt(0,0, "O");
        verify(view, never()).showWinner(anyString());

        clickAndAssertValueAt(2,1, "X");
        verify(view, never()).showWinner(anyString());

        clickAndAssertValueAt(1,1, "O");
        verify(view, never()).showWinner(anyString());

        clickAndAssertValueAt(0,2, "X");
        verify(view, never()).showWinner(anyString());

        clickAndAssertValueAt(2,2, "O");
        verify(view).showWinner("O");

        // 한번만 불렸어야 함을 검증
        verify(view, times(1)).showWinner(anyString());

    }


}
