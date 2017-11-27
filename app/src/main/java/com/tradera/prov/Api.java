package com.tradera.prov;

import com.afollestad.bridge.Bridge;
import com.afollestad.bridge.BridgeException;
import com.afollestad.bridge.Response;
import com.afollestad.bridge.ResponseConvertCallback;

import com.tradera.prov.events.GotProductListEvent;
import com.tradera.prov.model.ProductsList;

public class Api {

    private static final Api API = new Api();

    public static Api getInstance() {
        return API;
    }

    public void getProductList() {
        Bridge.get("http://www.tradera.com/static/images/NO_REV/frontend-task/ProductFeedResult.json")
                .asClass(ProductsList.class, new ResponseConvertCallback<ProductsList>() {
                    @Override
                    public void onResponse(Response response, ProductsList productsList, BridgeException e) {
                        if (response != null && productsList != null) {
                            if (response.isSuccess()) {
                                BusProvider.getInstance().post(new GotProductListEvent(productsList));
                            }
                        }
                    }
                });
    }
}
