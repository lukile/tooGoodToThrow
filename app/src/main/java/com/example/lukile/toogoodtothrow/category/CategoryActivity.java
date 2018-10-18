package com.example.lukile.toogoodtothrow.category;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.lukile.toogoodtothrow.OnBottomReachedListener;
import com.example.lukile.toogoodtothrow.R;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity implements ICategoryView {
    RecyclerView rcvCategory;

    private CategoryAdapter adapter;

    private boolean shouldLoadMore;

    List<String> categoryValues = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        rcvCategory = findViewById(R.id.rcv_category);

        categoryValues.add("Fruits et légumes");
        categoryValues.add("Produits laitiers");



        rcvCategory.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CategoryAdapter(this);
        printCategory(categoryValues);

        rcvCategory.setAdapter(adapter);
    }


    @Override
    public void printCategory(final List<String> categories) {
        adapter.resetData(categories);
        adapter.setOnBottomReachedListener(new OnBottomReachedListener() {
            @Override
            public void onBottomReached() {
                if(shouldLoadMore) {
                    categoryValues.add(null);
                    rcvCategory.post(new Runnable() {
                        @Override
                        public void run() {
                            adapter.notifyDataSetChanged();
                            Log.e("pouet",  categoryValues.toString());
                            categoryValues.size();
                        }
                    });
                }
            }
        });

    }
}
