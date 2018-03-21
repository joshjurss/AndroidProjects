package edu.lewis.cs.joshjurss.cookietracker;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import java.util.UUID;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.detail_fragment_container);
        if(fragment == null){
            UUID cookieID = (UUID)getIntent().getSerializableExtra("id");
            fragment = CookieDetailFragment.newInstance(cookieID);
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.detail_fragment_container, fragment);
            fragmentTransaction.commit();
        }

    }
}
