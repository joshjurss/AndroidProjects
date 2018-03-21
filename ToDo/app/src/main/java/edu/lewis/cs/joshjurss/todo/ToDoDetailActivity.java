package edu.lewis.cs.joshjurss.todo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import java.util.UUID;

public class ToDoDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_detail);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if (fragment == null){
            UUID toDoId = (UUID)getIntent().getSerializableExtra("id");
            fragment = ToDoFragment.newInstance(toDoId);
            //fragment = new ToDoFragment();
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(R.id.fragment_container, fragment);
            ft.commit();
        }
    }
}
