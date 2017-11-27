package com.tradera.prov.events;

import com.tradera.prov.model.ProductsList;

public class GotProductListEvent {
    public final ProductsList productsList;

    public GotProductListEvent(ProductsList productsList) {
        this.productsList = productsList;
    }

    public ProductsList getProductsList() {
        return productsList;
    }
}
