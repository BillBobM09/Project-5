package com.example.myapplication;

import java.util.ArrayList;

public class MainController {
    private static Order Shopcart;
    private static Coffee Covfefe;
    private static MainController MC;

    //    public Order[] storeOrders;
    public ArrayList<Order> storeOrders;

    public MainController() {
        storeOrders = new ArrayList<Order>();
    }

    private Donut HDonut;

   // public Order[] storeOrders;

/*
    public Order[] getOrderList(){
        return this.storeOrders;
    }

    public Order getOrderList(int i){
        return this.storeOrders[i];
    }

 */

    public ArrayList<Order> getOrderList(){
        return this.storeOrders;
    }
    public Order getOrderList(int i){
//        return this.storeOrders[i];
        return this.storeOrders.get(i);
    }


    public Donut getHDonut() {
        if (HDonut == null) {
            HDonut = new Donut();
        }
        return HDonut;
    }
/*
    public void addOrder(Order order){
        for(int i=0; i< storeOrders.length;i++){
            if(storeOrders[i]==null){
                storeOrders[i]=order;
                break;
            }else{
                continue;
            }
        }
    }

 */
    public void addOrder(Order order){
        storeOrders.add(order);
//        for(int i=0; i< storeOrders.length;i++){
//            if(storeOrders[i]==null){
//                storeOrders[i]=order;
//                break;
//            }else{
//                continue;
//            }
//        }
    }
    public static Coffee getCovfefe() {
        if (Covfefe == null) {
            Covfefe = new Coffee();
        }
        return Covfefe;
    }

    public static Order getShopCart() {
        if (Shopcart == null) {
            Shopcart = new Order();
        }
        return Shopcart;
    }
    public static MainController getMainController() {
        if (MC == null) {
            MC = new MainController();
        }
        return MC;
    }
    public void removeOrder(int i){
        storeOrders.set(i, null);

    }
}
