package edu.lewis.cs.joshjurss.dbtodo;

import java.util.UUID;

/**
 * Created by joshjurss on 4/19/2017.
 */

public class ToDo {
    private UUID id;
    private String title;
    private int priority;
    private boolean complete;

    public ToDo() {
        id = UUID.randomUUID();
    }

    public ToDo(String id, String title, int priority, int complete) {
        this.id=UUID.fromString(id);
        this.title = title;
        this.priority = priority;

        if(complete==1)
            this.complete=true;
        else
            this.complete=false;
    }

    public ToDo(String title, int priority, boolean complete) {
        id = UUID.randomUUID();

        this.title = title;
        this.priority = priority;
        this.complete = complete;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }


}