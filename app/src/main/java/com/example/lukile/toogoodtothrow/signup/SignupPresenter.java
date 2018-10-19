package com.example.lukile.toogoodtothrow.signup;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.StringRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

public class SignupPresenter {
    public SignupView signupView;
    private static final String TAG = "SignupPresenter";

    //constructeur
    public SignupPresenter(SignupView view) {
        this.signupView = view;
    }

    public void signup (String firstname, String lastname, String
            password, String confirmPassword, String email){
        String baseUrl = "http://10.0.2.2:8080/";
        JSONObject userJson = new JSONObject();
        try {
            userJson.put("firstname", firstname);
            userJson.put("lastname", lastname);
            userJson.put("password", password);
            userJson.put("password2", confirmPassword);
            userJson.put("mail", email);
            userJson.put("adress", "test");

        } catch (JSONException e) {
            e.printStackTrace();
        }


        AndroidNetworking.post(baseUrl + "user/add")
                .addJSONObjectBody(userJson)
                .setTag("Inscription")
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        String token = response; //stocker le token crypté envoyé par le serveur
                        signupView.validationData(token);
                    }

                    @Override
                    public void onError(ANError anError) {
                        anError.printStackTrace();
                    }
                });

    }
}
