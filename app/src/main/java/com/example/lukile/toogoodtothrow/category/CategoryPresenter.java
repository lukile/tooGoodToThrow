package com.example.lukile.toogoodtothrow.category;

import android.content.Context;
import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.example.lukile.toogoodtothrow.model.Category;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class CategoryPresenter {
    ICategoryView categoryView;

    public CategoryPresenter(ICategoryView view){
        categoryView = view;
    }

    public void allCategories(){
        final List<String> categoriessList = new ArrayList<>();


        String baseUrl = "http://10.0.2.2:8080/";


        AndroidNetworking.get(baseUrl+"category/all")
                .setTag("Connect")
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        JSONArray array = response;
                        JsonParser parser = new JsonParser();
                        JsonElement arr = parser.parse(array.toString());
                        Gson gson = new GsonBuilder().create();
                        Category[] results = gson.fromJson(arr, Category[].class);
                        Log.d( "fsdds","name : " + results[0].getName());

                        for (Category category : results) {
                            categoriessList.add(category.getName());
                        }

                        categoryView.printCategory(categoriessList);
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e("on error : ",anError.toString());
                    }
                });


    }
}
