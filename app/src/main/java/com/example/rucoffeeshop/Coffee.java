package com.example.rucoffeeshop;

import java.util.ArrayList;

public class Coffee extends MenuItem {
    private String Size;
//    private String[] AddIns = {"Sweat Cream", "Mocha", "French Vanilla", "Caramel", "Irish Cream"};


    private ArrayList<String> AddIns  = new ArrayList<>();
    /**
     adds a specific addIn to the AddIns array based on the string parameter
     @param AddName
     */
    public void addSelectedAddIn(String AddName) {
        this.AddIns.add(AddName);
    }
    /**
     returns selected add in
     @return AddIns
     */
    public ArrayList<String> getSelectedAddIn() {
        return this.AddIns;
    }
    /**
     removes an AddIn from the coffee based on the string parameter
     @param Cart
     */
    public void removeSelectedAddIn(String Cart) {
        this.AddIns.remove(Cart);
    }
    /**
     sets the size of coffee based on string parameter
     @param CofSize
     */
    public void setCoffeeSize(String CofSize) {
        this.Size = CofSize;
    }
    /**
     returns the size of the coffee
     @return Size
     */
    public String getCoffeeSize() {
        return this.Size;
    }
    /**
     returns the price of the coffee based on the String Size
     @return Price
     */
    public double itemPrice() {
        double Price = 0.0;
        if (Size.equals("Short")) {
            Price = 1.89 + (this.AddIns.size() * 0.30);
            return Price;
        } else if (Size.equals("Tall")) {
            Price = 2.29+ (this.AddIns.size() * 0.30);
            return Price;
        } else if (Size.equals("Grande")) {
            Price = 2.69 + (this.AddIns.size() * 0.30);
            return Price;
        } else if (Size.equals("Venti")) {
            Price = 3.09 + (this.AddIns.size() * 0.30);
            return Price ;
        }
        return Price;
    }
    /**
     returns the price of the coffee based on a specific size given through a parameter
     @return Price
     @param order
     */
    public double itemPrice2(String order) {
        double Price = 0.0;
        if (order.compareTo("Short")==0) {
            Price = 1.89;
            return Price;
        } else if (order.compareTo("Tall")==0) {
            Price = 2.29;
            return Price;
        } else if (order.compareTo("Grande")==0) {
            Price = 2.69;
            return Price;
        } else if (order.compareTo("Venti")==0) {
            Price = 3.09;
            return Price ;
        }
        return Price;
    }

}
