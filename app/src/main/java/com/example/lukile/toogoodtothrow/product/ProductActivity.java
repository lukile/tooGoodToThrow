package com.example.lukile.toogoodtothrow.product;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.lukile.toogoodtothrow.R;

import java.util.Calendar;


public class ProductActivity extends AppCompatActivity {

    EditText editTextExpiryDate;
    EditText editTextPickupDate;
    EditText editTextPickupTimeStart;
    EditText editTextPickupTimeEnd;
    Button buttonValidProduct;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        buttonValidProduct = findViewById(R.id.valide_product_btn);
        editTextExpiryDate = findViewById(R.id.expiry_date_edt);
        editTextPickupDate = findViewById(R.id.pickup_date_edt);
        editTextPickupTimeStart = findViewById(R.id.pickup_time_start_edt);
        editTextPickupTimeEnd = findViewById(R.id.pickup_time_end_edt);


        editTextExpiryDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar mcurrentDate=Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth=mcurrentDate.get(Calendar.MONTH);
                int mDay=mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker=new DatePickerDialog(ProductActivity.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        editTextExpiryDate.setText(selectedday + "/" + selectedmonth + "/" + selectedyear);
                    }
                },mYear, mMonth, mDay);
                mDatePicker.setTitle("Select date");
                mDatePicker.show();

            }
        });
        editTextPickupDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar mcurrentDate=Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth=mcurrentDate.get(Calendar.MONTH);
                int mDay=mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker=new DatePickerDialog(ProductActivity.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        editTextPickupDate.setText(selectedday + "/" + selectedmonth + "/" + selectedyear);
                    }
                },mYear, mMonth, mDay);
                mDatePicker.setTitle("Select date");
                mDatePicker.show();

            }
        });

        editTextPickupTimeStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar mcurrenttime=Calendar.getInstance();
                final int mHour = mcurrenttime.get(Calendar.HOUR_OF_DAY);
                int mMinute = mcurrenttime.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog;
                timePickerDialog = new TimePickerDialog(ProductActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute){
                        editTextPickupTimeStart.setText(hourOfDay + ":" + minute);
                    }
                }, mHour, mMinute, true);
                timePickerDialog.show();
            }
        });

        editTextPickupTimeEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar mcurrenttime=Calendar.getInstance();
                final int mHour = mcurrenttime.get(Calendar.HOUR_OF_DAY);
                int mMinute = mcurrenttime.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog;
                timePickerDialog = new TimePickerDialog(ProductActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute){
                        editTextPickupTimeEnd.setText(hourOfDay + ":" + minute);
                    }
                }, mHour, mMinute, true);
                timePickerDialog.show();
            }
        });


        buttonValidProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
            }
        });
    }
}
