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
    private int mTurns = 0;
    private final String[] mBlocks = new String[] {
            "*", "*", "*",
            "*", "*", "*",
            "*", "*", "*"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_board);

        mGridView = (GridView) findViewById(R.id.gridview);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, mBlocks);

        mGridView.setAdapter(adapter);

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                Toast.makeText(getApplicationContext(),
                        Integer.toString(position), Toast.LENGTH_SHORT).show();

                processNextMove(position, ((TextView) v));
            }

        });
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

    private void processNextMove(int position, TextView textView) {
        mTurns += 1;
        if (mTurns % 2 == 1 ) {
            textView.setText("X");
            if(mPlayer1.newMove(position)) {
                //Player 1 Wins
                Log.d("****", "Player 1 WINS");
            }
        }
        else {
            textView.setText("O");
            if(mPlayer2.newMove(position)) {
                //Player 2 Wins
                Log.d("****", "PLAYER 2 WINS");
            }
        }
    }

}
