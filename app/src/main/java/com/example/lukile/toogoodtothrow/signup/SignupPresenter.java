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
    public SignupPresenter(SignupView signupView) {
        this.signupView = signupView;
    }

    public void signup (String firstname, String lastname, String
            password, String confirmPassword, String email){
        String baseUrl = "http://10.0.2.2:3000/";
        JSONObject userJson = new JSONObject();
        try {
            userJson.put("firstname", firstname);
            userJson.put("lastname", lastname);
            userJson.put("password", password);
            userJson.put("confirmPassword", confirmPassword);
            userJson.put("email", email);

        } catch (JSONException e) {
            e.printStackTrace();
        }


        AndroidNetworking.post(baseUrl + "auth/signup")
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
