package com.tradera.prov.model;

import com.afollestad.ason.AsonName;
import com.afollestad.bridge.annotations.ContentType;


@ContentType("application/json")
public class Product {

    @AsonName(name = "id")
    private int productId;

    @AsonName(name = "title")
    private String productTitle;

    @AsonName(name = "price")
    private int productPrice;

    @AsonName(name = "currency")
    private String productCurrency;

    @AsonName(name = "image")
    private String productImageUrl;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public String getProductCurrency() {
        return productCurrency;
    }

    public String getProductImageUrl() {
        return productImageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (productId != product.productId) return false;
        if (productPrice != product.productPrice) return false;
        if (productTitle != null ? !productTitle.equals(product.productTitle) : product.productTitle != null) return false;
        if (productCurrency != null ? !productCurrency.equals(product.productCurrency) : product.productCurrency != null) return false;
        return productImageUrl != null ? productImageUrl.equals(product.productImageUrl) : product.productImageUrl == null;
    }

    @Override
    public int hashCode() {
        int result = productId;
        result = 31 * result + (productTitle != null ? productTitle.hashCode() : 0);
        result = 31 * result + productPrice;
        result = 31 * result + (productCurrency != null ? productCurrency.hashCode() : 0);
        result = 31 * result + (productImageUrl != null ? productImageUrl.hashCode() : 0);
        return result;
    }
}
