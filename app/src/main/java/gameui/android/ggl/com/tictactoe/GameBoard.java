package gameui.android.ggl.com.tictactoe;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by fangbrian on 7/8/15.
 */
public class GameBoard extends Activity {
    private GridView mGridView;
    private PlayerState mPlayer1 = new PlayerState();
    private PlayerState mPlayer2 = new PlayerState();
    private TextView mMessage;
    private TextView mRematch;
    private int mTurns = 0;
    private final String[] mBlocks = new String[] {
            "*", "*", "*",
            "*", "*", "*",
            "*", "*", "*"
    };
    private ComputerMove mComputerMove;
    private String[] mState = new String[] {
            "*", "*", "*",
            "*", "*", "*",
            "*", "*", "*"
    };
    private boolean mGameOver = false;
    private String mEnvironment = "Environment";

    private static final int MAX_NUMBER_OF_TURNS = 9;
    private static final int POSITION_UPPER_BOUND = 9;
    private static final int POSITION_LOWER_BOUND = 0;
    private static final String ENVIRONMENT_GAME = "Environment";
    private static final String PLAYER_VS_PLAYER_GAME = "2Player";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_board);

        mGridView = (GridView) findViewById(R.id.gridview);
        mMessage = (TextView) findViewById(R.id.message);
        mRematch = (TextView) findViewById(R.id.rematch);

        getIntentData();
        setRematchListener();
        initializeGameBoard();
    }

    private void getIntentData() {
        if (getIntent() != null || getIntent().getExtras() != null) {
            Log.d("MAIN ACTIVITY **** ", getIntent().getExtras().getString("Mode", ""));
            mEnvironment = getIntent().getExtras().getString("Mode", "");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setRematchListener() {
        mRematch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                initializeGameBoard();
            }
        });
    }

    private void initializeGameBoard() {
        mMessage.setVisibility(View.GONE);
        mRematch.setVisibility(View.GONE);
        mGameOver = false;

        mGridView.setAdapter(new BoxAdapter(this, mState));

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                processNextMove(position);
            }

        });
    }

    private void processNextMove(int position) {
        if (!mGameOver && checkValidPosition(position) && mEnvironment.equals(ENVIRONMENT_GAME)) {
            //((TextView) view.findViewById(R.id.square)).setText("X");
            ((TextView) mGridView.getChildAt(position).findViewById(R.id.square)).setText("X");
            mState[position] = "X";
            mTurns += 1;
            if (mTurns == MAX_NUMBER_OF_TURNS) {
                mMessage.setText("Tie Game");
                mMessage.setVisibility(View.VISIBLE);
                endGame();
                return;
            }
            if (mTurns % 2 == 1) {
                if (mPlayer1.newMove(position)) {
                    //Player 1 Wins
                    mMessage.setText("Player 1 Wins");
                    mMessage.setVisibility(View.VISIBLE);
                    endGame();
                } else {
                    computerTurn(position);
                }
            }
        }
        else if (!mGameOver && checkValidPosition(position) && mEnvironment.equals(PLAYER_VS_PLAYER_GAME)) {
            mTurns += 1;
            if (mTurns % 2 == 1 ) {
                ((TextView) mGridView.getChildAt(position).findViewById(R.id.square)).setText("X");
                mState[position] = "X";
                if(mPlayer1.newMove(position)) {
                    //Player 1 Wins
                    Log.d("****", "Player 1 WINS");
                    mMessage.setText("Player 1 Wins");
                    mMessage.setVisibility(View.VISIBLE);
                    endGame();
                }
            }
            else {
                ((TextView) mGridView.getChildAt(position).findViewById(R.id.square)).setText("O");
                mState[position] = "O";
                if(mPlayer2.newMove(position)) {
                    //Player 2 Wins
                    Log.d("****", "PLAYER 2 WINS");
                    mMessage.setText("Player 2 Wins");
                    mMessage.setVisibility(View.VISIBLE);
                    endGame();
                }
            }
            if (mTurns == MAX_NUMBER_OF_TURNS) {
                mMessage.setText("Tie Game");
                mMessage.setVisibility(View.VISIBLE);
                endGame();
                return;
            }

        }
    }

    private void computerTurn(int position) {
        mComputerMove = new ComputerMove(mState);
        position = mComputerMove.getNextMove();
        if (position < POSITION_UPPER_BOUND && position >= POSITION_LOWER_BOUND) {
            mState[position] = "O";
            ((TextView) mGridView.getChildAt(position).findViewById(R.id.square)).setText("O");
            mTurns += 1;
            if (mPlayer2.newMove(position)) {
                //Computer Wins
                mMessage.setText("Player 2 Wins");
                mMessage.setVisibility(View.VISIBLE);
                endGame();
            }
        }
        else {
            invalidInput();
        }
    }

    private boolean checkValidPosition(int position) {
        return mState[position].equals("*");
    }

    private void invalidInput() {
        mMessage.setText("Invalid Input");
        mMessage.setVisibility(View.VISIBLE);
        endGame();
    }

    private void endGame() {
        //Reset Parameters
        mTurns = 0;
        mPlayer1.resetParameters();
        mPlayer2.resetParameters();
        mRematch.setVisibility(View.VISIBLE);
        mGameOver = true;
        mState = new String[] {
                "*", "*", "*",
                "*", "*", "*",
                "*", "*", "*"
        };
    }

}
