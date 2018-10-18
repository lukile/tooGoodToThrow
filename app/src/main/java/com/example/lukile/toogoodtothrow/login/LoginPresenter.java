package com.example.lukile.toogoodtothrow.login;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.androidnetworking.interfaces.StringRequestListener;
import com.example.lukile.toogoodtothrow.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.util.ArrayList;

public class LoginPresenter {
    public LoginView loginView;
    private static final String TAG = "LoginPresenter";

    public LoginPresenter(LoginView loginView) {
        this.loginView = loginView;
    }

    public void verifyData(String email, String password){
        if(email.equals("") || password.equals("")){
            loginView.popupAlert();
        } else {
            signin(email, password);
        }
    }


    public void signin(String email, String password){
        //HttpURLConnection()

        String baseUrl = "http://10.0.2.2:8080/";
        AndroidNetworking.post(baseUrl+"user/login/"+email+"/"+password)
                .setTag("Connect")
                .build()
                .getAsObject(User.class, new ParsedRequestListener<User>() {
                    @Override
                    public void onResponse(User user) {
                        // do anything with response
                        Log.e("user", user.getToken());
                        String token = user.getToken();
                        int id = user.getId();
                        loginView.validationData(token, id);


                    }
                    @Override
                    public void onError(ANError anError) {
                        Log.e("on error : ",anError.toString());
                    }
                });
        /*
                .getAsJSONObject(new JSONObjectRequestListener(){

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("log", response.get("token").toString());
                        String token = null; //stocker le token crypté envoyé par le serveur
                        try {
                            token = response.get("token").toString();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        loginView.validationData(token, 1);
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e("error", anError.toString());
                    }
                });
                */
    }
}
