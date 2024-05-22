/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package waterstationpos;

/**
 *
 * @author allen
 */
public class Product {
    private String name;
    private double price;
    private int quantity;
    // Constructor
    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    // Getter for price
    public double getPrice() {
        return price;
    }
    
    // Setter for price (if needed)
    public void setPrice(double price) {
        this.price = price;
    }
    
    // Getter and setter for name (if needed)
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }
}

