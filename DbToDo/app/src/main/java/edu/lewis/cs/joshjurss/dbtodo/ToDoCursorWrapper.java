package edu.lewis.cs.joshjurss.dbtodo;

import android.database.Cursor;
import android.database.CursorWrapper;

/**
 * Created by joshjurss on 4/21/2017.
 */

public class ToDoCursorWrapper extends CursorWrapper {

    public ToDoCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public ToDo getToDo(){
        String uuid = getString(getColumnIndexOrThrow(ToDoTable.COL_UUID));
        String title = getString(getColumnIndexOrThrow(ToDoTable.COL_TITLE));
        int priority = getInt(getColumnIndexOrThrow(ToDoTable.COL_PRIORITY));
        int done = getInt(getColumnIndexOrThrow(ToDoTable.COL_DONE));

        ToDo toDo = new ToDo(uuid, title, priority, done);
        return toDo;
    }
}
