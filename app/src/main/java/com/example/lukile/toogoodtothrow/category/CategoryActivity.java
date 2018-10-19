package com.example.lukile.toogoodtothrow.category;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.lukile.toogoodtothrow.OnBottomReachedListener;
import com.example.lukile.toogoodtothrow.R;
import com.example.lukile.toogoodtothrow.model.Category;
import com.example.lukile.toogoodtothrow.product.ProductActivity;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity implements ICategoryView {
    RecyclerView rcvCategory;

    private CategoryAdapter adapter;
    private CategoryPresenter categoryPresenter;

    private boolean shouldLoadMore;

    List<String> categoryValues = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        categoryPresenter = new CategoryPresenter((ICategoryView) this);

        rcvCategory = findViewById(R.id.rcv_category);

        //categoryValues.add("Fruits et légumes");
        //categoryValues.add("Produits laitiers");

        categoryPresenter.allCategories();

        rcvCategory.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CategoryAdapter(this);
        rcvCategory.setAdapter(adapter);

    }


    @Override
    public void printCategory(final List<Category> categories) {
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_action, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search_product:
                Intent searchIntent = new Intent(CategoryActivity.this, CategoryActivity.class);
                CategoryActivity.this.startActivity(searchIntent);
                return true;
            case R.id.add_product:
                Intent addIntent = new Intent(CategoryActivity.this, ProductActivity.class);
                CategoryActivity.this.startActivity(addIntent);
                return true;
        }
        return true;
    }




}
