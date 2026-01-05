// src/main/java/com/inventory/entity/Sell.java
package com.inventory.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Sell {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productName; // Name of the product sold
    private int quantity;
    private double unitPrice; // Price at which this specific unit was sold
    private double totalRevenue;
    private LocalDate saleDate;
    private String customerName; // Simple string for customer name, mirroring previous Sell.java

    @ManyToOne // Many sells can be for one product
    @JoinColumn(name = "product_id") // This creates a foreign key column named product_id in the sells table
    private Product product; // Link to the Product entity

    // Default constructor (required by JPA)
    public Sell() {
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public LocalDate getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(LocalDate saleDate) {
        this.saleDate = saleDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Sell{" +
               "id=" + id +
               ", productName='" + productName + '\'' +
               ", quantity=" + quantity +
               ", unitPrice=" + unitPrice +
               ", totalRevenue=" + totalRevenue +
               ", saleDate=" + saleDate +
               ", customerName='" + customerName + '\'' +
               ", product=" + (product != null ? product.getName() : "null") + // Avoid circular reference
               '}';
    }
}