package gameui.android.ggl.com.tictactoe;

import android.util.Log;

/**
 * Created by fangbrian on 7/8/15.
 */
public class ComputerMove {
    private String[] mGameState = new String[] {
            "*", "*", "*",
            "*", "*", "*",
            "*", "*", "*"
    };
    private PlayerState mPlayer1 = new PlayerState();
    private PlayerState mComputer = new PlayerState();
    private int mTurns = 0;
    private int mNextMove;
    private int mCurrentScore = 0;

    public ComputerMove(String[] state) {
        for(int i = 0; i < state.length; i++) {
            mGameState[i] = state[i];
        }

        initializePlayers();
    }

    private void initializePlayers() {
        for (int i = 0; i < mGameState.length; i++) {
            if (mGameState[i].equals("*")) mNextMove = i;
            if (mGameState[i].equals("X")) {
                mTurns += 1;
                mPlayer1.newMove(i);
            }

            if(mGameState[i].equals("O")) {
                mTurns += 1;
                mComputer.newMove(i);
            }
        }
    }

    public int getNextMove() {
        maximizeScore(mGameState);
        return mNextMove;
    }

    private int maximizeScore(String[] state) {
        if (mPlayer1.isWinner()) {
            //Player 1 Wins
            return -1;
        }
        else if (mComputer.isWinner()) {
            //Computer Wins
            return 1;
        }
        else if (mTurns == 9) {
            //DRAW
            return 0;
        }
        else {
            for (int i = 0; i < state.length; i++) {
                if (state[i].equals("*")) {
                    if (mTurns % 2 == 1) {
                        mPlayer1.newMove(i);
                        state[i] = "X";
                        int score = maximizeScore(state);
                        if (score < mCurrentScore) {
                            mNextMove = i;
                            mCurrentScore = score;
                            state[i] = "*";
                            mTurns -= 1;
                            return mCurrentScore;
                        }
                    }
                    else {
                        mComputer.newMove(i);
                        state[i] = "O";
                        int score = maximizeScore(state);
                        if (score > mCurrentScore) {
                            mNextMove = i;
                            mCurrentScore = score;
                            state[i] = "*";
                            mTurns -= 1;
                            return mCurrentScore;
                        }
                    }
                }
            }
        }
        return mCurrentScore;
    }

    private int findNextAvailable() {
        int pos = 9;
        for(int i = 0; i < mGameState.length; i++) {
            if (mGameState[i].equals("*")) pos = i;
        }
        return pos;
    }

}
