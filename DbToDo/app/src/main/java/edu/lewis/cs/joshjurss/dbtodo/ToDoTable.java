package edu.lewis.cs.joshjurss.dbtodo;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by joshjurss on 4/19/2017.
 */

public class ToDoTable {

    public static final String TABLE_TODO="todos";
    public static final String COL_ID="_id";
    public static final String COL_UUID = "uuid";
    public static final String COL_TITLE="title";
    public static final String COL_PRIORITY="priority";
    public static final String COL_DONE="done";

    //database creation statement
    private static final String DATABASE_CREATE =
            "create table " + TABLE_TODO +" (" +
                    COL_ID +  " integer primary key autoincrement, " +
                    COL_UUID + " text not null, " +
                    COL_TITLE +  " text not null, " +
                    COL_PRIORITY + " integer default 0, " +
                    COL_DONE + " integer default 0"+ ");";

    public static void onCreate(SQLiteDatabase database){
        database.execSQL(DATABASE_CREATE);
        Log.d(ToDoTable.class.getName(), DATABASE_CREATE);

    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion){
        Log.w(ToDoTable.class.getName(), "Upgrading database from version " + oldVersion + " to " + newVersion  +
                "which will destroy all old data");
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_TODO);
        onCreate(database);
    }

}