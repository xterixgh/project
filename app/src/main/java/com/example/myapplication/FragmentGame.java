package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import java.util.Arrays;
import java.util.Random;

public class FragmentGame extends Fragment implements View.OnClickListener {
    private String[] words = {"авто", "человек", "киви", "подушка", "науру", "туатара", "боливия", "хибара", "нефрит", "наина", "хозяин", "фатализм"};
    private String[] questions = {"Транспортное средство", "Существо обладающее разумом", "Фрукт с зеленой мякотью", "Ткань с перьями", "Страна без столицы"
            , "Какое животное имеет 3 глаза?", "Страна с двумя столицами", "Бедный, неказистый домишко, избенка", "И болезнь, и камень",
            "Какое женское имя придумал Пушкин?", "Организм, в котором живет паразит", "Вера в предопределенность событий"};
    private String space = " ";
    private String word;
    private int remainingAttempts = 6;
    private int guessedLetters = 0;
    private boolean[] wordMask;
    private boolean[] letterUsed = new boolean[26];
    private ImageView healthView;
    private ImageView healthView2;
    private ImageView healthView3;
    private ImageView healthView4;
    private ImageView healthView5;
    private ImageView healthView6;


    GameViewModel viewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(requireActivity()).get(GameViewModel.class);
        View view = inflater.inflate(R.layout.fragmentgame, container, false);
        int wordIndex = new Random().nextInt(words.length);
        word = words[wordIndex];
        wordMask = new boolean[word.length()];
        Arrays.fill(wordMask, false);
        guessedLetters = 0;
        remainingAttempts = 6;
        TextView wordView = view.findViewById(R.id.word);
        TextView questionView = view.findViewById(R.id.questions);
        TextView spaceView = view.findViewById(R.id.space);
        String question = questions[wordIndex];
        StringBuilder guessWord = new StringBuilder(word.length());
        for (int i = 0; i < word.length(); i++) {
            guessWord.append("*");
        }
        wordView.setText(guessWord.toString());
        questionView.setText(question);
        spaceView.setText(space);
        healthView = view.findViewById(R.id.health);
        healthView2 = view.findViewById(R.id.healthTwo);
        healthView3 = view.findViewById(R.id.healthThree);
        healthView4 = view.findViewById(R.id.healthFour);
        healthView5 = view.findViewById(R.id.healthFive);
        healthView6 = view.findViewById(R.id.healthSix);
        healthView.setImageResource(R.drawable.health);
        healthView2.setImageResource(R.drawable.health);
        healthView3.setImageResource(R.drawable.health);
        healthView4.setImageResource(R.drawable.health);
        healthView5.setImageResource(R.drawable.health);
        healthView6.setImageResource(R.drawable.health);
        ViewGroup lettersContainer = view.findViewById(R.id.letters_container);
        for (char c = 'а'; c <= 'я'; c++) {
            Button letterButton = new Button(getContext());
            letterButton.setText(String.valueOf(c));
            letterButton.setTag(c);
            letterButton.setOnClickListener(this);
            lettersContainer.addView(letterButton);
        }

        return view;
    }

    @Override
    public void onClick(View v) {
        Button btn = (Button) v;
        char letter = (char) btn.getTag();
        btn.setEnabled(false);
        boolean hasLetter = false;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == letter) {
                hasLetter = true;
                wordMask[i] = true;
                guessedLetters++;
            }
        }

        TextView wordView = getView().findViewById(R.id.word);
        StringBuilder guessWord = new StringBuilder(word.length());
        for (int i = 0; i < word.length(); i++) {
            if (wordMask[i]) {
                guessWord.append(word.charAt(i));
            } else {
                guessWord.append("*");
            }
        }
        wordView.setText(guessWord.toString());


        if (hasLetter) {
            if (guessedLetters == word.length()) {
                viewModel.getResult().setValue(true);
                getParentFragmentManager().beginTransaction().replace(R.id.fragmnetgo, new FragmentEndGame()).commit();

            }
        } else {
            remainingAttempts--;
            if (remainingAttempts == 0) {
                setHangmanImage(remainingAttempts);
                viewModel.getResult().setValue(false);
                getParentFragmentManager().beginTransaction().replace(R.id.fragmnetgo, new FragmentEndGame()).commit();

            } else {
                setHangmanImage(remainingAttempts);
            }
        }
    }


    private void setHangmanImage(int attempt) {
        switch (attempt) {
            case 5:
                healthView.setVisibility(View.GONE);
                break;
            case 4:
                healthView2.setVisibility(View.GONE);
                break;
            case 3:
                healthView3.setVisibility(View.GONE);
                break;
            case 2:
                healthView4.setVisibility(View.GONE);
                break;
            case 1:
                healthView5.setVisibility(View.GONE);
                break;
            case 0:
                healthView6.setVisibility(View.GONE);
                break;
        }
    }

}

