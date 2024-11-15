package org.example.model;

public class Orders {
    private int id;
    private int clientId;
    private int productId;
    private int quantity;
    private int price;

    public Orders()
    {}

    public Orders(int clientId, int productId, int quantity, int price) {
        super();
        this.clientId = clientId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;

    }

    public Orders(int id, int clientId, int productId, int quantity, int price) {
        super();
        this.id = id;
        this.clientId = clientId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }


    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClientId() {
        return this.clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getProductId() {
        return this.productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
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
        return id + ", " + this.clientId + ", " + this.productId + ", " + this.quantity + ", " + this.price;
    }

}