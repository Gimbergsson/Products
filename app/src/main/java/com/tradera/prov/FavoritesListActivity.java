package com.tradera.prov;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.tradera.prov.adapters.ProductAdapter;
import com.tradera.prov.model.ProductsList;
import com.tradera.prov.tradera.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FavoritesListActivity extends AppCompatActivity {

    private Unbinder unbinder;

    @BindView(R.id.product_recycler_view)
    RecyclerView productRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites_list);
        unbinder = ButterKnife.bind(this);
        Gson gson = new Gson();

        SharedPreferences sharedPreferences = getSharedPreferences("Products", Context.MODE_PRIVATE);
        String savedFavoritesJson = sharedPreferences.getString("Favorites", "");

        ProductsList savedFavoritesList = gson.fromJson(savedFavoritesJson, ProductsList.class);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        productRecyclerView.setLayoutManager(layoutManager);

        ProductAdapter productAdapter = new ProductAdapter(getApplicationContext(), savedFavoritesList, true);
        productRecyclerView.setAdapter(productAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        unbinder = ButterKnife.bind(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unbinder.unbind();
    }
}
