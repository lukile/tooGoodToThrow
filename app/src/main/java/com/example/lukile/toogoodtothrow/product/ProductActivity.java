package com.example.lukile.toogoodtothrow.product;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.example.lukile.toogoodtothrow.OnBottomReachedListener;
import com.example.lukile.toogoodtothrow.R;
import com.example.lukile.toogoodtothrow.model.Category;

import java.util.Calendar;
import java.util.List;


public class ProductActivity extends AppCompatActivity {
    private ProductPresenter productPresenter;

    EditText editTextExpiryDate;
    EditText editTextPickupDate;
    EditText editTextPickupTimeStart;
    EditText editTextPickupTimeEnd;
    Button buttonValidProduct;
    Spinner spinnerProduct;
    RadioGroup categoryChoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        productPresenter = new ProductPresenter();


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        buttonValidProduct = findViewById(R.id.valide_product_btn);
        editTextExpiryDate = findViewById(R.id.expiry_date_edt);
        editTextPickupDate = findViewById(R.id.pickup_date_edt);
        editTextPickupTimeStart = findViewById(R.id.pickup_time_start_edt);
        editTextPickupTimeEnd = findViewById(R.id.pickup_time_end_edt);
        spinnerProduct = findViewById(R.id.product_choice_spinner);
        categoryChoice = findViewById(R.id.category_choice_rg);

        editTextExpiryDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar mcurrentDate = Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth = mcurrentDate.get(Calendar.MONTH);
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(ProductActivity.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        editTextExpiryDate.setText(selectedday + ":" + selectedmonth + ":" + selectedyear);
                    }
                }, mYear, mMonth, mDay);
                mDatePicker.setTitle("Select date");
                mDatePicker.show();

            }
        });
        editTextPickupDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar mcurrentDate = Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth = mcurrentDate.get(Calendar.MONTH);
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker = new DatePickerDialog(ProductActivity.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        editTextPickupDate.setText(selectedday + ":" + selectedmonth + ":" + selectedyear);
                    }
                }, mYear, mMonth, mDay);
                mDatePicker.setTitle("Select date");
                mDatePicker.show();

            }
        });

        editTextPickupTimeStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar mcurrenttime = Calendar.getInstance();
                final int mHour = mcurrenttime.get(Calendar.HOUR_OF_DAY);
                int mMinute = mcurrenttime.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog;
                timePickerDialog = new TimePickerDialog(ProductActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        editTextPickupTimeStart.setText(hourOfDay + ":" + minute);
                    }
                }, mHour, mMinute, true);
                timePickerDialog.show();
            }
        });

        editTextPickupTimeEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar mcurrenttime = Calendar.getInstance();
                final int mHour = mcurrenttime.get(Calendar.HOUR_OF_DAY);
                int mMinute = mcurrenttime.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog;
                timePickerDialog = new TimePickerDialog(ProductActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
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

        categoryChoice.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.fruits_and_vegetables:
                        populateSpinner(R.array.fruits_and_vegetable_arr);
                        break;
                    case R.id.milk_product:
                        populateSpinner(R.array.milk_product);
                        break;
                    case R.id.cooked_food:
                        populateSpinner(R.array.cooked_food);
                        break;
                    case R.id.starchy:
                        populateSpinner(R.array.starchy);
                        break;
                    case R.id.candy_and_sweet:
                        populateSpinner(R.array.candy_and_sweet);
                        break;
                    case R.id.drink:
                        populateSpinner(R.array.drink);
                        break;
                    case R.id.frozen:
                        populateSpinner(R.array.frozen);
                        break;
                    case R.id.vop:
                        populateSpinner(R.array.vop);
                        break;
                    case R.id.grocery:
                        populateSpinner(R.array.grocery);
                        break;
                    case R.id.baby_food:
                        populateSpinner(R.array.baby_food);
                        break;
                    case R.id.tea_coffee_chocolate:
                        populateSpinner(R.array.tea_coffee_chocolate);
                }


            }

            public void populateSpinner(int array) {
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(ProductActivity.this, array, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerProduct.setAdapter(adapter);
            }

        });
    }
}
