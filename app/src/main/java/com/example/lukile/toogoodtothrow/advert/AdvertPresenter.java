package com.example.lukile.toogoodtothrow.advert;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.androidnetworking.interfaces.StringRequestListener;
import com.example.lukile.toogoodtothrow.category.ICategoryView;
import com.example.lukile.toogoodtothrow.model.Advert;
import com.example.lukile.toogoodtothrow.model.Category;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AdvertPresenter {
    AdvertView advertView;

    public AdvertPresenter(AdvertView view) {
        this.advertView = view;
    }


    public void allAdvert(int idCat){
        final List<Advert> advertList = new ArrayList<>();


        String baseUrl = "http://10.0.2.2:8080/";


        AndroidNetworking.get(baseUrl+"advert/all?id_category="+String.valueOf(idCat)+"&state=1")
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


                        advertView.printAdvert(advertList);
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.e("on error : ",anError.toString());
                    }
                });


    }

    public void oneAdvert(int catId){
        final List<Advert> advertList = new ArrayList<>();


        String baseUrl = "http://10.0.2.2:8080/";


        AndroidNetworking.get(baseUrl+"advert/getAdvert/" + String.valueOf(catId))
                .setTag("Connect")
                .build()
                .getAsObject(Advert.class, new ParsedRequestListener<Advert>() {
                    @Override
                    public void onResponse(Advert advert) {
                        advertView.printOneAdvert(advert);

                    }
                    @Override
                    public void onError(ANError anError) {
                        Log.e("on error : ",anError.toString());
                    }
                });
    }

    public void updateAdvert (Advert advert){
        String baseUrl = "http://10.0.2.2:8080/";
        JSONObject userJson = new JSONObject();
        try {
            userJson.put("id", advert.getId());
            userJson.put("name", advert.getName());
            userJson.put("quantity", advert.getQuantity());
            userJson.put("state", 2);
            userJson.put("date_lapsing", advert.getDateLapsing());
            userJson.put("end_date", advert.getEndDate());
            userJson.put("start_time_slot", advert.getStartTimeSlot());
            userJson.put("end_time_slot", advert.getEndTimeSlot());
            userJson.put("comment", advert.getComment());

        } catch (JSONException e) {
            e.printStackTrace();
        }


        AndroidNetworking.post(baseUrl + "advert/update")
                .addJSONObjectBody(userJson)
                .setTag("Inscription")
                .build()
                .getAsString(new StringRequestListener() {
                    @Override
                    public void onResponse(String response) {
                        //
                    }

                    @Override
                    public void onError(ANError anError) {
                        anError.printStackTrace();
                    }
                });

    }
}
