package com.example.liquorshops.ui.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.liquorshops.R;
import com.example.liquorshops.jetpack.entities.ResultModel;
import com.example.liquorshops.utils.abstracts.OnEventOccurListeners;
import com.example.liquorshops.utils.constants.Constants;

import java.util.ArrayList;

public class ShopListAdapter extends RecyclerView.Adapter<ShopListAdapter.ShopListViewHolder> {
    private Context context;
    private ArrayList<ResultModel> resultModelArrayList;
    private OnEventOccurListeners listener;
    public ShopListAdapter(Context context, ArrayList<ResultModel> resultModels, OnEventOccurListeners listener) {
        this.context = context;
        this.resultModelArrayList = resultModels;
        this.listener=listener;
    }

    @NonNull
    @Override
    public ShopListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ShopListViewHolder(LayoutInflater.from(context).inflate(R.layout.adapter_liquor_shop, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ShopListViewHolder holder, int position) {
        holder.setData(resultModelArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return resultModelArrayList.size();
    }

    class ShopListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView shopImageView, favImageView;
        TextView shopNameTextView, ratingTextView, shopAddressTextView;

        public ShopListViewHolder(@NonNull View itemView) {
            super(itemView);
            shopImageView = itemView.findViewById(R.id.id_image_shop);
            favImageView = itemView.findViewById(R.id.id_image_fav);
            shopNameTextView = itemView.findViewById(R.id.id_text_shop_name);
            ratingTextView = itemView.findViewById(R.id.id_text_rating);
            shopAddressTextView = itemView.findViewById(R.id.id_text_shop_address);
            favImageView.setOnClickListener(this);
            ImageView directionImageView = itemView.findViewById(R.id.id_image_direction);
            directionImageView.setOnClickListener(this);
        }

        public void setData(ResultModel resultModel) {
           try{
               String url = Constants.urlImgReferenceApiStart + resultModel.getPhotoModels().get(0).getPhoto_reference() + Constants.urlApiEnd;
            Log.e("url is ", url);
            Glide.with(context).load(url).into(shopImageView);
           }
           catch(Exception exception){

           }
            shopNameTextView.setText(resultModel.getName());
            shopAddressTextView.setText(resultModel.getAddress());
            ratingTextView.append(resultModel.getRating() + "");
        }

        @Override
        public void onClick(View view) {
            ImageView imageView = (ImageView) view;
            if(view.getId()==R.id.id_image_fav) {
                imageView.setImageResource(R.drawable.ic_favorite_red_24dp);
                listener.getEventData(resultModelArrayList.get(getAdapterPosition()));
            }
            else {
                listener.getEventData(getAdapterPosition()+"");
            }
        }
    }
}
