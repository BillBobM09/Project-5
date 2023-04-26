package com.example.myapplication;

import android.widget.Spinner;

public class DonutModel {
    String Name;
    String Price;
    int image;



    public Spinner getSpinnerView() {
        return spinnerView;
    }

    private Spinner spinnerView;

    public DonutModel(String price, String Name, int image, Spinner spinnerView ) {
        this.Price = price;
        this.Name = Name;
        this.image = image;
        this.spinnerView = spinnerView;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPrice() {
        return Price;
    }

    public int getImage() {
        return image;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
