package edu.lewis.cs.joshjurss.sevenwondersapp;

import android.content.Intent;
import android.graphics.Color;
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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static edu.lewis.cs.joshjurss.sevenwondersapp.R.id.p1_text_view;

public class ListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ScoreAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        adapter = new ScoreAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setReverseLayout(true);
        mLayoutManager.setStackFromEnd(true);

        recyclerView.setLayoutManager(mLayoutManager);

    }

    @Override
    public void onResume() {
        super.onResume();
        if(adapter != null){
            adapter.setScores(ScoresList.get(getApplicationContext()).getScores());
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
                Intent intent = new Intent(getApplicationContext(), ScoringActivity.class);
                startActivity(intent);
                return true;
            default:
                return false;
        }
    }

    private class ScoreHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView p1TextView;
        private TextView p2TextView;
        private TextView gameDate;
        Score score;

        public ScoreHolder(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);
            //gameDate = (TextView)itemView.findViewById(R.id.game_date);
            p1TextView =(TextView)itemView.findViewById(p1_text_view);
            p2TextView = (TextView)itemView.findViewById(R.id.p2_text_view);
            gameDate = (TextView)itemView.findViewById(R.id.game_date);
        }

        public void bindToDo(Score score){
            this.score = score;

            SimpleDateFormat sdf = new SimpleDateFormat("E, MMM dd yyyy   |   hh:mm:ss a");
            gameDate.setText(sdf.format(new Date(score.getGameDate().longValue())));

            int p1Score = 0;
            int p2Score = 0;

            if(score.isP1ScienceVic() || score.isP1MilitaryVic()){
                p1TextView.setText(score.getP1Name() + " : " + "Auto Win" + "\t");
                p2TextView.setText("\t" + score.getP2Name() + " : " + "Auto Loss");
                p2TextView.setTextColor(Color.BLACK);
                if(score.isP1ScienceVic()){
                    p1TextView.setTextColor(Color.GREEN);
                }else{
                    p1TextView.setTextColor(Color.RED);
                }

            } else if(score.isP2ScienceVic() || score.isP2MilitaryVic()){
                p1TextView.setText(score.getP1Name() + " : " + "Auto Loss" + "\t");
                p2TextView.setText("\t" + score.getP2Name() + " : " + "Auto Win");
                p1TextView.setTextColor(Color.BLACK);
                if(score.isP2ScienceVic()){
                    p2TextView.setTextColor(Color.GREEN);
                }else{
                    p2TextView.setTextColor(Color.RED);
                }
            } else{
                p1TextView.setText(score.getP1Name() + " : " + score.getP1Total() + "\t");
                p2TextView.setText("\t" + score.getP2Name() + " : " + score.getP2Total());
                if(score.getP1Total() > score.getP2Total()){
                    p1TextView.setTextColor(Color.BLUE);
                    p2TextView.setTextColor(Color.BLACK);
                } else if(score.getP2Total() > score.getP1Total()){
                    p1TextView.setTextColor(Color.BLACK);
                    p2TextView.setTextColor(Color.BLUE);
                } else{
                    p1TextView.setTextColor(Color.BLACK);
                    p2TextView.setTextColor(Color.BLACK);
                }
            }
        }

        @Override
        public void onClick(View v){
            Intent intent=new Intent(ListActivity.this,ScoringActivity.class);
            intent.putExtra("id", score.getId());
            startActivity(intent);
        }
    }


    private class ScoreAdapter extends RecyclerView.Adapter<ScoreHolder>{
        private List<Score> scores;
        private ScoreHolder holder;

        public ScoreAdapter(){
            setScores(ScoresList.get(ListActivity.this).getScores());
        }

        public void setScores(List<Score> scores){
            this.scores = scores;
        }

        @Override
        public ScoreHolder onCreateViewHolder(ViewGroup parent, int viewType){
            LayoutInflater layoutInflater= LayoutInflater.from(ListActivity.this);
            View view=layoutInflater.inflate(R.layout.list_row,parent,false);
            holder=new ScoreHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(ScoreHolder holder, int position){
            Score score = scores.get(position);
            holder.bindToDo(score);
        }

        @Override
        public int getItemCount(){
            return scores.size();
        }
    }


}
