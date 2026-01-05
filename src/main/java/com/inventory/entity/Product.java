package com.inventory.entity;

import jakarta.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String category;
    private int quantity;
    private double unitPrice;
    private String imageUrl; 
    private int stock;
    // This field is already present and correct

    // Default constructor (important for JPA)
    public Product() {
    }

    public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	 // You might want to add a constructor for easier object creation,
    // but it's not strictly necessary for this specific feature.
     public Product(String name, String category, int quantity, double unitPrice, String imageUrl) {
         this.name = name;
         this.category = category;
         this.quantity = quantity;
         this.unitPrice = unitPrice;
         this.imageUrl = imageUrl;
     }

    // Getters and Setters (already present and correct)
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
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

    // Optional: Add a toString() method for better logging/debugging
    @Override
    public String toString() {
        return "Product{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", category='" + category + '\'' +
               ", quantity=" + quantity +
               ", unitPrice=" + unitPrice +
               ", imageUrl='" + imageUrl + '\'' +
               '}';
    }
}