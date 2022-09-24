package Lab4;

public class Item {
    private String veggies;
    private double price;
    private double kilograms;
    private double discount;
    public Item(String veggies, double price, double kilograms, double discount){
        this.veggies = veggies;
        this.price = price;
        this.kilograms = kilograms;
        this.discount = discount;
    }
    public String getVeggies() { return veggies; }

    public double getPrice() { return price; }

    public double getKilograms() { return kilograms; }

    public double getDiscount() { return discount; }
}

