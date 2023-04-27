package com.example.myapplication;

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
import java.util.List;
import java.util.StringTokenizer;

public class shoppingCartPage extends AppCompatActivity {

    private Button RemoveSelected;

    private Button PlaceOrder;

    private TextView Sub_TotalTF;

    private TextView Sales_TaxTF;

    private TextView TotalTF;

    private ListView BasketItems;
    private ArrayList<String> CustomerCart = new ArrayList<>();
    private ArrayList<String> addcartlist = new ArrayList<>();
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
        MainController MC = MainController.getMainController();
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
        addcartlist.addAll(Shopcart.getCOrderItems());
        addcartlist.addAll(Shopcart.getDOrderItems());

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
                        addcartlist.remove(item);



                        if (item.contains("[")) {
//                            addcartlist.remove(item);
                            double itemPrice = getCoffeePrice(item, Covfefe);
                            Sub_Total -= itemPrice;
                            Sale_Tax = 0.0625 * Sub_Total;
                            Total = Sub_Total + Sale_Tax;

                            Sub_TotalTF.setText(String.format("$%.2f", Sub_Total));
                            Sales_TaxTF.setText(String.format("$%.2f", Sale_Tax));
                            TotalTF.setText(String.format("$%.2f", Total));
                        }else{
                            double DonutPrice=getDonutPrice(item);
                            Sub_Total-=DonutPrice;
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


        PlaceOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Order finalOrder = new Order();
//                ListAdapter adapter = BasketItems.getAdapter();
//                    Toast.makeText(getApplicationContext(), "No item selected", Toast.LENGTH_SHORT).show();
//                ArrayList<String> orderItems = new ArrayList<>();
//                double totalPrice = 0.0;
//                orderItems.addAll(Shopcart.getCOrderItems());
//                orderItems.addAll(Shopcart.getDOrderItems());

//                for (int i = 0; i < adapter.getCount(); i++) {
//                    String item = adapter.getItem(i).toString();
//                    orderItems.add(item);
//                }

//                String totalPriceString = TotalTF.getText().toString().replace("$", "");
//                totalPrice = Double.parseDouble(totalPriceString);

                if (addcartlist.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Cart is empty", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Order Placed", Toast.LENGTH_SHORT).show();
                    finalOrder.setFinalOrder(addcartlist, Total);
                    MC.addOrder(finalOrder);
//                    addcartlist.clear();
                    addcartlist = new ArrayList<>();
                    adapter.clear();
                    adapter.add("Order Placed");
                    Sub_TotalTF.setText(String.format("$%.2f", 0.00));
                    Sales_TaxTF.setText(String.format("$%.2f", 0.00));
                    TotalTF.setText(String.format("$%.2f", 0.00));
                }
            }
        });

    }

private double getDonutPrice(String DonutItem) {

    StringTokenizer tokenizer = new StringTokenizer(DonutItem, "()");
    String name = tokenizer.nextToken();
    int quantity = Integer.parseInt(tokenizer.nextToken());
    String addInsString = tokenizer.nextToken();
    double price = 0;
    double CdonutPrice = 1.59;
    double YdonutPrice = 1.79;
    double HdonutPrice = 0.39;
    if (name.compareTo("Vanilla Frosted") == 0 || name.compareTo("Strawberry Frosted") == 0 || name.compareTo("Chocolate Frosted") == 0 || name.compareTo("Glazed") == 0
            || name.compareTo("Sugar") == 0 || name.compareTo("Jelly") == 0) {

        price = CdonutPrice * quantity;
        return price;
    } else if (name.compareTo("Boston Cream") == 0 || name.compareTo("Vanilla Sprinkled") == 0 || name.compareTo("Bavarian Cream") == 0) {

        price = YdonutPrice * quantity;
        return price;

    } else{

        price = HdonutPrice * quantity;
        return price;

    }
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