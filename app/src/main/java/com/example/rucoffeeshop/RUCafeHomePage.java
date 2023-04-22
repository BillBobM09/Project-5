package com.example.rucoffeeshop;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class RUCafeHomePage extends AppCompatActivity {
    private ImageButton donutButton;
    private ImageButton coffeeButton;
    private ImageButton shoppingCart;
    private ImageButton storeOrders;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.ru_cafe_homepage);
       // donutButton = findViewById(R.id.DonutButton);
     //   coffeeButton = findViewById(R.id.CoffeeButton);
      //  shoppingCart = findViewById(R.id.shoppingCartButton);
       // storeOrders = findViewById(R.id.storeOrdersButton);

    }
    @Override
    protected void onStart(){
        super.onStart();
        System.out.println("run start");//check to make sure its starting

    }

    @Override
    protected void onResume(){
        super.onResume();
        System.out.println("run onResume");

    }

    public void DonutClick(View view) {
        // Start a new activity here
        Intent intent = new Intent(this, DonutOrderPage.class);//creates new intent
        startActivity(intent);//starts activity intent(RuCafeHome)
    }


    public void coffeeClick(View view) {
        // Start a new activity here
        Intent intent = new Intent(this, CoffeeOrderPage.class);//creates new intent
        startActivity(intent);//starts activity intent(RuCafeHome)
    }

    public void shoppingCartClick(View view) {
        // Start a new activity here
        Intent intent = new Intent(this, shoppingCartPage.class);//creates new intent
        startActivity(intent);//starts activity intent(RuCafeHome)
    }


    public void storeOrdersClick(View view) {
        // Start a new activity here
        Intent intent = new Intent(this, storeOrdersPage.class);//creates new intent
        startActivity(intent);//starts activity intent(RuCafeHome)
    }



}

