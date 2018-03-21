package edu.lewis.cs.joshjurss.sevenwondersapp;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by joshjurss on 4/19/2017.
 */

public class ScoresTable {

    public static final String TABLE_SCORES="scores";
    public static final String COL_ID="_id";
    public static final String COL_UUID = "uuid";
    public static final String COL_P1NAME="player1";
    public static final String COL_P2NAME="player2";

    public static final String COL_P1BLUE="p1Blue";
    public static final String COL_P1GREEN="p1Green";
    public static final String COL_p1YELLOW="p1Yellow";
    public static final String COL_P1PURPLE="p1Purple";
    public static final String COL_P1WONDER="p1Wonder";
    public static final String COL_P1SCIENCE="p1Science";
    public static final String COL_P1MONEY="p1Money";
    public static final String COL_P1MILITARY="p1Military";

    public static final String COL_P2BLUE="p2Blue";
    public static final String COL_P2GREEN="p2Green";
    public static final String COL_p2YELLOW="p2Yellow";
    public static final String COL_P2PURPLE="p2Purple";
    public static final String COL_P2WONDER="p2Wonder";
    public static final String COL_P2SCIENCE="p2Science";
    public static final String COL_P2MONEY="p2Money";
    public static final String COL_P2MILITARY="p2Military";

    public static final String COL_GAMEDATE="Date";

    public static final String COL_P1SCIENCEVIC="p1ScienceVictory";
    public static final String COL_P1MILITARYVIC="p1MilitaryVictory";
    public static final String COL_P2SCIENCEVIC="p2ScienceVictory";
    public static final String COL_P2MILITARYVIC="p2MilitaryVictory";

    public static final String COL_P1TOTAL="p1Total";
    public static final String COL_P2TOTAL="p2Total";



    //database creation statement
    private static final String DATABASE_CREATE =
            "create table " + TABLE_SCORES +" (" +
                    COL_ID +  " integer primary key autoincrement, " +
                    COL_UUID + " text not null, " +
                    COL_P1NAME +  " text not null, " +
                    COL_P2NAME +  " text not null, " +
                    COL_P1BLUE + " text not null, " +
                    COL_P1GREEN + " text not null, " +
                    COL_p1YELLOW + " text not null, " +
                    COL_P1PURPLE + " text not null, " +
                    COL_P1WONDER + " text not null, " +
                    COL_P1SCIENCE + " text not null, " +
                    COL_P1MONEY + " text not null, " +
                    COL_P1MILITARY + " text not null, " +
                    COL_P2BLUE + " text not null, " +
                    COL_P2GREEN + " text not null, " +
                    COL_p2YELLOW + " text not null, " +
                    COL_P2PURPLE + " text not null, " +
                    COL_P2WONDER + " text not null, " +
                    COL_P2SCIENCE + " text not null, " +
                    COL_P2MONEY + " text not null, " +
                    COL_P2MILITARY + " text not null, " +
                    COL_GAMEDATE + " text not null, " +
                    COL_P1SCIENCEVIC + " integer default 0, " +
                    COL_P1MILITARYVIC + " integer default 0, " +
                    COL_P2SCIENCEVIC + " integer default 0, " +
                    COL_P2MILITARYVIC + " integer default 0, " +
                    COL_P1TOTAL + " text not null, " +
                    COL_P2TOTAL + " text not null " + ");";

    public static void onCreate(SQLiteDatabase database){
        database.execSQL(DATABASE_CREATE);
        Log.d(ScoresTable.class.getName(), DATABASE_CREATE);

    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion){
        Log.w(ScoresTable.class.getName(), "Upgrading database from version " + oldVersion + " to " + newVersion  +
                "which will destroy all old data");
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_SCORES);
        onCreate(database);
    }

}