package com.acme.tictactoe.presenter;

import com.acme.tictactoe.MainConstants;
import com.acme.tictactoe.model.Board;
import com.acme.tictactoe.model.Player;

public class MainPresenter implements MainConstants.Presenter {

    private MainConstants.View view;
    private Board model;

    public MainPresenter(MainConstants.View view) {
        this.view = view;
        this.model = new Board();
    }

    @Override
    public void onCreate() {
        model = new Board();
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {

    }

    public void onButtonSelected(int row, int col) {
        // 3. 필요한 데이터를 Model에 요청하여 응답을 받는다.
        Player playerThatMoved = model.mark(row, col);

        if(playerThatMoved != null) {
            // 4. 응답받은 데이터를 화면에 보여준다.
            view.setButtonText(row, col, playerThatMoved.toString());

            if (model.getWinner() != null) {
                // 4. 응답받은 데이터를 화면에 보여준다.
                view.showWinner(playerThatMoved.toString());
            }
        }
    }

    public void onResetSelected() {
        view.clearWinnerDisplay();
        view.clearButtons();
        model.restart();
    }


}
