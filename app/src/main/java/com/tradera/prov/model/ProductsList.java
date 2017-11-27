package com.tradera.prov.model;

import com.afollestad.ason.AsonName;
import com.afollestad.bridge.annotations.ContentType;

import java.util.ArrayList;
import java.util.List;

@ContentType("application/json")
public class ProductsList {

    public ProductsList() {}

    public ProductsList (List<Product> productList) {
        this.productList = productList;
    }

    @AsonName(name = "products")
    private List<Product> productList;

    public List<Product> getProductList() {
        if (productList != null) {
            return productList;
        } else {
            return new ArrayList<>();
        }
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
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
