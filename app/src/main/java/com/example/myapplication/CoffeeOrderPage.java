package com.example.myapplication;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CoffeeOrderPage extends AppCompatActivity {
    private Spinner SP_Size;
    private Spinner SP_Quantity;
    private CheckBox SweetCreamBox;
    private CheckBox MochaBox;
    private CheckBox FrenchBox;
    private CheckBox CaramelBox;
    private CheckBox IrishBox;
    private TextView CSub_Total;
    private Button AddOrder;
    private Double Total;
    private Double SubTotal;
    private ArrayList<String> CoffeOrdered = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coffee_page);
        Coffee Covfefe = MainController.getCovfefe();
        Order Shopcart = MainController.getShopCart();
        Total = 0.0;
        SubTotal = 0.0;
        Covfefe.removeSelectedAddIn("Sweet Cream");
        Covfefe.removeSelectedAddIn("Mocha");
        Covfefe.removeSelectedAddIn("French Vanilla");
        Covfefe.removeSelectedAddIn("Caramel");
        Covfefe.removeSelectedAddIn("Irish Cream");

        SP_Size = findViewById(R.id.SP_Size);
        String[] sizeArray = getResources().getStringArray(R.array.CofSize);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, sizeArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SP_Size.setAdapter(adapter);
        SP_Quantity = findViewById(R.id.SP_Quantity);
        Integer[] Quant = {1,2,3,4,5,6,7,8,9,10,11,12};
        ArrayAdapter<Integer> quantityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Quant);
        quantityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SP_Quantity.setAdapter(quantityAdapter);


        SP_Quantity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateTotalPrice(Covfefe);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        SP_Size.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateTotalPrice(Covfefe);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        SweetCreamBox = findViewById(R.id.SweetCreamBox);
        MochaBox = findViewById(R.id.MochaBox);
        FrenchBox = findViewById(R.id.FrenchBox);
        CaramelBox = findViewById(R.id.CaramelBox);
        IrishBox = findViewById(R.id.IrishBox);
        CSub_Total = findViewById(R.id.CSub_Total);
        AddOrder = findViewById(R.id.AddOrder);

        SweetCreamBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Covfefe.addSelectedAddIn("Sweet Cream");
                } else {
                    Covfefe.removeSelectedAddIn("Sweet Cream");
                }
                updateTotalPrice(Covfefe);
            }
        });

        MochaBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Covfefe.addSelectedAddIn("Mocha");
                } else {
                    Covfefe.removeSelectedAddIn("Mocha");
                }
                updateTotalPrice(Covfefe);
            }
        });

        FrenchBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Covfefe.addSelectedAddIn("French Vanilla");
                } else {
                    Covfefe.removeSelectedAddIn("French Vanilla");
                }
                updateTotalPrice(Covfefe);
            }
        });

        CaramelBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Covfefe.addSelectedAddIn("Caramel");
                } else {
                    Covfefe.removeSelectedAddIn("Caramel");
                }
                updateTotalPrice(Covfefe);
            }
        });

        IrishBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Covfefe.addSelectedAddIn("Irish Cream");
                } else {
                    Covfefe.removeSelectedAddIn("Irish Cream");
                }
                updateTotalPrice(Covfefe);
            }
        });

        AddOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddOrderClick(Covfefe, Shopcart);
            }
        });

    }

    private void updateTotalPrice(Coffee Covfefe) {
        String size = (String) SP_Size.getSelectedItem();
        Integer quantity = (Integer) SP_Quantity.getSelectedItem();

        double totalPrice = Covfefe.itemPrice2(size) * quantity;

        if (SweetCreamBox.isChecked()) {
            totalPrice += 0.30 * quantity;
        }
        if (MochaBox.isChecked()) {
            totalPrice += 0.30 * quantity;
        }
        if (FrenchBox.isChecked()) {
            totalPrice += 0.30 * quantity;
        }
        if (CaramelBox.isChecked()) {
            totalPrice += 0.30 * quantity;
        }
        if (IrishBox.isChecked()) {
            totalPrice += 0.30 * quantity;
        }

        CSub_Total.setText("$" + String.format("%.2f", totalPrice));
        this.SubTotal = totalPrice;
    }

    private void onAddOrderClick(Coffee Covfefe, Order Shopcart) {
        Integer num = (Integer) SP_Quantity.getSelectedItem();
        String size = (String) SP_Size.getSelectedItem();
        this.Total = Total + SubTotal;
        this.CoffeOrdered.add(size + "(" + num + ") " + Covfefe.getSelectedAddIn());
        Shopcart.setCOrder(CoffeOrdered, Total);

        Toast.makeText(this, "Order Placed", Toast.LENGTH_SHORT).show();
    }


//            this.CoffeOrdered.add(Covfefe.getCoffeeSize() + "(" + num + ") " + Covfefe.getSelectedAddIn());

}