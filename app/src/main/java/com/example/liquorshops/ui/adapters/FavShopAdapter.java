package com.example.liquorshops.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.liquorshops.R;
import com.example.liquorshops.jetpack.entities.FavoriteShopsModel;
import com.example.liquorshops.utils.abstracts.OnEventOccurListeners;

import java.util.ArrayList;
import java.util.List;

import static com.example.liquorshops.utils.constants.Constants.urlImgReferenceApiEnd;
import static com.example.liquorshops.utils.constants.Constants.urlImgReferenceApiStart;

public class FavShopAdapter extends RecyclerView.Adapter<FavShopAdapter.FavShopViewHolder> {
  private    ArrayList<FavoriteShopsModel> modelList;
 private  OnEventOccurListeners onEventOccurListener;
    public FavShopAdapter(List<FavoriteShopsModel> modelList, OnEventOccurListeners onEventOccurListener) {
        this.modelList = (ArrayList<FavoriteShopsModel>) modelList;
        this.onEventOccurListener=onEventOccurListener;
    }

    @NonNull
    @Override
    public FavShopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FavShopViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_fav_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FavShopViewHolder holder, int position) {
        holder.bindTo(modelList.get(position));
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    class FavShopViewHolder extends RecyclerView.ViewHolder {
        TextView shopNameTextView, shopAddressTextView, shopRatingTextView;
        ImageView shopImageView,deleteImageView,routeImageView;

        public FavShopViewHolder(@NonNull View itemView) {
            super(itemView);
            shopAddressTextView = itemView.findViewById(R.id.id_text_shop_address);
            shopNameTextView = itemView.findViewById(R.id.id_text_shop_name);
            shopRatingTextView = itemView.findViewById(R.id.id_text_rating);
            shopImageView = itemView.findViewById(R.id.id_image_fav);
            deleteImageView = itemView.findViewById(R.id.id_image_delete);
            routeImageView=itemView.findViewById(R.id.id_image_route);
            routeImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onEventOccurListener.getEventData(modelList.get(getAdapterPosition()));
                }
            });
            deleteImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onEventOccurListener.getEventData(getAdapterPosition());
                }
            });
        }

        void bindTo(FavoriteShopsModel model) {

            Glide.with(itemView.getContext()).load(urlImgReferenceApiStart + model.getShopImg() + urlImgReferenceApiEnd).into(shopImageView);
            shopRatingTextView.append(model.getShopRating());
            shopNameTextView.setText(model.getShopName());
            shopAddressTextView.setText(model.getShopAddress());
        }
    }
}
