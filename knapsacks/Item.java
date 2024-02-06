package knapsacks;

public class Item implements Comparable<Item> {
    private String name;
    private int weight;
    private double value;

    // Constructor specifying the name, weight, and value
    public Item(String name, int weight, double value) {
        this.name = name;
        this.weight = weight;
        this.value = value;
    }

    // constructor with no specified name
    public Item(int weight,double value){
        this("",weight,value);
    }

    //default constructor
    public Item() {
        this("", 0,0.0);
    }


    /**
     * Generates a String representation of this Item.
     * @return is the String representing this Item.
     */
    public String toString() {
        return this.name + ": " + this.weight + " | " + this.value;
    }

    /**
     * Getter for name variable.
     * @return is the name of the Item
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for the weight variable.
     * @return is the weight of the Item.
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Getter for the complete value of the item.
     * @return is the full value of the Item
     */
    public double getTotalValue() {
        return value;
    }

    /**
     * Calculated field returning the value per unit of the item
     * @return is the value per unit of the Item
     */
    public double getValuePerUnit() {
        return value / weight;
    }

    /**
     * Comparison for the Comparable interface.
     * @param item the object to be compared.
     * @return is 0 if the Items are equal, and the difference in value per unit otherwise.
     */
    public int compareTo(Item item) {
        return (int)(getValuePerUnit() - item.getValuePerUnit());
    }

}
