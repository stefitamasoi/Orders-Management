package org.example.model;

public class Product {
    private int id;
    private String name;
    private int quantity;
    private int price;

    public Product()
    {

    }
    public Product(String name, int quantity, int price) {
        super();
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public Product(int id, String name, int quantity, int price) {

        super();
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;

    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String a) {
        this.name = a;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String toString(){
        return id + ", " + "'" + this.name + "'" + ", " + this.quantity +"," + this.price;
    }


}