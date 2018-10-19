package com.example.lukile.toogoodtothrow;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.lukile.toogoodtothrow.model.Category;

public abstract class BaseActivity extends AppCompatActivity {

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search_product:
                Intent searchIntent = new Intent(this, Category.class);
                this.startActivity(searchIntent);
                return true;
        }
        return true;
    }
}
