package com.example.lukile.toogoodtothrow.user;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.example.lukile.toogoodtothrow.advert.AdvertView;
import com.example.lukile.toogoodtothrow.model.Advert;
import com.example.lukile.toogoodtothrow.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class UserPresenter {

    UserView userView;
    User user;
    String emailUser;
    final List<User> userList = new ArrayList<>();

    public UserPresenter(UserView view) {
        this.userView = view;
    }


    public void getUser(String user_id){
        String baseUrl = "http://10.0.2.2:8080/";


        AndroidNetworking.get(baseUrl+"user/all?id=" + user_id)
                .setTag("Connect")
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        JSONArray array = response;
                        JsonParser parser = new JsonParser();
                        JsonElement arr = parser.parse(array.toString());
                        Gson gson = new GsonBuilder().create();
                        User[] results = gson.fromJson(arr, User[].class);

                        for (User user : results) {
                            Log.e("array user", user.getMail());
                            userView.printEmail(user.getMail());
                        }

                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e("on error : ",anError.toString());
                    }
                });


    }
}
