package com.intelligence.mfarm.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.palette.graphics.Palette;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.intelligence.mfarm.R;
import com.intelligence.mfarm.adapters.FarmerBuyingAdapter;
import com.intelligence.mfarm.model.SellerItem;

import org.parceler.Parcels;

import java.util.ArrayList;

public class SellerItemDetailsActivity extends AppCompatActivity implements FarmerBuyingAdapter.OnCardClickListener {

    private Menu menu;
    AppBarLayout mAppBarLayout;
    CoordinatorLayout coordinatorLayout;
    Activity mActivity;

    TextView tv_item_name, tv_item_description, tv_item_seller_name, tv_item_cost;
    ImageView toolbarImage;
    private SellerItem sellerItem;
    private ArrayList<SellerItem> mSellerItemArrayList = new ArrayList<>();

    //private ShimmerFrameLayout mShimmerViewContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_item_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Unwrap parcel
        int position = getIntent().getIntExtra("pos", 0);
        mSellerItemArrayList = Parcels.unwrap(getIntent().getParcelableExtra("item"));

        tv_item_name = findViewById(R.id.details_item_name);
        tv_item_description = findViewById(R.id.details_item_description);
        tv_item_seller_name = findViewById(R.id.details_seller_name);
        tv_item_cost = findViewById(R.id.details_item_cost);
        toolbarImage = findViewById(R.id.expandedImage);


        //toolbar.setTitle(mSellerItemArrayList.get(position).getSeller_name());
        tv_item_seller_name.setText(mSellerItemArrayList.get(position).getSeller_name());
        tv_item_name.setText(mSellerItemArrayList.get(position).getItem_name());
        tv_item_description.setText(mSellerItemArrayList.get(position).getItem_description());
        tv_item_cost.setText(mSellerItemArrayList.get(position).getItem_cost());
        toolbarImage.setImageResource(mSellerItemArrayList.get(position).getImage_resource());
        //mShimmerViewContainer = findViewById(R.id.shimmer_view_container);

        //final Display dWidth = getWindowManager().getDefaultDisplay();
        final Point size = new Point();
        mActivity = this;
        mActivity.getWindowManager().getDefaultDisplay().getSize(size);
        mAppBarLayout = findViewById(R.id.app_bar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mAppBarLayout.post(new Runnable() {
            @Override
            public void run() {
                int heightPx = size.x / 3;
                setAppBarOffset(heightPx);
            }
        });

        final CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.toolbar_layout);
        collapsingToolbarLayout.setTitle(mSellerItemArrayList.get(position).getItem_name());

        toolbarImage.getLayoutParams().height = size.x;
        BitmapDrawable drawable = (BitmapDrawable) toolbarImage.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        /*Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.rice);*/
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(@Nullable Palette palette) {
                assert palette != null;
                int mutedColor = palette.getVibrantColor(getResources()
                        .getColor(R.color.colorPrimary));
                collapsingToolbarLayout.setContentScrimColor(mutedColor);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    getWindow().setStatusBarColor(palette.getVibrantColor(getResources().getColor(R.color.colorPrimary)));
                }

            }
        });

        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    isShow = true;
                    showOption(R.id.action_info);
                } else if (isShow) {
                    isShow = false;
                    hideOption(R.id.action_info);
                }
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


    }

    private void setAppBarOffset(int heightPx) {
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) mAppBarLayout.getLayoutParams();
        AppBarLayout.Behavior behavior = (AppBarLayout.Behavior) params.getBehavior();
        behavior.onNestedPreScroll(coordinatorLayout, mAppBarLayout, null, 0, heightPx, new int[]{0, 0});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;

        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        hideOption(R.id.action_info);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                startActivity(new Intent(this, ShimmerRecyclerView.class));
                return true;
            case R.id.action_info:
                return true;
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void hideOption(int id) {
        MenuItem item = menu.findItem(id);
        item.setVisible(false);
    }

    private void showOption(int id) {
        MenuItem item = menu.findItem(id);
        item.setVisible(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //mShimmerViewContainer.startShimmerAnimation();
    }

    @Override
    public void onCardClick(int position) {
        mSellerItemArrayList.get(position);
    }

}

