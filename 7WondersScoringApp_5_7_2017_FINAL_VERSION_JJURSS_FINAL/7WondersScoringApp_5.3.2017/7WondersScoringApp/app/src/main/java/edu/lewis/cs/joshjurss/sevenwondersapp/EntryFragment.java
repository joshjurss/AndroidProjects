package edu.lewis.cs.joshjurss.sevenwondersapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class EntryFragment extends Fragment {


    public EntryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_entry, container, false);

        Button newGameBtn = (Button) v.findViewById(R.id.new_game_btn);
        Button pastGameBtn = (Button) v.findViewById(R.id.past_games_btn);

        newGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ScoringActivity.class);
                startActivity(intent);
            }
        });

        pastGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ListActivity.class);
                startActivity(intent);

                FragmentManager fragmentManager = getFragmentManager();
                Fragment fragment = fragmentManager.findFragmentById(R.id.list_fragment_container);

                if (fragment == null) {
                    fragment = new EntryFragment();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.add(R.id.list_fragment_container, fragment);
                    fragmentTransaction.commit();
                }
            }
        });


        return v;
    }
}