package edu.lewis.cs.joshjurss.sevenwondersapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.UUID;

public class ScoringActivity extends AppCompatActivity {

    private Score score;

    private UUID id;
    private EditText p1Blue;
    private EditText p1Green;
    private EditText p1Yellow;
    private EditText p1Purple;
    private EditText p1Wonder;
    private EditText p1Science;
    private EditText p1Money;
    private EditText p1Military;
    private CheckBox p1MilitaryWin;
    private CheckBox p1ScienceWin;

    private EditText p2Blue;
    private EditText p2Green;
    private EditText p2Yellow;
    private EditText p2Purple;
    private EditText p2Wonder;
    private EditText p2Science;
    private EditText p2Money;
    private EditText p2Military;
    private CheckBox p2MilitaryWin;
    private CheckBox p2ScienceWin;

    private TextView p1Total;
    private TextView p2Total;

    private EditText p1Name;
    private EditText p2Name;

    private Button saveScoredGameButton;
    private Button calcScoreButton;

    boolean keyDel = false;

    public ScoringActivity() {
        id = UUID.randomUUID();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoring);

        p1Name = (EditText)findViewById(R.id.player_one_name);
        p2Name = (EditText)findViewById(R.id.player_two_name);

        p1Total = (TextView)findViewById(R.id.p1_total);

        p1Blue = (EditText)findViewById(R.id.p1_blue_points);
        p1Green = (EditText)findViewById(R.id.p1_green_points);
        p1Yellow = (EditText)findViewById(R.id.p1_yellow_points);
        p1Purple = (EditText)findViewById(R.id.p1_purple_points);
        p1Wonder = (EditText)findViewById(R.id.p1_wonder_points);
        p1Science = (EditText)findViewById(R.id.p1_science_points);
        p1Money = (EditText)findViewById(R.id.p1_money_points);
        p1Military = (EditText)findViewById(R.id.p1_military_points);
        p1MilitaryWin = (CheckBox)findViewById(R.id.p1_military_victory);
        p1ScienceWin = (CheckBox)findViewById(R.id.p1_science_victory);

        p2Total = (TextView)findViewById(R.id.p2_total);

        p2Blue = (EditText)findViewById(R.id.p2_blue_points);
        p2Green = (EditText)findViewById(R.id.p2_green_points);
        p2Yellow = (EditText)findViewById(R.id.p2_yellow_points);
        p2Purple = (EditText)findViewById(R.id.p2_purple_points);
        p2Wonder = (EditText)findViewById(R.id.p2_wonder_points);
        p2Science = (EditText)findViewById(R.id.p2_science_points);
        p2Money = (EditText)findViewById(R.id.p2_money_points);
        p2Military = (EditText)findViewById(R.id.p2_military_points);
        p2MilitaryWin = (CheckBox)findViewById(R.id.p2_military_victory);
        p2ScienceWin = (CheckBox)findViewById(R.id.p2_science_victory);

        saveScoredGameButton = (Button)findViewById(R.id.saveBtn);

        p1Name.addTextChangedListener(new NameListenerP1());
        p2Name.addTextChangedListener(new NameListenerP2());


        p1Blue.addTextChangedListener(new P1BlueListener());
        p1Blue.setOnKeyListener(new keyPressListener());

        p1Green.addTextChangedListener(new P1GreenListener());
        p1Green.setOnKeyListener(new keyPressListener());

        p1Yellow.addTextChangedListener(new P1YellowListener());
        p1Yellow.setOnKeyListener(new keyPressListener());

        p1Purple.addTextChangedListener(new P1PurpleListener());
        p1Purple.setOnKeyListener(new keyPressListener());

        p1Wonder.addTextChangedListener(new P1WonderListener());
        p1Wonder.setOnKeyListener(new keyPressListener());

        p1Science.addTextChangedListener(new P1ScienceListener());
        p1Science.setOnKeyListener(new keyPressListener());

        p1Money.addTextChangedListener(new P1MoneyListener());
        p1Money.setOnKeyListener(new keyPressListener());

        p1Military.addTextChangedListener(new P1MilitaryListener());
        p1Military.setOnKeyListener(new keyPressListener());

        p1MilitaryWin.setOnClickListener(new P1MilitaryVictoryListener());
        p1ScienceWin.setOnClickListener(new P1ScienceVictoryListener());

        ////////////////////////////////////P2 Listener Calls///////////////////////////////////////

        p2Blue.addTextChangedListener(new P2BlueListener());
        p2Blue.setOnKeyListener(new keyPressListener());

        p2Green.addTextChangedListener(new P2GreenListener());
        p2Green.setOnKeyListener(new keyPressListener());

        p2Yellow.addTextChangedListener(new P2YellowListener());
        p2Yellow.setOnKeyListener(new keyPressListener());

        p2Purple.addTextChangedListener(new P2PurpleListener());
        p2Purple.setOnKeyListener(new keyPressListener());

        p2Wonder.addTextChangedListener(new P2WonderListener());
        p2Wonder.setOnKeyListener(new keyPressListener());

        p2Science.addTextChangedListener(new P2ScienceListener());
        p2Science.setOnKeyListener(new keyPressListener());

        p2Money.addTextChangedListener(new P2MoneyListener());
        p2Money.setOnKeyListener(new keyPressListener());

        p2Military.addTextChangedListener(new P2MilitaryListener());
        p2Military.setOnKeyListener(new keyPressListener());

        p2MilitaryWin.setOnClickListener(new P2MilitaryVictoryListener());
        p2ScienceWin.setOnClickListener(new P2ScienceVictoryListener());


        UUID id = (UUID)getIntent().getSerializableExtra("id");
        if(id != null){
            score = ScoresList.get(getApplicationContext()).getScore(id);
        }

        //SET VALUES FROM SAVED GAMES *********************************************************************
        if(score != null){
            p1Name.setText(score.getP1Name());
            p2Name.setText(score.getP2Name());
            p1Blue.setText(Integer.valueOf(score.getP1Blue()).toString());
            p1Green.setText(Integer.valueOf(score.getP1Green()).toString());
            p1Yellow.setText(Integer.valueOf(score.getP1Yellow()).toString());
            p1Purple.setText(Integer.valueOf(score.getP1Purple()).toString());
            p1Wonder.setText(Integer.valueOf(score.getP1Wonder()).toString());
            p1Science.setText(Integer.valueOf(score.getP1Science()).toString());
            p1Money.setText(Integer.valueOf(score.getP1Money()).toString());
            p1Military.setText(Integer.valueOf(score.getP1Military()).toString());
            p1ScienceWin.setChecked(score.isP1ScienceVic());
            p1MilitaryWin.setChecked(score.isP1MilitaryVic());

            p2Blue.setText(Integer.valueOf(score.getP2Blue()).toString());
            p2Green.setText(Integer.valueOf(score.getP2Green()).toString());
            p2Yellow.setText(Integer.valueOf(score.getP2Yellow()).toString());
            p2Purple.setText(Integer.valueOf(score.getP2Purple()).toString());
            p2Wonder.setText(Integer.valueOf(score.getP2Wonder()).toString());
            p2Science.setText(Integer.valueOf(score.getP2Science()).toString());
            p2Money.setText(Integer.valueOf(score.getP2Money()).toString());
            p2Military.setText(Integer.valueOf(score.getP2Military()).toString());
            p2ScienceWin.setChecked(score.isP2ScienceVic());
            p2MilitaryWin.setChecked(score.isP2MilitaryVic());

            p1Total.setText(Integer.valueOf(score.getP1Total()).toString());
            p2Total.setText(Integer.valueOf(score.getP2Total()).toString());

            saveScoredGameButton.setText(R.string.update);
            saveScoredGameButton.setOnClickListener(new OnUpdateButtonClick());
        }else {
            score = new Score();
            saveScoredGameButton.setOnClickListener(new OnSaveScoresButtonClick());
        }

        calcScoreButton = (Button)findViewById(R.id.calcBtn);
        calcScoreButton.setOnClickListener(new CalcButtonClick());

    }

    private class keyPressListener implements View.OnKeyListener{
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {

            if (keyCode == KeyEvent.KEYCODE_DEL){
                keyDel = true;
            }else{
                keyDel = false;
            }
            return false;
        }
    }

    private class NameListenerP1 implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            score.setP1Name(s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

    private class NameListenerP2 implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            score.setP2Name(s.toString());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }


    private class OnSaveScoresButtonClick implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            ScoresList scoresList = ScoresList.get(getApplicationContext());
            scoresList.addScore(score);
            finish();
        }
    }

    private class OnUpdateButtonClick implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            ScoresList scoresList = ScoresList.get(getApplicationContext());
            scoresList.updateScore(score);
            finish();
        }
    }

    private class CalcButtonClick implements View.OnClickListener{
        @Override
        public void onClick(View view) {

            int totalP1 = 0;
            int totalP2 = 0;
            String player1 = score.getP1Name();
            String player2 = score.getP2Name();
            boolean cont = false;

            try{
                totalP1 += Integer.valueOf(p1Blue.getText().toString());
                totalP1 += Integer.valueOf(p1Green.getText().toString());
                totalP1 += Integer.valueOf(p1Yellow.getText().toString());
                totalP1 += Integer.valueOf(p1Purple.getText().toString());
                totalP1 += Integer.valueOf(p1Wonder.getText().toString());
                totalP1 += Integer.valueOf(p1Science.getText().toString());
                int p1TotCoins = Integer.valueOf(p1Money.getText().toString());
                totalP1 += (p1TotCoins/3);
                totalP1 += Integer.valueOf(p1Military.getText().toString());

                totalP2 += Integer.valueOf(p2Blue.getText().toString());
                totalP2 += Integer.valueOf(p2Green.getText().toString());
                totalP2 += Integer.valueOf(p2Yellow.getText().toString());
                totalP2 += Integer.valueOf(p2Purple.getText().toString());
                totalP2 += Integer.valueOf(p2Wonder.getText().toString());
                totalP2 += Integer.valueOf(p2Science.getText().toString());
                int p2TotCoins = Integer.valueOf(p2Money.getText().toString());
                totalP2 += (p2TotCoins/3);
                totalP2 += Integer.valueOf(p2Military.getText().toString());
                cont = true;

            } catch(NumberFormatException e) {
                Toast.makeText(getApplicationContext(), "Please fill out all score values...Even when it's 0", Toast.LENGTH_SHORT).show();
            }

            score.setP1Total(totalP1);
            String p1Score = "" + totalP1;
            p1Total.setText(p1Score);

            score.setP2Total(totalP2);
            String p2Score = "" + totalP2;
            p2Total.setText(p2Score);

            if(cont == true){
                try{
                    if(p1MilitaryWin.isChecked() || p1ScienceWin.isChecked()){
                        if(p1MilitaryWin.isChecked()){
                            Toast.makeText(getApplicationContext(), player1 + " Wins Automatically by Military!", Toast.LENGTH_LONG).show();
                        } else
                        {
                            Toast.makeText(getApplicationContext(), player1 + " Wins Automatically by Science!", Toast.LENGTH_LONG).show();
                        }
                    } else if(p2MilitaryWin.isChecked() || p2ScienceWin.isChecked()){
                        if(p2MilitaryWin.isChecked()){
                            Toast.makeText(getApplicationContext(), player2 + " Wins Automatically by Military!", Toast.LENGTH_LONG).show();
                        } else
                        {
                            Toast.makeText(getApplicationContext(), player2 + " Wins Automatically by Science!", Toast.LENGTH_LONG).show();
                        }
                    } else if (Integer.valueOf(p1Total.getText().toString()) > Integer.valueOf(p2Total.getText().toString())){
                        Toast.makeText(getApplicationContext(), player1 + " Wins!", Toast.LENGTH_LONG).show();
                    } else if (Integer.valueOf(p2Total.getText().toString()) > Integer.valueOf(p1Total.getText().toString())){
                        Toast.makeText(getApplicationContext(), player2 + " Wins!", Toast.LENGTH_LONG).show();
                    } else if (Integer.valueOf(p2Total.getText().toString()).equals(Integer.valueOf(p1Total.getText().toString()))){
                        if(Integer.valueOf(p2Blue.getText().toString()) > Integer.valueOf(p1Blue.getText().toString())){
                            Toast.makeText(getApplicationContext(), player2 + " Wins Tie!" + "\nCivilian Building (Blue Card) Tie Breaker", Toast.LENGTH_LONG).show();
                        } else if(Integer.valueOf(p2Blue.getText().toString()) < Integer.valueOf(p1Blue.getText().toString())){
                            Toast.makeText(getApplicationContext(), player1 + " Wins Tie!" + "\nCivilian Building (Blue Card) Tie Breaker", Toast.LENGTH_LONG).show();
                        } else{
                            Toast.makeText(getApplicationContext(), "You tied!", Toast.LENGTH_LONG).show();
                        }
                    }
                }catch(RuntimeException e) {

                }
            }

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.delete:
                ScoresList scoresList = ScoresList.get(getApplicationContext());
                scoresList.deleteScore(score);
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

    private class P1BlueListener implements TextWatcher{
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if (!keyDel) {
                score.setP1Blue(Integer.parseInt(charSequence.toString()));
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }


    private class P1GreenListener implements TextWatcher{
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if (!keyDel) {
                score.setP1Green(Integer.parseInt(charSequence.toString()));
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }


    private class P1YellowListener implements TextWatcher{
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if (!keyDel) {
                score.setP1Yellow(Integer.parseInt(charSequence.toString()));
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }


    private class P1PurpleListener implements TextWatcher{
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if (!keyDel) {
                score.setP1Purple(Integer.parseInt(charSequence.toString()));
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }


    private class P1WonderListener implements TextWatcher{
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if (!keyDel) {
                score.setP1Wonder(Integer.parseInt(charSequence.toString()));
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }


    private class P1ScienceListener implements TextWatcher{
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if (!keyDel) {
                score.setP1Science(Integer.parseInt(charSequence.toString()));
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }


    private class P1MoneyListener implements TextWatcher{
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if (!keyDel) {
                score.setP1Money(Integer.parseInt(charSequence.toString()));
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }

    private class P1MilitaryListener implements TextWatcher{
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if (!keyDel) {
                score.setP1Military(Integer.parseInt(charSequence.toString()));
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }

    private class P1ScienceVictoryListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            if(p1ScienceWin.isChecked()){
                score.setP1ScienceVic(true);
            }else{
                score.setP1ScienceVic(false);
            }
        }
    }

    private class P1MilitaryVictoryListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            if(p1MilitaryWin.isChecked()){
                score.setP1MilitaryVic(true);
            }else{
                score.setP1MilitaryVic(false);
            }
        }
    }

    ////////////////////////PLAYER 2 LISTENERS///////////////////////

    private class P2BlueListener implements TextWatcher{
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if (!keyDel) {
                score.setP2Blue(Integer.parseInt(charSequence.toString()));
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }


    private class P2GreenListener implements TextWatcher{
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if (!keyDel) {
                score.setP2Green(Integer.parseInt(charSequence.toString()));
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }


    private class P2YellowListener implements TextWatcher{
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if (!keyDel) {
                score.setP2Yellow(Integer.parseInt(charSequence.toString()));
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }


    private class P2PurpleListener implements TextWatcher{
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if (!keyDel) {
                score.setP2Purple(Integer.parseInt(charSequence.toString()));
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }


    private class P2WonderListener implements TextWatcher{
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if (!keyDel) {
                score.setP2Wonder(Integer.parseInt(charSequence.toString()));
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }


    private class P2ScienceListener implements TextWatcher{
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if (!keyDel) {
                score.setP2Science(Integer.parseInt(charSequence.toString()));
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }


    private class P2MoneyListener implements TextWatcher{
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if (!keyDel) {
                score.setP2Money(Integer.parseInt(charSequence.toString()));
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }

    private class P2MilitaryListener implements TextWatcher{
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if (!keyDel) {
                score.setP2Military(Integer.parseInt(charSequence.toString()));
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }

    private class P2ScienceVictoryListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            if(p2ScienceWin.isChecked()){
                score.setP2ScienceVic(true);
            }else{
                score.setP2ScienceVic(false);
            }
        }
    }

    private class P2MilitaryVictoryListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            if(p2MilitaryWin.isChecked()){
                score.setP2MilitaryVic(true);
            }else{
                score.setP2MilitaryVic(false);
            }
        }
    }

}
