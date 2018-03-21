package edu.lewis.cs.joshjurss.viewtester2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private RatingBar ratingBar;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ratingBar = (RatingBar) findViewById(R.id.rating_bar);
        ratingBar.setOnRatingBarChangeListener(new RatinBarChangeListener());
        spinner = (Spinner)findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(new SpinListener());
    }

    private class RatinBarChangeListener implements RatingBar.OnRatingBarChangeListener {
        @Override
        public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
            String toastString = rating + " stars";
            Toast.makeText(getApplicationContext(), toastString, Toast.LENGTH_SHORT).show();
        }
    }

    private class SpinListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            String color = (String)parent.getItemAtPosition(position);
            Toast.makeText(getApplicationContext(), color, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }
}
