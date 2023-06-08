package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Fragment extends androidx.fragment.app.Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment, container, false);

        Button startButton = view.findViewById(R.id.start_button);
        Button infoButton = view.findViewById(R.id.info);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentGame fragmentGame = new FragmentGame();
                getParentFragmentManager().beginTransaction().replace(R.id.fragmnetgo, fragmentGame).commit();
            }
        });
        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentInfo fragmentInfo = new FragmentInfo();
                getParentFragmentManager().beginTransaction().replace(R.id.fragmnetgo, fragmentInfo).addToBackStack("start").commit();
            }
        });

        return view;
    }

}
