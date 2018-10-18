package com.example.lukile.toogoodtothrow.signup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;

import com.example.lukile.toogoodtothrow.R;

public class SignupActivity extends AppCompatActivity {

    private static final String TAG = "SignupActivity";

    SignupPresenter signupPresenter;

    Button btnInscrip;
    EditText lastnameEdt;
    EditText firstnameEdt;
    EditText passwordEdt;
    EditText confirmPasswordEdt;
    EditText emailEdt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        signupPresenter = new SignupPresenter((SignupView) this);

        btnInscrip = findViewById(R.id.signup_btn);

        lastnameEdt = findViewById(R.id.signup_lastname_et);
        firstnameEdt = findViewById(R.id.signup_firstname_et);
        passwordEdt = findViewById(R.id.signup_password_et);
        confirmPasswordEdt = findViewById(R.id.signup_confirm_password_et);
        emailEdt = findViewById(R.id.signup_email_et);




        emailEdt.addTextChangedListener(new TextWatcher()  {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s)  {
                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(emailEdt.getText()).matches()) {
                    emailEdt.setError("Email incorrect");
                }
            }
        });

        passwordEdt.addTextChangedListener(new TextWatcher()  {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s)  {
                if ((passwordEdt.length() < 6) && (passwordEdt.length() > 10)) {
                    passwordEdt.setError("Add rules");
                }
            }
        });

        confirmPasswordEdt.addTextChangedListener(new TextWatcher()  {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s)  {
                if (confirmPasswordEdt.equals(passwordEdt)) {
                    confirmPasswordEdt.setError("Ne match pas");
                }
            }
        });

        btnInscrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //récup data du formulaire android

                String firstname = firstnameEdt.getText().toString();
                String lastname = lastnameEdt.getText().toString();
                String email = emailEdt.getText().toString();
                String password = passwordEdt.getText().toString();
                String confirmPassword = confirmPasswordEdt.getText().toString();

                signupPresenter.signup(firstname, lastname, password, confirmPassword, email);
            }
        });
    }
}
