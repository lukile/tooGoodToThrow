package com.example.lukile.toogoodtothrow.product;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.example.lukile.toogoodtothrow.model.Product;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class ProductPresenter {

    public void getProductFromCategory(int categoryId) {
        final List<Product> productList = new ArrayList<>();

        String baseUrl = "http://192.168.1.14:8080/";

        AndroidNetworking.get(baseUrl+"product/getProductByCat/"+categoryId)
                .setTag("Connect")
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        JSONArray array = response;
                        JsonParser parser = new JsonParser();
                        JsonElement arr = parser.parse(array.toString());
                        Gson gson = new GsonBuilder().create();
                        Product[] results = gson.fromJson(arr, Product[].class);
                        Log.d("Product : ", "name : " + results[0].getName());

                        for (Product product : results) {
                            productList.add(product);
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e("on error : ",anError.toString());
                    }
                });
    }


}
