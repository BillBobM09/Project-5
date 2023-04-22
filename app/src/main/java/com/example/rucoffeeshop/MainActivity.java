package com.example.rucoffeeshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button GetStarted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // GetStarted = findViewById(R.id.GetStartedButton);

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

    public void GetStartedClick(View view) {
        // Start a new activity here
        Intent intent = new Intent(this, RUCafeHomePage.class);//creates new intent
        startActivity(intent);//starts activity intent(RuCafeHome)
    }


}


