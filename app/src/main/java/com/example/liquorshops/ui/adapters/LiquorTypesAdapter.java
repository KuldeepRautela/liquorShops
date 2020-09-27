package com.example.liquorshops.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liquorshops.R;
import com.example.liquorshops.jetpack.entities.LiquorModel;
import com.example.liquorshops.utils.abstracts.OnEventOccurListeners;

import java.util.ArrayList;

public class LiquorTypesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private OnEventOccurListeners onEventOccurListeners;
    private ArrayList<LiquorModel> modelList;

    public LiquorTypesAdapter(ArrayList<LiquorModel> liquorsArrayList, OnEventOccurListeners onEventOccurListeners) {
        this.modelList = liquorsArrayList;
        this.onEventOccurListeners = onEventOccurListeners;

    }

    public LiquorTypesAdapter(ArrayList<LiquorModel> liquorsArrayList) {
        this.modelList = liquorsArrayList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0)
            return new LiquorTypesViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_liquor_types, parent, false));
        else
            return new LiquorBrandsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adpter_liquor_brands, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == 0) {
            holder = (LiquorTypesViewHolder) holder;
            ((LiquorTypesViewHolder) holder).setData(modelList.get(position));
        } else {
            holder = (LiquorBrandsViewHolder) holder;
            ((LiquorBrandsViewHolder) holder).setData(modelList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return modelList.get(position).getViewType();
    }

    class LiquorTypesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView liquorNameTextView, liquorDetailTextView, brandsTextView;

        public LiquorTypesViewHolder(@NonNull View itemView) {
            super(itemView);
            liquorNameTextView = itemView.findViewById(R.id.id_text_title_liquor);
            brandsTextView = itemView.findViewById(R.id.id_text_brands);
            liquorDetailTextView = itemView.findViewById(R.id.id_text_content_liquor);
            brandsTextView.setOnClickListener(this);
        }

        public void setData(LiquorModel liquor) {
            liquorNameTextView.setText(liquor.getLiquorName());
            liquorDetailTextView.setText(liquor.getLiquorDetails());
        }

        @Override
        public void onClick(View v) {
            onEventOccurListeners.getEventData(getAdapterPosition());
        }
    }

    class LiquorBrandsViewHolder extends RecyclerView.ViewHolder {
        TextView brandNameTextView, brandDetailTextView;
        ImageView brandImgImageView;

        public LiquorBrandsViewHolder(@NonNull View itemView) {
            super(itemView);
            brandNameTextView = itemView.findViewById(R.id.id_text_brand_title);
            brandDetailTextView = itemView.findViewById(R.id.id_text_brand_detail);
            brandImgImageView = itemView.findViewById(R.id.id_image_brand);
        }

        public void setData(LiquorModel liquorModel) {
            brandNameTextView.setText(liquorModel.getLiquorName());
            brandDetailTextView.setText(liquorModel.getLiquorDetails());
            if (liquorModel.getLiquorImg() == 0)
                brandImgImageView.setVisibility(View.GONE);
            else
                brandImgImageView.setBackgroundResource(liquorModel.getLiquorImg());
        }


    }
}
