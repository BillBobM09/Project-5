package com.example.rucoffeeshop;

public class MainController {
    private static Order Shopcart;
    private static Coffee Covfefe;

    public Order[] storeOrders;


    public Order[] getOrderList(){
        return this.storeOrders;
    }

    public Order getOrderList(int i){
        return this.storeOrders[i];
    }

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
}
