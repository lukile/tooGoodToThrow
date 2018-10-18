package com.example.lukile.toogoodtothrow.advert;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.lukile.toogoodtothrow.R;
import com.example.lukile.toogoodtothrow.model.Advert;

import org.w3c.dom.Text;

import java.util.List;

public class DetailAdvertActivity extends AppCompatActivity implements AdvertView {

    private AdvertPresenter advertPresenter;
    TextView textViewEmail;
    TextView textViewDatePicking;
    TextView textViewComment;
    Button buttonReserved;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_advert);
        advertPresenter = new AdvertPresenter((AdvertView) this);
        textViewEmail = findViewById(R.id.email_user_advert_tv);
        textViewDatePicking = findViewById(R.id.date_picking_tv);
        textViewComment = findViewById(R.id.comment_advert_tv);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            Log.e("advert id", String.valueOf(bundle.getInt("advert_id")));
            advertPresenter.oneAdvert(bundle.getInt("advert_id"));
        }
    }

    @Override
    public void printAdvert(List<Advert> advert) {

    }

    @Override
    public void printOneAdvert(Advert advert) {
        Log.e("printOne", advert.toString());
        textViewComment.setText(advert.getComment());
        textViewDatePicking.setText(advert.getDateLapsing());
    }
}
