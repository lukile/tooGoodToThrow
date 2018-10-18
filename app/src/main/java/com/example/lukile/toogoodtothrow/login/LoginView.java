package com.example.lukile.toogoodtothrow.login;

public interface LoginView {

    void validationData(String token, int idUser);
    void popupAlert();
    void errorConnectData();
}
