package edu.lewis.cs.joshjurss.cookietracker;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class CookieListFragment extends Fragment {

    private RecyclerView recyclerView;
    private CookieDB cookieDB;
    private List<Cookie> cookies;
    private CookieAdapter cookieAdapter;

    public CookieListFragment(){

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_cookie_list, container, false);

        recyclerView = (RecyclerView)v.findViewById(R.id.recycler_view);
        cookieDB = CookieDB.get();
        cookies = cookieDB.getCookies();

        cookieAdapter = new CookieAdapter(cookies);
        recyclerView.setAdapter(cookieAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(cookieAdapter != null){
            cookieAdapter.notifyDataSetChanged();
        }
    }

    //View Holder
    private class CookieHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView cookieNameTextView;
        private Cookie cookie;

        //ALT+INS to get constructor... select none
        public CookieHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            cookieNameTextView = (TextView)itemView.findViewById(R.id.cookie_name_text_view);
        }

        //Takes cookie, binds it to view item
        public void bindCookie(Cookie cookie){
            this.cookie = cookie;
            cookieNameTextView.setText(this.cookie.getName());
        }

        //CTRL+i to get methods to implement
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getActivity(), DetailActivity.class);
            intent.putExtra("id", cookie.getId());
            startActivity(intent);
        }
    }

    //Adapter class - provides data to view holder
    private class CookieAdapter extends RecyclerView.Adapter<CookieHolder>{

        private List<Cookie> cookieList;
        private CookieHolder cookieHolder;

        public CookieAdapter(List<Cookie> cookieList) {
            this.cookieList = cookieList;
        }

        @Override
        public CookieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.cookie_list_item, parent, false);
            cookieHolder = new CookieHolder(view);
            return cookieHolder;
        }

        @Override
        public void onBindViewHolder(CookieHolder holder, int position) {
            Cookie cookie = cookieList.get(position);
            cookieHolder.bindCookie(cookie);
        }

        @Override
        public int getItemCount() {
            return cookieList.size();
        }
    }

}
