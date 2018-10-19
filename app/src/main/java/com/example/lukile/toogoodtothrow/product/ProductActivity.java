package com.example.lukile.toogoodtothrow.product;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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
import com.example.lukile.toogoodtothrow.category.CategoryActivity;
import com.example.lukile.toogoodtothrow.model.Category;

import java.util.Calendar;
import java.util.List;


public class ProductActivity extends AppCompatActivity implements ProductView{
    private ProductPresenter productPresenter;

    EditText editTextExpiryDate;
    EditText editTextPickupDate;
    EditText editTextPickupTimeStart;
    EditText editTextPickupTimeEnd;
    EditText editTextQuantity;
    EditText editTextComment;
    Button buttonValidProduct;
    Spinner spinnerProduct;
    RadioGroup categoryChoice;

    int categoryId;
    String spinnerPro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        productPresenter = new ProductPresenter((ProductView) this);

        buttonValidProduct = findViewById(R.id.valide_product_btn);
        editTextComment = findViewById(R.id.comment_edt);
        editTextQuantity = findViewById(R.id.quantity_edt);
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



        categoryChoice.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.fruits_and_vegetables:
                        categoryId = 1;
                        populateSpinner(R.array.fruits_and_vegetable_arr);
                        break;
                    case R.id.milk_product:
                        categoryId = 2;
                        populateSpinner(R.array.milk_product);
                        break;
                    case R.id.cooked_food:
                        categoryId = 3;
                        populateSpinner(R.array.cooked_food);
                        break;
                    case R.id.starchy:
                        categoryId = 4;
                        populateSpinner(R.array.starchy);
                        break;
                    case R.id.candy_and_sweet:
                        categoryId = 5;
                        populateSpinner(R.array.candy_and_sweet);
                        break;
                    case R.id.drink:
                        categoryId = 6;
                        populateSpinner(R.array.drink);
                        break;
                    case R.id.frozen:
                        categoryId = 7;
                        populateSpinner(R.array.frozen);
                        break;
                    case R.id.vop:
                        categoryId = 8;
                        populateSpinner(R.array.vop);
                        break;
                    case R.id.grocery:
                        categoryId = 9;
                        populateSpinner(R.array.grocery);
                        break;
                    case R.id.baby_food:
                        categoryId = 10;
                        populateSpinner(R.array.baby_food);
                        break;
                    case R.id.tea_coffee_chocolate:
                        categoryId = 11;
                        populateSpinner(R.array.tea_coffee_chocolate);
                        break;
                }



            }

            public void populateSpinner(int array) {
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(ProductActivity.this, array, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerProduct.setAdapter(adapter);
            }

        });


        buttonValidProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productPresenter.postProduct(spinnerProduct.getSelectedItem().toString(), editTextQuantity.getText().toString(), editTextPickupDate.getText().toString(), editTextExpiryDate.getText().toString(), editTextPickupTimeStart.getText().toString(), editTextPickupTimeEnd.getText().toString(), editTextComment.getText().toString(), categoryId);
            }
        });
    }

    @Override
    public void redirectAfterPublish() {
        ProductActivity.this.startActivity(new Intent(ProductActivity.this, CategoryActivity.class));
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
                Intent searchIntent = new Intent(ProductActivity.this, CategoryActivity.class);
                ProductActivity.this.startActivity(searchIntent);
                return true;
            case R.id.add_product:
                Intent addIntent = new Intent(ProductActivity.this, ProductActivity.class);
                ProductActivity.this.startActivity(addIntent);
                return true;
        }
        return true;
    }

}
