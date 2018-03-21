package edu.lewis.cs.joshjurss.sevenwondersapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by joshjurss on 4/19/2017.
 */


public class ScoresList {

    private static ScoresList scoresList;
    private SQLiteDatabase database;

    public static ScoresList get(Context context){
        if(scoresList ==null){
            scoresList =new ScoresList(context);
        }
        return scoresList;
    }

    private ScoresList(Context context){

        database= new DbHelper(context).getWritableDatabase();
    }

    public List getScores(){
        ArrayList<Score> scores =  new ArrayList();

        Cursor c = database.query(ScoresTable.TABLE_SCORES, null, null, null, null, null, null);

        ScoreCursorWrapper cursorWrapper = new ScoreCursorWrapper(c);
        Score score;

        try{
            cursorWrapper.moveToFirst();
            while(!cursorWrapper.isAfterLast()){
                score = cursorWrapper.getScore();
                scores.add(score);
                cursorWrapper.moveToNext();
            }
        }finally{
            cursorWrapper.close();
        }

        return scores;
    }

    public Score getScore(UUID id) {

        String selection = ScoresTable.COL_UUID + "= ?";
        String[] selection_args = {id.toString()};

        Cursor cursor = database.query(ScoresTable.TABLE_SCORES, null, selection, selection_args, null, null, null);

        ScoreCursorWrapper cursorWrapper = new ScoreCursorWrapper(cursor);
        Score score = null;

        try{
            if(cursorWrapper.getCount() == 0) {
                return null;
            }

            cursorWrapper.moveToFirst();
            score = cursorWrapper.getScore();

        }finally {
            cursorWrapper.close();
        }

        return score;
    }

    private ContentValues getContentValues(Score score){
        ContentValues values = new ContentValues();

        values.put(ScoresTable.COL_UUID, score.getId().toString());
        values.put(ScoresTable.COL_P1NAME, score.getP1Name());
        values.put(ScoresTable.COL_P2NAME, score.getP2Name());
        values.put(ScoresTable.COL_P1BLUE, score.getP1Blue());
        values.put(ScoresTable.COL_P1GREEN, score.getP1Green());
        values.put(ScoresTable.COL_p1YELLOW, score.getP1Yellow());
        values.put(ScoresTable.COL_P1PURPLE, score.getP1Purple());
        values.put(ScoresTable.COL_P1WONDER, score.getP1Wonder());
        values.put(ScoresTable.COL_P1SCIENCE, score.getP1Science());
        values.put(ScoresTable.COL_P1MONEY, score.getP1Money());
        values.put(ScoresTable.COL_P1MILITARY, score.getP1Military());

        int p1Sc = 0;
        if(score.isP1ScienceVic()){
            p1Sc = 1;
        }
        values.put(ScoresTable.COL_P1SCIENCEVIC, p1Sc);

        int p1Mt = 0;
        if(score.isP1MilitaryVic()){
            p1Mt = 1;
        }
        values.put(ScoresTable.COL_P1MILITARYVIC, p1Mt);

        values.put(ScoresTable.COL_P2BLUE, score.getP2Blue());
        values.put(ScoresTable.COL_P2GREEN, score.getP2Green());
        values.put(ScoresTable.COL_p2YELLOW, score.getP2Yellow());
        values.put(ScoresTable.COL_P2PURPLE, score.getP2Purple());
        values.put(ScoresTable.COL_P2WONDER, score.getP2Wonder());
        values.put(ScoresTable.COL_P2SCIENCE, score.getP2Science());
        values.put(ScoresTable.COL_P2MONEY, score.getP2Money());
        values.put(ScoresTable.COL_P2MILITARY, score.getP2Military());

        int p2Sc = 0;
        if(score.isP2ScienceVic()){
            p2Sc = 1;
        }
        values.put(ScoresTable.COL_P2SCIENCEVIC, p2Sc);

        int p2Mt = 0;
        if(score.isP2MilitaryVic()){
            p2Mt = 1;
        }
        values.put(ScoresTable.COL_P2MILITARYVIC, p2Mt);

        values.put(ScoresTable.COL_GAMEDATE, score.getGameDate());

        values.put(ScoresTable.COL_P1TOTAL, score.getP1Total());
        values.put(ScoresTable.COL_P2TOTAL, score.getP2Total());

        return values;
    }

    public void addScore(Score score){
        ContentValues values = getContentValues(score);
        database.insert(ScoresTable.TABLE_SCORES, null, values);
    }

    public void updateScore(Score score){
        ContentValues contentValues = getContentValues(score);
        String uuid = score.getId().toString();
        String selection = ScoresTable.COL_UUID + "= ?";
        String[] selection_args = {uuid};

        database.update(ScoresTable.TABLE_SCORES, contentValues, selection, selection_args);
    }

    public void deleteScore(Score score){
        ContentValues contentValues = getContentValues(score);
        String uuid = score.getId().toString();
        String selection = ScoresTable.COL_UUID + "= ?";
        String[] selection_args = {uuid};


        database.delete(ScoresTable.TABLE_SCORES, selection, selection_args);
    }

}