package com.acme.tictactoe;

public interface MainConstants {

    interface View {
        void showWinner(String winningPlayerDisplayLabel);
        void clearWinnerDisplay();
        void clearButtons();
        void setButtonText(int row, int col, String text);
    }
    interface Presenter {
        void onCreate();
        void onPause();
        void onResume();
        void onDestroy();
    }
}
