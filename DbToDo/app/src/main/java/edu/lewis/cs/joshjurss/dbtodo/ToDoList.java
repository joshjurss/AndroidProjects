package edu.lewis.cs.joshjurss.dbtodo;

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


public class ToDoList {

    private static ToDoList toDoList;
    private SQLiteDatabase database;

    public static ToDoList get(Context context){
        if(toDoList==null){
            toDoList=new ToDoList(context);
        }
        return toDoList;
    }

    private ToDoList(Context context){

        database= new DbHelper(context).getWritableDatabase();
    }

    public List getToDos(){
        ArrayList<ToDo> toDos =  new ArrayList();

        Cursor c = database.query(ToDoTable.TABLE_TODO, null, null, null, null, null, null);

        ToDoCursorWrapper cursorWrapper = new ToDoCursorWrapper(c);
        ToDo toDo;

        try{
            cursorWrapper.moveToFirst();
            while(!cursorWrapper.isAfterLast()){
                toDo = cursorWrapper.getToDo();
                toDos.add(toDo);
                cursorWrapper.moveToNext();
            }
        }finally{
            cursorWrapper.close();
        }

        return toDos;
    }

    public ToDo getToDo(UUID id) {

        String selection = ToDoTable.COL_UUID + "= ?";
        String[] selection_args = {id.toString()};

        Cursor cursor = database.query(ToDoTable.TABLE_TODO, null, selection, selection_args, null, null, null);

        ToDoCursorWrapper cursorWrapper = new ToDoCursorWrapper(cursor);
        ToDo toDo = null;

        try{
            if(cursorWrapper.getCount() == 0) {
                return null;
            }

            cursorWrapper.moveToFirst();
            toDo = cursorWrapper.getToDo();

        }finally {
            cursorWrapper.close();
        }

        return toDo;
    }

    private ContentValues getContentValues(ToDo toDo){
        ContentValues values = new ContentValues();

        values.put(ToDoTable.COL_UUID, toDo.getId().toString());
        values.put(ToDoTable.COL_TITLE, toDo.getTitle());

        int done = 0;
        if(toDo.isComplete()){
            done = 1;
        }

        values.put(ToDoTable.COL_DONE, done);
        values.put(ToDoTable.COL_PRIORITY, toDo.getPriority());

        return values;
    }

    public void addToDo(ToDo toDo){
        ContentValues values = getContentValues(toDo);
        database.insert(ToDoTable.TABLE_TODO, null, values);
    }

    public void updateToDo(ToDo toDo){
        ContentValues contentValues = getContentValues(toDo);
        String uuid = toDo.getId().toString();
        String selection = ToDoTable.COL_UUID + "= ?";
        String[] selection_args = {uuid};

        database.update(ToDoTable.TABLE_TODO, contentValues, selection, selection_args);
    }

    public void deleteToDo(ToDo toDo){
        ContentValues contentValues = getContentValues(toDo);
        String uuid = toDo.getId().toString();
        String selection = ToDoTable.COL_UUID + "= ?";
        String[] selection_args = {uuid};


        database.delete(ToDoTable.TABLE_TODO, selection, selection_args);
    }

}