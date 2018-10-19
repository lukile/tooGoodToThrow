package com.example.lukile.toogoodtothrow.advert;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lukile.toogoodtothrow.R;
import com.example.lukile.toogoodtothrow.model.Advert;
import com.example.lukile.toogoodtothrow.model.User;
import com.example.lukile.toogoodtothrow.user.UserPresenter;
import com.example.lukile.toogoodtothrow.user.UserView;

import org.w3c.dom.Text;

import java.util.List;

public class DetailAdvertActivity extends AppCompatActivity implements AdvertView, UserView {

    private AdvertPresenter advertPresenter;
    private UserPresenter userPresenter;

    TextView textViewEmail;
    TextView textViewDatePicking;
    TextView textViewComment;
    Button buttonReserved;

    Advert advert;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_advert);
        advertPresenter = new AdvertPresenter((AdvertView) this);
        userPresenter = new UserPresenter((UserView) this);


        textViewEmail = findViewById(R.id.email_user_advert_tv);
        textViewDatePicking = findViewById(R.id.date_picking_tv);
        textViewComment = findViewById(R.id.comment_advert_tv);
        buttonReserved = findViewById(R.id.btn_reserved_advert);
        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            advertPresenter.oneAdvert(bundle.getInt("advert_id"));
            userPresenter.getUser(bundle.getString("user_id"));

        }






        buttonReserved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                advertPresenter.updateAdvert(advert);
            }
        });
    }

    @Override
    public void printAdvert(List<Advert> advert) {

    }

    @Override
    public void printOneAdvert(Advert advertPrint) {
        Log.e("printOne", advertPrint.toString());
        this.advert = advertPrint;
        textViewComment.setText(advertPrint.getComment());
        textViewDatePicking.setText(advertPrint.getDateLapsing());
    }

    @Override
    public void printEmail(String email) {
        Log.e("emaul user", email);
        textViewEmail.setText(email);
    }
}
