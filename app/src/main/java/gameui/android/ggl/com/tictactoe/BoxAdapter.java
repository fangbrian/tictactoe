package gameui.android.ggl.com.tictactoe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by fangbrian on 7/9/15.
 */
public class BoxAdapter  extends BaseAdapter {
    private Context context;
    private final String[] mState;

    public BoxAdapter(Context context, String[] state) {
        this.context = context;
        this.mState = state;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        if (convertView == null) {

            gridView = new View(context);

            // get layout from mobile.xml
            gridView = inflater.inflate(R.layout.tictactoe_box, null);

            // set image based on selected text
            TextView squareView = (TextView) gridView
                    .findViewById(R.id.square);


            squareView.setText(" ");


        } else {
            gridView = (View) convertView;
        }

        return gridView;
    }

    @Override
    public int getCount() {
        return mState.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

}
