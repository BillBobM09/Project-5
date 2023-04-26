package com.example.myapplication;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class storeOrdersPage extends AppCompatActivity {
    private Button CancelOrder;
    private ArrayList<String> CustomerCart = new ArrayList<>();
    private Integer[] Orders = {1,2,3,4,5,6,7,8,9,10,11,12};
    private TextView FinalTotalTF;
    private ListView OrderItems;
    private Spinner SP_OrderNum;
    private int orderIndex = 0;
    private int numOrders = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.store_order_page);
        CancelOrder = findViewById(R.id.CancelOrder);
        FinalTotalTF = findViewById(R.id.FinalTotalTF);
        ListView OrderItems = findViewById(R.id.OrderItems);
        MainController MC = MainController.getMainController();

        SP_OrderNum = findViewById(R.id.SP_OrderNum);
////        ArrayAdapter<Integer> quantityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Orders);

        ArrayAdapter<Integer> quantityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        quantityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        numOrders = MC.getOrderList().size();
        for (int i = 1; i <= numOrders; i++) {
            quantityAdapter.add(i);
        }
        if (numOrders == 0) {
            orderIndex= -1;
//            quantityAdapter.add(1);
        }
        SP_OrderNum.setAdapter(quantityAdapter);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, CustomerCart);
        OrderItems.setAdapter(adapter);
        if (numOrders > 0) {
            if (MC.getOrderList(0) == null) {
                adapter.add("Order Cancelled");
            } else {
                adapter.addAll(MC.getOrderList(0).getFinalOrder());
                FinalTotalTF.setText(String.format("$%.2f", MC.getOrderList(0).getFinalTotal()));
            }
        }

        SP_OrderNum.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                orderIndex = position;
                updateListView(MC, adapter);
//                orderIndex = position;
//                if (MC.getOrderList(position - 1) == null) {
//                    adapter.clear();
//                    adapter.add("Order Cancelled");
//                    Toast.makeText(getApplicationContext(), "lmao " + MC.getOrderList(position - 1).getFinalOrder(), Toast.LENGTH_SHORT).show();
//                } else {
//                    adapter.clear();
//                    adapter.addAll(MC.getOrderList(position - 1).getFinalOrder());
////                    Toast.makeText(getApplicationContext(), "lmao " + MC.getOrderList(position - 1).getFinalOrder(), Toast.LENGTH_SHORT).show();
//                }
//                orderIndex = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        CancelOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numOrders == 0) {
                    Toast.makeText(getApplicationContext(), "No orders to cancel", Toast.LENGTH_SHORT).show();
                    FinalTotalTF.setText(String.format("$%.2f", 0.00));
                } else {
                    MC.removeOrder(orderIndex);
                    adapter.clear();
                    adapter.addAll("Order Cancelled");
                    FinalTotalTF.setText(String.format("$%.2f", 0.00));
//                    Toast.makeText(getApplicationContext(), "lmao " + MC.getOrderList(orderIndex).getFinalOrder(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void updateListView(MainController MC, ArrayAdapter adapter) {
//        Integer position = SP_OrderNum.getSelectedItemPosition();
        if (orderIndex == -1){
            adapter.clear();
            adapter.add("No Orders");
        } else {
            if (MC.getOrderList(orderIndex ) == null) {
                adapter.clear();
                adapter.add("Order Cancelled");
                FinalTotalTF.setText(String.format("$%.2f", 0.00));
//                    Toast.makeText(getApplicationContext(), "lmao " + MC.getOrderList(orderIndex).getFinalOrder(), Toast.LENGTH_SHORT).show();
            } else {
                adapter.clear();
                adapter.addAll(MC.getOrderList(orderIndex ).getFinalOrder());
                FinalTotalTF.setText(String.format("$%.2f", MC.getOrderList(orderIndex).getFinalTotal()));
//                    Toast.makeText(getApplicationContext(), "lmao " + MC.getOrderList(orderIndex).getFinalOrder(), Toast.LENGTH_SHORT).show();
            }
        }

    }

}