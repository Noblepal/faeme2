package com.intelligence.mfarm.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.intelligence.mfarm.R;
import com.intelligence.mfarm.model.SellerItem;
import com.intelligence.mfarm.ui.SellerItemDetailsActivity;

import java.util.ArrayList;

public class FarmerBuyingAdapter extends RecyclerView.Adapter<FarmerBuyingAdapter.ViewHolder> {

    private OnCardClickListener mOnCardListener;
    private ArrayList<SellerItem> mArrayList;
    private Context mCtx;

    public FarmerBuyingAdapter(ArrayList<SellerItem> mArrayList, Context mCtx, OnCardClickListener onCardClickListener) {
        this.mOnCardListener = onCardClickListener;
        this.mArrayList = mArrayList;
        this.mCtx = mCtx;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView mItemName, mSellerName, mItemCost, mItemPostTime, mItemDescription;
        ImageButton mPopupImageBtn;
        ImageView mItemImage;
        MaterialCardView materialCardView;

        OnCardClickListener onCardClickListener;

        public ViewHolder(@NonNull View itemView, OnCardClickListener onCardClickListener) {
            super(itemView);
            mItemName = itemView.findViewById(R.id.provider_item_name);
            mSellerName = itemView.findViewById(R.id.provider_name);
            mItemDescription = itemView.findViewById(R.id.provider_item_description);
            mItemPostTime = itemView.findViewById(R.id.provider_item_post_time);
            mItemCost = itemView.findViewById(R.id.provider_item_price);
            mItemImage = itemView.findViewById(R.id.provider_item_image);
            mPopupImageBtn = itemView.findViewById(R.id.popup_btn_cards);
            materialCardView = itemView.findViewById(R.id.card_parent_item);

            this.onCardClickListener = onCardClickListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onCardClickListener.onCardClick(getAdapterPosition());
        }
    }

    public interface OnCardClickListener {
        void onCardClick(int position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.provider_list_item, parent, false);
        return new ViewHolder(v, mOnCardListener);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        SellerItem item = mArrayList.get(position);

        holder.mItemName.setText(item.getItem_name());
        holder.mSellerName.setText(item.getSeller_name());
        holder.mItemCost.setText(item.getItem_cost());
        holder.mItemDescription.setText(item.getItem_description());
        holder.mItemPostTime.setText(item.getItem_post_time());
        holder.mItemImage.setImageResource(item.getImage_resource());

        holder.mPopupImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(mCtx, holder.mPopupImageBtn);
                popupMenu.inflate(R.menu.provider_cards_popup_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.provider_menu_contact:
                                break;
                            case R.id.provider_menu_more_by_seller:
                                break;
                            case R.id.provider_menu_report:
                                break;
                            case R.id.provider_menu_save:
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });
//            holder.materialCardView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                Intent intent = new Intent(mCtx, SellerItemDetailsActivity.class);
//                mCtx.startActivity(intent);
//            }
//       });
    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }
}
