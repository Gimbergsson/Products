package com.tradera.prov.model;

import com.afollestad.ason.AsonName;
import com.afollestad.bridge.annotations.ContentType;

import java.util.List;

@ContentType("application/json")
public class ProductsList {

    @AsonName(name = "products")
    private List<Product> productList;

    public List<Product> getProductList() {
        return productList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductsList that = (ProductsList) o;

        return productList != null ? productList.equals(that.productList) : that.productList == null;
    }

    @Override
    public int hashCode() {
        return productList != null ? productList.hashCode() : 0;
    }
}
