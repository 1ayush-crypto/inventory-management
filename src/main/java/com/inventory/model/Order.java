package com.inventory.model;

import java.sql.Timestamp;

/**
 * Represents an order placed for a product.
 * Every order is linked to a product using productId.
 */
public class Order {

    private int id;
    private int productId;
    private int quantity;
    private String status;       // PLACED, CANCELLED
    private Timestamp orderDate;

    public Order() {
    }

    // Used when a new order is created by the customer
    public Order(int productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
        this.status = "PLACED";
    }

    // Used when reading an order back from the database
    public Order(int id, int productId, int quantity, String status, Timestamp orderDate) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.status = status;
        this.orderDate = orderDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }
}
