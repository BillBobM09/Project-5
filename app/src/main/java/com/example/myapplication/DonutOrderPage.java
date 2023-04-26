package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DonutOrderPage extends AppCompatActivity implements recyclerViewInterface {
    Spinner spinView;
    private Double Total;
    private Double SubTotal;

    int SelectedQuantity;
    int quantity = 0;
     private TextView DSub_Total;
     private TextView displayDonut;

    private Button AddOrder;

    private ArrayList<String> HDonutsOrdered;


    ArrayList<DonutModel> donutModels = new ArrayList<>();
    int[] donutImages = {R.drawable.vanilla_donut, R.drawable.strawberry_frosted,R.drawable.chocolate_frosted,R.drawable.chocolate_frosted,
    R.drawable.glazed_donut, R.drawable.sugar_donut,R.drawable.jelly_donut,
    R.drawable.vanilla_sprinkled,R.drawable.boston_cream,R.drawable.bavarian_cream,R.drawable.plain_donut,
    R.drawable.grain_donut, R.drawable.raisin_donut};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donut_page);
        RecyclerView recyclerView = findViewById(R.id.donutRecycler);
        Order Shopcart = MainController.getShopCart();
        setUpDonutArray();
        donut_RecyclerViewAdaptor adaptor = new donut_RecyclerViewAdaptor(this, donutModels, this);
        recyclerView.setAdapter(adaptor);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        Total = 0.0;
        SubTotal = 0.0;
        SelectedQuantity = 0;
        DSub_Total = findViewById(R.id.donut_subtotal);
        displayDonut = findViewById(R.id.selectedDonut);
        AddOrder = findViewById(R.id.AddOrderButton);

        AddOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddOrderClick(Shopcart);
            }
        });

    }


    private void setUpDonutArray(){
        Integer[] Quant = {1,2,3,4,5,6,7,8,9,10,11,12};
        spinView = findViewById(R.id.quantSpinner);
        ArrayAdapter<Integer> quantityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Quant);
        quantityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    String[] donutNames = getResources().getStringArray(R.array.donut_selection);
    String[] prices = getResources().getStringArray(R.array.donut_price);
    String[] spinner = getResources().getStringArray(R.array.numbers);
for(int i =0; i<donutNames.length;i++){
    donutModels.add(new DonutModel(prices[i], donutNames[i],donutImages[i], spinView));


}


    }

    @Override
    public void onClick(int position) {
        DonutModel donutModel = donutModels.get(position);
         SelectedQuantity = (int) donutModel.getSpinnerView().getSelectedItem();//number of a specific donut
        quantity = quantity + SelectedQuantity;//total number of donuts
        String Donutname = donutModel.getName();
        String Donutprice = donutModel.getPrice();
        updateTotalPrice(Donutname, Donutprice);
        displayCurrentDonut(Donutname);
    }

    public void displayCurrentDonut(String Donutname) {
        displayDonut.setText(Donutname);
    }

    private void updateTotalPrice(String Donutname, String Donutprice) {
        double DonutPriceConversion = Double.parseDouble(Donutprice);
        SubTotal= SubTotal + (DonutPriceConversion * quantity);


        DSub_Total.setText("$" + String.format("%.2f", SubTotal));
        this.SubTotal = Total;//////////////////////////////////////////////
    }
    private void onAddOrderClick(Order Shopcart) {
      //  Integer num = (Integer) SP_Quantity.getSelectedItem();
        //String size = (String) SP_Size.getSelectedItem();
        this.Total = Total + SubTotal;
        this.HDonutsOrdered.add(displayDonut.getText() + "(" + SelectedQuantity + ")");
        Shopcart.setDOrder(HDonutsOrdered, Total);

        Toast.makeText(this, "Order added to cart", Toast.LENGTH_SHORT).show();
    }
}
