package edu.lewis.cs.joshjurss.sevenwondersapp;

import android.database.Cursor;
import android.database.CursorWrapper;

/**
 * Created by joshjurss on 4/21/2017.
 */

public class ScoreCursorWrapper extends CursorWrapper {

    public ScoreCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Score getScore(){
        String uuid = getString(getColumnIndexOrThrow(ScoresTable.COL_UUID));
        String p1Name = getString(getColumnIndexOrThrow(ScoresTable.COL_P1NAME));
        String p2Name = getString(getColumnIndexOrThrow(ScoresTable.COL_P2NAME));
        int p1Blue = getInt(getColumnIndexOrThrow(ScoresTable.COL_P1BLUE));
        int p1Green = getInt(getColumnIndexOrThrow(ScoresTable.COL_P1GREEN));
        int p1Yellow = getInt(getColumnIndexOrThrow(ScoresTable.COL_p1YELLOW));
        int p1Purple = getInt(getColumnIndexOrThrow(ScoresTable.COL_P1PURPLE));
        int p1Wonder = getInt(getColumnIndexOrThrow(ScoresTable.COL_P1WONDER));
        int p1Science = getInt(getColumnIndexOrThrow(ScoresTable.COL_P1SCIENCE));
        int p1Money = getInt(getColumnIndexOrThrow(ScoresTable.COL_P1MONEY));
        int p1Military = getInt(getColumnIndexOrThrow(ScoresTable.COL_P1MILITARY));
        int p2Blue = getInt(getColumnIndexOrThrow(ScoresTable.COL_P2BLUE));
        int p2Green = getInt(getColumnIndexOrThrow(ScoresTable.COL_P2GREEN));
        int p2Yellow = getInt(getColumnIndexOrThrow(ScoresTable.COL_p2YELLOW));
        int p2Purple = getInt(getColumnIndexOrThrow(ScoresTable.COL_P2PURPLE));
        int p2Wonder = getInt(getColumnIndexOrThrow(ScoresTable.COL_P2WONDER));
        int p2Science = getInt(getColumnIndexOrThrow(ScoresTable.COL_P2SCIENCE));
        int p2Money = getInt(getColumnIndexOrThrow(ScoresTable.COL_P2MONEY));
        int p2Military = getInt(getColumnIndexOrThrow(ScoresTable.COL_P2MILITARY));
        long gameDate = getLong(getColumnIndexOrThrow(ScoresTable.COL_GAMEDATE));
        int p1ScienceVic = getInt(getColumnIndexOrThrow(ScoresTable.COL_P1SCIENCEVIC));
        int p1MilitaryVic = getInt(getColumnIndexOrThrow(ScoresTable.COL_P1MILITARYVIC));
        int p2ScienceVic = getInt(getColumnIndexOrThrow(ScoresTable.COL_P2SCIENCEVIC));
        int p2MilitaryVic = getInt(getColumnIndexOrThrow(ScoresTable.COL_P2MILITARYVIC));
        int p1Total = getInt(getColumnIndexOrThrow(ScoresTable.COL_P1TOTAL));
        int p2Total = getInt(getColumnIndexOrThrow(ScoresTable.COL_P2TOTAL));

        Score score = new Score(uuid, p1Name, p2Name, p1Blue, p1Green, p1Yellow, p1Purple, p1Wonder,
                p1Science, p1Money, p1Military, p2Blue, p2Green, p2Yellow, p2Purple, p2Wonder,
                p2Science, p2Money, p2Military, gameDate, p1ScienceVic, p1MilitaryVic,
                p2ScienceVic, p2MilitaryVic, p1Total, p2Total);
        return score;
    }
}
