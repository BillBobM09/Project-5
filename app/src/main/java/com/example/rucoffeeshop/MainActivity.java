package com.example.rucoffeeshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Coffee Covfefe;
    private Button GetStartedButton;


    public Coffee getCovfefe() {
        return Covfefe;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GetStartedButton = (Button) findViewById(R.id.GetStartedButton);
        GetStartedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetStartedClick();
            }
        });

        Covfefe = new Coffee();

//        CoffeeOrderPage coffeeController = loader.getController();
//        coffeeController.setMainController(this);

//        View coffeeOrderPage = LayoutInflater.from(this).inflate(R.layout.coffee_page, null);
//        CoffeeOrderPage coffeeController = (CoffeeOrderPage) coffeeOrderPage.getTag();
//        coffeeController.setMainController(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("run start");//check to make sure its starting

    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("run onResume");

    }

    public void GetStartedClick() {
        // Start a new activity here
        Intent intent = new Intent(this, RUCafeHomePage.class);//creates new intent
        startActivity(intent);//starts activity intent(RuCafeHome)
    }


}


