package com.example.rucoffeeshop;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.StringTokenizer;

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
    private int listIndex = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopcart_page);
        Order Shopcart = MainController.getShopCart();
        Coffee Covfefe = MainController.getCovfefe();
        Order finalOrder = new Order();
        double coffeeTotal = Shopcart.getCoffeeTotal();
        double donutTotal = Shopcart.getDonutTotal();
        Sub_Total = coffeeTotal + donutTotal;
        Sale_Tax = 0.0625 * Sub_Total;
        Total = Sub_Total + Sale_Tax;

        Sub_TotalTF = findViewById(R.id.Sub_TotalTF);
        Sales_TaxTF = findViewById(R.id.Sales_TaxTF);
        TotalTF = findViewById(R.id.TotalTF);
        RemoveSelected = findViewById(R.id.RemoveSelected);
        PlaceOrder = findViewById(R.id.PlaceOrder);

        Sub_TotalTF.setText(String.format("$%.2f", Sub_Total));
        Sales_TaxTF.setText(String.format("$%.2f", Sale_Tax));
        TotalTF.setText(String.format("$%.2f", Total));

        ListView BasketItems = findViewById(R.id.BasketItems);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, CustomerCart);
        BasketItems.setAdapter(adapter);
        adapter.addAll(Shopcart.getDOrderItems());
        adapter.addAll(Shopcart.getCOrderItems());

        // set OnClickListener for RemoveSelected button

        BasketItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                listIndex = position;
                Toast.makeText(getApplicationContext(), "Selected " + position, Toast.LENGTH_SHORT).show();
            }
        });

        /**
         When remove selected is clicked this function removes the chosen item from the user's cart.  The function
         then updates the total price of the cart accordingly
         */
        RemoveSelected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listIndex != -1) {
                    ListAdapter adapter = BasketItems.getAdapter();
                    if (adapter instanceof ArrayAdapter) {
                        ArrayAdapter arrayAdapter = (ArrayAdapter) adapter;
                        String item = (String) arrayAdapter.getItem(listIndex);
                        arrayAdapter.remove(item);
                        arrayAdapter.notifyDataSetChanged();

                        if (item.contains("[")) {
                            double itemPrice = getCoffeePrice(item, Covfefe);
                            Sub_Total -= itemPrice;
                            Sale_Tax = 0.0625 * Sub_Total;
                            Total = Sub_Total + Sale_Tax;

                            Sub_TotalTF.setText(String.format("$%.2f", Sub_Total));
                            Sales_TaxTF.setText(String.format("$%.2f", Sale_Tax));
                            TotalTF.setText(String.format("$%.2f", Total));
                        }

                        listIndex = -1;
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "No item selected", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }



    private double getCoffeePrice(String coffeeItem, Coffee Covfefe) {
        // Split the coffee item string into different parts using the delimiters
        StringTokenizer tokenizer = new StringTokenizer(coffeeItem, "()");

        // Extract the size, quantity, and add-ins array from the string
        String size = tokenizer.nextToken();
        int quantity = Integer.parseInt(tokenizer.nextToken());
        String addInsString = tokenizer.nextToken();

        double basePrice = Covfefe.itemPrice2(size);
        int numAddIns = 0;

        if (!addInsString.isEmpty()) {
            if (addInsString.contains("Sweet Cream")) {
                numAddIns++;
            }
            if (addInsString.contains("Mocha")) {
                numAddIns++;
            }
            if (addInsString.contains("French Vanilla")) {
                numAddIns++;
            }
            if (addInsString.contains("Caramel")) {
                numAddIns++;
            }
            if (addInsString.contains("Irish Cream")) {
                numAddIns++;
            }
        }
        double addInsPrice = numAddIns * 0.30;
        double totalPrice = (basePrice + addInsPrice) * quantity;

        return totalPrice;
    }



}
