package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class FragmentInfo extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentinfo, container, false);
        TextView textInfo = view.findViewById(R.id.text_info);
        textInfo.setTextSize(20);
        textInfo.setText("Игра \"Поле чудес\" от Xterix - это игра, смысл которой заключен в отгадывании зашифрованного слова. \n" +
                "На отгадовние слова дается 6 попыток, они выглядят в виде сердечек, за каждую неправильную букву минусуется сердце.\n" +
                "Рядом со словом появляется вопрос, который помогает его отгадать.");

        Button backButton = view.findViewById(R.id.back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager().popBackStack();

            }
        });
        return view;
    }
}
