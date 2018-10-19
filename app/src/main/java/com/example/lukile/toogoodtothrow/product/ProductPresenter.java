package com.example.lukile.toogoodtothrow.product;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.StringRequestListener;
import com.example.lukile.toogoodtothrow.model.Category;
import com.example.lukile.toogoodtothrow.model.Product;
import com.example.lukile.toogoodtothrow.model.User;
import com.example.lukile.toogoodtothrow.signup.SignupView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductPresenter {
    ProductView productView;
    public ProductPresenter(ProductView view) {
        this.productView = view;
    }


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


    public void postProduct (String name, String quantity, String expiryDate, String dateLapsing, String startTime, String endTime, String comment, int categoryId){
        String baseUrl = "http://10.0.2.2:8080/";
        JSONObject productJson = new JSONObject();
        try {
            productJson.put("name", name);
            productJson.put("state", 1);
            productJson.put("quantity", quantity);
            productJson.put("date_lapsing", dateLapsing);
            productJson.put("end_date", expiryDate);
            productJson.put("start_time_slot", startTime);
            productJson.put("end_time_slot", endTime);
            productJson.put("comment", comment);
            productJson.put("id_product", 1);
            productJson.put("id_user", 1);
            productJson.put("id_category", categoryId);

        } catch (JSONException e) {
            e.printStackTrace();
        }


        AndroidNetworking.post(baseUrl + "advert/add")
                .addJSONObjectBody(productJson)
                .setTag("Inscription")
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        productView.redirectAfterPublish();
                    }

                    @Override
                    public void onError(ANError anError) {
                        anError.printStackTrace();
                    }
                });

    }


}
