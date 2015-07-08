package gameui.android.ggl.com.tictactoe;

/**
 * Created by fangbrian on 7/8/15.
 */
public class PlayerState {
    private int mRow0;
    private int mRow1;
    private int mRow2;
    private int mCol0;
    private int mCol1;
    private int mCol2;
    private int mDiag0;
    private int mDiag1;

    public PlayerState() {
        mRow0 = mRow1 = mRow2 = mCol0 = mCol1 = mCol2 = mDiag0 = mDiag1 = 0;
    }

    //TRUE indicates player is winner
    public boolean newMove(int position) {
        switch(position) {
            case 0:
                mRow0 += 1;
                mCol0 += 1;
                mDiag0 += 1;
                if (mRow0 >= 3 || mCol0 >= 3 || mDiag0 >= 3) return true;
                break;
            case 1:
                mRow0 += 1;
                mCol1 += 1;
                if (mRow0 >= 3 || mCol1 >= 3) return true;
                break;
            case 2:
                mRow0 += 1;
                mCol2 += 1;
                mDiag1 += 1;
                if (mRow0 >= 3 || mCol2 >= 3 || mDiag1 >= 3) return true;
                break;
            case 3:
                mRow1 += 1;
                mCol0 += 1;
                if (mRow1 >= 3 || mCol0 >= 3) return true;
                break;
            case 4:
                mRow1 += 1;
                mCol1 += 1;
                mDiag0 += 1;
                mDiag1 += 1;
                if (mRow1 >= 3 || mCol1 >= 3 || mDiag0 >= 3 || mDiag1 >= 3) return true;
                break;
            case 5:
                mRow1 += 1;
                mCol2 += 1;
                if (mRow1 >= 3 || mCol2 >= 3) return true;
                break;
            case 6:
                mRow2 += 1;
                mCol0 += 1;
                mDiag1 += 1;
                if (mRow2 >= 3 || mCol0 >= 3 || mDiag1 >= 3) return true;
                break;
            case 7:
                mRow2 += 1;
                mCol1 += 1;
                if (mRow2 >= 3 || mCol1 >= 3) return true;
                break;
            case 8:
                mRow2 += 1;
                mCol2 += 1;
                mDiag0 += 1;
                if (mRow2 >= 3 || mCol2 >= 3 || mDiag0 >= 3) return true;
                break;
            default:
                return false;
        }
        return false;
    }
}
