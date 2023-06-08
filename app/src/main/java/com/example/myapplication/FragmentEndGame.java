package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class FragmentEndGame extends Fragment {

    GameViewModel viewModel;

    public  FragmentEndGame(){
        super(R.layout.fragmentendgame);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        TextView textView = view.findViewById(R.id.reset_text);
        Button buttonReset = view.findViewById(R.id.reset);
        viewModel = new ViewModelProvider(requireActivity()).get(GameViewModel.class);
        viewModel.getResult().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isWin) {
                if(isWin){
                    textView.setTextSize(18);
                    textView.setText("Вы выиграли, желаете начать заново?");
                }
                else {
                    textView.setTextSize(18);
                    textView.setText("Вы проиграли, желаете начать заново?");
                }
            }
        });

        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                com.example.myapplication.Fragment fragment = new com.example.myapplication.Fragment();
                getParentFragmentManager().beginTransaction().replace(R.id.fragmnetgo, fragment).commit();
            }
        });
    }
}
