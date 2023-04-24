package com.example.rucoffeeshop;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class shoppingCartPage extends AppCompatActivity {

    private Button RemoveSelected;

    private Button PlaceOrder;

    private TextView Sub_TotalTF;

    private TextView Sales_TaxTF;

    private TextView TotalTF;

    private ListView BasketItems;
    private ArrayList<String> CustomerCart = new ArrayList<>();
    private Double Sub_Total;
    private Double Sale_Tax = 0.0625;
    private Double Total;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopcart_page);
        Order Shopcart = MainController.getShopCart();
        Order finalOrder = new Order();

        Sub_TotalTF = findViewById(R.id.Sub_TotalTF);
        Sales_TaxTF = findViewById(R.id.Sales_TaxTF);
        TotalTF = findViewById(R.id.TotalTF);

        ListView BasketItems = findViewById(R.id.BasketItems);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, CustomerCart);
        BasketItems.setAdapter(adapter);
        adapter.addAll(Shopcart.getDOrderItems());
        adapter.addAll(Shopcart.getCOrderItems());

//        if (Shopcart.getDOrderItems() == null) {
//            finalOrder.setDOrder(new ArrayList<>(), -1.0);
//        } else {
//            finalOrder.setDOrder(Shopcart.getDOrderItems(), Shopcart.getDonutTotal());
//        }
//
//        // If there are no coffee items in the cart, set an empty list and -1.0 for the total
//        if (Shopcart.getCOrderItems() == null) {
//            finalOrder.setCOrder(new ArrayList<>(), -1.0);
//        } else {
//            finalOrder.setCOrder(Shopcart.getCOrderItems(), Shopcart.getCoffeeTotal());
//        }

//        MainController.addOrder(finalOrder);
    }



}
