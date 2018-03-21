package edu.lewis.cs.joshjurss.quizapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private static final String TAG = "QuizApp";

    private Button trueButton;
    private Button falseButton;
    private ImageButton nextButton;
    private ImageButton previousButton;
    private TextView questionTextView;

    private Question[] questions = new Question[]{
        new Question(R.string.question1, false),
        new Question(R.string.question2, true),
        new Question(R.string.question3, false)
    };

    private int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setContentView(R.layout.activity_quiz);

        if(savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt("index");
        }

        trueButton = (Button)findViewById(R.id.true_button);
        falseButton = (Button)findViewById(R.id.false_button);

        trueButton.setOnClickListener(new TrueClickListener());
        falseButton.setOnClickListener(new FalseClickListener());

        nextButton = (ImageButton)findViewById(R.id.next_button);
        nextButton.setOnClickListener(new NextClickListener());

        previousButton = (ImageButton)findViewById(R.id.previous_button);
        previousButton.setOnClickListener(new PreviousClickListener());

        questionTextView = (TextView)findViewById(R.id.question_textView);
        updateQuestion();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState");
        outState.putInt("index", currentIndex);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    private void updateQuestion(){
        int question = questions[currentIndex].getTextResId();
        questionTextView.setText(question);
    }

    private void checkAnswer(boolean userPress){
        boolean correctAnswer = questions[currentIndex].isAnswerTrue();
        int toastTextId;

        if(userPress == correctAnswer){
            toastTextId = R.string.correct_toast;
        }else{
            toastTextId = R.string.incorrect_toast;
        }

        Toast.makeText(this, toastTextId, Toast.LENGTH_SHORT).show();
    }

    private class TrueClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            checkAnswer(true);
        }
    }

    private class FalseClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            checkAnswer(false);
        }
    }

    private class NextClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            currentIndex = (currentIndex + 1) % questions.length;
            updateQuestion();
        }
    }

    private class PreviousClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            currentIndex = (currentIndex - 1);
            if(currentIndex < 0){
                currentIndex = (questions.length - 1);
            }
            updateQuestion();
        }
    }
}