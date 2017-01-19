package Modele;

public class ItemPanierModele {

    private String Partnumber;
    private String Modeldescription;
    private double Unitcost;
    private int quantity;
    private double totalCost;

    public String getPartNumber() {
        return Partnumber;
    }

    public void setPartNumber(String number) {
        this.Partnumber = number;
    }

    public String getModelDescription() {
        return Modeldescription;
    }

    public void setModelDescription(String description) {
        this.Modeldescription = description;
    }

    public double getUnitCost() {
        return Unitcost;
    }

    public void setUnitCost(double cost) {
        this.Unitcost = cost;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

}
