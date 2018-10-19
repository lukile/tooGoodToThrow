package com.example.lukile.toogoodtothrow.model;

import android.content.Intent;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Advert {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("state")
    @Expose
    private Integer state;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("date_lapsing")
    @Expose
    private String dateLapsing;
    @SerializedName("end_date")
    @Expose
    private String endDate;
    @SerializedName("start_time_slot")
    @Expose
    private String startTimeSlot;
    @SerializedName("end_time_slot")
    @Expose
    private String endTimeSlot;
    @SerializedName("comment")
    @Expose
    private String comment;
    @SerializedName("id_user")
    @Expose
    private Integer idUser;
    @SerializedName("id_product")
    @Expose
    private Integer idProduct;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getDateLapsing() {
        return dateLapsing;
    }

    public void setDateLapsing(String dateLapsing) {
        this.dateLapsing = dateLapsing;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStartTimeSlot() {
        return startTimeSlot;
    }

    public void setStartTimeSlot(String startTimeSlot) {
        this.startTimeSlot = startTimeSlot;
    }

    public String getEndTimeSlot() {
        return endTimeSlot;
    }

    public void setEndTimeSlot(String endTimeSlot) {
        this.endTimeSlot = endTimeSlot;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Integer getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Integer idProduct) {
        this.idProduct = idProduct;
    }

}
