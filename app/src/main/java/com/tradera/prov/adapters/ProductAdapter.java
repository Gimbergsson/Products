package com.tradera.prov.adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.tradera.prov.model.Product;
import com.tradera.prov.model.ProductsList;
import com.tradera.prov.tradera.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private ProductsList productList;
    private Context context;
    private SharedPreferences sharedPreferences;
    private Gson gson;
    private boolean asFavoriteList;

    public ProductAdapter(Context context, ProductsList productsList, boolean asFavoriteList) {
        this.context = context;
        this.productList = productsList;
        this.asFavoriteList = asFavoriteList;
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.product_title)
        TextView productTitle;

        @BindView(R.id.product_image)
        ImageView productImage;

        @BindView(R.id.product_price)
        TextView productPrice;

        @BindView(R.id.product_id)
        TextView productId;

        @BindView(R.id.favorite_icon)
        ImageView favoriteIcon;

        ProductViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View productView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_product, parent, false);
        return new ProductViewHolder(productView);
    }

    @Override
    public void onBindViewHolder(final ProductViewHolder holder, final int position) {
        gson = new Gson();
        final Product product = productList.getProductList().get(position);

        holder.productTitle.setText(product.getProductTitle());
        holder.productPrice.setText(context.getString(R.string.price, product.getProductPrice(), product.getProductCurrency()));
        holder.productId.setText(String.valueOf(product.getProductId()));

        Picasso.with(holder.productImage.getContext())
                .load(product.getProductImageUrl())
                .fit()
                .into(holder.productImage);

        holder.favoriteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isFavorite(product)) {
                    if (asFavoriteList) {
                        removeProduct(product);
                    }
                    removeFromFavoritesList(product);
                } else {
                    addToFavoritesList(product);
                }
                setFavoriteIconState(holder, product);
            }
        });

        setFavoriteIconState(holder, product);
    }

    @Override
    public int getItemCount() {
        if (productList != null) {
            return productList.getProductList().size();
        }
        return 0;
    }

    private void setFavoriteIconState(ProductViewHolder holder, Product product) {
        holder.favoriteIcon.setImageResource(isFavorite(product) ? R.drawable.ic_heart : R.drawable.ic_heart_outline);
    }

    private ProductsList getFavorites() {
        sharedPreferences = context.getSharedPreferences("Products", Context.MODE_PRIVATE);
        String savedFavoritesJson = sharedPreferences.getString("Favorites", "");
        ProductsList productsList = gson.fromJson(savedFavoritesJson, ProductsList.class);

        if (productsList != null) {
            return productsList;
        }

        List<Product> newProductList = new ArrayList<>();
        return new ProductsList(newProductList);
    }

    private void addToFavoritesList(Product product) {
        ProductsList favorites = getFavorites();
        favorites.getProductList().add(product);

        String listString = gson.toJson(favorites);
        sharedPreferences.edit().putString("Favorites", listString).apply();
    }

    private void removeFromFavoritesList(Product product) {
        ProductsList favorites = getFavorites();
        favorites.getProductList().remove(product);

        String listString = gson.toJson(favorites);
        sharedPreferences.edit().putString("Favorites", listString).apply();
    }

    private void removeProduct(Product product) {
        productList.getProductList().remove(product);
        notifyDataSetChanged();
    }

    private boolean isFavorite(Product product) {
        return getFavorites().getProductList().contains(product);
    }
}
