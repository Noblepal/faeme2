package com.intelligence.mfarm.model;

import android.widget.ImageView;

import org.parceler.Parcel;

@Parcel
public class SellerItem {
    public String item_name, item_cost, item_post_time,
            item_description, seller_name;
    private int image_resource;

    public SellerItem(String item_name, String item_cost, String item_post_time, String item_description, String seller_name, int image_resource) {
        this.item_name = item_name;
        this.item_cost = item_cost;
        this.item_post_time = item_post_time;
        this.item_description = item_description;
        this.seller_name = seller_name;
        this.image_resource = image_resource;
    }

    public SellerItem() {
    }

    public String getItem_name() {
        return item_name;
    }

    public int getImage_resource() {
        return image_resource;
    }

    public void setImage_resource(int image_resource) {
        this.image_resource = image_resource;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_cost() {
        return item_cost;
    }

    public void setItem_cost(String item_cost) {
        this.item_cost = item_cost;
    }

    public String getItem_post_time() {
        return item_post_time;
    }

    public void setItem_post_time(String item_post_time) {
        this.item_post_time = item_post_time;
    }

    public String getItem_description() {
        return item_description;
    }

    public void setItem_description(String item_description) {
        this.item_description = item_description;
    }

    public String getSeller_name() {
        return seller_name;
    }

    public void setSeller_name(String seller_name) {
        this.seller_name = seller_name;
    }
}
