package edu.lewis.cs.joshjurss.todo;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;


/**
 * A simple {@link Fragment} subclass.
 */
public class ToDoFragment extends Fragment {
    private ToDo toDo;
    private EditText titleField;
    private Button dateButton;
    private Spinner prioritySpinner;
    private CheckBox completeCheckBox;
    private static final String TAG = "ToDoFragment";
    private static final int REQUEST_CODE = 5;

    public static ToDoFragment newInstance(UUID toDoId){
        Bundle args = new Bundle();
        args.putSerializable("id", toDoId);

        ToDoFragment fragment = new ToDoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public ToDoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //toDo = new ToDo();
        //UUID toDoId = (UUID)getActivity().getIntent().getSerializableExtra("id");
        UUID toDoId = (UUID)getArguments().getSerializable("id");
        toDo = ToDoList.get().getToDo(toDoId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_to_do, container, false);
        titleField = (EditText)view.findViewById(R.id.title_field);

        dateButton = (Button)view.findViewById(R.id.due_date_button);
        setDateButtonText();

        prioritySpinner = (Spinner)view.findViewById(R.id.spinner);

        completeCheckBox = (CheckBox)view.findViewById(R.id.complete_checkbox);

        titleField.addTextChangedListener(new TitleListener());
        prioritySpinner.setOnItemSelectedListener(new PrioritySelect());
        completeCheckBox.setOnClickListener(new CompleteChangeListener());
        dateButton.setOnClickListener(new OnDateButtonClickListener());

        titleField.setText(toDo.getTitle());
        prioritySpinner.setSelection(toDo.getPriority());
        completeCheckBox.setChecked(toDo.isComplete());

        return view;
    }

    private void setDateButtonText() {
        Date date = toDo.getDueDate();
        if(date == null) {
            date = new Date();
        }
        SimpleDateFormat format = new SimpleDateFormat("EE MMM d");
        String formattedDate = format.format(date);
        dateButton.setText(formattedDate);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE) {
            Date date = (Date)data.getSerializableExtra("date");
            toDo.setDueDate(date);
            setDateButtonText();
        }

    }

    private class OnDateButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            FragmentManager manager = getFragmentManager();
            DatePickerFragment dialog = DatePickerFragment.newInstance(toDo.getDueDate());
            dialog.setTargetFragment(ToDoFragment.this, REQUEST_CODE);
            dialog.show(manager, "dialog");
        }
    }

    private class TitleListener implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            toDo.setTitle(s.toString());
            Log.d(TAG, "updated title to " + toDo.getTitle());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

    private class CompleteChangeListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (completeCheckBox.isChecked()) {
                toDo.setComplete(true);
            } else {
                toDo.setComplete(false);
            }
            Log.d(TAG, "updated complete to " + toDo.isComplete());
        }
    }

    private class PrioritySelect implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            toDo.setPriority(position);
            Log.d(TAG, "updated priority to " + toDo.getPriority());
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }

}
