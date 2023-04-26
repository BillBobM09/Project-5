package com.example.rucoffeeshop;

import java.util.ArrayList;

/**This class contains the arrayLists of ordered donuts and coffees, including the functions associated with them
 @author Juan Caizaguano William Mayhood
 */
public class Order {
    private int OrderNumber;
    private ArrayList<String> DShopCart  = new ArrayList<>();
    private ArrayList<String> CShopCart  = new ArrayList<>();
    private ArrayList<String> FinalCart  = new ArrayList<>();
    private Double DonutTotal=0.0;
    private Double CoffeeTotal=0.0;
    private Double Total;
    private Double FinalTotal;

    private Double totalWithTax;



    public double getDonutTotal(){
        return this.DonutTotal;

    }
    public double getCoffeeTotal(){
        return this.CoffeeTotal;

    }
    public double getTotal(){

        Total=DonutTotal+CoffeeTotal;
        return this.Total;

    }

    public double getFinalTotal(){
        return this.FinalTotal;
    }


    public void setTotalWithTax(Double totalWithTax) {
        this.totalWithTax = totalWithTax;
    }

    public double getTotalWithTax() {
        return this.totalWithTax;
    }

    /**
     This function updates the donut order arrayList with all donuts in the user's cart and sets the total
     price of the donuts to the DonutTotal variable
     * @param Cart
     * @param Dtotal
     */
    public void setDOrder(ArrayList<String> Cart, Double Dtotal) {
        this.DShopCart = Cart;
        this.DonutTotal = Dtotal;
//        System.out.println(DShopCart);
//        System.out.println(Dtotal);
//        System.out.println(DonutTotal);
    }
    /**
     Returns the arrayList of donut orders
     @return DShopCart
     */
    public ArrayList<String> getDOrderItems() {
        return this.DShopCart;
    }//returns array of ordered donuts
    public void removeCOrderItems(String item) {
        this.CShopCart.remove(item);
    }

    /**
     This function updates the coffee order arrayList with all coffees in the user's cart and sets the total
     price of the coffees to the Ctotal  variable
     * @param Cart
     * @param Ctotal
     */
    public void setCOrder(ArrayList<String> Cart, Double Ctotal) {
        this.CShopCart = Cart;
        this.CoffeeTotal = Ctotal;
    }

    /**
     Returns the arrayList of coffee orders
     @return CshopCart
     */

    public ArrayList<String> getCOrderItems() {
        return this.CShopCart;
    }//returns array of ordered coffee

    public void setFinalOrder(ArrayList<String> FinalCart, Double Ftotal) {
        this.FinalCart = FinalCart;
        this.FinalTotal = Ftotal;
    }
    public ArrayList<String> getFinalOrder() {
        return this.FinalCart;
    }
}
