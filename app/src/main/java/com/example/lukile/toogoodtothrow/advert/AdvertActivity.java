package com.example.lukile.toogoodtothrow.advert;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.lukile.toogoodtothrow.OnBottomReachedListener;
import com.example.lukile.toogoodtothrow.R;
import com.example.lukile.toogoodtothrow.category.CategoryAdapter;
import com.example.lukile.toogoodtothrow.category.CategoryPresenter;
import com.example.lukile.toogoodtothrow.category.ICategoryView;
import com.example.lukile.toogoodtothrow.model.Advert;

import java.util.ArrayList;
import java.util.List;

public class AdvertActivity extends AppCompatActivity implements AdvertView{
    RecyclerView rcvAdvert;

    private AdvertAdapter adapter;
    private AdvertPresenter advertPresenter;

    private boolean shouldLoadMore;

    List<String> advertList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advert);

        advertPresenter = new AdvertPresenter((AdvertView) this);

        rcvAdvert = findViewById(R.id.rcv_advert);

        //categoryValues.add("Fruits et légumes");
        //categoryValues.add("Produits laitiers");

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            Log.e("id_category", String.valueOf(bundle.getInt("id_category")));
            advertPresenter.allAdvert(bundle.getInt("id_category"));
           // advertPresenter.allAdvert();
        }


        rcvAdvert.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AdvertAdapter(this);
        rcvAdvert.setAdapter(adapter);
    }

    @Override
    public void printAdvert(final List<Advert> advert) {
        adapter.resetData(advert);
        adapter.setOnBottomReachedListener(new OnBottomReachedListener() {
            @Override
            public void onBottomReached() {
                if(shouldLoadMore) {
                    advertList.add(null);
                    rcvAdvert.post(new Runnable() {
                        @Override
                        public void run() {
                            adapter.notifyDataSetChanged();
                            Log.e("pouet",  advertList.toString());
                            advertList.size();
                        }
                    });
                }
            }
        });

    }

    @Override
    public void printOneAdvert(Advert advert) {
        //
    }
}
