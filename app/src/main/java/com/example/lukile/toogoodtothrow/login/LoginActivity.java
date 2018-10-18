package com.example.lukile.toogoodtothrow.login;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lukile.toogoodtothrow.R;
import com.example.lukile.toogoodtothrow.category.CategoryActivity;
import com.example.lukile.toogoodtothrow.model.SaveUserDataPreferences;

public class LoginActivity extends AppCompatActivity implements LoginView {
    Button btnLogin;
    LoginPresenter loginPresenter;

    EditText editTextEmail;
    EditText editTextPassword;
    SaveUserDataPreferences dataUser = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginPresenter = new LoginPresenter(this);

        btnLogin = findViewById(R.id.btn_login);

        editTextEmail = findViewById(R.id.email_input);
        editTextPassword = findViewById(R.id.pwd_input);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();

                loginPresenter.verifyData(email, password);
            }
        });
    }

    @Override
    public void validationData(String token, int idUser) {
        //Créer le fichier spécifique au user
        String email = editTextEmail.getText().toString();
        SaveUserDataPreferences dataUser = new SaveUserDataPreferences(email);



        //Sauvegarder avec SharedPreferences
        SaveUserDataPreferences.setToken(this, token);
        SaveUserDataPreferences.setId(this, idUser);

        Intent intent = new Intent(LoginActivity.this, CategoryActivity.class);
        LoginActivity.this.startActivity(intent);

    }

    @Override
    public void popupAlert() {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        builder.setTitle("Attention")
                .setMessage("Veuillez saisir tous les champs")
                .setNeutralButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    @Override
    public void errorConnectData() {
        Toast.makeText(this, "Données insérées incorrectes", Toast.LENGTH_SHORT).show();
    }
}
