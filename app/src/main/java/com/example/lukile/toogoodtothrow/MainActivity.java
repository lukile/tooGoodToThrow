package com.example.lukile.toogoodtothrow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

        Intent myIntent = new Intent(MainActivity.this, LoginActivity.class);
        MainActivity.this.startActivity(myIntent);
    }


}
