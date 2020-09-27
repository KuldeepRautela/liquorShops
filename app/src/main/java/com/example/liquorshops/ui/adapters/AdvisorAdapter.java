package com.example.liquorshops.ui.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liquorshops.R;
import com.example.liquorshops.jetpack.entities.AdvisorModel;
import com.example.liquorshops.utils.abstracts.OnEventOccurListeners;

import java.util.ArrayList;

public class AdvisorAdapter extends RecyclerView.Adapter<AdvisorAdapter.AdvisorViewHolder> {
    private ArrayList<AdvisorModel> modelList;
    private OnEventOccurListeners listener;
    private boolean checkedAns[];
    private int checkedAnsPosition[];

    @NonNull
    @Override
    public AdvisorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdvisorViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_advisor, parent, false));
    }

    public AdvisorAdapter( ArrayList<AdvisorModel> advisorModelArrayList, OnEventOccurListeners listener) {
        this.modelList = advisorModelArrayList;
        this.listener = listener;
        this.checkedAns = new boolean[advisorModelArrayList.size()];
        this.checkedAnsPosition = new int[advisorModelArrayList.size()];
        for (int i = 0; i < advisorModelArrayList.size(); i++)
            checkedAns[i] = false;
    }

    @Override
    public void onBindViewHolder(@NonNull AdvisorViewHolder holder, int position) {
        holder.bindTo(modelList.get(position));
        holder.ansOneTextView.setBackgroundResource(R.drawable.ic_rect_stroke_text_view_border);
        holder.ansTwoTextView.setBackgroundResource(R.drawable.ic_rect_stroke_text_view_border);
        holder.ansThreeTextView.setBackgroundResource(R.drawable.ic_rect_stroke_text_view_border);
        holder.ansFourTextView.setBackgroundResource(R.drawable.ic_rect_stroke_text_view_border);
        holder.ansFiveTextView.setBackgroundResource(R.drawable.ic_rect_stroke_text_view_border);
        Log.e("cheked position ",position +" and "+checkedAnsPosition[position]);
        if (checkedAns[position]) {
            switch (checkedAnsPosition[position]) {
                case 1:
                    holder.ansOneTextView.setBackgroundResource(R.drawable.ic_rect_cocktail);
                    break;
                case 2:
                    holder.ansTwoTextView.setBackgroundResource(R.drawable.ic_rect_cocktail);
                    break;
                case 3:
                    holder.ansThreeTextView.setBackgroundResource(R.drawable.ic_rect_cocktail);
                    break;
                case 4:
                    holder.ansFourTextView.setBackgroundResource(R.drawable.ic_rect_cocktail);
                    break;
                case 5:
                    holder.ansFiveTextView.setBackgroundResource(R.drawable.ic_rect_cocktail);
                    break;
            }
        }
        if (modelList.get(position).getAnsFour() != null)
            holder.ansFourTextView.setText(modelList.get(position).getAnsFour());
        else
            holder.ansFourTextView.setVisibility(View.GONE);
        if (modelList.get(position).getAnsFive() != null)
            holder.ansFiveTextView.setText(modelList.get(position).getAnsFive());
        else
            holder.ansFiveTextView.setVisibility(View.GONE);

    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    class AdvisorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView quesTextView, ansOneTextView, ansTwoTextView, ansThreeTextView, ansFourTextView, ansFiveTextView;
        void bindTo(AdvisorModel model){
            quesTextView.setText(model.getQuestion());
            ansOneTextView.setText(model.getAnsOne());
            ansTwoTextView.setText(model.getAnsTwo());
            ansThreeTextView.setText(model.getAnsThree());
        }
        public AdvisorViewHolder(@NonNull View itemView) {
            super(itemView);
            quesTextView = itemView.findViewById(R.id.id_adapter_text_ques);
            ansOneTextView = itemView.findViewById(R.id.id_text_ans_one);
            ansTwoTextView = itemView.findViewById(R.id.id_text_ans_two);
            ansThreeTextView = itemView.findViewById(R.id.id_text_ans_three);
            ansFourTextView = itemView.findViewById(R.id.id_text_ans_four);
            ansFiveTextView = itemView.findViewById(R.id.id_text_ans_five);
            ansOneTextView.setOnClickListener(this);
            ansTwoTextView.setOnClickListener(this);
            ansThreeTextView.setOnClickListener(this);
            ansFourTextView.setOnClickListener(this);
            ansFiveTextView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            view.setBackgroundResource(R.drawable.ic_rect_cocktail);
            checkedAns[getAdapterPosition()] = true;
//           itemView.setAlpha(0.5f);
            switch (view.getId()) {
                case R.id.id_text_ans_one:
                    this.ansTwoTextView.setClickable(false);
                    this.ansThreeTextView.setClickable(false);
                    this.ansFourTextView.setClickable(false);
                    this.ansFiveTextView.setClickable(false);
                    checkedAnsPosition[getAdapterPosition()] = 1;
                    listener.getEventData(getAdapterPosition(), 0);
                    break;
                case R.id.id_text_ans_two:
                    this.ansOneTextView.setClickable(false);
                    this.ansThreeTextView.setClickable(false);
                    this.ansFourTextView.setClickable(false);
                    this.ansFiveTextView.setClickable(false);
                    checkedAnsPosition[getAdapterPosition()] = 2;
                    listener.getEventData(getAdapterPosition(), 1);
                    break;
                case R.id.id_text_ans_three:
                    checkedAnsPosition[getAdapterPosition()] = 3;
                    this.ansTwoTextView.setClickable(false);
                    this.ansOneTextView.setClickable(false);
                    this.ansFourTextView.setClickable(false);
                    this.ansFiveTextView.setClickable(false);
                    listener.getEventData(getAdapterPosition(), 2);
                    break;
                case R.id.id_text_ans_four:
                    this.ansTwoTextView.setClickable(false);
                    this.ansThreeTextView.setClickable(false);
                    this.ansOneTextView.setClickable(false);
                    this.ansFiveTextView.setClickable(false);
                    checkedAnsPosition[getAdapterPosition()] = 4;
                    listener.getEventData(getAdapterPosition(), 3);
                    break;
                case R.id.id_text_ans_five:
                    this.ansTwoTextView.setClickable(false);
                    this.ansThreeTextView.setClickable(false);
                    this.ansFourTextView.setClickable(false);
                    this.ansOneTextView.setClickable(false);
                    checkedAnsPosition[getAdapterPosition()] = 5;
                    listener.getEventData(getAdapterPosition(), 4);
                    break;

            }
        }
    }

}
