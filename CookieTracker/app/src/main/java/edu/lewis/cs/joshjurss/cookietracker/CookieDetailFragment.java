package edu.lewis.cs.joshjurss.cookietracker;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.UUID;

/**
 * A simple {@link Fragment} subclass.
 */
public class CookieDetailFragment extends Fragment {

    private Cookie cookie;
    private EditText cookieNameEditText;
    private EditText cookieToppingEditText;
    private CheckBox healthyCheckBox;

    public static CookieDetailFragment newInstance(UUID cookieId){
        Bundle args = new Bundle();
        args.putSerializable("id", cookieId);
        CookieDetailFragment fragment = new CookieDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public CookieDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UUID cookieId = (UUID)getArguments().getSerializable("id");
        cookie = CookieDB.get().getCookie(cookieId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_cookie_detail, container, false);

        cookieNameEditText = (EditText)v.findViewById(R.id.cookie_name_edit_text);
        cookieNameEditText.addTextChangedListener(new NameTextListener());

        cookieToppingEditText = (EditText)v.findViewById(R.id.topping_edit_text);
        cookieToppingEditText.addTextChangedListener(new ToppingTextListener());

        healthyCheckBox = (CheckBox)v.findViewById(R.id.healthy_check_box);
        healthyCheckBox.setOnClickListener(new HealthyCheckListener());

        cookieNameEditText.setText(cookie.getName());
        cookieToppingEditText.setText(cookie.getTopping());
        healthyCheckBox.setChecked(cookie.isHealthy());

        return v;
    }

    private class HealthyCheckListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            cookie.setHealthy(healthyCheckBox.isChecked());
        }
    }

    private class NameTextListener implements TextWatcher{
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            cookie.setName(charSequence.toString());
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }

    private class ToppingTextListener implements TextWatcher{
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            cookie.setTopping(charSequence.toString());
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }

}
