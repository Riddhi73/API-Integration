package com.example.retrofit.NavFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.retrofit.R;
import com.example.retrofit.SharedPreference;


public class DashboardFragment extends Fragment {

    TextView etname_dash,etemail_dash;
    SharedPreference sharedPreference;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        etemail_dash = view.findViewById(R.id.etemail_dash);
        etname_dash = view.findViewById(R.id.etname_dash);

        sharedPreference = new SharedPreference(getActivity());
        String userName = "Hey! " + sharedPreference.getuser().getUsername();
        etname_dash.setText(userName);
        etemail_dash.setText(sharedPreference.getuser().getEmail());

        return view;
    }
}