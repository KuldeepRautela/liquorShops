package com.example.liquorshops.ui.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.liquorshops.R;
import com.example.liquorshops.jetpack.entities.AdvisorModel;
import com.example.liquorshops.ui.adapters.AdvisorAdapter;
import com.example.liquorshops.utils.abstracts.OnEventOccurListeners;

import java.util.ArrayList;

public class AdvisorFrag extends Fragment {
    ArrayList<AdvisorModel> advisorModelArrayList;
    int noOfQuesDone = 0;
    int totalScore = 0;
    private AdvisorAdapter advisorAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_advisor, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView advisorRecyclerView = view.findViewById(R.id.id_recycler_advisor);
        advisorModelArrayList = new ArrayList<>();
        addQues(getString(R.string.string_text_advisor_question_one), getString(R.string.string_text_advisor_answer_one).split(","));
        addQues(getString(R.string.string_text_advisor_question_two), getString(R.string.string_text_advisor_answer_two).split(","));
        addQues(getString(R.string.string_text_advisor_question_three), getString(R.string.string_text_advisor_answer_three).split(","));
        addQues(getString(R.string.string_text_advisor_question_four), getString(R.string.string_text_advisor_answer_four).split(","));
        addQues(getString(R.string.string_text_advisor_question_five), getString(R.string.string_text_advisor_answer_five).split(","));
        addQues(getString(R.string.string_text_advisor_question_six), getString(R.string.string_text_advisor_answer_six).split(","));
        addQues(getString(R.string.string_text_advisor_question_seven), getString(R.string.string_text_advisor_answer_seven).split(","));
        addQues(getString(R.string.string_text_advisor_question_eight), getString(R.string.string_text_advisor_answer_eight).split(","));
        addQues(getString(R.string.string_text_advisor_question_nine), getString(R.string.string_text_advisor_answer_nine).split(","));
        addQues(getString(R.string.string_text_advisor_question_ten), getString(R.string.string_text_advisor_answer_ten).split(","));
        OnEventOccurListeners listener = new OnEventOccurListeners() {
            @Override
            public void getEventData(Object object, int comeFor) {
                super.getEventData(object, comeFor);
                noOfQuesDone++;
                int position = (Integer) object;
                int points = comeFor;
                if (position == 8 || position == 9) {
                    if (points == 1)
                        totalScore += 2;
                    else if (points == 2)
                        totalScore += 4;
                } else
                    totalScore += points;
                Log.e("position ", position + "");
                Log.e("total score and ques", totalScore + " and " + noOfQuesDone);
                advisorAdapter.notifyItemChanged(position);
                if (noOfQuesDone == 10)
                    showResult(totalScore);
            }
        };
        advisorAdapter = new AdvisorAdapter(advisorModelArrayList, listener);
        advisorRecyclerView.setAdapter(advisorAdapter);
        advisorRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    }

    private void addQues(String ques, String[] answers) {
        if (answers.length == 3)
            advisorModelArrayList.add(new AdvisorModel(ques, answers[0], answers[1], answers[2]));
        else
            advisorModelArrayList.add(new AdvisorModel(ques, answers[0], answers[1], answers[2], answers[3], answers[4]));
    }

    private void showResult(int totalScore) {
        Toast.makeText(getContext(), "Your Score is " + totalScore, Toast.LENGTH_SHORT).show();
    }
}
