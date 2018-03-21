package edu.lewis.cs.joshjurss.todo;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */

public class ToDoListFragment extends Fragment {
    private RecyclerView recyclerView;
    //public List<ToDo> toDoList;
    private ToDoList toDoList;
    private List<ToDo> toDos;
    private ToDoAdapter toDoAdapter;

    public ToDoListFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_to_do_list, container, false);
        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);

        toDoList = ToDoList.get();
        toDos = toDoList.getToDos();

        toDoAdapter = new ToDoAdapter(toDos);
        recyclerView.setAdapter(toDoAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        Button addButton = (Button)v.findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                toDoList.addRandomToDo();
                int end = toDos.size() - 1;

                toDoAdapter.notifyItemInserted(end);
                recyclerView.scrollToPosition(end);
            }
        });

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(toDoAdapter != null){
            toDoAdapter.notifyDataSetChanged();
        }
    }

    private class ToDoHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView titleTextView;
        private TextView dateTextView;
        private CheckBox completeCheckBox;
        private ToDo toDo;

        public ToDoHolder(View itemView) {
            super(itemView);

            titleTextView = (TextView)itemView.findViewById(R.id.title_text_view);
            dateTextView = (TextView)itemView.findViewById(R.id.date_text_view);
            completeCheckBox = (CheckBox)itemView.findViewById(R.id.task_complete_checkbox);

            completeCheckBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    toDo.setComplete(completeCheckBox.isChecked());

                    String toastString;
                    if(toDo.isComplete()){
                        toastString = "ToDo Marked Complete";
                    } else {
                        toastString = "ToDo Marked Not Complete";
                    }
                    Toast.makeText(getActivity(), toastString, Toast.LENGTH_SHORT).show();
                }
            });

            itemView.setOnClickListener(this);
        }

        public void bindToDo(ToDo toDo) {
            this.toDo = toDo;

            titleTextView.setText(toDo.getTitle());
            dateTextView.setText(toDo.getDueDate().toString());
            completeCheckBox.setChecked(toDo.isComplete());
        }

        @Override
        public void onClick(View view) {
            //Toast.makeText(getActivity(), toDo.getId().toString(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getActivity(), ToDoDetailActivity.class);
            intent.putExtra("id", toDo.getId());
            startActivity(intent);
        }
    }

    private class ToDoAdapter extends RecyclerView.Adapter<ToDoHolder> {
        private List<ToDo> toDos;
        private ToDoHolder holder;
        private ToDo toDo;

        public ToDoAdapter(List<ToDo> toDos) {
            this.toDos = toDos;
        }

        @Override
        public ToDoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_todo, parent, false);

            holder = new ToDoHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(ToDoHolder holder, int position) {
            ToDo toDo = toDos.get(position);
            holder.bindToDo(toDo);
        }

        @Override
        public int getItemCount() {
            return toDos.size();
        }
    }

}
