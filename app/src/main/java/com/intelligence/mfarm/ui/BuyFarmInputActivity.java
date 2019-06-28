package com.intelligence.mfarm.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.MenuItem;

import com.intelligence.mfarm.R;
import com.intelligence.mfarm.adapters.FarmerBuyingAdapter;
import com.intelligence.mfarm.model.SellerItem;

import org.parceler.Parcels;

import java.util.ArrayList;

public class BuyFarmInputActivity extends AppCompatActivity implements FarmerBuyingAdapter.OnCardClickListener {

    private RecyclerView mRecyclerView;
    private FarmerBuyingAdapter mAdapter;
    private ArrayList<SellerItem> mSellerItemArrayList = new ArrayList<>();
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_farm_input);
        getSupportActionBar().setTitle("Buy Farm Input");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initLayout();
    }

    private void initLayout() {
        mRecyclerView = findViewById(R.id.provider_items_recycler_view);
        mLayoutManager = new GridLayoutManager(this, 2);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mSellerItemArrayList.add(new SellerItem("Carrots", "Ksh. 2,750", "2 days",
                "Fresh orange carrots", "Josphat Njoroge", R.drawable.carrots));
        mSellerItemArrayList.add(new SellerItem("Rice", "Ksh. 8,300", "12 mins",
                "Freshly harvested pishori rice from Mwea Tebere irrigation sector", "Mkulima Bora", R.drawable.rice));
        mSellerItemArrayList.add(new SellerItem("Cabbage", "Ksh. 6,210", "42 mins",
                "Fresh cabbages", "Wanjiku Wainaina", R.drawable.cabbage));
        mSellerItemArrayList.add(new SellerItem("Beans", "Ksh. 1,250", "Just now",
                "Yellow beans, freshly harvested", "George Mwangi", R.drawable.beans));
        mSellerItemArrayList.add(new SellerItem("Carrots", "Ksh. 2,750", "2 days",
                "Fresh orange carrots", "Josphat Njoroge", R.drawable.carrots));
        mSellerItemArrayList.add(new SellerItem("Rice", "Ksh. 8,300", "12 mins",
                "Freshly harvested pishori rice from Mwea Tebere irrigation sector", "Mkulima Bora", R.drawable.rice));
        mSellerItemArrayList.add(new SellerItem("Cabbage", "Ksh. 6,210", "42 mins",
                "Fresh cabbages", "Wanjiku Wainaina", R.drawable.cabbage));
        mSellerItemArrayList.add(new SellerItem("Beans", "Ksh. 1,250", "Just now",
                "Yellow beans, freshly harvested", "George Mwangi", R.drawable.beans));

        mAdapter = new FarmerBuyingAdapter(mSellerItemArrayList, this, this);
        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onCardClick(int position) {
        mSellerItemArrayList.get(position);
        Intent intent = new Intent(this, SellerItemDetailsActivity.class);
        intent.putExtra("item", Parcels.wrap(mSellerItemArrayList));
        intent.putExtra("pos", position);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
