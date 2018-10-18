package com.example.lukile.toogoodtothrow.advert;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.example.lukile.toogoodtothrow.category.ICategoryView;
import com.example.lukile.toogoodtothrow.model.Advert;
import com.example.lukile.toogoodtothrow.model.Category;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class AdvertPresenter {
    AdvertView advertView;

    public AdvertPresenter(AdvertView view) {
        this.advertView = view;
    }


    public void allAdvert(){
        final List<Advert> advertList = new ArrayList<>();


        String baseUrl = "http://10.0.2.2:8080/";


        AndroidNetworking.get(baseUrl+"advert/all")
                .setTag("Connect")
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        JSONArray array = response;
                        JsonParser parser = new JsonParser();
                        JsonElement arr = parser.parse(array.toString());
                        Gson gson = new GsonBuilder().create();
                        Advert[] results = gson.fromJson(arr, Advert[].class);

                        for (Advert advert : results) {
                            advertList.add(advert);
                        }

                        Log.e("advert", advertList.toString());
                        advertView.printAdvert(advertList);
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e("on error : ",anError.toString());
                    }
                });


    }
}