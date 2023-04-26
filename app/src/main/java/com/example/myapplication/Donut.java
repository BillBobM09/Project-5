package com.example.myapplication;
import java.util.ArrayList;
/**This class contains all the attributes and functions that can be performed with the HoleDonut
 @author Juan Caizaguano William Mayhood
 */
//This is used as the HoleDonut since there is no reason for Donut base class since the only difference is the price
public class Donut extends MenuItem {

    private int NumOfDonuts;
    private String FlavorDonut;
    private int TotalDonuts;


    /**
     creates a string array list of ordered donut holes

    public Donut() {
        this.HDonutsOrdered = new ArrayList<String>();
    }
     */
    /**
     adds NumOfDonuts amount of donut holes to the CDonutsOrdered arrayList.

    public void setHDonutsOrdered() {
        this.HDonutsOrdered.add(FlavorDonut + "(" + NumOfDonuts+ ")");
    }
     */

    /**
     sets the number of donut holes and updates the total number of donuts
     with the parameter quantity
     @param Quantity
     */
    public void setNumOfDonuts(int Quantity) {
        this.NumOfDonuts = Quantity;
        this.TotalDonuts += Quantity;
    }
    /**
     returns the arraylist of donut holes ordered
     @return HDonutsOrdered

    public ArrayList<String> getHDonutsOrdered() {
        return this.HDonutsOrdered;
    }
     */

    /**
     returns the total number of donuts
     @return TotalDonuts
     */
    public int getTotalOfDonuts(){
        return this.TotalDonuts;
    }

    /**
     sets the flavor of the donut hole based on the string name of the flavor
     @param Flav
     */
    public void setFlavorDonut(String Flav) {
        this.FlavorDonut = Flav;
    }

    /**
     returns the flavor of the donut hole
     @return FlavorDonut
     */
    public String getFlavorDonut(){
        return this.FlavorDonut;
    }
    /**
     returns  the number of donut holes
     @return NumOfDonuts
     */
    public int getNumOfDonuts(){
        return this.NumOfDonuts;
    }
    /**
     returns the price of a donut hole
     @return Price
     */
    public double itemPrice() {
        double Price = 0.39;
//                * getTotalOfDonuts();
        return Price;
    }

}

