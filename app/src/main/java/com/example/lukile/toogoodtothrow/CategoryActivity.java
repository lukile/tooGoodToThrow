package com.example.lukile.toogoodtothrow;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {

    /*String[] categoryValues = new String[] {
            "Fruits et légumes",
            "Produits laitiers"
    };

    int[] categoryListViewImages = new int[] {
            R.drawable.fruit, R.drawable.milk,
    };*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

       /* List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();

        for (int i = 0; i < 8; i++) {
            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put("listview_title", categoryValues[i]);
            hm.put("listview_image", Integer.toString(categoryListViewImages[i]));
            aList.add(hm);
        }
        String[] from = {"listview_image", "listview_title"};
        int[] to = {R.id.listview_image, R.id.listview_item_title };

        SimpleAdapter simpleAdapter = new SimpleAdapter(getBaseContext(), aList, R.layout.activity_category, from, to);
        ListView androidListView = (ListView) findViewById(R.id.list_view);
        androidListView.setAdapter(simpleAdapter);*/



    }
}
