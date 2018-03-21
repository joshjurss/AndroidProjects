package edu.lewis.cs.joshjurss.dbtodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ToDoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        adapter = new ToDoAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onResume() {
        super.onResume();
        if(adapter != null){
            adapter.setToDos(ToDoList.get(getApplicationContext()).getToDos());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.add:
                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                startActivity(intent);
                return true;
            default:
                return false;
        }
    }

    private class ToDoHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView titleTextView;
        ToDo toDo;

        public ToDoHolder(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);
            titleTextView=(TextView)itemView.findViewById(R.id.title_text_view);
        }

        public void bindToDo(ToDo toDo){
            this.toDo=toDo;
            titleTextView.setText(toDo.getTitle());
        }

        @Override
        public void onClick(View v){
            Intent intent=new Intent(MainActivity.this,DetailActivity.class);
            intent.putExtra("id",toDo.getId());
            startActivity(intent);
        }
    }


    private class ToDoAdapter extends RecyclerView.Adapter<ToDoHolder>{
        private List<ToDo> toDos;
        private ToDoHolder holder;

        public ToDoAdapter(){
            setToDos(ToDoList.get(MainActivity.this).getToDos());
        }

        public void setToDos(List<ToDo> toDos){
            this.toDos=toDos;
        }

        @Override
        public ToDoHolder onCreateViewHolder(ViewGroup parent, int viewType){
            LayoutInflater layoutInflater= LayoutInflater.from(MainActivity.this);
            View view=layoutInflater.inflate(R.layout.list_row,parent,false);
            holder=new ToDoHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(ToDoHolder holder,int position){
            ToDo toDo=toDos.get(position);
            holder.bindToDo(toDo);
        }

        @Override
        public int getItemCount(){
            return toDos.size();
        }
    }

}
