package com.example.lukile.toogoodtothrow;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.lukile.toogoodtothrow.product.ProductActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent myIntent = new Intent(MainActivity.this, ProductActivity.class);
        MainActivity.this.startActivity(myIntent);
    }
}
