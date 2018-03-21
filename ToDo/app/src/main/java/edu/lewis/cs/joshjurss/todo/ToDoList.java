package edu.lewis.cs.joshjurss.todo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * Created by Josh Jurss on 3/18/2017.
 */

public class ToDoList {

    private static ToDoList toDoList;
    private List<ToDo> toDos;

    public static ToDoList get(){
        if (toDoList == null) {
            toDoList = new ToDoList();
        }
        return toDoList;
    }

    private ToDoList() {
        toDos = new ArrayList<>();
        toDos.add(new ToDo("Grade exams", 2, false));
        toDos.add(new ToDo("Get Groceries", 0, true));
        toDos.add(new ToDo("Do Homework", 1, false));
    }

    public List getToDos(){
        return toDos;
    }

    public ToDo getToDo(UUID id) {
        ToDo toDo=null;
        for(ToDo td:toDos){
            if(td.getId().equals(id)){
                toDo = td;
            }
        }
        return toDo;
    }

    public void addRandomToDo(){
        Random random = new Random();
        int taskNo = random.nextInt(20);
        Boolean done;
        if(taskNo % 2 == 0){
            done = true;
        } else {
            done = false;
        }

        int priority = random.nextInt(3);

        toDos.add(new ToDo("Task #"+taskNo, priority, done));
    }
}
