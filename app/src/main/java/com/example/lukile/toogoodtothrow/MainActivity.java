package com.example.lukile.toogoodtothrow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.androidnetworking.AndroidNetworking;
import com.example.lukile.toogoodtothrow.category.CategoryActivity;
import com.example.lukile.toogoodtothrow.login.LoginActivity;
import com.example.lukile.toogoodtothrow.model.Category;
import com.example.lukile.toogoodtothrow.product.ProductActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        Intent myIntent = new Intent(MainActivity.this, CategoryActivity.class);
        MainActivity.this.startActivity(myIntent);
    }


}
