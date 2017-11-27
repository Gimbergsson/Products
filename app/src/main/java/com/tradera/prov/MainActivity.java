package com.tradera.prov;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.squareup.otto.Subscribe;
import com.tradera.prov.adapters.ProductAdapter;
import com.tradera.prov.events.GotProductListEvent;
import com.tradera.prov.tradera.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {

    private Unbinder unbinder;
    private ProductAdapter productAdapter;

    @BindView(R.id.product_recycler_view)
    RecyclerView productRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        productRecyclerView.setLayoutManager(layoutManager);

        Api.getInstance().getProductList();
    }

    @Subscribe
    public void onProductListResponse(GotProductListEvent event) {
        productAdapter = new ProductAdapter(getApplicationContext(), event.productsList, true);
        productRecyclerView.setAdapter(productAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        BusProvider.getInstance().register(this);
        unbinder = ButterKnife.bind(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        BusProvider.getInstance().unregister(this);
        unbinder.unbind();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.favorite_products:
                Intent favoritesActivity = new Intent(this, FavoritesListActivity.class);
                startActivity(favoritesActivity);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
