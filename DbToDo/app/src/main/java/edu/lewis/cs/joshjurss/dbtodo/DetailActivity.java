package edu.lewis.cs.joshjurss.dbtodo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.UUID;

public class DetailActivity extends AppCompatActivity {

    private ToDo toDo;
    private EditText titleField;
    private Spinner prioritySpinner;
    private CheckBox completeCheckBox;
    private Button addEditButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        titleField = (EditText)findViewById(R.id.title_field);
        addEditButton = (Button)findViewById(R.id.add_edit_button);
        prioritySpinner = (Spinner)findViewById(R.id.spinner);
        completeCheckBox = (CheckBox)findViewById(R.id.complete_checkbox);

        titleField.addTextChangedListener(new TitleListener());
        prioritySpinner.setOnItemSelectedListener(new PrioritySelect());
        completeCheckBox.setOnClickListener(new CompleteChangeListener());

        UUID id = (UUID)getIntent().getSerializableExtra("id");
        if(id != null){
            toDo = ToDoList.get(getApplicationContext()).getToDo(id);
        }

        if(toDo != null){
            titleField.setText(toDo.getTitle());
            prioritySpinner.setSelection(toDo.getPriority());
            completeCheckBox.setChecked(toDo.isComplete());

            addEditButton.setText(R.string.update);
            addEditButton.setOnClickListener(new OnUpdateButtonClick());
        }else {
            toDo = new ToDo();
            addEditButton.setOnClickListener(new OnAddButtonClick());
        }
    }

    private class TitleListener implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            toDo.setTitle(s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

    private class CompleteChangeListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            if(completeCheckBox.isChecked()){
                toDo.setComplete(true);
            }else{
                toDo.setComplete(false);
            }
        }
    }

    private class PrioritySelect implements AdapterView.OnItemSelectedListener{
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            toDo.setPriority(position);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

    private class OnAddButtonClick implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            ToDoList toDoList = ToDoList.get(getApplicationContext());
            toDoList.addToDo(toDo);
            finish();
        }
    }

    private class OnUpdateButtonClick implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            ToDoList toDoList = ToDoList.get(getApplicationContext());
            toDoList.updateToDo(toDo);
            finish();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.delete:
                ToDoList toDoList = ToDoList.get(getApplicationContext());
                toDoList.deleteToDo(toDo);
                finish();
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.delete_menu, menu);
        return true;
    }
}
